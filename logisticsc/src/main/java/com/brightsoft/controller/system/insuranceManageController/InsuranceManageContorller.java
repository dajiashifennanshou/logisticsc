package com.brightsoft.controller.system.insuranceManageController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.InsuranceCompany;
import com.brightsoft.model.InsuranceRate;
import com.brightsoft.model.InsuranceTsType;
import com.brightsoft.model.InsuranceType;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.platform.InsuranceManageService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;

@Controller
@RequestMapping("/system/ins/")
public class InsuranceManageContorller {
	
	private static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	
	@Autowired
	private InsuranceManageService insuranceManageService;

	/**
	 * 跳转到保险公司页面
	 * @param model
	 * @return
	 */
	@RequestMapping("toInsCom")
	public String toListInsCom(ModelMap model){
		HashMap<String, Object> map = insuranceManageService.selectTyAndTsTy();
		model.addAttribute("map", map);
		return "/system/systemsetting/ins_com";
	}
	
	/**
	 * 跳转到险种页面
	 * @return
	 */
	@RequestMapping("toInsType")
	public String toListInsType(){
		return "/system/systemsetting/ins_type";
	}
	
	/**
	 * 跳转特殊类型管理页面
	 * @return
	 */
	@RequestMapping("toInsTsType")
	public String toListInsTsType(){
		return "/system/systemsetting/ins_ts_type";
	}
	
	/**
	 * 跳转到保险费率页面
	 * @param model
	 * @return
	 */
	@RequestMapping("toInsFee")
	public String toListInsFee(ModelMap model){
		List<InsuranceCompany> list = insuranceManageService.selectInsCompany();
		model.addAttribute("insCom", list);
		return "/system/systemsetting/ins_com_fee";
	}
	
	/**
	 * 查询保险公司信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("searchInsCom")
	@ResponseBody
	public Result searchInsCom(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		result = insuranceManageService.selectInsComByCondition(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 查询险种信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("searchInsType")
	@ResponseBody
	public Result searchInsType(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		result = insuranceManageService.selectInsTypeByCondition(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 查询特殊类型信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("searchInsTsType")
	@ResponseBody
	public Result searchInsTsType(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		result = insuranceManageService.selectInsTsTypeByCondition(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 查询保险费率信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("searchInsFee")
	@ResponseBody
	public Result searchInsFee(SearchParams searchParams,Page<?> page){
		Result result = new Result();
		result = insuranceManageService.selectInsRateByCondition(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 添加险种
	 * @param insuranceType
	 * @param session
	 * @return
	 */
	@RequestMapping("addInsType")
	@ResponseBody
	public Result addInsType(InsuranceType insuranceType,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute("user");
		if(user!=null){
			if(insuranceType.getId() != null){
				if(insuranceManageService.updateInsType(insuranceType)){
					result.setResult(true);
				}
			}else{
				if(insuranceManageService.insertInsType(insuranceType,user)){
					result.setResult(true);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 添加特殊类型
	 * @param insuranceTsType
	 * @param session
	 * @return
	 */
	@RequestMapping("addInsTsType")
	@ResponseBody
	public Result addInsTsType(InsuranceTsType insuranceTsType,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute("user");
		if(user!=null){
			if(insuranceTsType.getId() != null){
				if(insuranceManageService.updateInsTsType(insuranceTsType)){
					result.setResult(true);
				}
			}else{
				if(insuranceManageService.insertInsTsType(insuranceTsType,user)){
					result.setResult(true);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 添加保险公司
	 * @param insuranceCompany
	 * @param insTypes
	 * @param insTsTypes
	 * @param mulFile
	 * @param session
	 * @return
	 */
	@RequestMapping("addInsCom")
	@ResponseBody
	public Result addInsCom(InsuranceCompany insuranceCompany,@RequestParam(value="insTypes",required=false)String[] insTypes,
			@RequestParam(value="insTsTypes",required=false)String[] insTsTypes,@RequestParam(value="mulFile",required=false)MultipartFile mulFile,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute("user");
		if(user != null){
			if(insuranceCompany.getId() == null){
				if(mulFile == null || mulFile.isEmpty()){
					result.setMsg("请选择上传图片");
					return result;
				}
			}
			if(mulFile != null && !mulFile.isEmpty()){
				String fileName = mulFile.getOriginalFilename();
				String suffix = fileName.substring(fileName.lastIndexOf('.')+1);
				if(suffix.matches(bundle.getString("reg"))){
					String dirName ="";
	            	
	        		dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
	                        + bundle.getString("uploadBXLOGO"), fileName);
	    			
	                /* 根据图片名生成唯一文件路径 */
	                File fl = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
	                // 写入文件到实际路径
	                try {
	                	mulFile.transferTo(fl);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	                String filePath = dirName + fl.getName();
	                insuranceCompany.setInsComLogoUrl(filePath);
				}
			}
			if(insuranceCompany.getId() != null){
            	if(insuranceManageService.updateInsCom(insuranceCompany, insTypes, insTsTypes)){
            		result.setResult(true);
            	}
            }else{
            	if(insuranceManageService.insertInsCom(insuranceCompany, insTypes, insTsTypes, user)){
                	result.setResult(true);
                }
            }
		}
		
		return result;
	}
	
	/**
	 * 添加费率
	 * @param insuranceRate
	 * @param session
	 * @return
	 */
	@RequestMapping("addInsFee")
	@ResponseBody
	public Result addInsFee(InsuranceRate insuranceRate,HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute("user");
		if(user != null){
			if(insuranceRate.getId() != null){
				if(insuranceManageService.updateInsRate(insuranceRate)){
					result.setResult(true);
				}
			}else{
				if(insuranceManageService.insertInsRate(insuranceRate,user)){
					result.setResult(true);
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取险种和特殊类型
	 * @param companyId
	 * @param feeType
	 * @return
	 */
	@RequestMapping("getInsTsAndT")
	@ResponseBody
	public Result getInsTypeAndTsTypeByCompanyId(Long companyId,Integer feeType){
		Result result = new Result();
		HashMap<String, Object> map = insuranceManageService.selectTyAndTsTyByCompanyId(companyId,feeType);
		result.setData(map);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 删除保险公司
	 * @param comIds
	 * @return
	 */
	@RequestMapping("delCom")
	@ResponseBody
	public Result removeCom(@RequestParam("comIds[]")String[] comIds){
		Result result = new Result();
		if(insuranceManageService.deleteCom(comIds)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 删除险种
	 * @param typeIds
	 * @return
	 */
	@RequestMapping("delType")
	@ResponseBody
	public Result removeType(@RequestParam("typeIds[]")String[] typeIds){
		Result result = new Result();
		if(insuranceManageService.deleteType(typeIds)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 删除特殊类型
	 * @param tsTypeIds
	 * @return
	 */
	@RequestMapping("delTsType")
	@ResponseBody
	public Result removeTsType(@RequestParam("tsTypeIds[]")String[] tsTypeIds){
		Result result = new Result();
		if(insuranceManageService.deleteTsType(tsTypeIds)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 删除保险费率
	 * @param tsTypeIds
	 * @return
	 */
	@RequestMapping("delInsRate")
	@ResponseBody
	public Result removeInsRate(@RequestParam("rateIds[]")String[] rateIds){
		Result result = new Result();
		if(insuranceManageService.deleteInsRate(rateIds)){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 通过险种标签查询险种是否存在
	 * @param typeTag
	 * @return
	 */
	@RequestMapping("existType")
	@ResponseBody
	public Result typeIsExist(String typeTag){
		Result result = new Result();
		result.setData(insuranceManageService.selectTypeByTypeTag(typeTag));
		result.setResult(true);
		return result;
	}
	
	/**
	 * 通过特殊类型tag判断其是否存在
	 * @param tsTypeTag
	 * @return
	 */
	@RequestMapping("existTsType")
	@ResponseBody
	public Result tsTypeIsExist(String tsTypeTag){
		Result result = new Result();
		result.setData(insuranceManageService.selectTsTypeByTsTypeTag(tsTypeTag));
		result.setResult(true);
		return result;
	}
	
	/**
	 * 判断保险公司是否存在
	 * @param comTag
	 * @return
	 */
	@RequestMapping("existCom")
	@ResponseBody
	public Result comIsExist(String comTag){
		Result result = new Result();
		result.setData(insuranceManageService.selectComByComTag(comTag));
		result.setResult(true);
		return result;
	}
	
	/**
	 * 判断保险费率是否存在
	 * @param comTag
	 * @return
	 */
	@RequestMapping("existInsRate")
	@ResponseBody
	public Result insRateIsExist(String comTag,String typeTag,String tsTypeTag){
		Result result = new Result();
		if(StringUtils.isNotBlank(typeTag)){
			result.setData(insuranceManageService.selectRateByComAndTypeTag(comTag, typeTag));
			result.setResult(true);
		}else if(StringUtils.isNotBlank(tsTypeTag)){
			result.setData(insuranceManageService.selectRateByComAndTsTypeTag(comTag, tsTypeTag));
			result.setResult(true);
		}
		return result;
	}
}

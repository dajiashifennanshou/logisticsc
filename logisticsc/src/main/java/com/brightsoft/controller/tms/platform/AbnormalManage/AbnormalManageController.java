package com.brightsoft.controller.tms.platform.AbnormalManage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.common.enums.AbnormalNodeEnum;
import com.brightsoft.common.enums.AbnormalTypeEnum;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.Abnormal;
import com.brightsoft.model.AbnormalHandle;
import com.brightsoft.model.LadingOrder;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.User;
import com.brightsoft.model.WayBillOrder;
import com.brightsoft.service.tms.platform.AbnormalHandleService;
import com.brightsoft.service.tms.platform.AbnormalService;
import com.brightsoft.service.tms.platform.OutletsService;
import com.brightsoft.service.tms.platform.startsitemanager.LadingOrderService;
import com.brightsoft.service.tms.platform.startsitemanager.WayBillOrderService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;

@Controller
@RequestMapping("/tms/abnormal/")
public class AbnormalManageController {

	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private AbnormalService abnormalService;
	
	@Autowired
	private WayBillOrderService wayBillOrderService;
	
	@Autowired
	private AbnormalHandleService abnormalHandleService;
	
	@Autowired
	private LadingOrderService ladingOrderService;
	
	private static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	
	@RequestMapping("list")
	public ModelAndView toListAbnormal(HttpSession session){
		ModelAndView mav = new ModelAndView("/tms/platform/tmsabnormalmanage/listabnormalinfo");
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user!=null){
			List<OutletsInfo> outletsInfos = outletsService.selectByCompanyId(user.getCompanyId());
			HashMap<Integer,String> map = new HashMap<Integer,String>();
			map.put(AbnormalTypeEnum.DAMAGE_LIST.getValue(), AbnormalTypeEnum.DAMAGE_LIST.getName());
			map.put(AbnormalTypeEnum.FRIGHT_ABNORMAL.getValue(), AbnormalTypeEnum.FRIGHT_ABNORMAL.getName());
			map.put(AbnormalTypeEnum.REFUSE_PAY_FREIGHT.getValue(), AbnormalTypeEnum.REFUSE_PAY_FREIGHT.getName());
			map.put(AbnormalTypeEnum.REFUSE_PAY_CARGO.getValue(), AbnormalTypeEnum.REFUSE_PAY_CARGO.getName());
			map.put(AbnormalTypeEnum.UNMANNED_SIGN.getValue(), AbnormalTypeEnum.UNMANNED_SIGN.getName());
			map.put(AbnormalTypeEnum.SECTION_SIGN.getValue(), AbnormalTypeEnum.SECTION_SIGN.getName());
			map.put(AbnormalTypeEnum.OTHERS.getValue(), AbnormalTypeEnum.OTHERS.getName());
			mav.addObject("abnormalTypes", map);
			mav.addObject("outletsInfos",outletsInfos);
		}
		return mav;
	}
	
	@RequestMapping("search")
	@ResponseBody
	public Result searchAbnormal(Page<?> page,SearchParams searchParams,
			HttpSession session){
		Result result = new Result();
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user.getOutletsId() == null||user.getOutletsId().equals("")){
			List<OutletsInfo> outs =outletsService.selectOutlesNotBind(user.getCompanyId());
			String ids = "";
			for(int i =0;i<outs.size();i++){
				if(i == 0){
					ids += outs.get(i).getId();
				}else{
					ids += ","+outs.get(i).getId();
				}
			}
			if(outs == null || outs.size() == 0){
				searchParams.setOutletsIds("no");
			}else{
				searchParams.setOutletsIds(ids);
			}
		}else{
			searchParams.setOutletsIds(user.getOutletsId()+"");
		}
		result = abnormalService.selectByCondition(page, user, searchParams);
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("addabnormal")
	public ModelAndView toAddAbnormal(String wayBillNumber,HttpSession session){
		ModelAndView mav = new ModelAndView("/tms/platform/tmsabnormalmanage/addabnormalinfo");
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(user != null){
			List<OutletsInfo> outletsList = outletsService.selectByCompanyId(user.getCompanyId());
			
			mav.addObject("outletsList", outletsList);
			mav.addObject("abnormalTypes", getAbnormalType());
			mav.addObject("abnormalNodes", getAbnormalNode());
		}
		if(StringUtils.isNotBlank(wayBillNumber)){
			WayBillOrder wayBillOrder = wayBillOrderService.selectByWayBillNumber(wayBillNumber);
			if(wayBillOrder == null){
				WayBillOrder wayBillOrderTemp = new WayBillOrder();
				LadingOrder ladingOrder = ladingOrderService.selectByWayBillNumber(wayBillNumber);
				if(ladingOrder != null){
					wayBillOrderTemp.setWayBillNumber(wayBillNumber);
					wayBillOrderTemp.setConsignor(ladingOrder.getConsignor());
					wayBillOrderTemp.setConsignorMobile(ladingOrder.getConsignorMobile());
					wayBillOrderTemp.setConsignee(ladingOrder.getConsignee());
					wayBillOrderTemp.setConsigneeMobile(ladingOrder.getConsigneeMobile());
					wayBillOrderTemp.setStartOutletsName(ladingOrder.getStartOutletsName());
					wayBillOrderTemp.setTargetOutletsName(ladingOrder.getTargetOutletsName());
					wayBillOrderTemp.setInsuranceMoney(ladingOrder.getInsuranceMoney());
					wayBillOrderTemp.setWayBillOrderTime(ladingOrder.getLadingOrderTime());
					wayBillOrder = wayBillOrderTemp;
				}
			}
			mav.addObject("wayBillOrder", wayBillOrder);
		}
		return mav;
	}
	
	/**
	 * 获取 异常类型 
	 * @return
	 */
	private List<Map<String, Object>> getAbnormalType(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		AbnormalTypeEnum[] abnormalTypeEnums = AbnormalTypeEnum.values();
		for (AbnormalTypeEnum abnormalTypeEnum : abnormalTypeEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", abnormalTypeEnum.getValue());
			map.put("name", abnormalTypeEnum.getName());
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 获取 异常环节
	 * @return
	 */
	private List<Map<String, Object>> getAbnormalNode(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		AbnormalNodeEnum[] abnormalNodeEnums = AbnormalNodeEnum.values();
		for (AbnormalNodeEnum abnormalNodeEnum : abnormalNodeEnums) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", abnormalNodeEnum.getValue());
			map.put("name", abnormalNodeEnum.getName());
			result.add(map);
		}
		return result;
	}
	
	@RequestMapping("insert")
	@ResponseBody
	public Result addAbnormal(Abnormal abnormal,@RequestParam(value="fileName",required=false)MultipartFile file, HttpSession session){
		Result result = new Result();
		if(file!=null&&!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf('.')+1);
			if(suffix.matches(bundle.getString("reg"))){
				String dirName ="";
	        	
	    		dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
	                    + bundle.getString("uploadAbnormal"), fileName);
				
	            /* 根据图片名生成唯一文件路径 */
	    		String filePath = UploadFileUtil.getOriginalFileDirName(fileName, dirName);
	            File fl = new File(filePath);
	            // 写入文件到实际路径
	            try {
					file.transferTo(fl);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	            abnormal.setAbImgPath(filePath);
			}
		}
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		if(abnormalService.insertAbnormal(abnormal, user)){
			result.setResult(true);
		}
		return result;
	}
	
	@RequestMapping("gtwybll")
	@ResponseBody
	public Result getWayBillInfo(String wayBillNumber){
		Result result = new Result();
		if(StringUtils.isNotBlank(wayBillNumber)){
			WayBillOrder wayBillOrder = wayBillOrderService.selectByWayBillNumber(wayBillNumber);
			if(wayBillOrder == null){
				WayBillOrder wayBillOrderTemp = new WayBillOrder();
				LadingOrder ladingOrder = ladingOrderService.selectByWayBillNumber(wayBillNumber);
				if(ladingOrder != null){
					wayBillOrderTemp.setWayBillNumber(wayBillNumber);
					wayBillOrderTemp.setConsignor(ladingOrder.getConsignor());
					wayBillOrderTemp.setConsignorMobile(ladingOrder.getConsignorMobile());
					wayBillOrderTemp.setConsignee(ladingOrder.getConsignee());
					wayBillOrderTemp.setConsigneeMobile(ladingOrder.getConsigneeMobile());
					wayBillOrderTemp.setStartOutletsName(ladingOrder.getStartOutletsName());
					wayBillOrderTemp.setTargetOutletsName(ladingOrder.getTargetOutletsName());
					wayBillOrderTemp.setInsuranceMoney(ladingOrder.getInsuranceMoney());
					wayBillOrderTemp.setWayBillOrderTime(ladingOrder.getLadingOrderTime());
					wayBillOrder = wayBillOrderTemp;
				}
			}
			result.setData(wayBillOrder);
			result.setResult(true);
			
		}
		return result;
	}
	
	@RequestMapping("handleabnormal")
	public ModelAndView toHandleAbnormal(Long abnormalId){
		ModelAndView mav = new ModelAndView("/tms/platform/tmsabnormalmanage/handleabnormal");
		mav.addObject("abnormalId", abnormalId);
		return mav;
	}
	
	@RequestMapping("insrthandlret")
	@ResponseBody
	public Result addHandleAbnormalResult(AbnormalHandle abnormalHandle, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		String userName = user.getTrueName();
		if(StringUtils.isEmpty(userName)){
			userName = user.getLoginName();
		}
		abnormalHandle.setHandlePerson(userName);
		Result result = new Result();
		if(abnormalHandleService.insert(abnormalHandle, user)){
			result.setResult(true);
		}
		return result;
	}
	
	@RequestMapping("clsewybllrdr")
	@ResponseBody
	public Result closeWayBillOrder(@RequestParam("wayBillNumbers[]")String[] wayBillNumbers){
		Result result = new Result();
		int rows = abnormalService.saveCloseException(wayBillNumbers);
		if(rows > 0){
			result.setResult(true);
		}
		return result;
	}
	
	/**
	 * 跳转到异常 详情页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/toabnormalinfodetail")
	public String toAbnormalInfoDetail(Long id, HttpServletRequest request){
		Abnormal abnormal = abnormalService.selectByPrimaryKey(id);
		AbnormalHandle abnormalHandle = abnormalHandleService.selectByAbnormalId(abnormal.getId());
		request.setAttribute("abnormal", abnormal);
		request.setAttribute("abnormalHandle", abnormalHandle);
		return "/tms/platform/tmsabnormalmanage/abnormal-info-detail";
	}
}

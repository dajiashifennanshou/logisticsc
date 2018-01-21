package com.brightsoft.controller.system.tms.ad;

import java.io.File;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.brightsoft.common.constants.DictionaryType;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.AdvertisementInfo;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.platform.DictionaryService;
import com.brightsoft.service.tms.system.AdManageService;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;

@Controller
@RequestMapping("/system/tms/ad")
public class AdManageController {

	private static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	
	@Autowired
	private AdManageService adManageService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	/**
	 * 跳转到显示页面
	 * @return
	 */
	@RequestMapping("/list")
	public String toAd(ModelMap model){
		model.addAttribute("adPositions", dictionaryService.selectDictDataByType(DictionaryType.AD_POSITION));
		return "/system/tms/ad/listadinfo";
	}
	
	@RequestMapping("/search")
	@ResponseBody
	public Result search(Page<?> page,SearchParams searchParams){
		Result result = adManageService.selectByCondition(page, searchParams);
		result.setResult(true);;
		return result;
		
	}
	/**
	 * 添加广告
	 * @param advertisementInfo
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Result addAdInfo(AdvertisementInfo advertisementInfo,@RequestParam("fileName")MultipartFile uploadFile,
			HttpSession session){
		Result result = new Result();
		SysUser user = (SysUser)session.getAttribute("user");
		if(user!=null){
			if(uploadFile==null||uploadFile.isEmpty()){
				
			}else{
				String fileName = uploadFile.getOriginalFilename();
				String suffix = fileName.substring(fileName.lastIndexOf('.')+1);
				if(suffix.matches(bundle.getString("reg"))){
					String dirName ="";
	            	
	        		dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
	                        + bundle.getString("uploadAd"), fileName);
	    			
	                /* 根据图片名生成唯一文件路径 */
	                File fl = new File(UploadFileUtil.getOriginalFileDirName(fileName, dirName));
	                // 写入文件到实际路径
	                try {
						uploadFile.transferTo(fl);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
	                String filePath = dirName + fl.getName();
	                advertisementInfo.setAdFilePath(filePath);
	                advertisementInfo.setCreatePersonId(user.getId());
	                advertisementInfo.setPublishType(Const.AD_PUBLISH_TYPE_TMS);
	                if(adManageService.insertAd(advertisementInfo)){
	                	result.setResult(true);
	                }
				}
			}
		}
		return result;
	}
	
	/**
	 * 验证日期
	 * @return
	 */
	@RequestMapping("/vrfydt")
	@ResponseBody
	public Result verifyAdDate(String startTime,String endTime,Integer adPosition){
		Result result = adManageService.select2VerifyDate(startTime, endTime, adPosition);
		return result;
	}
		
}

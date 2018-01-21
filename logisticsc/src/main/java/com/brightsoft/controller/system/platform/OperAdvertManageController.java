package com.brightsoft.controller.system.platform;

import java.io.File;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.AdvertisementInfo;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.platform.OperAdvertManageService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;

/**
 * 货运交易系统--广告管理
 * 2016年3月17日 下午11:29:51
 * @author zhouna
 */
@Controller
@RequestMapping("/system/yypt/advert")
public class OperAdvertManageController {
	
	
	@Autowired
	private  OperAdvertManageService  operAdvertManageService;
	private static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	/**
	 * 跳转广告页面
	 * 2016年3月18日 下午3:18:40
	 * @return 
	 * @author zhouna
	 */
	@RequestMapping("/list")
	public String advertManagementlist() {
		return "/system/platform/advertmanagement/advertmanalist";
	}
	
	/**
	 * 查询货运交易系统类型的广告信息
	 * @param page
	 * @param searchParams
	 * @return
	 */
	@RequestMapping("/listdata")
	@ResponseBody
	private Result advertManagementlistdata(Page<?> page,SearchParams searchParams){
		 return operAdvertManageService.advertManagementlistdata(searchParams,page);
	}
	
	/**
	 * 通过id删除
	 * 2016年3月23日 上午11:08:12
	 * @param id
	 * @return 
	 * @author zhouna
	 */
	@RequestMapping("/deletes")
	@ResponseBody
	private Result advertManagementDelId(String idss){
		 return operAdvertManageService.advertManagementDelId(idss);
	}
	
	/**
	 * 保存广告
	 * 2016年3月23日 下午12:56:51
	 * @param session
	 * @param advertisementInfo
	 * @param uploadFile
	 * @return 
	 * @author zhouna
	 */
	@RequestMapping("/saveadvert")
	@ResponseBody
	private Result advertManagementsave(HttpSession session ,AdvertisementInfo advertisementInfo,
			@RequestParam(value="fileName",required=false)MultipartFile uploadFile){
		
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		if(user!=null){
			if (advertisementInfo.getId() == null) {
			return saveadvertinfo(user,advertisementInfo,uploadFile);
			}else {
			return  updateadvertinfo(user,advertisementInfo,uploadFile);
			}
		}
		return null;
	}

	/***
	 * 编辑广告信息
	 * @param user
	 * @param advertisementInfo
	 * @param uploadFile
	 * @return
	 */
	private Result updateadvertinfo(SysUser user,AdvertisementInfo advertisementInfo, MultipartFile uploadFile) {
		Result result = new Result();
		if (uploadFile == null) {
			if (operAdvertManageService.advertManagementupdate(advertisementInfo) > 0 ) {
				result.setResult(true);
			}
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
                if (operAdvertManageService.advertManagementupdate(advertisementInfo) > 0 ) {
    				result.setResult(true);
    			}
		}
		}
		return result;
	}

	/**
	 * 保存广告信息
	 * @param user
	 * @param advertisementInfo
	 * @param uploadFile
	 * @return
	 */
	private Result saveadvertinfo(SysUser user,AdvertisementInfo advertisementInfo,MultipartFile uploadFile) {
		Result result = new Result();
		if(uploadFile==null||uploadFile.isEmpty()){
			result.setResult(false);
		}else {
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
                advertisementInfo.setPublishType(0);
                advertisementInfo.setState(1);
                if(operAdvertManageService.advertManagementsave(advertisementInfo)){
                	result.setResult(true);
                }
			}
		}
		return result;
	}
	
}
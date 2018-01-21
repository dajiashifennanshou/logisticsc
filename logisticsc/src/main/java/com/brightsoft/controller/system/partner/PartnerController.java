package com.brightsoft.controller.system.partner;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.SysPartner;
import com.brightsoft.model.SysUser;
import com.brightsoft.service.system.platform.SysPartnerServiceImpl;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
import com.brightsoft.utils.UploadFileUtil;

@Controller
@RequestMapping("/system/partner/")
public class PartnerController {
	
	private static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");
	
	@Autowired
	private SysPartnerServiceImpl sysPartner;
	/**
	 * 跳转到合作伙伴
	 * @return
	 */
	@RequestMapping("jumpPartner")
	public String jumpPartner(){
		return "/system/partner/partner_platform_list";
	}
	
	@RequestMapping("/doGetPartner")
	@ResponseBody
	private Result doGetPartner(Page<?> page,SearchParams searchParams){
		 return sysPartner.doGetPartner(searchParams, page);
	}
	/**
	 * 添加 修改 我的合作伙伴
	 * @param uploadFile
	 * @param session
	 * @param partner
	 * @return
	 */
	@RequestMapping("/doPartner")
	@ResponseBody
	public Result doPartner(@RequestParam(value="fileName",required=false)MultipartFile uploadFile,HttpSession session,SysPartner partner){
		Result ret = new Result();
		SysUser user = (SysUser)session.getAttribute(SystemConstant.YYPT_USER_SESSION);
		if(partner.getId() == null ||partner.getId() <= 0){
			if (uploadFile == null) {
				ret.setMsg("合作伙伴图片不能为空");
	     	    ret.setResult(false);
			}else{
				String fileName = uploadFile.getOriginalFilename();
				String suffix = fileName.substring(fileName.lastIndexOf('.')+1);
				if(suffix.matches(bundle.getString("reg"))){
					String dirName ="";
	            	
	        		dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
	                        + bundle.getString("uploadPartner"), fileName);
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
	                partner.setPictureUrl(filePath);
	                partner.setUserId(user.getId());
	               if(sysPartner.insertPartner(partner)){
	            	   ret.setResult(true);
	               }else{
	            	   ret.setResult(false);
	               }
			}
			}
		}else{
			if (uploadFile == null) {
				   partner.setUserId(user.getId());
	               if(sysPartner.updateParner(partner)){
	            	   ret.setResult(true);
	               }else{
	            	   ret.setResult(false);
	               }
			}else{
				String fileName = uploadFile.getOriginalFilename();
				String suffix = fileName.substring(fileName.lastIndexOf('.')+1);
				if(suffix.matches(bundle.getString("reg"))){
					String dirName ="";
	            	
	        		dirName = UploadFileUtil.getSecondPathByHashCode(bundle.getString("baseUrl")
	                        + bundle.getString("uploadPartner"), fileName);
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
	                partner.setPictureUrl(filePath);
	                partner.setUserId(user.getId());
	               if(sysPartner.updateParner(partner)){
	            	   ret.setResult(true);
	               }else{
	            	   ret.setResult(false);
	               }
			}
			}
		}
		return ret;
	}
	
	@RequestMapping("/deletePartner")
	@ResponseBody
	public Result deletePartner(String idss){
		Result srt = new Result();
		List<Long> list = JSONArray.parseArray(idss, Long.class);
		if(sysPartner.deleteParner(list)){
			srt.setResult(true);
		}else{
			srt.setResult(false);
		}
		return srt;
	}
}

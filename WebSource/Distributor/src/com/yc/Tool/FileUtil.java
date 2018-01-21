package com.yc.Tool;


import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FileUtil {

	/**
	 * 上传银行卡和身份证
	 * @Author:luojing
	 * @2016年7月23日 上午10:56:46
	 */
	public static String uploadBankImg(HttpServletRequest request,String file){
		try{
			MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
			MultipartFile multipartFile = multipartRequset.getFile("file");
			//存放路径
			String filePath = request.getSession().getServletContext().getRealPath("/");
			String path1= "/upload/bank/"+file;
        	File f1 = new File(filePath+path1);
        	//验证文件夹是否存在
        	if(!f1.exists()){
        		f1.mkdirs();
        	}
        	String path2 = filePath+path1+"/"+multipartFile.getOriginalFilename();
	        multipartFile.transferTo(new File(path2));
	        //验证是否上传成功
	        File f2 = new File(path2);
	        if(f2.exists()){
	        	return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/upload/bank/"+file+"/"+multipartFile.getOriginalFilename();
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}

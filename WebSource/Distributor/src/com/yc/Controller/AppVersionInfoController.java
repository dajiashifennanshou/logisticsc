package com.yc.Controller; 
import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.AppVersionInfo;
import com.yc.Service.AppVersionInfoService;
import com.yc.Tool.FengUtil;
import com.yc.Tool.MD5;

import net.dongliu.apk.parser.ApkParser;
import net.dongliu.apk.parser.bean.ApkMeta;   

/**
 * app版本信息管理
 * @Author:luojing
 * @2016年8月11日 下午3:13:22
 */
@Controller 
public class AppVersionInfoController {  
	
	@Autowired 
	private AppVersionInfoService iAppVersionInfoService; 
	
	/**
	 * 查询版本信息
	 * @Author:luojing
	 * @2016年8月11日 下午3:13:05
	 */
	@RequestMapping("app/getVersion")
	public void getVersion(HttpServletRequest request,HttpServletResponse response){
		try{
			/*String path = request.getSession().getServletContext().getRealPath("/apk/xsl-yc.apk");
			ApkParser apkParser = new ApkParser(path);
			ApkMeta apkMeta = apkParser.getApkMeta();
			int versionCode = Integer.parseInt(request.getParameter("versionCode"));
			if(apkMeta.getVersionCode()>versionCode){
				//有新版本
				AppVersionInfo version = new AppVersionInfo();
				version.setVersionCode(Integer.parseInt(apkMeta.getVersionCode().toString()));
				File file = new File(path);
				version.setReleaseTime(file.lastModified()+"");
				version.setLatestMd5(MD5.md5sum(path));
				version.setFileSize(file.length()+"");
				version.setVersionName(apkMeta.getVersionName());
				version.setFileUri(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/apk/xsl-yc.apk");
				version.setChangeLog("");
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, version, response);
			}else{
				FengUtil.RuntimeExceptionFeng("当前为最新版本");
			}*/
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}
}

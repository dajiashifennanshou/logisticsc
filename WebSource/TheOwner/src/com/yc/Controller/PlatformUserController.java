package com.yc.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yc.Canstant.Constant;
import com.yc.Entity.PlatformUser;
import com.yc.Entity.PlatformUserCompany;
import com.yc.Entity.PlatformUserTemporaryCompany;
import com.yc.Service.PlatformUserCompanyService;
import com.yc.Service.PlatformUserService;
import com.yc.Service.PlatformUserTemporaryCompanyService;
import com.yc.Tool.FengRuntimeException;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;
import com.yc.Tool.StrUtil;
import com.yc.Tool.UploadFileUtil;
/**
 * APP登陆接口
 * Author:luojing
 * 2016年6月27日 下午5:32:39
 */
@Controller
public class PlatformUserController {
	
	@Autowired
	private PlatformUserService platformUserService;
	@Autowired
	private PlatformUserTemporaryCompanyService tmpCompanyService;
	
	/**
	 * 获取公司图片文件对象
	 */
	public static ResourceBundle bundle = PropertyResourceBundle.getBundle("companyDocumentsConfig");

	/**
	 * 登录
	 * Author:luojing
	 * 2016年6月27日 下午3:47:19
	 */
	@RequestMapping("app/consignerLogin")
	public void dealerLogin(HttpServletRequest request,PlatformUser user,HttpServletResponse response){
		try{
			//user = (LcPlatformUser) DES.ObjectDecrypt(user);
			if(user==null){
				FengUtil.RuntimeExceptionFeng("未获取到登录信息");
			}
			PlatformUser users = platformUserService.getPlatformUserInfo(user);
			if(users!=null){
				String uuid = FengUtil.getUUID();//token
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("user", users);
				map.put("token", uuid);
				//保存user到session中
				request.getSession().setAttribute(uuid, users);
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, map, response);
			}else{
				FengUtil.RuntimeExceptionFeng("用户名或密码错误");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getLocalizedMessage(), response);
		}
		
	}
	
	/**
	 * 注册
	 * Author:luojing
	 * 2016年6月28日 下午2:51:27
	 */
	@RequestMapping("app/consignerRegister")
	public void register(PlatformUser user,String validCode,HttpServletRequest request,HttpServletResponse response){
		try{
			if(user==null || "".equals(validCode)){
				FengUtil.RuntimeExceptionFeng("未获取到注册信息");
			}
			if(user==null || !StrUtil.VString(validCode) ){
				FengUtil.RuntimeExceptionFeng("数据不能传递NULL..");
			}
			if(validCode.equals(request.getSession().getAttribute(user.getMobile()))){
				Integer i = platformUserService.addUserInfo(user);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, user, response);
				}else{
					FengUtil.RuntimeExceptionFeng("该账号已经注册");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("验证码不正确,请重新输入");
			}
		}catch(FengRuntimeException e){
			e.printErrorInfo();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, "异常", response);
		}
	}
	
	/**
	 * 修改密码（忘记密码）
	 * Author:luojing
	 * 2016年6月28日 下午2:52:54
	 */
	@RequestMapping("app/updatePassword")
	public void updatePassword(PlatformUser user,String validCode,HttpServletRequest request,HttpServletResponse response){
		try{
			if(user==null || "".equals(validCode)){
				FengUtil.RuntimeExceptionFeng("为获取到修改信息");
			}
			if(validCode.equals(request.getSession().getAttribute(user.getMobile()))){
				Integer i = platformUserService.updatePassword(user);
				if(i>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
				}else{
					FengUtil.RuntimeExceptionFeng("密码修改失败,稍后请重试");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("验证码错误,请重新获取");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}
	
	/**
	 * 查询个人信息
	 * Author:luojing
	 * 2016年6月28日 下午2:20:20
	 */
	@RequestMapping("app/getUser")
	public void getUser(PlatformUser user,HttpServletRequest request,HttpServletResponse response){
		try{
			PlatformUser us = platformUserService.getPlatformUserInfo(user);
			FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, us, response);
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, "暂无结果", response);
		}
	}
	
	
	/**
	 * 修改个人信息
	 * @Author:luojing
	 * @2016年8月18日 上午10:41:56
	 */
	@RequestMapping("app/updateUser")
	public void updateUser(PlatformUser user,HttpServletRequest request,HttpServletResponse response){
		try{
			if(user!=null){
				if(platformUserService.updatePassword(user)>0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
				}else{
					FengUtil.RuntimeExceptionFeng("个人信息修改失败");
				}	
			}else{
				FengUtil.RuntimeExceptionFeng("为获取到修改信息");
			}
		}catch(FengRuntimeException e){
			e.printErrorInfo();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}
	
	/**
	 * 申请企业货主
	 * Author:luojing
	 * 2016年6月28日 下午2:57:14
	 */
	@RequestMapping("app/applyEnterprise")
	public void applyEnterprise(BigInteger userId,PlatformUserTemporaryCompany company,HttpServletRequest request,HttpServletResponse response){
		try{
			if(request instanceof MultipartHttpServletRequest){
				for (int i = 0; i < 5; i++) {
					 MultipartHttpServletRequest multipartRequset = (MultipartHttpServletRequest)request;
		             MultipartFile multipartFile = multipartRequset.getFile("fileName"+i);
		             if(null == multipartFile || multipartFile.isEmpty()){
		                 continue;
		             }else if(multipartFile.getSize() > 512000){
		            	 	FengUtil.RuntimeExceptionFeng("上传图片不能大于512KB!");
			             }else{
		            	 // 获得图片名称
		                 String fileName = multipartFile.getOriginalFilename();
//		                 String path = request.getSession().getServletContext().getRealPath("/");
		                 String path = bundle.getString("baseUrl");
	                	 if(("fileName"+i).equals("fileName0")){
	                		 //公司logo照片   bundle.getString("baseUrl")
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(path + bundle.getString("uploadLogo"), fileName);
	                		 String url = UploadFileUtil.getOriginalFileDirName(fileName, dirName);
		                	 File file = new File(url);
		                     multipartFile.transferTo(file);
//		                     String url = UploadFileUtil.getOriginalFileDirName(fileName, dirName);//图片存放路径
		                     company.setLogo(UploadFileUtil.getOriginalFileDirName(fileName,UploadFileUtil.getSecondPathByHashCode(bundle.getString("uploadLogo"), fileName)));
	                	 }else if(("fileName"+i).equals("fileName1")){
	                		 //营业执照照片
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(path + bundle.getString("uploadBusinessLicense"), fileName);
	                		 String url =UploadFileUtil.getOriginalFileDirName(fileName, dirName);
		                	 File file = new File(url);
		                     multipartFile.transferTo(file);
//		                     String url =UploadFileUtil.getOriginalFileDirName(fileName, dirName);//图片存放路径
		                     company.setBusiness_license(UploadFileUtil.getOriginalFileDirName(fileName,UploadFileUtil.getSecondPathByHashCode(bundle.getString("uploadBusinessLicense"), fileName)));
	                	 }else if(("fileName"+i).equals("fileName2")){
	                		 //公司照片
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(path + bundle.getString("uploadCompanyPhoto"), fileName);
	                		 String url =UploadFileUtil.getOriginalFileDirName(fileName, dirName);
		                	 File file = new File(url);
		                     multipartFile.transferTo(file);
//		                     String url =UploadFileUtil.getOriginalFileDirName(fileName, dirName);//图片存放路径
		                     company.setCompany_photo(UploadFileUtil.getOriginalFileDirName(fileName,UploadFileUtil.getSecondPathByHashCode(bundle.getString("uploadCompanyPhoto"), fileName)));
	                	 }else if(("fileName"+i).equals("fileName3")){
	                		//法定人身份证照片
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(path + bundle.getString("uploadLegalPhoto"), fileName);
	                		 String url =UploadFileUtil.getOriginalFileDirName(fileName, dirName);
		                	 File file = new File(url);
		                     multipartFile.transferTo(file);
//		                     String url =UploadFileUtil.getOriginalFileDirName(fileName, dirName);//图片存放路径
		                     company.setLegal_photo(UploadFileUtil.getOriginalFileDirName(fileName,UploadFileUtil.getSecondPathByHashCode(bundle.getString("uploadLegalPhoto"), fileName)));
	                	 }else if(("fileName"+i).equals("fileName4")){
	                		//名片照片
	                		 String dirName = UploadFileUtil.getSecondPathByHashCode(path + bundle.getString("uploadCardPhoto"), fileName);
	                		 String url =UploadFileUtil.getOriginalFileDirName(fileName, dirName);
		                	 File file = new File(url);
		                     multipartFile.transferTo(file);
//		                     String url =UploadFileUtil.getOriginalFileDirName(fileName, dirName);//图片存放路径
		                     company.setCard_photo(UploadFileUtil.getOriginalFileDirName(fileName,UploadFileUtil.getSecondPathByHashCode(bundle.getString("uploadCardPhoto"), fileName)));
	                	 }
		             }
				}
			}
			if(tmpCompanyService.addTempCompan(userId,company)){
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, response);
			}else{
				FengUtil.RuntimeExceptionFeng("申请企业货主失败!");
			}
		}catch(FengRuntimeException e){
			e.printErrorInfo();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, "异常", response);
		}
	}
	
	/**
	 * 下载图片
	 * @Author:luojing
	 * @2016年8月19日 上午10:55:48
	 */
	@RequestMapping("app/getImg")
	public void loadImg(String url,HttpServletRequest request,HttpServletResponse response){
		url = request.getSession().getServletContext().getRealPath("/")+url;
		if(StringUtils.isNotBlank(url)){
			try {
				UploadFileUtil.readImg(url, response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

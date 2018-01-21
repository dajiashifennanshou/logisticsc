package com.yc.Controller.sms;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.PlatformUser;
import com.yc.Service.PlatformUserService;
import com.yc.Tool.FengRuntimeException;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;
import com.yc.Tool.sms.SendMsg;

@Controller
public class SMSController {
	
	@Autowired
	private PlatformUserService platformUserService;
	
	/** 
	* 获取验证码
	* Author:luojing
	* type:向手机发送，还是向邮箱发送
	*/ 
	@RequestMapping("app/validCode") 
	public void validCode(PlatformUser user,HttpServletRequest request,HttpServletResponse response) {  
		try{
			//user = (LcPlatformUser) DES.ObjectDecrypt(user);
			PlatformUser us = platformUserService.getPlatformUserInfo(user);
			if(us == null){
				String code=FengUtil.AuthCode();
				request.getSession().setAttribute(user.getMobile(), code);
				request.getSession().setMaxInactiveInterval(5*60);
				Map<String, Object> map = SendMsg.Verification(user.getMobile(), code, SendMsg.PLATFORM_SENDTYPE_0);
				Integer msg = (Integer) map.get("error_code");
				if(msg==0){
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, code, response);
				}else{
					FengUtil.RuntimeExceptionFeng("发送失败,请重试!");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("用户存在,请登录!");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,e.getMessage(), response);
		}
	}
	
	/**
	 * luojing
	 * @Author:luojing
	 * @2016年7月21日 下午4:16:37
	 */
	@RequestMapping("app/getCode") 
	public void getCode(PlatformUser user,HttpServletRequest request,HttpServletResponse response) {  
		try{
			//user = (LcPlatformUser) DES.ObjectDecrypt(user);
			String code=FengUtil.AuthCode();
			request.getSession().setAttribute(user.getMobile(), code);
			request.getSession().setMaxInactiveInterval(5*60);
			if(platformUserService.getPlatformUserInfo(user)==null){
				FengUtil.RuntimeExceptionFeng("你并未注册过账号，请移步至注册..");
			}
			Map<String, Object> map = SendMsg.Verification(user.getMobile(), code, SendMsg.PLATFORM_SENDTYPE_0);
			Integer msg = (Integer) map.get("error_code");
			if(msg==0){
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, code, response);
			}else{
				FengUtil.RuntimeExceptionFeng("发送失败,请重试!");
			}
		}catch(FengRuntimeException e){
			FengUtil.OUTAPPERROR(Constant.APP_ERROR ,e.getMsg(), response);
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,e.getMessage(), response);
		}
	}
}

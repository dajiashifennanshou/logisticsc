package com.yc.Controller.sms;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.Canstant.Constant;
import com.yc.Entity.LcPlatformUser;
import com.yc.Service.PlatformUserService;
import com.yc.Tool.DES;
import com.yc.Tool.FengUtil;
import com.yc.Tool.LogUtil;
import com.yc.Tool.sms.SendMsg;

@Controller
public class SMSController {
	
	@Autowired
	private PlatformUserService lpos;
	
	/** 
	* 获取验证码
	* Author:luojing
	*/ 
	@RequestMapping("app/validCode") 
	public void validCode(HttpServletRequest request,HttpServletResponse response) {  
		try{
			LcPlatformUser user = (LcPlatformUser) FengUtil.getObject(request.getParameterMap(), new LcPlatformUser());
			LcPlatformUser us = lpos.getSingleInfo(user);
			if(us == null){
				String code=FengUtil.AuthCode();
				Map<String, Object> map = SendMsg.Verification(user.getLogin_name(), code, SendMsg.PLATFORM_SENDTYPE_0);
				Integer msg = (Integer) map.get("error_code");
				if(msg==0){
					Cookie cook = new Cookie(user.getLogin_name(), code);
					cook.setMaxAge(180);
					response.addCookie(cook);
					FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, code, response);
				}else{
					FengUtil.RuntimeExceptionFeng("发送失败,请重试!");
				}
			}else{
				FengUtil.RuntimeExceptionFeng("用户存在,请登录!");
			}
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
	public void getCode(HttpServletRequest request,HttpServletResponse response) {  
		try{
			LcPlatformUser user = (LcPlatformUser) FengUtil.getObject(request.getParameterMap(), new LcPlatformUser());
			String code=FengUtil.AuthCode();
			Map<String, Object> map = SendMsg.Verification(user.getMobile(), code, SendMsg.PLATFORM_SENDTYPE_0);
			Integer msg = (Integer) map.get("error_code");
			if(msg==0){
				Cookie cook = new Cookie(user.getMobile(), code);
				cook.setMaxAge(180);
				response.addCookie(cook);
				FengUtil.OUTAPPSUCCESS(Constant.APP_SUCCESS, code, response);
			}else{
				FengUtil.RuntimeExceptionFeng("发送失败,请重试!");
			}
		}catch(Exception e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getStackTrace());
			FengUtil.OUTAPPERROR(Constant.APP_ERROR,e.getMessage(), response);
		}
	}
}

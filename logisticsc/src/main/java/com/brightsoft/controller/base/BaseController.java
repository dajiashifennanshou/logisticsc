package com.brightsoft.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.User;
import com.brightsoft.utils.Const;


public class BaseController {
	
	public static String SYS_NAME = "logisticsc";
	
	protected static String SUCCESS_STATUS = "1";
	
	protected static String FAILURE_STATUS = "0";
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}
	
	/**
     * 页面跳转
     * 
     * @param url
     * @return
     */
    @RequestMapping("/pageJump")
    public String pageJump(String url){
    	
        return url;
    }
	
    /**
     * TMS 获取当前用户对应的企业ID
     * @param session
     * @return
     */
    public Long getCompanyId(HttpSession session){
    	PlatformUser platformUser = (PlatformUser)session.getAttribute(SystemConstant.USER_SESSION);
		Integer userType = platformUser.getUserType();
		if(userType == Const.PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR || 
				userType == Const.PLATFORM_USER_TYPE_ENTERPRISE_CONSIGNOR){
			User tmsUser = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
			return tmsUser.getPlatformUserCompany().getId();
		}else{
			return platformUser.getCompanyId();
		}
    }
    
    public String formatStr(String text) {
		return text == null ? "" : text.trim();
	}
}

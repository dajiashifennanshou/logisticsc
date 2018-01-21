package com.brightsoft.controller.yc;

import javax.servlet.http.HttpServletRequest;

import com.brightsoft.utils.yc.FengUtil;

/**
 *Good Luck
 *
*/
public class ValitedLogin<T> {

	public  T VALITEDLOGIN(HttpServletRequest request,String key){
		Object o=request.getSession().getAttribute(key);
		if(o==null){
			FengUtil.FengRuntimeException("未登录...");
		}
		T t=(T)o ;
		return t;
	}
	
	
}

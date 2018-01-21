package com.brightsoft.repeat;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 验证重复提交的拦截器
 * @author yangshoubao
 *
 */
public class RepeatSubmissionInterceptor extends HandlerInterceptorAdapter{

	/** 日志 */
	private static final Logger logger = Logger.getLogger(RepeatSubmissionInterceptor.class);
	
	/** 普通表单重复提交重定向路径 */
	private String redirectPath;
	
	/**
	 * 拦截请求的方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Method method = handlerMethod.getMethod();
		RepeatSubmission annotation = method.getAnnotation(RepeatSubmission.class);
		if(annotation == null){
			return true;
		}
		boolean needSaveToken = annotation.needSaveToken();
		if(needSaveToken){
			// 设置随机产生的token
			request.getSession().setAttribute("token", String.valueOf(System.currentTimeMillis()));
			logger.info("【方法：" + method.getName() + "，保存token】");
			return true;
		}
		boolean needUpdateToken = annotation.needUpdateToken();
		if(!needUpdateToken){
			return true;
		}
		if(!isRepeatSubmit(request)){
			// 非重复提交
			String token = String.valueOf(System.currentTimeMillis());
			request.getSession().setAttribute("token", token);
			// 如果是ajax请求相应头会有，x-requested-with;
			if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
				response.addHeader("token", token);
			}
			return true;
		}
		logger.warn("【表单重复提交】， url：" + request.getServletPath());
		// 如果是ajax请求相应头会有，x-requested-with;
		if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			response.addHeader("isRepeatSubmit", "true");
		} else {
			// 普通表单重复提交处理
			redirect(response);
		}
		return false;
	}
	
	/**
	 * 普通表单请求的重定向
	 * @param response
	 * @throws Exception
	 */
	public void redirect(HttpServletResponse response) throws Exception {
		if(StringUtils.isNotBlank(redirectPath)){
			response.sendRedirect(redirectPath);
		} else {
			response.setCharacterEncoding("GBK");
			response.getWriter().write("请不要重复提交");
		}
	}
	
	/**
	 * 表单重复提交 验证方法
	 * @param request
	 * @return
	 */
	public boolean isRepeatSubmit(HttpServletRequest request){
		String serverToken = (String)request.getSession().getAttribute("token");
		if(serverToken == null){
			return true;
		}
		String clientToken = request.getParameter("token");
		if(clientToken == null){
			return true;
		}
		if(!serverToken.equals(clientToken)){
			return true;
		}
		return false;
	}

	public void setRedirectPath(String redirectPath) {
		this.redirectPath = redirectPath;
	}
}

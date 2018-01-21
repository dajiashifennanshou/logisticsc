package com.brightsoft.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.model.PlatformUser;
import com.brightsoft.model.SysUser;

public class InterceptorController extends HandlerInterceptorAdapter{
	public String[] allowUrls;//还没发现可以直接配置不拦截的资源，所以在代码里面来排除  
    
    public void setAllowUrls(String[] allowUrls) {  
        this.allowUrls = allowUrls;  
    }  
  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  
            Object arg2) throws Exception {  
        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");    
        if(null != allowUrls && allowUrls.length>=1)  
            for(String url : allowUrls) {    
                if(requestUrl.contains(url)) {    
                    return true;    
                }    
            }  
          
        PlatformUser user = (PlatformUser) request.getSession().getAttribute(SystemConstant.USER_SESSION);
        SysUser sysUser = (SysUser)request.getSession().getAttribute(SystemConstant.YYPT_USER_SESSION);
        if(user != null || sysUser !=null) {    
            return true;  //返回true，则这个方面调用后会接着调用postHandle(),  afterCompletion()  
        }else{  
        	throw new SessionTimeoutException();//返回到配置文件中定义的路径 
        }
    }  
      
    @Override  
    public void afterCompletion(HttpServletRequest arg0,  
            HttpServletResponse arg1, Object arg2, Exception arg3)  
            throws Exception {  
    }  
  
    @Override  
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,  
            Object arg2, ModelAndView arg3) throws Exception {  
    }  
}

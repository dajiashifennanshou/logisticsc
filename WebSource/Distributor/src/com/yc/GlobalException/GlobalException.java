package com.yc.GlobalException;


import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   
  
import org.springframework.web.servlet.HandlerExceptionResolver;   
import org.springframework.web.servlet.ModelAndView;

import com.yc.Tool.LogUtil;
import com.yc.Tool.StrUtil;
public class GlobalException implements HandlerExceptionResolver {   
  
    @Override  
    public ModelAndView resolveException(HttpServletRequest request,   
            HttpServletResponse response, Object handler, Exception ex) {   
        // TODO Auto-generated method stub   
    	ex.printStackTrace();
    	LogUtil.LogError.error("异常："+StrUtil.getString(ex.getMessage(), ex.getLocalizedMessage()));
        return new ModelAndView("error");   
    }   
  
} 
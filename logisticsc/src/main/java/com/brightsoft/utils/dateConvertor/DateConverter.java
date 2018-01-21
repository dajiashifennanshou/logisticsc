package com.brightsoft.utils.dateConvertor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class DateConverter implements WebBindingInitializer {    
	private static final String YMD = "yyyy-MM-dd";
	private static final String YMDHM = "yyyy-MM-dd hh:mm";
	private static final String YMDHMS = "yyyy-MM-dd hh:mm:ss";
	
    public void initBinder(WebDataBinder binder, WebRequest request) {    
          
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(YMD), true));    
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(YMDHM), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(YMDHMS), true));
    }
} 
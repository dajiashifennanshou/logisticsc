package com.brightsoft.controller.base;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.service.base.ImgService;

@Controller
@RequestMapping("/img/")
public class ImgServiceController {

	@Autowired
	private ImgService imgService;
	
	@RequestMapping("retrive")
	public void loadImg(String resource,HttpServletResponse response){
		if(StringUtils.isNotBlank(resource)){
			try {
				imgService.readImg(resource, response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

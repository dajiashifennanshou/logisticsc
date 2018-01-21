package com.brightsoft.controller.yc; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.service.yc.IYcVenderService;   

/** 
* YcVender控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcVenderController {  
	@Autowired 
	private IYcVenderService iYcVenderService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcVenderSingle") 
	public void getYcVenderSingle(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcVenderList") 
	public void getYcVenderList(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcVender") 
	public void addYcVender(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcVender") 
	public void modYcVender(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcVender") 
	public void delYcVender(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
}

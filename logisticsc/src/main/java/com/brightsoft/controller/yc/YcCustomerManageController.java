package com.brightsoft.controller.yc; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.service.yc.IYcCustomerManageService;   

/** 
* YcCustomerManage控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcCustomerManageController {  
	@Autowired 
	private IYcCustomerManageService iYcCustomerManageService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcCustomerManageSingle") 
	public void getYcCustomerManageSingle(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcCustomerManageList") 
	public void getYcCustomerManageList(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcCustomerManage") 
	public void addYcCustomerManage(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcCustomerManage") 
	public void modYcCustomerManage(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcCustomerManage") 
	public void delYcCustomerManage(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
}

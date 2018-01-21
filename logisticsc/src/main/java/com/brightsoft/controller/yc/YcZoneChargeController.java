package com.brightsoft.controller.yc; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.service.yc.IYcZoneChargeService;   

/** 
* YcZoneCharge控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcZoneChargeController {  
	@Autowired 
	private IYcZoneChargeService iYcZoneChargeService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcZoneChargeSingle") 
	public void getYcZoneChargeSingle(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcZoneChargeList") 
	public void getYcZoneChargeList(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcZoneCharge") 
	public void addYcZoneCharge(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcZoneCharge") 
	public void modYcZoneCharge(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcZoneCharge") 
	public void delYcZoneCharge(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
}

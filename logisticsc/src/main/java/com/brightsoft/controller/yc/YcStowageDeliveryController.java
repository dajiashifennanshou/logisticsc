package com.brightsoft.controller.yc; 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.YcStowageDelivery;
import com.brightsoft.service.yc.IYcStowageDeliveryService;
import com.brightsoft.utils.yc.FengUtil;   

/** 
* YcStowageDelivery控制器 
* Auther:FENG 
*/ 
@Controller 
public class YcStowageDeliveryController {  
	@Autowired 
	private IYcStowageDeliveryService iYcStowageDeliveryService; 


	/** 
	* 获取单条记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcStowageDeliverySingle") 
	public void getYcStowageDeliverySingle(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	 * 获取所有信息
	 * Auther:FENG 
	 */ 
	@RequestMapping("getYcStowageOrderAllList") 
	public void getYcStowageOrderAllList(YcStowageDelivery ysd,HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows) {  
		try{
			List<YcStowageDelivery> lst=new ArrayList<YcStowageDelivery>();
			lst=iYcStowageDeliveryService.getYcDeliveryOrderAllList(ysd);
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, lst, response);
		}catch(Exception e){
			FengUtil.OUTADDERROR(e.getMessage(), Constant.RESULT_ERROR_CODE, response);
		}
	}
	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@RequestMapping("getYcStowageDeliveryList") 
	public void getYcStowageDeliveryList(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 添加方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("addYcStowageDelivery") 
	public void addYcStowageDelivery(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 修改方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("modYcStowageDelivery") 
	public void modYcStowageDelivery(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
	/** 
	* 删除方法 
	* Auther:FENG 
	*/ 
	@RequestMapping("delYcStowageDelivery") 
	public void delYcStowageDelivery(HttpServletRequest request,HttpServletResponse response) {  
 
	} 
}

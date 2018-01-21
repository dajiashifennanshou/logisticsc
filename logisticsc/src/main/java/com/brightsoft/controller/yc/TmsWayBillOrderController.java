package com.brightsoft.controller.yc; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brightsoft.Constant.Constant;
import com.brightsoft.entity.TmsWayBillOrder;
import com.brightsoft.service.yc.ITmsWayBillOrderService;
import com.brightsoft.utils.yc.FengUtil;   

/** 
* Tms运单信息 
* Author:luojing 
*/ 
@Controller 
public class TmsWayBillOrderController {  
	@Autowired 
	private ITmsWayBillOrderService iTmsWayBillOrderService; 

	/**
	 * 运单集合查询
	 * Author:luojing
	 * 2016年6月24日 下午3:55:52
	 */
	@RequestMapping("getWayBillOrder")
	public void getWayBillOrder(TmsWayBillOrder wbo,HttpServletRequest request,HttpServletResponse response){
		try{
			FengUtil.OUTPRINTRESULT("", Constant.RESULT_SUCCESS_CODE, iTmsWayBillOrderService.getWayBillOrder(wbo), response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

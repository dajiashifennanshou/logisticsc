package com.brightsoft.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.service.platform.XzqhServiceImpl;

/**
 * 行政区划信息 控制器
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/xzqh")
public class XzqhController {

	@Autowired
	private XzqhServiceImpl xzqhService;
	
	/**
	 * 获取行政区划信息
	 * @param pid
	 * @return
	 */
	@RequestMapping("/getxzqhinfo")
	@ResponseBody
	public Object getXzqhInfo(String pid){
		return xzqhService.selectByPid(pid);
	}
	
	@RequestMapping("/toyeepay")
	public String toYeePay(){
		return "/payment/yeepay/index";
	}
	
	@RequestMapping("/toyeepaytest")
	public String toYeePaytest(){
		return "/payment/yeepaytest/44payApiRequest";
	}
	
}

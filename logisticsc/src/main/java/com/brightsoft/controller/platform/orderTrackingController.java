package com.brightsoft.controller.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.brightsoft.model.PlatformOrderTracking;
import com.brightsoft.service.platform.PlatformOrderFollowServiceImpl;
import com.brightsoft.utils.Result;

/**
 * 订单跟踪
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping("/orderTracking")
public class orderTrackingController {
	
	@Autowired
	private PlatformOrderFollowServiceImpl platformOrderFollow;
	/**
	 * 跳转到订单跟踪
	 * @return
	 */
	@RequestMapping("/toorderorderTracking")
	public String toOrderListPage(String chooseid,Long condition,ModelMap model){
		model.addAttribute("condition",JSON.toJSONString(condition));
		model.addAttribute("chooseid",chooseid);
		return "/platform/orderTracking/order_tracking";
	}
	/**
	 * 获取订单跟踪数据
	 * @param orderTracking
	 * @return
	 */
	@RequestMapping("/getOrderTracking")
	@ResponseBody
	public Result getOrderTracking(PlatformOrderTracking orderTracking){
		Result ret = new Result();
		orderTracking = platformOrderFollow.getOrderTracking(orderTracking);
		if(null != orderTracking){
			ret.setData(orderTracking);
			ret.setMsg("获取订单跟踪成功!");
			ret.setResult(true);
		}else{
			ret.setMsg("获取订单跟踪失败!");
			ret.setResult(false);
		}
		return ret;
	}
}

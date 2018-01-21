package com.brightsoft.controller.system.dataAnalysis;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.service.platform.PlatformOrderServiceImpl;
import com.brightsoft.service.system.PvStatisticsService;
import com.brightsoft.utils.Result;

@RequestMapping("/system/dataAnalysis/")
@Controller
public class SystemDataAnalysisController {

	@Autowired
	private PlatformOrderServiceImpl platformOrderServiceImpl;
	
	@Autowired
	private PvStatisticsService pvStatisticsService;
	
	/**
	 * 跳转到点击数据分析页面
	 * @return
	 */
	@RequestMapping("toDataClick")
	public String toDataClick(){
		return "/system/dataAnalysis/data_click";
	}
	
	/**
	 * 跳转到交易数据分析页面
	 * @return
	 */
	@RequestMapping("toDataTrade")
	public String toDataTrade(){
		return "/system/dataAnalysis/data_trade";
	}
	
	/**
	 * 跳转到数据字典页面
	 * @return
	 */
	@RequestMapping("toDataDic")
	public String toDataDictionary(){
		return "/system/dataAnalysis/data_dictionary";
	}
	
	@RequestMapping("getData")
	@ResponseBody
	public Result getData(){
		Result result = new Result();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pv", pvStatisticsService.selectPvAmount());
		map.put("orderAmount", platformOrderServiceImpl.selectOrderAmount());
		result.setData(map);
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("getMonthPv")
	@ResponseBody
	public Result getPv(){
		Result result = new Result();
		result.setData(pvStatisticsService.selectPvStatistics());
		result.setResult(true);
		return result;
	}
	
	@RequestMapping("getMonthOrder")
	@ResponseBody
	public Result getOrderAmount(){
		Result result = new Result();
		result.setData(platformOrderServiceImpl.selectOrderStatistics());
		result.setResult(true);
		return result;
	}
}

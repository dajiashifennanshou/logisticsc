package com.brightsoft.controller.platform;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.brightsoft.model.OutletsInfo;
import com.brightsoft.model.PlatformCompanyinfo;
import com.brightsoft.model.PlatformDeliverGoods;
import com.brightsoft.model.PlatformUserEvaluation;
import com.brightsoft.service.platform.PlatformLogisticsCompanyServiceImpl;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;
/**
 * 物流提供商店铺
 * @author ThinkPad
 *
 */
@Controller
@RequestMapping("/company")
public class PlatformCompanyController {
	
	@Autowired
	public PlatformLogisticsCompanyServiceImpl PlatformLogisticsCompany;
	/**
	 * 跳转到物流提供商
	 * @return
	 */
	@RequestMapping("/toorderPersonalcompany")
	public String toorderPersonalcompany(Long companyId,ModelMap model){
		model.addAttribute("companyId", JSON.toJSONString(companyId));
		return "/platform/logisticsCompany/logistics_company";
	}
	/**
	 * 获取物流提供商公司信息
	 * @param companyId
	 * @return
	 */
	@RequestMapping("/getLogisticsCompany")
	@ResponseBody
	public Result getLogisticsCompany(Long companyId){
		Result ret = new Result();
		PlatformCompanyinfo companyinfo =PlatformLogisticsCompany.selectLogistics(companyId);
		ret.setData(companyinfo);
		ret.setMsg("获取物流提供商!");
		ret.setResult(true);
		return ret;
	}
	/**
	 * 店铺线路信息
	 * @param deliverGoods
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getListCompanCondition")
	@ResponseBody
	public Result getListCompanCondition(PlatformDeliverGoods deliverGoods,Page<?> page){
		Result ret = new Result();
		page = PlatformLogisticsCompany.selectCompanCondition(deliverGoods, page);
		ret.setData(page);
		ret.setMsg("店铺线路信息!");
		ret.setResult(true);
		return ret;
	}
	/**
	 * 获取网点信息
	 * @param outletsInfo
	 * @param page
	 * @return
	 */
	@RequestMapping("/getOutletsInfo")
	@ResponseBody
	public Result getOutletsInfo(OutletsInfo outletsInfo,Page<?> page){
		Result ret = new Result();
		page = PlatformLogisticsCompany.selectOutletsInfo(outletsInfo, page);
		ret.setData(page);
		ret.setMsg("店铺网点信息!");
		ret.setResult(true);
		return ret;
	}
	/**
	 * 获取单个网点信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/getOutletsInfoId")
	@ResponseBody
	public Result getOutletsInfoId(Long id){
		Result ret = new Result();
		OutletsInfo outletsInfo = PlatformLogisticsCompany.selectOutletsId(id);
		ret.setData(outletsInfo);
		ret.setMsg("店铺网点单个信息!");
		ret.setResult(true);
		return ret;
	}
	/**
	 * 获取店铺评价信息
	 * @param evaluation
	 * @param page
	 * @return
	 */
	@RequestMapping("/getCompanInfo")
	@ResponseBody
	public Result getCompanInfo(PlatformUserEvaluation evaluation,Page<?> page){
		Result ret = new Result();
		page = PlatformLogisticsCompany.selectUserEvaluation(evaluation, page);
		ret.setData(page);
		ret.setMsg("店铺评价信息!");
		ret.setResult(true);
		return ret;
	}
}

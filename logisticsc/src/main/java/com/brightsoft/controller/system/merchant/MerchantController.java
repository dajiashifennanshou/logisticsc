package com.brightsoft.controller.system.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.PlatformInsurance;
import com.brightsoft.service.platform.PlatformInsuranceServiceImpl;
import com.brightsoft.service.platform.PlatformUserConsumeRecordService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/system/merchant/")
public class MerchantController {
	
	@Autowired
	private PlatformInsuranceServiceImpl platformInsuranceServiceImpl;
	
	@Autowired
	private PlatformUserConsumeRecordService platformUserConsumeRecordService;

	/**
	 * 跳转到电子保单页面
	 * @return
	 */
	@RequestMapping("ins")
	public String toInsurance(){
		return "/system/merchant/ins_items";
	}
	
	/**
	 * 跳转到电子保单消费记录页面
	 * @return
	 */
	@RequestMapping("insConsRecord")
	public String toInsComsumeRecord(){
		return "/system/merchant/ins_consume_record";
	}
	
	/**
	 * 查询电子保单信息
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("searchIns")
	@ResponseBody
	public Result searchIns(SearchParams searchParams,Page<?> page){
		Result result = platformInsuranceServiceImpl.selectByParams(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取保险消费记录
	 * @param searchParams
	 * @param page
	 * @return
	 */
	@RequestMapping("searchInsComRec")
	@ResponseBody
	public Result searchInsComsumeRecord(SearchParams searchParams,Page<?> page){
		Result result = platformUserConsumeRecordService.selectInsByParams(searchParams, page);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 获取保单详情
	 * @param insId
	 * @param model
	 * @return
	 */
	@RequestMapping("getInsItems")
	public String getInsItems(Long insId,ModelMap model){
		PlatformInsurance platformInsurance = platformInsuranceServiceImpl.selectById(insId);
		model.addAttribute("platIns", platformInsurance);
		return "/system/merchant/ins_content_items";
	}
}

package com.brightsoft.controller.system.tms.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.enums.DepositRatioStatusEnum;
import com.brightsoft.model.DepositRatio;
import com.brightsoft.service.tms.system.SysDepositRatioService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/system/tms/ratio")
public class SysDepositRatioController {

	@Autowired
	private SysDepositRatioService depositRatioService;
	
	@RequestMapping("/list")
	public String toListOutletsConf(ModelMap model){
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put(DepositRatioStatusEnum.PUBLISHED.getValue(), DepositRatioStatusEnum.PUBLISHED.getName());
		map.put(DepositRatioStatusEnum.PUBLISH_FAILURE.getValue(), DepositRatioStatusEnum.PUBLISH_FAILURE.getName());
		map.put("ratios", map);
		return "/system/tms/activitymanage/listdepositinfo";
	}
	
	/**
	 * 查询预存信息
	 * @param page
	 * @param depositRatio
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result search(Page<?> page,DepositRatio depositRatio){
		Result result = depositRatioService.selectByCondition(page, depositRatio);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 审核预存费信息/通过
	 */
	@RequestMapping("/audit/pass")
	@ResponseBody
	public Result auditApplicationPass(@RequestParam("ratioIds[]")String ratioIds[]){
		Result result = new Result();
		if(ratioIds!=null&&ratioIds.length>0){
			List<DepositRatio> list = new ArrayList<DepositRatio>();
			for (String ratioId : ratioIds) {
				DepositRatio depositRatio = new DepositRatio();
				depositRatio.setId(Long.parseLong(ratioId));
				depositRatio.setStatus(DepositRatioStatusEnum.PUBLISHED.getValue());
				list.add(depositRatio);
			}
			if(depositRatioService.update4Audit(list)>0){
				result.setResult(true);
			}
		}
		return result;
	}
	
	/**
	 * 审核预存费信息/未通过
	 */
	@RequestMapping("/audit/nopass")
	@ResponseBody
	public Result audioNoPass(@RequestParam("ratioIds[]")String ratioIds[]){
		Result result = new Result();
		if(ratioIds!=null&&ratioIds.length>0){
			List<DepositRatio> list = new ArrayList<DepositRatio>();
			for (String ratioId : ratioIds) {
				DepositRatio depositRatio = new DepositRatio();
				depositRatio.setId(Long.parseLong(ratioId));
				depositRatio.setStatus(DepositRatioStatusEnum.PUBLISH_FAILURE.getValue());
				list.add(depositRatio);
			}
			if(depositRatioService.update4Audit(list)>0){
				result.setResult(true);
			}
		}
		return result;
	}
}
 
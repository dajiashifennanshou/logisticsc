package com.brightsoft.controller.tms.platform.syssetting;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.common.constants.SystemConstant;
import com.brightsoft.controller.base.BaseController;
import com.brightsoft.controller.vo.PlatformBaseSearchParams;
import com.brightsoft.model.CustomWayBillCost;
import com.brightsoft.model.User;
import com.brightsoft.service.tms.platform.CustomWayBillCostService;

/**
 * 自定义运单费用Controller
 * @author yangshoubao
 *
 */
@Controller
@RequestMapping("/tms/waybillcost")
public class CustomWayBillCostController extends BaseController{

	@Autowired
	private CustomWayBillCostService customWayBillCostService;
	
	/**
	 * 跳转到TMS系统设置 自定义运单费用管理页面
	 * @return
	 */
	@RequestMapping("/tocustomwaybillcostpage")
	public String toTmsBasicDataPage(){
		return "/tms/platform/systemmanager/customwaybillcost/custom-way-bill-cost";
	}
	
	/**
	 * 获取自定义运单费用信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/getcustomwaybillcost")
	@ResponseBody
	public Object getCustomWayBillCost(HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		return customWayBillCostService.selectByCompanyId(user.getCompanyId());
	}
	
	/**
	 * 保存 自定义运单费用信息
	 * @param customWayBillCost
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(CustomWayBillCost customWayBillCost, HttpSession session){
		User user = (User)session.getAttribute(SystemConstant.TMS_USER_SESSION);
		customWayBillCost.setCompanyId(user.getCompanyId());
		int rows = customWayBillCostService.save(customWayBillCost);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(String ids){
		if(StringUtils.isEmpty(ids)){
			return FAILURE_STATUS;
		}
		int rows = customWayBillCostService.delete(ids);
		if(rows > 0){
			return SUCCESS_STATUS;
		}
		return FAILURE_STATUS;
	}
	
	/**
	 * 获取自定义运单费用(货运交易系统)
	 * @param params
	 * @return
	 */
	@RequestMapping("/getcustomwaybillcostofplatform")
	@ResponseBody
	public Object getCustomWayBillCostOfPaltform(PlatformBaseSearchParams params){
		return customWayBillCostService.selectByParamsOfPlatform(params);
	}
}

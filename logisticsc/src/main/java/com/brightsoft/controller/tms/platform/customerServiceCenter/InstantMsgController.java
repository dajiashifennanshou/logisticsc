package com.brightsoft.controller.tms.platform.customerServiceCenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/insmsg/")
public class InstantMsgController {

	@Autowired
	private PlatformUserCompanyService platformUserCompanyService;
	
	@RequestMapping("list")
	public String toListMsg(){
		return "/tms/platform/customerservicecenter/communicate";
	}
	
	@RequestMapping("searchLineCom")
	@ResponseBody
	public Result searchLineCompany(Page<?> page,PlatformUserCompany platformUserCompany){
		Result result = platformUserCompanyService.selectByCondition(page, platformUserCompany);
		result.setResult(true);
		return result;
	}
}

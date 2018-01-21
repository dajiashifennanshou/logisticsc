package com.brightsoft.controller.tms.system.MemberManage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.service.tms.system.CompanyService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Controller
@RequestMapping("/tms/sys/cmp")
public class LogisticsShopController {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/list")
	public String toShopList(){
		return "/tms/system/membermanage/listmemberinfo";
	}
	
	/**
	 * 查询企业信息
	 * @param page
	 * @param platformUserCompany
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Result search(Page<?> page,PlatformUserCompany platformUserCompany){
		Result result = new Result();
		result = companyService.selectByCondition(page, platformUserCompany);
		result.setResult(true);
		return result;
	}
	
	/**
	 * 禁用账号
	 * @param userId
	 * @return
	 */
	@RequestMapping("/forbid")
	@ResponseBody
	public Result forbidAccount(@RequestParam("userIds[]")String userIds[]){
		Result result = new Result();
		if(userIds!=null&&userIds.length!=0){
			List<Long> list = new ArrayList<Long>();
			for (String userId : userIds) {
				list.add(Long.parseLong(userId));
			}
			if(companyService.update2Forbid(list)>0){
				result.setResult(true);
			}
		}
		return result;
	}
}

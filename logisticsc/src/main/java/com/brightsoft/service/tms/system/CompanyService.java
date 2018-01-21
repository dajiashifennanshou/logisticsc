package com.brightsoft.service.tms.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.model.PlatformUserCompany;
import com.brightsoft.service.platform.PlatformUserCompanyService;
import com.brightsoft.service.platform.PlatformUserService;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

@Service
public class CompanyService {

	@Autowired
	private PlatformUserCompanyService platformUserCompanyService;
	
	@Autowired
	private PlatformUserService platformUserService;
	
	/**
	 * 查询企业信息
	 * @param page
	 * @param platformUserCompany
	 * @return
	 */
	public Result selectByCondition(Page<?> page,PlatformUserCompany platformUserCompany){
		return platformUserCompanyService.selectByCondition(page, platformUserCompany);
	}
	/**
	 * 禁用账号
	 * @param list
	 * @return
	 */
	public int update2Forbid(List<Long> list){
		return platformUserService.update2Forbid(list);
	}
}

package com.brightsoft.service.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformUserCompaninfoMapper;
import com.brightsoft.model.PlatformUserCompaninfo;

@Service
public class PlatformCompanyInfoServiceImpl {

	@Autowired
	private PlatformUserCompaninfoMapper platformUserCompanyinfoMapper;
	
	public PlatformUserCompaninfo selectByCompanyId(Long companyId){
		return platformUserCompanyinfoMapper.selectByCompanyId(companyId);
	}
}

package com.brightsoft.service.platform;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.InsuranceRateMapper;

@Service
public class InsuranceRateService{

	@Autowired
	private InsuranceRateMapper insuranceRateMapper;
	
	public HashMap<String, Object> selectByComTyTsTy(String insCompanyTag,String insTypeTag,String insTsTypeTag){
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(insTsTypeTag)){
			map.put("tsType", insuranceRateMapper.selectByComTyTsTY(insCompanyTag, null, insTsTypeTag));
		}
		map.put("type", insuranceRateMapper.selectByComTyTsTY(insCompanyTag, insTypeTag, null));
		return map;
	}

}

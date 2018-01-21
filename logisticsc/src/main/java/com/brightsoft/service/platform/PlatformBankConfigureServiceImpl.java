package com.brightsoft.service.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.platformBankConfigureMapper;
import com.brightsoft.model.platformBankConfigure;
import com.brightsoft.utils.DateTools;

@Service
public class PlatformBankConfigureServiceImpl {

	@Autowired
	private platformBankConfigureMapper platformBankConfigureMapper;
	
	public platformBankConfigure selectBankConfigure(){
		return platformBankConfigureMapper.selectPlatformBankInfo();
	}
	
	public boolean updateBankConfig(platformBankConfigure platformBankConfigure){
		boolean flag = false;
		platformBankConfigure.setTime(DateTools.getYMDHMS());
		if(platformBankConfigureMapper.updateByPrimaryKeySelective(platformBankConfigure)>0){
			flag = true;
		}
		return flag;
	}

}


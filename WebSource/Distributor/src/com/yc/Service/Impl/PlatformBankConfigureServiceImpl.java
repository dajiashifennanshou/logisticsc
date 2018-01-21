package com.yc.Service.Impl; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformBankConfigureDao;
import com.yc.Entity.PlatformBankConfigure;
import com.yc.Service.PlatformBankConfigureService; 
/** 
* LcPlatformBankConfigure服务层 
* Auther:FENG 
*/ 
@Service 
public class PlatformBankConfigureServiceImpl implements PlatformBankConfigureService { 

	@Autowired
	private PlatformBankConfigureDao iLcPlatformBankConfigureDao;

	@Override
	public PlatformBankConfigure getPlatformBankConfigure() {
		// TODO Auto-generated method stub
		return iLcPlatformBankConfigureDao.getSingleInfo(new PlatformBankConfigure());
	}

}

package com.yc.Service.Impl; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformBankAccountDao;
import com.yc.Entity.PlatformBankAccount;
import com.yc.Service.PlatformBankAccountService; 
/** 
* lc_platform_bank_account服务层 
* Auther:FENG 
*/ 
@Service 
public class PlatformBankAccountServiceImpl implements PlatformBankAccountService { 

	@Autowired
	private PlatformBankAccountDao bankAccountDao;

	@Override
	public Integer addBankAccount(PlatformBankAccount ba) {
		// TODO Auto-generated method stub
		return bankAccountDao.addSingleInfo(ba);
	}

	@Override
	public Integer updateBankAccount(PlatformBankAccount ba) {
		// TODO Auto-generated method stub
		return bankAccountDao.modSingleInfo(ba);
	}

	@Override
	public PlatformBankAccount getBankAccount(PlatformBankAccount ba) {
		// TODO Auto-generated method stub
		return bankAccountDao.getSingleInfo(ba);
	}
	
}

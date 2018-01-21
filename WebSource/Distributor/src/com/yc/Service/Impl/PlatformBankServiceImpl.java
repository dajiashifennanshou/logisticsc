package com.yc.Service.Impl; 
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformBankDao;
import com.yc.Entity.PlatformBank;
import com.yc.Service.PlatformBankService;
import com.yc.Tool.Pager; 
/** 
* LcPlatformBank服务层 
* Auther:FENG 
*/ 
@Service 
public class PlatformBankServiceImpl implements PlatformBankService { 

	@Autowired
	private PlatformBankDao iLcPlatformBankDao;

	@Override
	public Integer addSingleInfo(PlatformBank bank) {
		// TODO Auto-generated method stub
		return iLcPlatformBankDao.addSingleInfo(bank);
	}

	@Override
	public PlatformBank getPlatformBank(BigInteger userId) {
		// TODO Auto-generated method stub
		PlatformBank bank = new PlatformBank();
		bank.setUser_id(userId);
		return iLcPlatformBankDao.getSingleInfo(bank);
	}

	@Override
	public Integer updateSingleInfo(PlatformBank bank) {
		// TODO Auto-generated method stub
		return iLcPlatformBankDao.modSingleInfo(bank);
	}
}

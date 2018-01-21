package com.yc.Service.Impl; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.PlatformBankPaymentDao;
import com.yc.Entity.PlatformBankPayment;
import com.yc.Service.PlatformBankPaymentService; 
/** 
* lc_platform_bank_payment服务层 
* Auther:FENG 
*/ 
@Service 
public class PlatformBankPaymentServiceImpl implements PlatformBankPaymentService { 

	@Autowired
	private PlatformBankPaymentDao bankPaymentDao;

	@Override
	public Integer addBankPayment(PlatformBankPayment bp) {
		// TODO Auto-generated method stub
		return bankPaymentDao.addSingleInfo(bp);
	}

	@Override
	public PlatformBankPayment getBankPayment(PlatformBankPayment bp) {
		// TODO Auto-generated method stub
		return bankPaymentDao.getSingleInfo(bp);
	}

	@Override
	public Integer updateBankPayment(PlatformBankPayment bp) {
		// TODO Auto-generated method stub
		return bankPaymentDao.modSingleInfo(bp);
	}

}

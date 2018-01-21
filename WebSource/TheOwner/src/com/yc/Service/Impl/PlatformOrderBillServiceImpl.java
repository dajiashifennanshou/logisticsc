package com.yc.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.ILcPlatformOrderBillDao;
import com.yc.Entity.LcPlatformOrderBill;
import com.yc.Service.PlatformOrderBillService;
@Service
public class PlatformOrderBillServiceImpl implements PlatformOrderBillService {

	@Autowired
	private ILcPlatformOrderBillDao orderBillDao;
	
	@Override
	public Integer updateOrderBill(LcPlatformOrderBill bill) {
		// TODO Auto-generated method stub
		return orderBillDao.modSingleInfo(bill);
	}

}

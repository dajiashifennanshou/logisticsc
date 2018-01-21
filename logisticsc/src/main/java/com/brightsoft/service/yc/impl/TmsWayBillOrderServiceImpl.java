package com.brightsoft.service.yc.impl; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.yc.ITmsWayBillOrderDao;
import com.brightsoft.entity.TmsWayBillOrder;
import com.brightsoft.service.yc.ITmsWayBillOrderService; 
/** 
* TmsWayBillOrder服务层 
* Auther:FENG 
*/ 
@Service 
public class TmsWayBillOrderServiceImpl implements ITmsWayBillOrderService { 

	@Autowired
	private ITmsWayBillOrderDao iTmsWayBillOrderDao;
	
	public List<TmsWayBillOrder> getWayBillOrder(TmsWayBillOrder wbl) {
		// TODO Auto-generated method stub
		return iTmsWayBillOrderDao.getWayBillOrder(wbl);
	}
}

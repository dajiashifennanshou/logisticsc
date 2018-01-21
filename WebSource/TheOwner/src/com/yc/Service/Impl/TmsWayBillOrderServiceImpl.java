package com.yc.Service.Impl; 
import com.yc.Dao.TmsWayBillOrderDao;
import com.yc.Entity.TmsWayBillOrder;
import com.yc.Service.TmsWayBillOrderService;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager;

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;
import java.util.Map; 
/** 
* TmsWayBillOrder服务层 
* Auther:FENG 
*/ 
@Service 
public class TmsWayBillOrderServiceImpl implements TmsWayBillOrderService { 

	@Autowired
	private TmsWayBillOrderDao iTmsWayBillOrderDao;
	
	@Override
	public List<TmsWayBillOrder> getWayBillOrder(TmsWayBillOrder wbl) {
		// TODO Auto-generated method stub
		return iTmsWayBillOrderDao.getWayBillOrder(wbl);
	}
}

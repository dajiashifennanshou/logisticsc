package com.yc.Service.Impl; 
import com.yc.Dao.TmsWayBillOrderDao;
import com.yc.Entity.LcPlatformOrder;
import com.yc.Entity.TmsWayBillOrder;
import com.yc.Service.TmsWayBillOrderService;
import com.yc.Tool.DateUtil;
import com.yc.Tool.FengUtil;
import com.yc.Tool.Pager;

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
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
	public List<TmsWayBillOrder> getListWayBillOrder(TmsWayBillOrder wbl) {
		// TODO Auto-generated method stub
		return iTmsWayBillOrderDao.getListWayBillOrder(wbl);
	}
	/**
	 * 专线运输费用
	 * @Author:luojing
	 * @2016年7月5日 下午4:39:44
	 */
	public Pager<TmsWayBillOrder> getSpecialTransportation(Pager<TmsWayBillOrder> pager,Integer dealerId,String startTime,String endTime){
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dealerId", dealerId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("limitMax", pager.getLimitMax());
		map.put("limitMin", pager.getLimitMin());
		List<TmsWayBillOrder> list = iTmsWayBillOrderDao.getSpecialTransportation(map);
		for(int i=0;i<list.size();i++){
			list.get(i).setWay_bill_order_time(DateUtil.getTime2Long(list.get(i).getWay_bill_order_time()));
		}
		pager.setObjectList(list);
		return pager;
	}
	@Override
	public TmsWayBillOrder getWayBillOrder(String wbNumber) {
		// TODO Auto-generated method stub
		TmsWayBillOrder wb = new TmsWayBillOrder();
		wb.setWay_bill_number(wbNumber);
		return iTmsWayBillOrderDao.getWayBillOrder(wb);
	}
}

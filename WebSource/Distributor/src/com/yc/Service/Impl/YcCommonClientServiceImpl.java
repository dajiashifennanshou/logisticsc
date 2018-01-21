package com.yc.Service.Impl; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.Dao.IYcCommonClientDao;
import com.yc.Dao.IYcDeliveryOrderDao;
import com.yc.Entity.YcCommonClient;
import com.yc.Entity.YcDeliveryOrder;
import com.yc.Service.IYcCommonClientService;
import com.yc.Tool.Pager; 
/** 
* YcCommonClient服务层 
* Auther:FENG 
*/ 
@Service 
public class YcCommonClientServiceImpl implements IYcCommonClientService { 

	@Autowired
	private IYcCommonClientDao iYcCommonClientDao;
	@Autowired
	private IYcDeliveryOrderDao iYcDeliveryOrderDao;

	/** 
	* 获取分页记录 
	* Auther:FENG 
	*/ 
	@Override
	public Pager<YcCommonClient> getListPagerInfo(Pager<YcCommonClient> pager,YcCommonClient cc) {
		//TODO Auto-generated method stub
		cc.setType(0);
		Map<String,Object> map=pager.getElestMap(cc);
		List<YcCommonClient> list = new ArrayList<YcCommonClient>();
		for(YcCommonClient c:iYcCommonClientDao.getListPagerInfo(map)){
			YcDeliveryOrder order = new YcDeliveryOrder();
			order.setDealerId(c.getDealerId());
			order.setConsigneePhone(c.getPhone());
			order.setConsigneeName(c.getClientName());
			order.setConsigneeAddr(c.getAddress());
			List<YcDeliveryOrder> orderList= iYcDeliveryOrderDao.getDeliveryNo(order);
			c.setList(orderList);
			list.add(c);
		}
		pager.setObjectList(list);
		return pager;
	}
	
}

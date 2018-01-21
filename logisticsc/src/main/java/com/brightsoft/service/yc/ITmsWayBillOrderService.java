package com.brightsoft.service.yc; 
import java.util.List;

import com.brightsoft.entity.TmsWayBillOrder; 
/** 
* TmsWayBillOrder服务接口层 
* Auther:FENG 
*/ 
public interface ITmsWayBillOrderService{  

	/**
	 * 运单集合查询
	 * Author:luojing
	 * 2016年6月24日 下午3:57:13
	 */
	public List<TmsWayBillOrder> getWayBillOrder(TmsWayBillOrder wbl);
}

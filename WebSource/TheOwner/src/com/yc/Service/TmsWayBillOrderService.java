package com.yc.Service; 
import java.util.List;

import com.yc.Entity.TmsWayBillOrder;
import com.yc.Tool.ISqlServer; 
/** 
* TmsWayBillOrder服务接口层 
* Auther:FENG 
*/ 
public interface TmsWayBillOrderService{  

	/**
	 * 运单集合查询
	 * Author:luojing
	 * 2016年6月24日 下午3:57:13
	 */
	public List<TmsWayBillOrder> getWayBillOrder(TmsWayBillOrder wbl);
}

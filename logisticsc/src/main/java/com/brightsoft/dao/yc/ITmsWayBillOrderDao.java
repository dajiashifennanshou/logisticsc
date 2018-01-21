package com.brightsoft.dao.yc; 
import java.util.List;

import com.brightsoft.entity.TmsWayBillOrder;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* TmsWayBillOrder数据层 
* Auther:FENG 
*/ 
public interface ITmsWayBillOrderDao extends ISqlDao<TmsWayBillOrder> {  
	/**
	 * 运单集合查询
	 * Author:luojing
	 * 2016年6月24日 下午3:57:13
	 */
	public List<TmsWayBillOrder> getWayBillOrder(TmsWayBillOrder wbl);
}

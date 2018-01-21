package com.yc.Dao; 
import java.util.List;

import com.yc.Entity.TmsWayBillOrder;
import com.yc.Tool.ISqlDao; 
/** 
* TmsWayBillOrder数据层 
* Auther:FENG 
*/ 
public interface TmsWayBillOrderDao extends ISqlDao<TmsWayBillOrder> {  
	/**
	 * 运单集合查询
	 * Author:luojing
	 * 2016年6月24日 下午3:57:13
	 */
	public List<TmsWayBillOrder> getWayBillOrder(TmsWayBillOrder wbl);
}

package com.yc.Dao; 
import java.util.List;
import java.util.Map;

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
	public List<TmsWayBillOrder> getListWayBillOrder(TmsWayBillOrder wbl);
	/**
	 * 专线运输费用
	 * @Author:luojing
	 * @2016年7月5日 下午4:39:44
	 */
	public List<TmsWayBillOrder> getSpecialTransportation(Map<String,Object> map);
	
	/**
	 * 查询运单
	 * @Author:luojing
	 * @2016年8月12日 上午9:56:13
	 */
	public TmsWayBillOrder getWayBillOrder(TmsWayBillOrder wbl);
}

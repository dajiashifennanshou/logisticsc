package com.yc.Service; 
import java.util.List;

import com.yc.Entity.TmsWayBillOrder;
import com.yc.Tool.Pager;

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
	public List<TmsWayBillOrder> getListWayBillOrder(TmsWayBillOrder wbl);
	
	/**
	 * 查询运单
	 * @Author:luojing
	 * @2016年8月12日 上午9:56:13
	 */
	public TmsWayBillOrder getWayBillOrder(String wbNumber);
	
	/**
	 * 获取专线运输费用
	 * @Author:luojing
	 * @2016年8月12日 上午9:59:42
	 */
	public Pager<TmsWayBillOrder> getSpecialTransportation(Pager<TmsWayBillOrder> pager,Integer dealerId,String startTime,String endTime);
}

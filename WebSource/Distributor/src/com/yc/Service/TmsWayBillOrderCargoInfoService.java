package com.yc.Service; 


import com.yc.Entity.TmsWayBillOrderCargoInfo;
import com.yc.Tool.Pager;
/** 
* TmsWayBillOrderCargoInfo服务接口层 
* Auther:FENG 
*/ 
public interface TmsWayBillOrderCargoInfoService {  
	/**
	 * 货物信息分页查询
	 * Author:luojing
	 * 2016年6月24日 下午6:00:10
	 */
	public Pager<TmsWayBillOrderCargoInfo> getWayBillOrderCargoInfoPager(Pager<TmsWayBillOrderCargoInfo> pager,TmsWayBillOrderCargoInfo wbc);
}

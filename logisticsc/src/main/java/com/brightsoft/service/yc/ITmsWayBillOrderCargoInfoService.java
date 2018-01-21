package com.brightsoft.service.yc;

import com.brightsoft.entity.TmsWayBillOrderCargoInfo;
import com.brightsoft.utils.yc.Pager;

/** 
* TmsWayBillOrderCargoInfo服务接口层 
* Auther:FENG 
*/ 
public interface ITmsWayBillOrderCargoInfoService {  
	/**
	 * 货物信息分页查询
	 * Author:luojing
	 * 2016年6月24日 下午6:00:10
	 */
	public Pager<TmsWayBillOrderCargoInfo> getWayBillOrderCargoInfoPager(Pager<TmsWayBillOrderCargoInfo> pager,TmsWayBillOrderCargoInfo wbc);
}

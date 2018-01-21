package com.yc.Service;

import com.yc.Entity.LcPlatformOrderBill;

/**
 * 账单信息
 * @Author:luojing
 * @2016年8月17日 下午4:21:15
 */
public interface PlatformOrderBillService{  
	
	/**
	 * 修改支付状态
	 * @Author:luojing
	 * @2016年8月17日 下午4:23:14
	 */
	public Integer updateOrderBill(LcPlatformOrderBill bill);
}

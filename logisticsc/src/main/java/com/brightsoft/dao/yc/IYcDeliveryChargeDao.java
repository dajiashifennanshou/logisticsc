package com.brightsoft.dao.yc; 
import com.brightsoft.entity.YcDeliveryCharge;
import com.brightsoft.utils.yc.ISqlDao; 
/** 
* YcDeliveryCharge数据层 
* Auther:FENG 
*/ 
public interface IYcDeliveryChargeDao extends ISqlDao<YcDeliveryCharge> {  

	/**
	 * 根据车牌号，获取配送费用
	 * Author:FENG
	 * 2016年7月2日
	 * @param carNo
	 */
	public YcDeliveryCharge getDeliveryCostByCarNo(String carNo,String branchNo);
}

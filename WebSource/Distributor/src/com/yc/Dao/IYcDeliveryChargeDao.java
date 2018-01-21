package com.yc.Dao; 
import com.yc.Entity.YcDeliveryCharge;
import com.yc.Tool.ISqlDao; 
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

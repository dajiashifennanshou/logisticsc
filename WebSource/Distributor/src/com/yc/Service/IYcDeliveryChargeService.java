package com.yc.Service; 
import com.yc.Entity.YcDeliveryCharge; 
import com.yc.Tool.ISqlServer; 
/** 
* YcDeliveryCharge服务接口层 
* Auther:FENG 
*/ 
public interface IYcDeliveryChargeService/* extends ISqlServer<YcDeliveryCharge>*/ {  

	/**
	 * 根据车牌号，获取配送费用
	 * Author:FENG
	 * 2016年7月2日
	 * @param carNo
	 */
	public YcDeliveryCharge getDeliveryCostByCarNo(String carNo,String branchNo);
}

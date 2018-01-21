package com.brightsoft.service.yc; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.brightsoft.entity.YcDeliveryOrder;
import com.brightsoft.utils.yc.ISqlServer; 
/** 
* YcDeliveryOrder服务接口层 
* Auther:FENG 
*/ 
public interface IYcDeliveryOrderService extends ISqlServer<YcDeliveryOrder> {  

	/**
	 * 删除（修改评价状态）
	 * Author:luojing
	 * 2016年6月16日 上午11:15:24
	 */
	public Integer modEvaluateStatus(List<BigInteger> list);

	/**
	 * 根据订单号修改订单状态
	 * Author:FENG
	 * 2016年6月24日
	 * @param list
	 * @return
	 */
	public Integer modOrderByNo(YcDeliveryOrder s);
	/**
	 * 根据订单号修改订单状态
	 * Author:FENG
	 * 2016年6月24日
	 * @param list
	 * @return
	 */
	public Integer modOrderByStowageNo(YcDeliveryOrder s);
	/**
	 * 获取所有订单
	 * Author:FENG
	 * 2016年6月17日
	 * @return
	 */
	public List<YcDeliveryOrder> getYcDeliveryOrderAllList(YcDeliveryOrder deliveryOrder);
	public List<YcDeliveryOrder> getYcDeliveryOrderAllListByStatus(Map<String,String> map);
	
	public List<YcDeliveryOrder> getYcDeliveryOrderByStowage(String stowage);
	
	
	
	
	
	
}

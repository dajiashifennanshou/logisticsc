package com.yc.Service; 
import java.math.BigInteger;
import java.util.List;

import com.yc.Entity.TempGoodsInfo;
import com.yc.Entity.YcDeliveryOrder;
import com.yc.Tool.ISqlServer;
/** 
* YcDeliveryOrder服务接口层 
* Auther:FENG 
*/
import com.yc.Tool.Pager; 
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
	 * 获取所有订单
	 * Author:FENG
	 * 2016年6月17日
	 * @return
	 */
	public List<YcDeliveryOrder> getYcDeliveryOrderAllList(YcDeliveryOrder deliveryOrder);
	
	
	public List<YcDeliveryOrder> getYcDeliveryOrderByStowage(String stowage);
	
	/**
	 * 订单配送、安装费
	 * @Author:luojing
	 * @2016年7月5日 下午4:09:06
	 */
	public Pager<YcDeliveryOrder> getOrderCostList(Pager<YcDeliveryOrder> pager,YcDeliveryOrder deliveryOrder,String startTime,String endTime);
	
	/**
	 * 查询出库货物信息
	 * @Author:luojing
	 * @2016年7月5日 下午4:09:06
	 */
	public Pager<TempGoodsInfo> getPageOutStorageGoods(Pager<TempGoodsInfo> pager,BigInteger dealerId);
	
	/**
	 * 修改支付状态
	 * @Author:luojing
	 * @2016年8月10日 下午3:29:03
	 */
	public Integer updatePayStatus(YcDeliveryOrder order);
	
}

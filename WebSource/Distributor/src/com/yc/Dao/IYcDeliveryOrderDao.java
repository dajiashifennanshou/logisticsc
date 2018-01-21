package com.yc.Dao; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.yc.Entity.TempGoodsInfo;
import com.yc.Entity.YcDeliveryOrder;
import com.yc.Tool.ISqlDao;
import com.yc.Tool.Pager; 
/** 
* YcDeliveryOrder数据层 
* Auther:FENG 
*/ 
public interface IYcDeliveryOrderDao extends ISqlDao<YcDeliveryOrder> {  
	/**
	 * 删除（修改评价状态）
	 * Author:luojing
	 * 2016年6月16日 上午11:15:24
	 */
	public Integer modEvaluateStatus(List<BigInteger> list);
	/**
	 * 获取所有订单
	 * Author:FENG
	 * 2016年6月17日
	 * @return
	 */
	public List<YcDeliveryOrder> getYcDeliveryOrderAllList(YcDeliveryOrder deliveryOrder);
	/**
	 * 根据配载编号获取配送订单
	 * Author:FENG
	 * 2016年6月20日
	 * @param stowage
	 * @return
	 */
	public List<YcDeliveryOrder> getYcDeliveryOrderByStowage(String stowage);
	
	/**
	 * 根据订单号修改订单状态
	 * Author:FENG
	 * 2016年6月24日
	 * @param list
	 * @return
	 */
	public Integer modOrderByNo(YcDeliveryOrder s);
	
	/**
	 * 订单配送、安装费
	 * @Author:luojing
	 * @2016年7月5日 下午4:09:06
	 */
	public List<YcDeliveryOrder> getOrderCostList(Map<String,Object> map);
	
	/**
	 * 订单配送、安装费
	 * @Author:luojing
	 * @2016年7月5日 下午4:09:06
	 */
	public List<YcDeliveryOrder> getDeliveryOrderBy(Map<String,Object> map);
	
	/**
	 * 查询配送单号，根据客户手机号和经销商ID
	 * @Author:luojing
	 * @2016年7月5日 下午4:09:06
	 */
	public List<YcDeliveryOrder> getDeliveryNo(YcDeliveryOrder order);
	
	/**
	 * 添加常用联系人
	 * Author:FENG
	 * 2016年7月8日
	 * @param deliveryOrder
	 * @return
	 */
	public Integer addCustomer(YcDeliveryOrder deliveryOrder);
	
	/**
	 * 修改支付状态
	 * @Author:luojing
	 * @2016年8月10日 下午3:29:03
	 */
	public Integer updatePayStatus(YcDeliveryOrder order);
}

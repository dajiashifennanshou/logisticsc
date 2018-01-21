package com.brightsoft.dao.yc; 
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.brightsoft.entity.YcDeliveryOrder;
import com.brightsoft.utils.yc.ISqlDao;

/** 
* YcDeliveryOrder数据层 
* Auther:FENG 
*/ 
public interface IYcDeliveryOrderDao{  
	
	/**
	 * 根据条件获取单行数据
	 * Author:FENG
	 * 2016年5月11日
	 * @return
	 */
	public YcDeliveryOrder getSingleInfo(YcDeliveryOrder t);
	/**
	 * 获取总行数
	 * Author:FENG
	 * 2016年5月13日
	 * @return
	 */
	public Integer getSumCount(YcDeliveryOrder t);
	public List<YcDeliveryOrder> getYcDeliveryOrderAllListByStatus(Map<String,String> map);

	/**
	 * chax
	 * Author:FENG
	 * 2016年7月25日
	 * @param ycdeliveryorder
	 * @return
	 */
	public Integer  selectOrderIsOver(String stowageNo);
	/**
	 * 根据条件获取分页数据
	 * Author:FENG
	 * 2016年5月11日
	 * @param pager
	 * @param id
	 * @return
	 */
	public List<YcDeliveryOrder> getListPagerInfo(Map<String,Object> map);

	/**
	 * 根据条件删除信息
	 * Author:FENG
	 * 2016年5月11日
	 * @param id
	 * @return
	 */
	public Integer delSingleInfo(YcDeliveryOrder t);
	
	/**
	 * 根据条件删除选中ID（多条删除）
	 * Author:luojing
	 * 2016年6月13日 上午11:35:46
	 */
	public Integer delSelect(List<BigInteger> list);
	
	/**
	 * 根据条件修改信息
	 * Author:FENG
	 * 2016年5月11日
	 * @param t
	 * @return
	 */
	public Integer modSingleInfo(YcDeliveryOrder t);
	/**
	 * 添加对应信息
	 * Author:FENG
	 * 2016年5月11日
	 * @param t
	 * @return
	 */
	public Integer addSingleInfo(YcDeliveryOrder t);
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
	 * 根据订单号修改订单状态
	 * Author:FENG
	 * 2016年6月24日
	 * @param list
	 * @return
	 */
	public Integer modOrderByStowageNo(YcDeliveryOrder s);
	/**
	 * 添加常用联系人
	 * Author:FENG
	 * 2016年7月8日
	 * @param deliveryOrder
	 * @return
	 */
	public Integer addCustomer(YcDeliveryOrder deliveryOrder);
}

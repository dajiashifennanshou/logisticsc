package com.yc.Dao;

import java.util.List;
import java.util.Map;

import com.yc.Entity.LcPlatformOrder;
import com.yc.Entity.LcPlatformOrderAdditionalServer;
import com.yc.Entity.LcPlatformOrderCargo;
import com.yc.Tool.ISqlDao;
/**
 * 订单信息
 * @Author:luojing
 * @2016年7月5日 下午2:01:31
 */
public interface PlatformOrderDao extends ISqlDao<LcPlatformOrder> {
	/**
	 * 查询订单
	 * @Author:luojing
	 * @2016年7月5日 上午10:31:42
	 */
	public LcPlatformOrder getOrder(LcPlatformOrder order);
	
	/**
	 * 查询订订增值服务
	 * @Author:luojing
	 * @2016年7月5日 上午11:45:47
	 */
	public LcPlatformOrderAdditionalServer getOrderAdditionalServer(LcPlatformOrderAdditionalServer server);
	
	/**
	 * 订单货物信息
	 * @Author:luojing
	 * @2016年7月5日 上午11:57:09
	 */
	public List<LcPlatformOrderCargo> getOrderCargoList(LcPlatformOrderCargo cargo);
	
	public Integer getCount(Integer dealerId);
	
	/**
	 * 订单修改
	 * @Author:luojing
	 * @2016年8月12日 下午3:06:06
	 */
	public Integer updatePlatformOrder(LcPlatformOrder order);
	
}

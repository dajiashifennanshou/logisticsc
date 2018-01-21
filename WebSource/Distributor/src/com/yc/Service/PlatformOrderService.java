package com.yc.Service;

import java.util.List;

import com.yc.Entity.LcPlatformOrder;
import com.yc.Entity.LcPlatformOrderAdditionalServer;
import com.yc.Entity.LcPlatformOrderCargo;
/**
 * 订单信息
 * @Author:luojing
 * @2016年7月5日 下午2:02:10
 */
public interface PlatformOrderService {
	
	/**
	 * 查询订单号
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
	
	/**
	 * 订单修改
	 * @Author:luojing
	 * @2016年8月12日 下午3:06:06
	 */
	public Integer updatePlatformOrder(LcPlatformOrder order);
	
}

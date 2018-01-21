package com.yc.Service; 
import javax.servlet.http.HttpServletRequest;

import com.yc.Entity.LcPlatformOrderAdditionalServer;
import com.yc.Entity.PlatformOrder;
import com.yc.Entity.ResultEntity;
import com.yc.Tool.ISqlServer;
/** 
* LcPlatformOrder服务接口层 
* Auther:FENG 
*/
import com.yc.Tool.Pager; 
public interface PlatformOrderService extends ISqlServer<PlatformOrder>{  

	/**
	 * 添加货物
	 * Author:FENG
	 * 2016年8月5日
	 * @param request
	 * @param order
	 * @param cargos
	 * @return
	 */
	public ResultEntity addPlatFormOrder(HttpServletRequest request,PlatformOrder order,String orderAddServer,String cargos);
	
	/**
	 * 查询订单
	 * @Author:luojing
	 * @2016年8月8日 下午4:25:08
	 */
	public PlatformOrder getOrder(PlatformOrder order);
	
	/**
	 * 订单分页查询
	 * @Author:luojing
	 * @2016年8月8日 下午4:26:21
	 */
	public Pager<PlatformOrder> getPageOrder(Pager<PlatformOrder> pager,PlatformOrder order);
	
	/**
	 * 修改订单状态
	 * @Author:luojing
	 * @2016年8月17日 下午1:59:04
	 */
	public ResultEntity updateOrder(PlatformOrder order);
}

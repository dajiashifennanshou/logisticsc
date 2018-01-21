package com.brightsoft.service.platform;

import java.util.List;
import java.util.Map;

import com.brightsoft.model.PlatformMineBillInfo;
import com.brightsoft.model.PlatformOrderBill;
import com.brightsoft.utils.Page;

public interface PlatformOrderBillService {
	
	/**
	 * 分页获取账单信息
	 */
	public Page<?> selectBySelectedCondition(PlatformOrderBill orderBill, Page<?> page);
	
	/**
	 * 根据订单编号 查询账单信息
	 * @param orderNumber
	 * @return
	 */
	public PlatformOrderBill selectByOrderNumber(String orderNumber);
	/**
	 * 查看账单详情
	 * @param userId
	 * @param orderNumber
	 * @return
	 */
	public PlatformMineBillInfo selectMineBillInfo(Long userId,String orderNumber);
	/**
	 * 导出我的订单
	 * @return
	 */
	public List<Map<String, Object>> exportMineBillInfo(PlatformOrderBill orderBill);
	/**
	 * 修改是否已付金额
	 * @param orderNumber
	 * @return
	 */
	public boolean updateIsPayment(String orderNumber);
	
	public boolean updatePrepaidOst(Double prepaidCost,String orderNumber);
}

package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatformMineBillInfo;
import com.brightsoft.model.PlatformOrderBill;
import com.brightsoft.utils.Page;

public interface PlatformOrderBillMapper {
	
	int insert(PlatformOrderBill record);
	
	/**
	 * 查询筛选条件账单
	 * @param orderBill
	 * @param page
	 * @return
	 */
	public List<PlatformOrderBill> selectBySelectedCondition(@Param("orderBill") PlatformOrderBill orderBill,@Param("page")Page<?> page);
	/**
	 * 获取总数
	 * @param orderBill
	 * @return
	 */
    public int countRows(@Param("orderBill") PlatformOrderBill orderBill);
    
    /**
     * 根据订单编号 查询账单信息
     * @param orderNumber
     * @return
     */
    public PlatformOrderBill selectByOrderNumber(String orderNumber);
    /**
     * 查看订单详情
     * @param userId
     * @param orderNumber
     * @return
     */
    public PlatformMineBillInfo selectMineBillInfo(@Param("userId") Long userId,@Param("orderNumber") String orderNumber);
    /**
     * 导出我的账单数据
     * @param userId
     * @param id
     * @return
     */
    public List<PlatformMineBillInfo> selectMineBill(@Param("userId") Long userId,@Param("id") List<Long> id);
    /**
     * 修改账单状态
     * @param bill
     * @return
     */
    public int updateOrderBillState(PlatformOrderBill bill);
    /**
     * 导出账单ID
     * @param orderBill
     * @return
     */
    public List<Long> selectBill(@Param("orderBill") PlatformOrderBill orderBill);
    
    public int updateByOrderNumberSelective(PlatformOrderBill orderBill);
    
    public int updateIsPayment(@Param("orderNumber") String orderNumber);
    
    public int updatePrepaidOst(@Param("prepaidCost")Double prepaidCost,@Param("orderNumber") String orderNumber);
}
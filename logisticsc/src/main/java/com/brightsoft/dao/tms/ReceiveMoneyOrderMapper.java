package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.controller.vo.ReceiveMoneyOrderSearchParams;
import com.brightsoft.model.ReceiveMoneyOrder;

public interface ReceiveMoneyOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceiveMoneyOrder record);

    int insertSelective(ReceiveMoneyOrder record);

    ReceiveMoneyOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceiveMoneyOrder record);

    int updateByPrimaryKey(ReceiveMoneyOrder record);
    
    List<ReceiveMoneyOrder> selectByParams(ReceiveMoneyOrderSearchParams params);
    
    int selectByParamsCount(ReceiveMoneyOrderSearchParams params);
    
    ReceiveMoneyOrder selectByWayBillNumberAndCostType(@Param("wayBillNumber") String wayBillNumber, @Param("costType") Integer costType);

    ReceiveMoneyOrder selectByOrderNumber(String orderNumber);
    
    int updateByOrderNumberSelective(ReceiveMoneyOrder record);
    
    ReceiveMoneyOrder selectByOrderNumberAndOutletsId(@Param("orderNumber") String orderNumber, @Param("outletsId") Long outletsId);

    int getTotalNotTransfer(String loginName);
}
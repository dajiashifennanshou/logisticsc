package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.WayBillOrderCostInfo;

public interface WayBillOrderCostInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WayBillOrderCostInfo record);

    int insertSelective(WayBillOrderCostInfo record);

    WayBillOrderCostInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WayBillOrderCostInfo record);

    int updateByPrimaryKey(WayBillOrderCostInfo record);
    
    int batchInsert(List<WayBillOrderCostInfo> list);
    
    List<WayBillOrderCostInfo> selectByWayBillOrderId(Long wayBillOrderId);

	int deleteByWayBillOrderId(Long wayBillOrderId);
}
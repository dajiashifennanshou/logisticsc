package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.LadingOrderCostInfo;

public interface LadingOrderCostInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LadingOrderCostInfo record);

    int insertSelective(LadingOrderCostInfo record);

    LadingOrderCostInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LadingOrderCostInfo record);

    int updateByPrimaryKey(LadingOrderCostInfo record);
    
    int batchInsert(List<LadingOrderCostInfo> list);

	List<LadingOrderCostInfo> selectByWayBillNumber(String wayBillNumber);
}
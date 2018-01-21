package com.brightsoft.dao.tms;

import com.brightsoft.model.BaseWayBillCost;

public interface BaseWayBillCostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseWayBillCost record);

    int insertSelective(BaseWayBillCost record);

    BaseWayBillCost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseWayBillCost record);

    int updateByPrimaryKey(BaseWayBillCost record);
}
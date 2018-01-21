package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.WayBillOrderFollow;

public interface WayBillOrderFollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WayBillOrderFollow record);

    int insertSelective(WayBillOrderFollow record);

    WayBillOrderFollow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WayBillOrderFollow record);

    int updateByPrimaryKey(WayBillOrderFollow record);
    
    List<WayBillOrderFollow> selectByWayBillNumber(String wayBillNumber);
}
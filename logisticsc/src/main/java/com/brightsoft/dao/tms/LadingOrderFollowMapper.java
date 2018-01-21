package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.LadingOrderFollow;

public interface LadingOrderFollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LadingOrderFollow record);

    int insertSelective(LadingOrderFollow record);

    LadingOrderFollow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LadingOrderFollow record);

    int updateByPrimaryKey(LadingOrderFollow record);
    
    List<LadingOrderFollow> selectByWayBillNumber(String wayBillNumber);
}
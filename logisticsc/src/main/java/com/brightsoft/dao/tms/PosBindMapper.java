package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.PosBind;

public interface PosBindMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PosBind record);

    int insertSelective(PosBind record);

    PosBind selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PosBind record);

    int updateByPrimaryKey(PosBind record);
    
    PosBind selectNewestByOutletsIds(List<Long> list);
    
    List<PosBind> selectByOutletsId(Long outletsId);
    
    PosBind selectByPosSn(String posSn);
    
    PosBind selectByLoginAccount(String loginAccount);
}
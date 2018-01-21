package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.PosOrderRecord;

public interface PosOrderRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PosOrderRecord record);

    int insertSelective(PosOrderRecord record);

    PosOrderRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PosOrderRecord record);

    int updateByPrimaryKey(PosOrderRecord record);
    
    List<PosOrderRecord> selectByOrderNumber(String orderNumber);
}
package com.brightsoft.dao.tms;

import com.brightsoft.model.SignRecord;

public interface SignRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SignRecord record);

    int insertSelective(SignRecord record);

    SignRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SignRecord record);

    int updateByPrimaryKey(SignRecord record);
    
    SignRecord selectByWayBillNumber(String wayBillNumber);
}
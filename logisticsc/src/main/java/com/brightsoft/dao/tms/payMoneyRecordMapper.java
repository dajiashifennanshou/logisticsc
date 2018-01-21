package com.brightsoft.dao.tms;

import com.brightsoft.model.payMoneyRecord;

public interface payMoneyRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(payMoneyRecord record);

    int insertSelective(payMoneyRecord record);

    payMoneyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(payMoneyRecord record);

    int updateByPrimaryKey(payMoneyRecord record);
}
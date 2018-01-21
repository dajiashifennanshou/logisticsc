package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.ReceiveMoneyOrderRecord;

public interface ReceiveMoneyOrderRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceiveMoneyOrderRecord record);

    int insertSelective(ReceiveMoneyOrderRecord record);

    ReceiveMoneyOrderRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceiveMoneyOrderRecord record);

    int updateByPrimaryKey(ReceiveMoneyOrderRecord record);
    
    List<ReceiveMoneyOrderRecord> selectByReceiveMoneyOrderId(Long receiveMoneyOrderId);
}
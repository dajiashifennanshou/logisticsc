package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.ReceivablePayBillRecord;

public interface ReceivablePayBillRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceivablePayBillRecord record);

    int insertSelective(ReceivablePayBillRecord record);

    ReceivablePayBillRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceivablePayBillRecord record);

    int updateByPrimaryKey(ReceivablePayBillRecord record);
    
    public List<ReceivablePayBillRecord> selectByBillId(Long billId);
}
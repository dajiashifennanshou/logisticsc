package com.brightsoft.dao.tms;

import com.brightsoft.model.WayBillOutStoreRecord;

public interface WayBillOutStoreRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WayBillOutStoreRecord record);

    int insertSelective(WayBillOutStoreRecord record);

    WayBillOutStoreRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WayBillOutStoreRecord record);

    int updateByPrimaryKey(WayBillOutStoreRecord record);
    
    WayBillOutStoreRecord selectByWayBillOrderId(Long wayBillOrderId);
}
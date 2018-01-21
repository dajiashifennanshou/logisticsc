package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.WayBillTransferRecord;

public interface WayBillTransferRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WayBillTransferRecord record);

    int insertSelective(WayBillTransferRecord record);

    WayBillTransferRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WayBillTransferRecord record);

    int updateByPrimaryKey(WayBillTransferRecord record);
    
    int deleteByTransferDepartNumber(String transferDepartNumber);
    
    int batchInsert(List<WayBillTransferRecord> list);
    
    WayBillTransferRecord selectByWayBillNumber(String wayBillNumber);
}
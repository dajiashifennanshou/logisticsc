package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.WayBillOutSourceRecord;

public interface WayBillOutSourceRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WayBillOutSourceRecord record);

    int insertSelective(WayBillOutSourceRecord record);

    WayBillOutSourceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WayBillOutSourceRecord record);

    int updateByPrimaryKey(WayBillOutSourceRecord record);
    
    int batchInsert(List<WayBillOutSourceRecord> list);
    
    int deleteByOutDepartNumber(String outDepartNumber);
}
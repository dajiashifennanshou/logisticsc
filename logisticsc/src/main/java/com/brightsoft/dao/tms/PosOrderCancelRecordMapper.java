package com.brightsoft.dao.tms;

import com.brightsoft.model.PosOrderCancelRecord;

public interface PosOrderCancelRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PosOrderCancelRecord record);

    int insertSelective(PosOrderCancelRecord record);

    PosOrderCancelRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PosOrderCancelRecord record);

    int updateByPrimaryKey(PosOrderCancelRecord record);
}
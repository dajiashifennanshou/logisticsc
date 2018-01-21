package com.brightsoft.dao.tms;

import com.brightsoft.model.PosOrderSignRecord;

public interface PosOrderSignRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PosOrderSignRecord record);

    int insertSelective(PosOrderSignRecord record);

    PosOrderSignRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PosOrderSignRecord record);

    int updateByPrimaryKey(PosOrderSignRecord record);
}
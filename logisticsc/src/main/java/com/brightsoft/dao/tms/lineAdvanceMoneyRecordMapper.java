package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.lineAdvanceMoneyRecord;

public interface lineAdvanceMoneyRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(lineAdvanceMoneyRecord record);

    int insertSelective(lineAdvanceMoneyRecord record);

    lineAdvanceMoneyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(lineAdvanceMoneyRecord record);

    int updateByPrimaryKey(lineAdvanceMoneyRecord record);

	List<lineAdvanceMoneyRecord> selectByLineId(Long parseLong);
}
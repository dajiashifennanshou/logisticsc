package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.controller.vo.SearchParams;
import com.brightsoft.model.MoneyDiaryRecord;

public interface MoneyDiaryRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyDiaryRecord record);

    int insertSelective(MoneyDiaryRecord record);

    MoneyDiaryRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoneyDiaryRecord record);

    int updateByPrimaryKey(MoneyDiaryRecord record);
    
    int selectByParamsCount(SearchParams searchParams);
    
    List<MoneyDiaryRecord> selectByParams(SearchParams searchParams);
    
    double selectIncomeByParamsTotal(SearchParams searchParams);
    
    double selectExpendByParamsTotal(SearchParams searchParams);
}
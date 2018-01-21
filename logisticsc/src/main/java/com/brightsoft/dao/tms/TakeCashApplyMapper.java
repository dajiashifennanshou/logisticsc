package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.model.TakeCashApply;

public interface TakeCashApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TakeCashApply record);

    int insertSelective(TakeCashApply record);

    TakeCashApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TakeCashApply record);

    int updateByPrimaryKey(TakeCashApply record);
    
    List<TakeCashApply> selectByParams(BaseSearchParams params);
    
    int selectByParamsCount(BaseSearchParams params);
}
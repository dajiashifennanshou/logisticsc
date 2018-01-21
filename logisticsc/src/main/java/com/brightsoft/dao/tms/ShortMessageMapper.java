package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.controller.vo.BaseSearchParams;
import com.brightsoft.controller.vo.PlatformBaseSearchParams;
import com.brightsoft.model.ShortMessage;

public interface ShortMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShortMessage record);

    int insertSelective(ShortMessage record);

    ShortMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShortMessage record);

    int updateByPrimaryKey(ShortMessage record);
    
    List<ShortMessage> selectByParams(BaseSearchParams params);
    
    int selectByParamsCount(BaseSearchParams params);
    
    List<ShortMessage> selectByParamsOfPlatform(PlatformBaseSearchParams params);
    
    int selectByParamsCountOfPlatform(PlatformBaseSearchParams params);
}
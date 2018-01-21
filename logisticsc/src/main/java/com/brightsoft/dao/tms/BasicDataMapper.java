package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.controller.vo.PlatformBaseSearchParams;
import com.brightsoft.model.BasicData;

public interface BasicDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BasicData record);

    int insertSelective(BasicData record);

    BasicData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BasicData record);

    int updateByPrimaryKey(BasicData record);
    
    BasicData selectByCompanyId(Long companyId);
    
    List<BasicData> selectByParamsOfPlatform(PlatformBaseSearchParams params);
    
    int selectByParamsCountOfPlatform(PlatformBaseSearchParams params);
}
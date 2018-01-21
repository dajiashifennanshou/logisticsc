package com.brightsoft.dao.platform;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.PlatfoemBankSplitConfigure;

public interface PlatfoemBankSplitConfigureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatfoemBankSplitConfigure record);

    int insertSelective(PlatfoemBankSplitConfigure record);

    PlatfoemBankSplitConfigure selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatfoemBankSplitConfigure record);

    int updateByPrimaryKey(PlatfoemBankSplitConfigure record);
    
    public PlatfoemBankSplitConfigure selectSplitConfigure(@Param("record") PlatfoemBankSplitConfigure record);
}
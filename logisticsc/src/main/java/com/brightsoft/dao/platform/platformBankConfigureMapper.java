package com.brightsoft.dao.platform;

import com.brightsoft.model.platformBankConfigure;

public interface platformBankConfigureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformBankConfigure record);

    int insertSelective(platformBankConfigure record);

    platformBankConfigure selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformBankConfigure record);

    int updateByPrimaryKey(platformBankConfigure record);
    
    platformBankConfigure selectPlatformBankInfo();
    
}
package com.brightsoft.dao.platform;

import com.brightsoft.model.platformBondConfigure;

public interface platformBondConfigureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformBondConfigure record);

    int insertSelective(platformBondConfigure record);

    platformBondConfigure selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformBondConfigure record);

    int updateByPrimaryKey(platformBondConfigure record);
    
    public platformBondConfigure selectBond();
}
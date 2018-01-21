package com.brightsoft.dao.platform;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.platformBank;

public interface platformBankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(platformBank record);

    int insertSelective(platformBank record);

    platformBank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(platformBank record);

    int updateByPrimaryKey(platformBank record);
    
    public platformBank selectBankUserId(@Param("userId") Long userId);
}
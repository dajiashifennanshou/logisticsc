package com.brightsoft.dao.system;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.sysBank;

public interface sysBankMapper {
    int deleteByPrimaryKey(Long id);

    int insert(sysBank record);

    int insertSelective(sysBank record);

    sysBank selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(sysBank record);

    int updateByPrimaryKey(sysBank record);
    
    public sysBank selectSysuserId(@Param("sysUserId") Long sysUserId);
}
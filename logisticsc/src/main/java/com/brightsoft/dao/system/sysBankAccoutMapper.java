package com.brightsoft.dao.system;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.sysBankAccout;

public interface sysBankAccoutMapper {
    int deleteByPrimaryKey(Long id);

    int insert(sysBankAccout record);

    int insertSelective(sysBankAccout record);

    sysBankAccout selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(sysBankAccout record);

    int updateByPrimaryKey(sysBankAccout record);
    
    public sysBankAccout selectSysUserId(@Param("sysUserId") Long sysUserId);
    
    public sysBankAccout selectSysBank();
}
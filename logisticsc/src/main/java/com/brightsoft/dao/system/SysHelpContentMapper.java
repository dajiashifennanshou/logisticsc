package com.brightsoft.dao.system;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.SysHelpContent;

public interface SysHelpContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysHelpContent record);

    int insertSelective(SysHelpContent record);

    SysHelpContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysHelpContent record);

    int updateByPrimaryKeyWithBLOBs(SysHelpContent record);

    int updateByPrimaryKey(SysHelpContent record);
    
    SysHelpContent selectHelpContent(@Param("id")Long id);
}
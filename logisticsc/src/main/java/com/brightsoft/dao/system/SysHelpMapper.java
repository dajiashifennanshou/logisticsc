package com.brightsoft.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.SysHelp;

public interface SysHelpMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysHelp record);

    int insertSelective(SysHelp record);

    SysHelp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysHelp record);

    int updateByPrimaryKey(SysHelp record);
    
    public List<SysHelp> selectListSysyHelp();
    
    public List<SysHelp> selectHelps(Long id);
    
    public int updateHelp(@Param("id")Long id);
}
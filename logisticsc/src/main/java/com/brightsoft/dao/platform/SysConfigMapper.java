package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.SysConfig;

public interface SysConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKeyWithBLOBs(SysConfig record);

    int updateByPrimaryKey(SysConfig record);
    
    SysConfig getSysConfigByAbbr(@Param("itemAbbr")String itemAbbr);
    
    List<SysConfig> getSysConfig();
    
    int getSysConfigCount();
}
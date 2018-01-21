package com.yc.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yc.Entity.PlatformDictionary;
import com.yc.Tool.ISqlDao;


public interface PlatformDictionaryMapper extends ISqlDao<PlatformDictionary> {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformDictionary record);

    int insertSelective(PlatformDictionary record);

    PlatformDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformDictionary record);

    int updateByPrimaryKey(PlatformDictionary record);
    
    
    List<PlatformDictionary> selectDictDataByType(String type);
    
    PlatformDictionary selectByPrimaryId(@Param("id")Long id,@Param("type")String type);
    
    String selectCradId(@Param("id")Integer id,@Param("type")String type);
}
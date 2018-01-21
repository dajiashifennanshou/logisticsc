package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.BuiDictionary;
import com.brightsoft.model.PlatformDictionary;

public interface PlatformDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformDictionary record);

    int insertSelective(PlatformDictionary record);

    PlatformDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformDictionary record);

    int updateByPrimaryKey(PlatformDictionary record);
    
    List<BuiDictionary> selectDictDataByType4Bui(String type);
    
    List<PlatformDictionary> selectDictDataByType(String type);
    
    PlatformDictionary selectByPrimaryId(@Param("id")Long id,@Param("type")String type);
    
    String selectCradId(@Param("id")Integer id,@Param("type")String type);
}
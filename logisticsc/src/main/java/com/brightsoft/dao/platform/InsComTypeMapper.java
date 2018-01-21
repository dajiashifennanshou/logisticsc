package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.InsComType;

public interface InsComTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InsComType record);

    int insertSelective(InsComType record);

    InsComType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InsComType record);

    int updateByPrimaryKey(InsComType record);
    
    int insertBatch(@Param("list")List<InsComType> list);
    
    int deleteBatchByComIds(String[] comIds);
    
    int deleteBatchByTypeIds(String[] typeIds);
}
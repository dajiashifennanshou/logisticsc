package com.brightsoft.dao.platform;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.InsComTsType;

public interface InsComTsTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InsComTsType record);

    int insertSelective(InsComTsType record);

    InsComTsType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InsComTsType record);

    int updateByPrimaryKey(InsComTsType record);
    
    int insertBatch(List<InsComTsType> list);
    
    int deleteBatchByComIds(String[] comIds);
    
    int deleteBatchByTsTypeIds(String[] tsTypeIds);
}
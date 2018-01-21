package com.brightsoft.dao.tms;

import com.brightsoft.model.TransOrder;

public interface TransOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransOrder record);

    int insertSelective(TransOrder record);

    TransOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransOrder record);

    int updateByPrimaryKey(TransOrder record);
}
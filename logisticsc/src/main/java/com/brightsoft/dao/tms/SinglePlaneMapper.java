package com.brightsoft.dao.tms;

import com.brightsoft.model.SinglePlane;

public interface SinglePlaneMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SinglePlane record);

    int insertSelective(SinglePlane record);

    SinglePlane selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SinglePlane record);

    int updateByPrimaryKey(SinglePlane record);
}
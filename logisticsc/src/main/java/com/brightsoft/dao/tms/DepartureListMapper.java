package com.brightsoft.dao.tms;

import com.brightsoft.model.DepartureList;

public interface DepartureListMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DepartureList record);

    int insertSelective(DepartureList record);

    DepartureList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DepartureList record);

    int updateByPrimaryKey(DepartureList record);
}
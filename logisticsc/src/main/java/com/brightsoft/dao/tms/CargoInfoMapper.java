package com.brightsoft.dao.tms;

import com.brightsoft.model.CargoInfo;

public interface CargoInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CargoInfo record);

    int insertSelective(CargoInfo record);

    CargoInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CargoInfo record);

    int updateByPrimaryKey(CargoInfo record);
}
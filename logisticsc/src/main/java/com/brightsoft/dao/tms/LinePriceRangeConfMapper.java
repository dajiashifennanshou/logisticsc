package com.brightsoft.dao.tms;

import com.brightsoft.model.LinePriceRangeConf;

public interface LinePriceRangeConfMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LinePriceRangeConf record);

    int insertSelective(LinePriceRangeConf record);

    LinePriceRangeConf selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LinePriceRangeConf record);

    int updateByPrimaryKey(LinePriceRangeConf record);
}
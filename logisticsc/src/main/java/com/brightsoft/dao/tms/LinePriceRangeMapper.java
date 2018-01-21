package com.brightsoft.dao.tms;

import com.brightsoft.model.LinePriceRange;

public interface LinePriceRangeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LinePriceRange record);

    int insertSelective(LinePriceRange record);

    LinePriceRange selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LinePriceRange record);

    int updateByPrimaryKey(LinePriceRange record);
    
    
}
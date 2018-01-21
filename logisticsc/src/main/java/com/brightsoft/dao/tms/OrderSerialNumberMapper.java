package com.brightsoft.dao.tms;

import java.util.Map;

import com.brightsoft.model.OrderSerialNumber;

public interface OrderSerialNumberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderSerialNumber record);

    int insertSelective(OrderSerialNumber record);

    OrderSerialNumber selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderSerialNumber record);

    int updateByPrimaryKey(OrderSerialNumber record);
    
    OrderSerialNumber selectByParams(Map<String, Object> params);
}
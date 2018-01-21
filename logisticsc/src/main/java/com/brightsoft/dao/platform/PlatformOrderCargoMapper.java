package com.brightsoft.dao.platform;

import java.util.List;

import com.brightsoft.model.PlatformOrderCargo;

public interface PlatformOrderCargoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformOrderCargo record);

    int insertSelective(PlatformOrderCargo record);

    PlatformOrderCargo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformOrderCargo record);

    int updateByPrimaryKey(PlatformOrderCargo record);
    
    int batchInsert(List<PlatformOrderCargo> list);
    
    List<PlatformOrderCargo> selectByOrderId(long orderId);
    
    List<PlatformOrderCargo> selectByOrderNumber(String orderNumber);
    
    int deleteByOrderNumber(String orderNumber);
}
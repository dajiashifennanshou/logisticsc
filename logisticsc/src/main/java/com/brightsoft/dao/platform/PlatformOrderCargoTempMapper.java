package com.brightsoft.dao.platform;

import java.util.List;

import com.brightsoft.model.PlatformOrderCargoTemp;

public interface PlatformOrderCargoTempMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformOrderCargoTemp record);

    int insertSelective(PlatformOrderCargoTemp record);

    PlatformOrderCargoTemp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformOrderCargoTemp record);

    int updateByPrimaryKey(PlatformOrderCargoTemp record);
    
    int batchInsert(List<PlatformOrderCargoTemp> list);
    
    List<PlatformOrderCargoTemp> selectByOrderId(long orderId);
    
    List<PlatformOrderCargoTemp> selectByOrderNumber(String orderNumber);
}
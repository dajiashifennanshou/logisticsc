package com.brightsoft.dao.platform;

import com.brightsoft.model.PlatformOrderAdditionalServer;

public interface PlatformOrderAdditionalServerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformOrderAdditionalServer record);

    int insertSelective(PlatformOrderAdditionalServer record);

    PlatformOrderAdditionalServer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlatformOrderAdditionalServer record);

    int updateByPrimaryKey(PlatformOrderAdditionalServer record);
    
    PlatformOrderAdditionalServer selectByOrderId(long orderId);
    
    PlatformOrderAdditionalServer selectByOrderNumber(String orderNumber);
}
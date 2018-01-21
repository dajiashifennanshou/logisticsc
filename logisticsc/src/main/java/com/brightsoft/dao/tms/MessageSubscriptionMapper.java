package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.MessageSubscription;

public interface MessageSubscriptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageSubscription record);

    int insertSelective(MessageSubscription record);

    MessageSubscription selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageSubscription record);

    int updateByPrimaryKey(MessageSubscription record);
    
    /**
     * 获取消息订阅
     * @return
     */
    public List<MessageSubscription> selectMessageSubscription();
}
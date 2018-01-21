package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.MessageSubscriptionConf;

public interface MessageSubscriptionConfMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageSubscriptionConf record);

    int insertSelective(MessageSubscriptionConf record);

    MessageSubscriptionConf selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageSubscriptionConf record);

    int updateByPrimaryKey(MessageSubscriptionConf record);
    /**
     * 获取当前用户选中的消息订阅
     * @param userId
     * @return
     */
    public List<MessageSubscriptionConf> selectMessageSubscriptionConf(@Param("userId") Long userId);
    
    public int deleteMessageSubscriptionConf(@Param("subscriptionConfs") List<MessageSubscriptionConf> subscriptionConfs);
    
    public int insertMessageSubscriptionConf(@Param("subscriptionConfs") List<MessageSubscriptionConf> subscriptionConfs);
}
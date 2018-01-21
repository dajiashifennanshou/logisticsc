package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.ReceiveMoneyOrderRelation;

public interface ReceiveMoneyOrderRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceiveMoneyOrderRelation record);

    int insertSelective(ReceiveMoneyOrderRelation record);

    ReceiveMoneyOrderRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceiveMoneyOrderRelation record);

    int updateByPrimaryKey(ReceiveMoneyOrderRelation record);
    
    int batchInsert(List<ReceiveMoneyOrderRelation> list);
    
    List<ReceiveMoneyOrderRelation> selectByReceiveMoneyOrderId(Long receiveMoneyOrderId);
}
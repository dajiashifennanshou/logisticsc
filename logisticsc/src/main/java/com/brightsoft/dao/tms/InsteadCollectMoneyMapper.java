package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.InsteadCollectMoney;

public interface InsteadCollectMoneyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InsteadCollectMoney record);

    int insertSelective(InsteadCollectMoney record);

    InsteadCollectMoney selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InsteadCollectMoney record);

    int updateByPrimaryKey(InsteadCollectMoney record);
    
    List<InsteadCollectMoney> selectByWayBillNumber(String wayBillNumber);
}
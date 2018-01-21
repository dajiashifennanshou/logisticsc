package com.brightsoft.dao.tms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.brightsoft.model.receiveMoneyRecord;

public interface receiveMoneyRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(receiveMoneyRecord record);

    int insertSelective(receiveMoneyRecord record);

    receiveMoneyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(receiveMoneyRecord record);

    int updateByPrimaryKey(receiveMoneyRecord record);
    
    List<receiveMoneyRecord> selectByWayBillNumbers(@Param("wayBillNumbers") String[] wayBillNumbers);
}
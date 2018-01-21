package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.WayBillOrderCargoInfo;

public interface WayBillOrderCargoInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WayBillOrderCargoInfo record);

    int insertSelective(WayBillOrderCargoInfo record);

    WayBillOrderCargoInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WayBillOrderCargoInfo record);

    int updateByPrimaryKey(WayBillOrderCargoInfo record);
    
    int batchInsert(List<WayBillOrderCargoInfo> list);

	List<WayBillOrderCargoInfo> selectByWayBillOrderId(Long wayBillOrderId);
	
	int deleteByWayBillOrderId(Long wayBillOrderId);
}
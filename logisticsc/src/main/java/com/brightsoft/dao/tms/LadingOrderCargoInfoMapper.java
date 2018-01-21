package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.model.LadingOrderCargoInfo;

public interface LadingOrderCargoInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LadingOrderCargoInfo record);

    int insertSelective(LadingOrderCargoInfo record);

    LadingOrderCargoInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LadingOrderCargoInfo record);

    int updateByPrimaryKey(LadingOrderCargoInfo record);
    
    int batchInsert(List<LadingOrderCargoInfo> list);

	List<LadingOrderCargoInfo> selectByWayBillNumber(String wayBillNumber);
}
package com.brightsoft.dao.tms;

import java.util.List;

import com.brightsoft.controller.vo.PlatformBaseSearchParams;
import com.brightsoft.model.CustomWayBillCost;

public interface CustomWayBillCostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomWayBillCost record);

    int insertSelective(CustomWayBillCost record);

    CustomWayBillCost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomWayBillCost record);

    int updateByPrimaryKey(CustomWayBillCost record);

	List<CustomWayBillCost> selectByCompanyId(Long companyId);
	
	List<CustomWayBillCost> selectByParamsOfPlatform(PlatformBaseSearchParams params);
	
	int selectByParamsCountOfPlatform(PlatformBaseSearchParams params);
}
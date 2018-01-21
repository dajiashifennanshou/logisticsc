package com.brightsoft.service.tms.platform.startsitemanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.LadingOrderCostInfoMapper;
import com.brightsoft.model.LadingOrderCostInfo;

@Service
public class LadingOrderCostInfoService {
    
	@Autowired
	private LadingOrderCostInfoMapper ladingOrderCostInfoMapper;
	
	/**
	 * 根据运单号 查询提货单费用信息
	 * @param wayBillNumber
	 * @returno
	 */
	public List<LadingOrderCostInfo> selectByWayBillNumber(String wayBillNumber){
		return ladingOrderCostInfoMapper.selectByWayBillNumber(wayBillNumber);
	}
	
}
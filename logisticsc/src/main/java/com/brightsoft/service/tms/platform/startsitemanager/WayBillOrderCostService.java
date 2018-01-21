package com.brightsoft.service.tms.platform.startsitemanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.tms.WayBillOrderCostInfoMapper;
import com.brightsoft.model.WayBillOrderCostInfo;

/**
 * 运单费用 service
 * @author yangshoubao
 *
 */
@Service
public class WayBillOrderCostService {

	@Autowired
	private WayBillOrderCostInfoMapper wayBillOrderCostInfoMapper;
	
	/**
	 * 根据运单ID 查询运单货物信息
	 * @param wayBillOrderId
	 * @return
	 */
	public List<WayBillOrderCostInfo> selectByWayBillOrderId(Long wayBillOrderId){
		return wayBillOrderCostInfoMapper.selectByWayBillOrderId(wayBillOrderId);
	}
}

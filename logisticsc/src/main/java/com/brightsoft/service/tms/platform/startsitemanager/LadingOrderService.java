package com.brightsoft.service.tms.platform.startsitemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.PayModeEnum;
import com.brightsoft.dao.platform.XzqhInfoMapper;
import com.brightsoft.dao.tms.LadingOrderMapper;
import com.brightsoft.model.LadingOrder;

@Service
public class LadingOrderService {
    
	@Autowired
	private LadingOrderMapper ladingOrderMapper;
	
	@Autowired
	private XzqhInfoMapper xzqhInfoMapper;
	
	/**
	 * 根据运单号 查询提货单信息
	 * @param wayBillNumber
	 * @return
	 */
	public LadingOrder selectByWayBillNumber(String wayBillNumber){
		return ladingOrderMapper.selectByWayBillNumber(wayBillNumber);
	}
	
	public LadingOrder selectById(Long id){
		LadingOrder ladingOrder = ladingOrderMapper.selectByPrimaryKey(id);
		ladingOrder.setTargetProvinceName(xzqhInfoMapper.selectByPrimaryKey(ladingOrder.getTargetProvince()).getName());
		ladingOrder.setTargetCityName(xzqhInfoMapper.selectByPrimaryKey(ladingOrder.getTargetCity()).getName());
		ladingOrder.setTargetCountyName(xzqhInfoMapper.selectByPrimaryKey(ladingOrder.getTargetCounty()).getName());
		PayModeEnum[] payModeEnums = PayModeEnum.values();
		for (PayModeEnum payModeEnum : payModeEnums) {
			if(ladingOrder.getPayMethod() == payModeEnum.getValue()){
				ladingOrder.setPayMethodName(payModeEnum.getName());
			}
		}
		return ladingOrder;
	}
	
}
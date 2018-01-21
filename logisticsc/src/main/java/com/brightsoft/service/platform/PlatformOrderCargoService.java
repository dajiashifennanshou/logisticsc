package com.brightsoft.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.CountCostModeEnum;
import com.brightsoft.dao.platform.PlatformOrderCargoMapper;
import com.brightsoft.model.PlatformOrderCargo;

/**
 * 货运交易系统订单货物service
 */
@Service
public class PlatformOrderCargoService {

	@Autowired
	private PlatformOrderCargoMapper orderCargoMapper;
	
	/**
	 * 根据订单ID 查询货运交易系统订单货物信息
	 * @param orderId
	 * @return
	 */
	public List<PlatformOrderCargo> selectByOrderId(long orderId){
		return orderCargoMapper.selectByOrderId(orderId);
	}
	
	public List<PlatformOrderCargo> selectByOrderNumber(String orderNumber){
		List<PlatformOrderCargo> orderCargos = orderCargoMapper.selectByOrderNumber(orderNumber);
		for (PlatformOrderCargo orderCargo : orderCargos) {
			CountCostModeEnum[] enums = CountCostModeEnum.values();
			for (CountCostModeEnum modeEnum : enums) {
				if(orderCargo.getCountCostMode() == modeEnum.getValue()){
					orderCargo.setCountCostModeName(modeEnum.getName());
				}
			}
			// 计算总重量，总体积
			orderCargo.setTotalWeight(orderCargo.getSingleWeight());
			orderCargo.setTotalVolume(orderCargo.getSingleVolume());
		}
		return orderCargos;
	}
}

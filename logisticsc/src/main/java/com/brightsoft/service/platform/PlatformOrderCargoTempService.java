package com.brightsoft.service.platform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.common.enums.CountCostModeEnum;
import com.brightsoft.dao.platform.PlatformOrderCargoTempMapper;
import com.brightsoft.model.PlatformOrderCargoTemp;

/**
 * 货运交易系统订单货物service
 */
@Service
public class PlatformOrderCargoTempService {

	@Autowired
	private PlatformOrderCargoTempMapper orderCargoTempMapper;
	
	/**
	 * 根据订单ID 查询货运交易系统订单货物信息
	 * @param orderId
	 * @return
	 */
	public List<PlatformOrderCargoTemp> selectByOrderId(long orderId){
		return orderCargoTempMapper.selectByOrderId(orderId);
	}
	
	public List<PlatformOrderCargoTemp> selectByOrderNumber(String orderNumber){
		List<PlatformOrderCargoTemp> orderCargos = orderCargoTempMapper.selectByOrderNumber(orderNumber);
		for (PlatformOrderCargoTemp orderCargo : orderCargos) {
			CountCostModeEnum[] enums = CountCostModeEnum.values();
			for (CountCostModeEnum modeEnum : enums) {
				if(orderCargo.getCountCostMode() == modeEnum.getValue()){
					orderCargo.setCountCostModeName(modeEnum.getName());
				}
			}
			// 计算总重量，总体积
			Integer number = orderCargo.getNumber();
			if(number == null){
				number = orderCargo.getSetNumber();
			}
			orderCargo.setTotalWeight(orderCargo.getSingleWeight() * number);
			orderCargo.setTotalVolume(orderCargo.getSingleVolume() * number);
		}
		return orderCargos;
	}
}

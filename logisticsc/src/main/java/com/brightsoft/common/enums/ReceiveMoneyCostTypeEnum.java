package com.brightsoft.common.enums;

/**
 * 收款 订单 费用类型
 * @author yangshoubao
 *
 */
public enum ReceiveMoneyCostTypeEnum {

	FREIGHT("运费", 0),
	AGENCY_FUND("代收款", 1);
	
	private String name;
	
	private Integer value;
	
	ReceiveMoneyCostTypeEnum(String name, Integer value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
	
	
}

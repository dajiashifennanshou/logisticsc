package com.brightsoft.common.enums;

/**
 * 货运交易系统订单状态
 * @author yangshoubao
 *
 */
public enum ReceiveMoneyTypeEnum {

	CASH_RECEIVE_MONEY("现金收款", 0),
	POS_RECEIVE_MONEY("POS机收款", 1);
	
	private String name;
	
	private Integer value;
	
	ReceiveMoneyTypeEnum(String name, Integer value){
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

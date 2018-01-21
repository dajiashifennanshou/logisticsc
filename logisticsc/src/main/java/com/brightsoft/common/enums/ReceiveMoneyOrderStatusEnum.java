package com.brightsoft.common.enums;

/**
 * 货运交易系统订单状态
 * @author yangshoubao
 *
 */
public enum ReceiveMoneyOrderStatusEnum {

	NOT_FINISH("未完结", 0),
	FINISHED("已完结", 1),
	CANCELED("已撤销", 2);
	
	private String name;
	
	private Integer value;
	
	ReceiveMoneyOrderStatusEnum(String name, Integer value){
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

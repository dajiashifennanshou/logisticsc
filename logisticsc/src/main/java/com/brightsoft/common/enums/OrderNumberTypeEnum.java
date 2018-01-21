package com.brightsoft.common.enums;

/**
 * 单号 类型
 * @author yangshoubao
 *
 */
public enum OrderNumberTypeEnum {

	PLATFORM_ORDER_NUMBER("货运交易系统订单号", 0),
	WAY_BILL_NUMBER("运单号", 1),
	DEPART_ORDER_NUMBER("发车单号",2),
	OUT_SOURCE_DEPART_ORDER_NUMBER("外包发车单号",3),
	TRANSFER_DEPART_ORDER_NUMBER("中转发车单号",4),
	RECEIVE_MONEY_ORDER_NUMBER("收款订单号",5);
	
	private String name;
	
	private Integer value;
	
	OrderNumberTypeEnum(String name, Integer value){
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

package com.brightsoft.common.enums;
/**
 * 保证金支付记录
 * @author ThinkPad
 *
 */
public enum PaymentTypeEnum {
	BALANCEPAY("余额支付",0),
	ONLINEPAY("在线支付",1);
	
	private String name;
	
	private Integer value;
	
	PaymentTypeEnum(String name, Integer value){
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

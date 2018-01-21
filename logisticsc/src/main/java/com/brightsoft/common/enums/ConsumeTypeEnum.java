package com.brightsoft.common.enums;

public enum ConsumeTypeEnum {

	RECHARGE("充值",0),
	TRANSFER_ACCOUNT("转账",1),
	TAKE_CASH("提现", 2),
	INSURE("投保",3);
	
	private String name;
		
	private Integer value;
		
	ConsumeTypeEnum(String name, Integer value){
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

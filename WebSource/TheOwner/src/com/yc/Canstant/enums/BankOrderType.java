package com.yc.Canstant.enums;

public enum BankOrderType {
	INSURANCE("保险",0),
	APPOINTMENT("预约",1),
	FREIGHT("运费",2),
	BOND("保证金",3),
	INSTALL("安装配送",4);
	
	private String name;
	
	private Integer value;
		
	BankOrderType(String name, Integer value){
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

package com.brightsoft.common.enums;

/**
 * 运单签收状态
 * @author yangshoubao
 *
 */
public enum WayBillSIgnStatusEnum {

	NOT_SIGN("未签收", 0),
	ALREADY_SIGN("已签收", 1);
	
	private String name;
	
	private Integer value;
	
	WayBillSIgnStatusEnum(String name, Integer value){
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

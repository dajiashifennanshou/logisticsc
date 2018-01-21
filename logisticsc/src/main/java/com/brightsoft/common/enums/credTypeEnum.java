package com.brightsoft.common.enums;
/**
 * 用户证件类型
 * @author ThinkPad
 *
 */
public enum credTypeEnum {
	
	ID("身份证",0);
	
	private String name;
	
	private Integer value;
		
	credTypeEnum(String name, Integer value){
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

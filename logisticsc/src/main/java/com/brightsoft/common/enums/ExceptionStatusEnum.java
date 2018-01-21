package com.brightsoft.common.enums;

/**
 * 异常状态
 * @author yangshoubao
 *
 */
public enum ExceptionStatusEnum {

	NO("无", 0),
	REGISTER("异常登记", 1),
	HANDLER("异常处理",2);
	
	private String name;
	
	private Integer value;
	
	ExceptionStatusEnum(String name, Integer value){
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

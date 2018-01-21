package com.brightsoft.common.enums;

/**
 * 货运交易系统异常环节
 * @author yangshoubao
 *
 */
public enum PublishTypeEnum {

	ENTERPRISE("企业", 0),
	PLATFOMR("系统", 1);
	
	private String name;
	
	private Integer value;
	
	PublishTypeEnum(String name, Integer value){
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

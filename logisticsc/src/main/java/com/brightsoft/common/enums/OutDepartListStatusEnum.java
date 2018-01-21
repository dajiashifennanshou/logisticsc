package com.brightsoft.common.enums;

/**
 * 发车清单状态
 * @author yangshoubao
 *
 */
public enum OutDepartListStatusEnum {

	LADING("配载中", 0),
	ALREADY_OUT_SOURCE("已外包",1),
	DISCARDED("已作废",2);
	
	private String name;
	
	private Integer value;
	
	OutDepartListStatusEnum(String name, Integer value){
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

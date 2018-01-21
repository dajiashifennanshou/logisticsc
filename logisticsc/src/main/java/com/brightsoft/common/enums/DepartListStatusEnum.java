package com.brightsoft.common.enums;

/**
 * 发车清单状态
 * @author yangshoubao
 *
 */
public enum DepartListStatusEnum {

	LADING("配载中", 0),
	TRANSPORTING("运输中", 1),
	ALREADY_ARRIVED("已到站",2),
	UNLOADED("已卸货",3),
	ABOLISHED("已作废",4);
	
	private String name;
	
	private Integer value;
	
	DepartListStatusEnum(String name, Integer value){
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

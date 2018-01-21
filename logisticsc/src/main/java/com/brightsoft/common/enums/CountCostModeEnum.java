package com.brightsoft.common.enums;

/**
 * 货物计费方式
 * @author yangshoubao
 *
 */
public enum CountCostModeEnum {

	WEIGHT("按重量", 0),
	VOLUME("按体积", 1),
	PIECE("按件数",2),
	PACKAGE("按套数",3);
	
	private String name;
	
	private Integer value;
	
	CountCostModeEnum(String name, Integer value){
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

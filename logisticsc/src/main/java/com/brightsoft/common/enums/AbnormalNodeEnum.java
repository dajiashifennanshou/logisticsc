package com.brightsoft.common.enums;

/**
 * 货运交易系统异常环节
 * @author yangshoubao
 *
 */
public enum AbnormalNodeEnum {

	PICK_CARGO("提货", 0),
	STOWAGE("配载", 1),
	TRANSPORT_WAY("途中",2),
	ARRIVE_IN("到站",3),
	DELIVERY("配送",4),
	TRANSTER("中转",5),
	OUTSOURCING("外包",6);
	
	private String name;
	
	private Integer value;
	
	AbnormalNodeEnum(String name, Integer value){
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

package com.brightsoft.common.enums;

/**
 * 配送方式
 * @author yangshoubao
 *
 */
public enum ReceiveTypeEnum {

	/** 自提 */
	SELF_PICK("自提", 0),

	/** 送货上门 */
	HOME_DELIVERY("送货上门", 1),
	
	/** 中转 */
	CONNECTION("中转",2);
	
	private String name;
	
	private Integer value;
	
	ReceiveTypeEnum(String name, Integer value){
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

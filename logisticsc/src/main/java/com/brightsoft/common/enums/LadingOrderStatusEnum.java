package com.brightsoft.common.enums;

/**
 * 提货单状态
 * @author yangshoubao
 *
 */
public enum LadingOrderStatusEnum {

	NO_DISPATCH_VEHICLE("未派车", 0),
	ALREADY_DISPATCH_VEHICLE("已派车", 1),
	ALREADY_STORAGE("已开单入库",2),
	ALREADY_ABOLISH("已作废",3);
	
	private String name;
	
	private Integer value;
	
	LadingOrderStatusEnum(String name, Integer value){
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

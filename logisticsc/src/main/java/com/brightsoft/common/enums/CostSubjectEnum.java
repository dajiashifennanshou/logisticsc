package com.brightsoft.common.enums;

public enum CostSubjectEnum {
	
	FREIGHT("运费"),
	AGENCY_FUND("代收货款"),
	OUT_SOURCE_COST("外包费"),
	TRANSFER_COST("中转费"),
	DRIVER_FREIGHT("司机运费"),
	MIX_COST("杂费");
	
	private String name;
	
	CostSubjectEnum(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

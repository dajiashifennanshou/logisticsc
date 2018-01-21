package com.brightsoft.common.enums;

public enum InsuranceStatusEnum {

	saved("已保存",0),
	checking("审核中",1),
	takeEffect("已生效",2),
	cancellationInsurance("已作废",3);
	
	
	private String name;
	
	private Integer value;
	
	InsuranceStatusEnum(String name, Integer value){
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

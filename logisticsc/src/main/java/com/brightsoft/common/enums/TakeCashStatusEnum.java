package com.brightsoft.common.enums;

/**
 * 发车清单状态
 * @author yangshoubao
 *
 */
public enum TakeCashStatusEnum {

	AUDITING("审核中", 0),
	AUDIT_PASS("审核通过", 1),
	AUDIT_NOT_PASS("审核不通过",2);
	
	private String name;
	
	private Integer value;
	
	TakeCashStatusEnum(String name, Integer value){
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

package com.brightsoft.common.enums;

/**
 * 发车清单状态
 * @author yangshoubao
 *
 */
public enum DepositRatioStatusEnum {

	NO_PUBLISH("未发布", 1),
	AUDITING("审核中", 2),
	PUBLISHED("已发布",3),
	PUBLISH_FAILURE("发布失败",4),
	EXPIRED("已过期",5);
	
	private String name;
	
	private Integer value;
	
	DepositRatioStatusEnum(String name, Integer value){
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

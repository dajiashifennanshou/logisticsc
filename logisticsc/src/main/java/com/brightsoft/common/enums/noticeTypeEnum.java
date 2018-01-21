package com.brightsoft.common.enums;

public enum noticeTypeEnum {
	
	MESSAGE_NOTICE("短信通知",0),
	MAIL_NOTICE("邮箱通知",1);
	
	private String name;
	
	private Integer value;
	
	noticeTypeEnum(String name, Integer value){
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

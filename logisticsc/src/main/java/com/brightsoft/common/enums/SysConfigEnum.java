package com.brightsoft.common.enums;

public enum SysConfigEnum {

	YANGGUAN("阳光保险注意事项","yangguan"),
	TAIPINGYANG("太平洋保险注意事项","taipingyang"),
	RENBAO("太平洋保险注意事项","renbao"),
	PINGAN("太平洋保险注意事项","pingan"),
	ZHONGAN("太平洋保险注意事项","zhongan");
	
	private String name;
	private String value;
	private SysConfigEnum(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}


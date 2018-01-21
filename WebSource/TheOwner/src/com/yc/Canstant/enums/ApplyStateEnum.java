package com.yc.Canstant.enums;
/**
 * 用户申请状态
 * @author ThinkPad
 *
 */
public enum ApplyStateEnum {
	
	APPLYING("申请中",0),
	APPLYSUCESS("申请成功",100);
	
	private String name;
	
	private Integer value;
		
	ApplyStateEnum(String name, Integer value){
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

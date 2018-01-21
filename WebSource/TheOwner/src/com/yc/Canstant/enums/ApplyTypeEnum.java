package com.yc.Canstant.enums;
/**
 * 用户申请类型
 * @author ThinkPad
 *
 */
public enum ApplyTypeEnum {
	
	ENTERPRISEOWNER("企业货主", 0),
	LOGISTICSPROVIDERS("物流提供商",1),
	SALESMAN("专线",2),
	MODIFY("修改资质",3);
	private String name;
		
	private Integer value;
		
	ApplyTypeEnum(String name, Integer value){
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

package com.brightsoft.common.enums;

/**
 * 回单状态
 * @author yangshoubao
 *
 */
public enum ReceiptStatusEnum {

	/** 上线已寄出 */
	UP_LINE_MAILED("上线已寄出", 0),
	
	/** 下线已接收 */
	DOWN_LINE_RECEIVED("下线已接收", 1),
	
	/** 下线已寄出 */
	PART_START_VEHICLE("下线已寄出",2),
	
	/** 上线已接收 */
	UP_LINE_RECEIVED("上线已接收",3),
	
	/** 客户已签收 */
	SIGNED("客户已签收",4),
	
	/** 不寄 */
	NOT_MAIL("不寄",5);
	
	private String name;
	
	private Integer value;
	
	ReceiptStatusEnum(String name, Integer value){
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

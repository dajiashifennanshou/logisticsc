package com.brightsoft.common.enums;

/**
 * 运单状态
 * @author yangshoubao
 *
 */
public enum WayBillOrderStatusEnum {

	/** 新开单 */
	NEW_WAY_BILL_ORDER("新开单", 0),
	
	/** 已发车 */
	ALREADY_START_VEHICLE("已发车", 1),
	
	/** 部分发车 */
	PART_START_VEHICLE("部分发车",2),
	
	/** 已到货 */
	ALREADY_REACH_CARGO("已到货",3),
	
	/** 部分到货 */
	PART_REACH_CARGO("部分到货",4),
	
	/** 已卸货 */
	ALREADY_DISCHARGE("已卸货",5),
	
	/** 部分卸货 */
	PART_DISCHARGE("部分卸货",6),
	
	/** 已外包 */
	ALREADY_OUT_SOURCE("已外包",7),
	
	/** 已签收 */
	RECEIVED("已签收",8),
	
	/** 已作废 */
	DISCARDED("已作废",9),
	
	/** 已关闭 */
	CLOSE("已关闭",10);
	
	private String name;
	
	private Integer value;
	
	WayBillOrderStatusEnum(String name, Integer value){
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

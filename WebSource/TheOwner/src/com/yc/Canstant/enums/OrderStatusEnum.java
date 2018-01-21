package com.yc.Canstant.enums;

/**
 * 平台订单状态
 * @author yangshoubao
 *
 */
public enum OrderStatusEnum {

	WAIT_ACCEPT("预约", 0),
	ALREADY_ACCEPT("已受理", 1),
	TAkING_CARGO("提货中",2),
	CONFIRM_CARGO_INFO("议价", 3),
	CARGO_STORED("货物入库",4),
	TRANSPORTING("运输中",5),
	ARRIVED("已到达",6),
	DELIVERING("送货中",7),
	RECEIVED("已签收",8),
	REFUSED("已拒绝",9),
	DISCARDED("已作废",10),
	CANCEL("已取消",11);
	private String name;
	
	private Integer value;
	
	OrderStatusEnum(String name, Integer value){
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

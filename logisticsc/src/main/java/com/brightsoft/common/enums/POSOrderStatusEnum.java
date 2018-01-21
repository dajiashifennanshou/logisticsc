package com.brightsoft.common.enums;

public enum POSOrderStatusEnum {
	NO_ORDER("20","查无此单"),
	SIGNED("21","订单已签收"),
	PAYED_UNDIGN("22","已收款，未签收"),
	UNPAYED_UNDIGN("23","未支付，未签收"),
	AUTOMATIC("81","自定义文本内容");
	
	private String OrderStatus;
	private String OrderStatusMsg;
	
	public String getOrderStatus() {
		return OrderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}

	public String getOrderStatusMsg() {
		return OrderStatusMsg;
	}

	public void setOrderStatusMsg(String orderStatusMsg) {
		OrderStatusMsg = orderStatusMsg;
	}

	private POSOrderStatusEnum(String orderStatus, String orderStatusMsg) {
		OrderStatus = orderStatus;
		OrderStatusMsg = orderStatusMsg;
	}

}

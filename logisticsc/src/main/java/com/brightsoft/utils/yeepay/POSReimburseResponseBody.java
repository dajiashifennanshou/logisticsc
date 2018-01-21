package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;

public class POSReimburseResponseBody extends POSBody{

	public String getOrderNo() {
		return OrderNo;
	}
	
	@XmlElement(name = "OrderNo")
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getReferNo() {
		return ReferNo;
	}

	@XmlElement(name = "ReferNo")
	public void setReferNo(String referNo) {
		ReferNo = referNo;
	}

	private String OrderNo;//
	private String ReferNo;//交易参考号
}

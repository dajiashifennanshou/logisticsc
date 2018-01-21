package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;

public class POSSignRequestBody extends POSBody{

	
	public String getEmployeeID() {
		return EmployeeID;
	}
	
	@XmlElement(name = "EmployeeID")
	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	
	@XmlElement(name = "OrderNo")
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	
	public String getPosSn() {
		return PosSn;
	}
	
	@XmlElement(name = "PosSn")
	public void setPosSn(String posSn) {
		PosSn = posSn;
	}



	public String getAmount() {
		return Amount;
	}

	@XmlElement(name = "Amount")
	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getReferNo() {
		return ReferNo;
	}

	@XmlElement(name = "ReferNo")
	public void setReferNo(String referNo) {
		ReferNo = referNo;
	}

	public String getBankCardNo() {
		return BankCardNo;
	}

	@XmlElement(name = "BankCardNo")
	public void setBankCardNo(String bankCardNo) {
		BankCardNo = bankCardNo;
	}
	
	

	
	private String EmployeeID;//登录号
	private String PosSn;//机器sn码
	private String OrderNo;//
	private String Amount;//金额
	private String ReferNo;//交易参考号
	private String BankCardNo;//
}

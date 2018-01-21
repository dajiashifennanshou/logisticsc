package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;

public class POSOrderRequestBody extends POSBody{

	
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
	public String getCustomerNo() {
		return CustomerNo;
	}
	
	@XmlElement(name = "CustomerNo")
	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}
	private String EmployeeID;//登录号
	private String OrderNo;//密码
	private String PosSn;//机器sn码
	private String CustomerNo;//易宝商编
}

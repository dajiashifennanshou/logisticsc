package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class POSLoginRequestBody{

	public String getEmployee_ID() {
		return employee_ID;
	}
	
	@XmlElement(name = "Employee_ID")
	public void setEmployee_ID(String employee_ID) {
		this.employee_ID = employee_ID;
	}
	public String getPassword() {
		return password;
	}
	
	@XmlElement(name = "Password")
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPosSn() {
		return posSn;
	}
	
	@XmlElement(name = "PosSn")
	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	
	@XmlElement(name = "CustomerNo")
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	private String employee_ID;//登录号
	private String password;//密码
	private String posSn;//机器sn码
	private String customerNo;//易宝商编
}

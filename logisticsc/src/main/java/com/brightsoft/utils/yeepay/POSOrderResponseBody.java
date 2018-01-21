package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;

public class POSOrderResponseBody extends POSBody{

	
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
	
	public String getCompanyCode() {
		return CompanyCode;
	}

	@XmlElement(name = "CompanyCode")
	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	public String getCompanyTel() {
		return CompanyTel;
	}

	@XmlElement(name = "CompanyTel")
	public void setCompanyTel(String companyTel) {
		CompanyTel = companyTel;
	}

	public String getCompanyAddr() {
		return CompanyAddr;
	}

	@XmlElement(name = "CompanyAddr")
	public void setCompanyAddr(String companyAddr) {
		CompanyAddr = companyAddr;
	}

	public String getReceiverOrderNo() {
		return ReceiverOrderNo;
	}

	@XmlElement(name = "ReceiverOrderNo")
	public void setReceiverOrderNo(String receiverOrderNo) {
		ReceiverOrderNo = receiverOrderNo;
	}

	public String getReceiverName() {
		return ReceiverName;
	}

	@XmlElement(name = "ReceiverName")
	public void setReceiverName(String receiverName) {
		ReceiverName = receiverName;
	}

	public String getRceiverTel() {
		return RceiverTel;
	}

	@XmlElement(name = "RceiverTel")
	public void setRceiverTel(String rceiverTel) {
		RceiverTel = rceiverTel;
	}

	public String getAmount() {
		return Amount;
	}

	@XmlElement(name = "Amount")
	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getOrderStatus() {
		return OrderStatus;
	}

	@XmlElement(name = "OrderStatus")
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
	
	public String getPaDetails() {
		return PaDetails;
	}

	@XmlElement(name = "PaDetails")
	public void setPaDetails(String paDetails) {
		PaDetails = paDetails;
	}

	public String getPcAutoSplit() {
		return PcAutoSplit;
	}

	@XmlElement(name = "PcAutoSplit")
	public void setPcAutoSplit(String pcAutoSplit) {
		PcAutoSplit = pcAutoSplit;
	}

	private String EmployeeID;//登录号
	private String CompanyCode;//公司
	private String CompanyTel;//
	private String CompanyAddr;//
	private String OrderNo;//密码
	private String ReceiverOrderNo;//同orderNo
	private String ReceiverName;//收件人姓名
	private String RceiverTel;//收件人电话
	private String Amount;//金额
	private String OrderStatus;//订单状态
	private String PosSn;//机器sn码
	private String CustomerNo;//易宝商编
	private String PaDetails;//分账规则
	private String PcAutoSplit;//是否自动分账
}

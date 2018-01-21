package com.brightsoft.utils.yeepay;

import javax.xml.bind.annotation.XmlElement;

public class POSPayRequestBody extends POSBody{

	
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
	
	public String getPosRequestID() {
		return PosRequestID;
	}

	@XmlElement(name = "PosRequestID")
	public void setPosRequestID(String posRequestID) {
		PosRequestID = posRequestID;
	}

	public String getPayTypeCode() {
		return PayTypeCode;
	}

	@XmlElement(name = "PayTypeCode")
	public void setPayTypeCode(String payTypeCode) {
		PayTypeCode = payTypeCode;
	}

	public String getPayMethod() {
		return PayMethod;
	}

	@XmlElement(name = "PayMethod")
	public void setPayMethod(String payMethod) {
		PayMethod = payMethod;
	}

	public String getChequeNo() {
		return ChequeNo;
	}

	@XmlElement(name = "ChequeNo")
	public void setChequeNo(String chequeNo) {
		ChequeNo = chequeNo;
	}

	public String getBankCardName() {
		return BankCardName;
	}

	@XmlElement(name = "BankCardName")
	public void setBankCardName(String bankCardName) {
		BankCardName = bankCardName;
	}

	public String getBankCardType() {
		return BankCardType;
	}

	@XmlElement(name = "BankCardType")
	public void setBankCardType(String bankCardType) {
		BankCardType = bankCardType;
	}

	public String getBankOrderNo() {
		return BankOrderNo;
	}

	@XmlElement(name = "BankOrderNo")
	public void setBankOrderNo(String bankOrderNo) {
		BankOrderNo = bankOrderNo;
	}

	public String getYeepayOrderNo() {
		return YeepayOrderNo;
	}

	@XmlElement(name = "YeepayOrderNo")
	public void setYeepayOrderNo(String yeepayOrderNo) {
		YeepayOrderNo = yeepayOrderNo;
	}

	public String getCustomerNo() {
		return CustomerNo;
	}

	@XmlElement(name = "CustomerNo")
	public void setCustomerNo(String customerNo) {
		CustomerNo = customerNo;
	}

	public String getPosMenue() {
		return PosMenue;
	}

	@XmlElement(name = "PosMenue")
	public void setPosMenue(String posMenue) {
		PosMenue = posMenue;
	}

	private String EmployeeID;//登录号
	private String PosSn;//机器sn码
	private String OrderNo;//
	private String Amount;//金额
	private String PosRequestID;//凭证号
	private String ReferNo;//交易参考号
	private String PayTypeCode;//支付方式标识 1 银行卡 2 储值卡 3 微信支付
	private String PayMethod;//支付类型(1 整单支付 2 拆单支付)
	private String ChequeNo;//支票号
	private String BankCardNo;//银行卡号(中间会有星号)
	private String BankCardName;//发卡行名称
	private String BankCardType;//银行卡类型 DEBIT_CARD:借记卡,CREDIT_CARD:贷记卡 PREPAID_CARD:预付费卡,SEMI_CREDIT_CARD:准贷记卡
	private String BankOrderNo;//银行订单号
	private String YeepayOrderNo;//易宝订单号
	private String CustomerNo;//易宝商编
	private String PosMenue;//
}

package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单分账表
 * @author ThinkPad
 *
 */
public class platformVoSplitOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//订单
	private String orderNumber;
	
	private Integer orderState;
	
	//订单消费记录
	private String paymentRequestid;
	
	private String paymentAmount;
	
	private Integer paymentOrderType;
	
	private Date paymentTime;
	
	//货运交易系统用户
	private String platformLoginName;
	
	//专线营运系统用户
	private String tmsLoginName;
	
	//网点名称
	private String outletsName;
	
	//公司信息
	private String companyCode;
	
	private String companyName;
	
	//订单分账
	private List<SplitState> splitStateList;
	
	//子账户
	private String ledgerno;
	
	//查询条件
	private String startTime;
	
	private String endTime;
	
	private String bankaccountnumber;
	
	private Integer tmsUserId;
	
	private String amount;
	
	
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getTmsUserId() {
		return tmsUserId;
	}

	public void setTmsUserId(Integer tmsUserId) {
		this.tmsUserId = tmsUserId;
	}

	public String getBankaccountnumber() {
		return bankaccountnumber;
	}

	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLedgerno() {
		return ledgerno;
	}

	public void setLedgerno(String ledgerno) {
		this.ledgerno = ledgerno;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public String getPaymentRequestid() {
		return paymentRequestid;
	}

	public void setPaymentRequestid(String paymentRequestid) {
		this.paymentRequestid = paymentRequestid;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Integer getPaymentOrderType() {
		return paymentOrderType;
	}

	public void setPaymentOrderType(Integer paymentOrderType) {
		this.paymentOrderType = paymentOrderType;
	}

	public String getPlatformLoginName() {
		return platformLoginName;
	}

	public void setPlatformLoginName(String platformLoginName) {
		this.platformLoginName = platformLoginName;
	}

	public String getTmsLoginName() {
		return tmsLoginName;
	}

	public void setTmsLoginName(String tmsLoginName) {
		this.tmsLoginName = tmsLoginName;
	}

	public String getOutletsName() {
		return outletsName;
	}

	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<SplitState> getSplitStateList() {
		return splitStateList;
	}

	public void setSplitStateList(List<SplitState> splitStateList) {
		this.splitStateList = splitStateList;
	}
	
}

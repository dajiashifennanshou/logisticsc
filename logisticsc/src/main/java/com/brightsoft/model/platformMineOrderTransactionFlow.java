package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class platformMineOrderTransactionFlow implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String orderNumber;

    private String wayBillNumber;

    private String flowNumber;

    private Double transactionMoney;

    private Date time;

    private String chargeUser;

    private String payUser;
	
	 private String startProvince;

	 private String startCity;
	 
	 private String startCounty;
	    
	 private String endProvince;

	 private String endCity;
	 
	 private String endCounty;
	 
	 private String startTime;//起始时间
	    
	 private String endTime;//结束时间
	    
	 private String condition;//查询输入条件
	 
	 

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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}

	public String getFlowNumber() {
		return flowNumber;
	}

	public void setFlowNumber(String flowNumber) {
		this.flowNumber = flowNumber;
	}

	public Double getTransactionMoney() {
		return transactionMoney;
	}

	public void setTransactionMoney(Double transactionMoney) {
		this.transactionMoney = transactionMoney;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getChargeUser() {
		return chargeUser;
	}

	public void setChargeUser(String chargeUser) {
		this.chargeUser = chargeUser;
	}

	public String getPayUser() {
		return payUser;
	}

	public void setPayUser(String payUser) {
		this.payUser = payUser;
	}

	public String getStartProvince() {
		return startProvince;
	}

	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getStartCounty() {
		return startCounty;
	}

	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
	}

	public String getEndProvince() {
		return endProvince;
	}

	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public String getEndCounty() {
		return endCounty;
	}

	public void setEndCounty(String endCounty) {
		this.endCounty = endCounty;
	}
}

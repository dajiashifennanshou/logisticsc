package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 代收货款
 * @author ThinkPad
 *
 */
public class PlatformCollectionPayment implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String orderNumber;

    private String wayBillNumber;
    
    private String consignorName;
    
    private String consigneeName;
    
    private Double agencyFund;

    private Double receivedFund;

    private Double uncollectedFund;

    private Date operateTime;
    
    private Long userId;

    private Integer state;
    
    private String condition; //订单号/运单号
    
    private String startTime; //开始时间
    
    private String endTime; //结束时间
    
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getConsignorName() {
		return consignorName;
	}

	public void setConsignorName(String consignorName) {
		this.consignorName = consignorName;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public Double getAgencyFund() {
		return agencyFund;
	}

	public void setAgencyFund(Double agencyFund) {
		this.agencyFund = agencyFund;
	}

	public Double getReceivedFund() {
		return receivedFund;
	}

	public void setReceivedFund(Double receivedFund) {
		this.receivedFund = receivedFund;
	}

	public Double getUncollectedFund() {
		return uncollectedFund;
	}

	public void setUncollectedFund(Double uncollectedFund) {
		this.uncollectedFund = uncollectedFund;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	    
}

package com.brightsoft.model;
import java.io.Serializable;
import java.util.Date;

public class PlatformMineOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String orderNumber;

	 private String wayBillNumber;

	 private String startProvince;

	 private String startCity;
	 
	 private String startCounty;
	    
	 private String endProvince;

	 private String endCity;
	 
	 private String endCounty;
	 
	 private String companyName;
	 
	 private Double totalWeight;

	 private Double totalVolume;
	 
	 private Double advancePrice;

	 private Double finalPrice;
	 
	 private Double paidPrice;
	 
	 private Date orderTime;
	 
	 private String state;
	 
	 private String startTime;//起始时间
	    
	 private String endTime;//结束时间
	    
	 private String condition;//查询输入条件
	 
	 private String conditionType;
	 
	 private Long userId;
	 
	 private String orderTimeStr;
	 
	 private Integer isEvaluation;
	 
	 private Integer isComplain;
	 
    private Double estimateWeight;
    
    private Double estimateVolume;
    
    private Double estimateFreight;
    
    private Integer isConfirm;
	 
    private Double totalCost;
    
    private Integer billState;
    
    private Double takeCargoCost;
    
    private Double prepaidCost;
    
    private Integer isDraft;
    
    private Double estimateTotal;
    
    private Integer billPayment;
    
    private Integer isPayment;
    
    private Integer receiveCargoType;
    private Integer sendCargoType;
    
    private Integer payType;
    
    
    
    
	public Integer getSendCargoType() {
		return sendCargoType;
	}

	public void setSendCargoType(Integer sendCargoType) {
		this.sendCargoType = sendCargoType;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getReceiveCargoType() {
		return receiveCargoType;
	}

	public void setReceiveCargoType(Integer receiveCargoType) {
		this.receiveCargoType = receiveCargoType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getBillPayment() {
		return billPayment;
	}

	public void setBillPayment(Integer billPayment) {
		this.billPayment = billPayment;
	}

	public Integer getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(Integer isPayment) {
		this.isPayment = isPayment;
	}

	public Double getEstimateTotal() {
		return estimateTotal;
	}

	public void setEstimateTotal(Double estimateTotal) {
		this.estimateTotal = estimateTotal;
	}

	public Integer getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}

	public Double getTakeCargoCost() {
		return takeCargoCost;
	}

	public void setTakeCargoCost(Double takeCargoCost) {
		this.takeCargoCost = takeCargoCost;
	}

	public Double getPrepaidCost() {
		return prepaidCost;
	}

	public void setPrepaidCost(Double prepaidCost) {
		this.prepaidCost = prepaidCost;
	}

	public Integer getBillState() {
		return billState;
	}

	public void setBillState(Integer billState) {
		this.billState = billState;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getEstimateWeight() {
		return estimateWeight;
	}

	public void setEstimateWeight(Double estimateWeight) {
		this.estimateWeight = estimateWeight;
	}

	public Double getEstimateVolume() {
		return estimateVolume;
	}

	public void setEstimateVolume(Double estimateVolume) {
		this.estimateVolume = estimateVolume;
	}

	public Double getEstimateFreight() {
		return estimateFreight;
	}

	public void setEstimateFreight(Double estimateFreight) {
		this.estimateFreight = estimateFreight;
	}

	public Integer getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Integer isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Integer getIsEvaluation() {
		return isEvaluation;
	}

	public void setIsEvaluation(Integer isEvaluation) {
		this.isEvaluation = isEvaluation;
	}

	public Integer getIsComplain() {
		return isComplain;
	}

	public void setIsComplain(Integer isComplain) {
		this.isComplain = isComplain;
	}

	public Double getPaidPrice() {
		return paidPrice;
	}

	public void setPaidPrice(Double paidPrice) {
		this.paidPrice = paidPrice;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public String getOrderTimeStr() {
		return orderTimeStr;
	}

	public void setOrderTimeStr(String orderTimeStr) {
		this.orderTimeStr = orderTimeStr;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Double getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(Double totalVolume) {
		this.totalVolume = totalVolume;
	}

	public Double getAdvancePrice() {
		return advancePrice;
	}

	public void setAdvancePrice(Double advancePrice) {
		this.advancePrice = advancePrice;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		//this.setOrderTimeStr(DateTools.dateToStr(orderTime));
		this.orderTime = orderTime;
	}
	 
}

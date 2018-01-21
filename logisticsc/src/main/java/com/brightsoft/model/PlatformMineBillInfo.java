package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 我的账单 详情
 * @author ThinkPad
 *
 */
public class PlatformMineBillInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String orderNumber;

    private String wayBillNumber;

    private String consignorName;

    private String consignorProvince;

    private String consignorCity;

    private String consignorCounty;
    
    private String consignorPhoneNumber;

    private String consigneeName;

    private String consigneeProvince;

    private String consigneeCity;

    private String consigneeCounty;

    private String consigneePhoneNumber;
    
    private Double finalPrice;
    
    private Double freight;
    
    private Integer state;
    
    private Date payDate;
    
    private Double addedServiceCharge;

    private Double insurance;
    
    private Double totalCost;

    private Double prepaidCost;

    private Double otherCost;
    
    private String loginName;
    
    private Date createTime;
    
    private Double takeCargoCost;
    
    private Double sendCargoCost;
    
    private Double loadCargoCost;
    
    private Double unloadCargoCost;
    
    private Double orderCost;
    
    private Double agencyFundPoundage;
    
    private Double estimateTakeCargoCost;
    
    private Double estimateSendCargoCost;
    
    private Double estimateLoadCargoCost;
    
    private Double estimateUnloadCargoCost;
    
    private Double estimateFreight;

    private Double estimateTotalCost;
    
    
	public Double getAgencyFundPoundage() {
		return agencyFundPoundage;
	}

	public void setAgencyFundPoundage(Double agencyFundPoundage) {
		this.agencyFundPoundage = agencyFundPoundage;
	}

	public Double getEstimateTakeCargoCost() {
		return estimateTakeCargoCost;
	}

	public void setEstimateTakeCargoCost(Double estimateTakeCargoCost) {
		this.estimateTakeCargoCost = estimateTakeCargoCost;
	}

	public Double getEstimateSendCargoCost() {
		return estimateSendCargoCost;
	}

	public void setEstimateSendCargoCost(Double estimateSendCargoCost) {
		this.estimateSendCargoCost = estimateSendCargoCost;
	}

	public Double getEstimateLoadCargoCost() {
		return estimateLoadCargoCost;
	}

	public void setEstimateLoadCargoCost(Double estimateLoadCargoCost) {
		this.estimateLoadCargoCost = estimateLoadCargoCost;
	}

	public Double getEstimateUnloadCargoCost() {
		return estimateUnloadCargoCost;
	}

	public void setEstimateUnloadCargoCost(Double estimateUnloadCargoCost) {
		this.estimateUnloadCargoCost = estimateUnloadCargoCost;
	}

	public Double getEstimateTotalCost() {
		return estimateTotalCost;
	}

	public void setEstimateTotalCost(Double estimateTotalCost) {
		this.estimateTotalCost = estimateTotalCost;
	}

	public void setOrderCost(Double orderCost) {
		this.orderCost = orderCost;
	}

	public void setEstimateFreight(Double estimateFreight) {
		this.estimateFreight = estimateFreight;
	}

	public double getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(double orderCost) {
		this.orderCost = orderCost;
	}

	public double getEstimateFreight() {
		return estimateFreight;
	}

	public void setEstimateFreight(double estimateFreight) {
		this.estimateFreight = estimateFreight;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getTakeCargoCost() {
		return takeCargoCost;
	}

	public void setTakeCargoCost(Double takeCargoCost) {
		this.takeCargoCost = takeCargoCost;
	}

	public Double getSendCargoCost() {
		return sendCargoCost;
	}

	public void setSendCargoCost(Double sendCargoCost) {
		this.sendCargoCost = sendCargoCost;
	}

	public Double getLoadCargoCost() {
		return loadCargoCost;
	}

	public void setLoadCargoCost(Double loadCargoCost) {
		this.loadCargoCost = loadCargoCost;
	}

	public Double getUnloadCargoCost() {
		return unloadCargoCost;
	}

	public void setUnloadCargoCost(Double unloadCargoCost) {
		this.unloadCargoCost = unloadCargoCost;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getConsignorProvince() {
		return consignorProvince;
	}

	public void setConsignorProvince(String consignorProvince) {
		this.consignorProvince = consignorProvince;
	}

	public String getConsignorCity() {
		return consignorCity;
	}

	public void setConsignorCity(String consignorCity) {
		this.consignorCity = consignorCity;
	}

	public String getConsignorCounty() {
		return consignorCounty;
	}

	public void setConsignorCounty(String consignorCounty) {
		this.consignorCounty = consignorCounty;
	}

	public String getConsignorPhoneNumber() {
		return consignorPhoneNumber;
	}

	public void setConsignorPhoneNumber(String consignorPhoneNumber) {
		this.consignorPhoneNumber = consignorPhoneNumber;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeProvince() {
		return consigneeProvince;
	}

	public void setConsigneeProvince(String consigneeProvince) {
		this.consigneeProvince = consigneeProvince;
	}

	public String getConsigneeCity() {
		return consigneeCity;
	}

	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}

	public String getConsigneeCounty() {
		return consigneeCounty;
	}

	public void setConsigneeCounty(String consigneeCounty) {
		this.consigneeCounty = consigneeCounty;
	}

	public String getConsigneePhoneNumber() {
		return consigneePhoneNumber;
	}

	public void setConsigneePhoneNumber(String consigneePhoneNumber) {
		this.consigneePhoneNumber = consigneePhoneNumber;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Double getAddedServiceCharge() {
		return addedServiceCharge;
	}

	public void setAddedServiceCharge(Double addedServiceCharge) {
		this.addedServiceCharge = addedServiceCharge;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getPrepaidCost() {
		return prepaidCost;
	}

	public void setPrepaidCost(Double prepaidCost) {
		this.prepaidCost = prepaidCost;
	}

	public Double getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(Double otherCost) {
		this.otherCost = otherCost;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
    
    
    
}

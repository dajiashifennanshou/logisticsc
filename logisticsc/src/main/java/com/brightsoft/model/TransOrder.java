package com.brightsoft.model;

import java.io.Serializable;

public class TransOrder implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long ladingId;

    private Long orderId;

    private Long startOutlets;

    private Long targetOutlets;

    private String targetProvince;

    private String targetCity;

    private String targetCounty;

    private String consignor;

    private String consignorCompany;

    private String consignorMobile;

    private String consignorAddress;

    private String consignee;

    private String consigneeCompany;

    private String consigneeMobile;

    private String consigneeAddress;

    private String distributionMode;

    private Integer receiptSlip;

    private Integer receiptSlipNum;

    private Long singlePlaneCostId;

    private String payMethod;

    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLadingId() {
        return ladingId;
    }

    public void setLadingId(Long ladingId) {
        this.ladingId = ladingId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getStartOutlets() {
        return startOutlets;
    }

    public void setStartOutlets(Long startOutlets) {
        this.startOutlets = startOutlets;
    }

    public Long getTargetOutlets() {
        return targetOutlets;
    }

    public void setTargetOutlets(Long targetOutlets) {
        this.targetOutlets = targetOutlets;
    }

    public String getTargetProvince() {
		return targetProvince;
	}

	public void setTargetProvince(String targetProvince) {
		this.targetProvince = targetProvince;
	}

	public String getTargetCity() {
        return targetCity;
    }

    public void setTargetCity(String targetCity) {
        this.targetCity = targetCity == null ? null : targetCity.trim();
    }

    public String getTargetCounty() {
        return targetCounty;
    }

    public void setTargetCounty(String targetCounty) {
        this.targetCounty = targetCounty == null ? null : targetCounty.trim();
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor == null ? null : consignor.trim();
    }

    public String getConsignorCompany() {
        return consignorCompany;
    }

    public void setConsignorCompany(String consignorCompany) {
        this.consignorCompany = consignorCompany == null ? null : consignorCompany.trim();
    }

    public String getConsignorMobile() {
        return consignorMobile;
    }

    public void setConsignorMobile(String consignorMobile) {
        this.consignorMobile = consignorMobile == null ? null : consignorMobile.trim();
    }

    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress == null ? null : consignorAddress.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getConsigneeCompany() {
        return consigneeCompany;
    }

    public void setConsigneeCompany(String consigneeCompany) {
        this.consigneeCompany = consigneeCompany == null ? null : consigneeCompany.trim();
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile == null ? null : consigneeMobile.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public String getDistributionMode() {
        return distributionMode;
    }

    public void setDistributionMode(String distributionMode) {
        this.distributionMode = distributionMode == null ? null : distributionMode.trim();
    }

    public Integer getReceiptSlip() {
        return receiptSlip;
    }

    public void setReceiptSlip(Integer receiptSlip) {
        this.receiptSlip = receiptSlip;
    }

    public Integer getReceiptSlipNum() {
        return receiptSlipNum;
    }

    public void setReceiptSlipNum(Integer receiptSlipNum) {
        this.receiptSlipNum = receiptSlipNum;
    }

    public Long getSinglePlaneCostId() {
        return singlePlaneCostId;
    }

    public void setSinglePlaneCostId(Long singlePlaneCostId) {
        this.singlePlaneCostId = singlePlaneCostId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
package com.brightsoft.model;

import java.io.Serializable;

public class BasicData implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer isImmediatePay;

    private Integer isArrivePay;

    private Integer isAdvancePay;

    private Integer isCommonReceipt;

    private Double commonReceiptRate;

    private Integer isAddTaxReceipt;

    private Double addTaxReceiptRate;

    private Integer isNoReceipt;

    private Integer isSupportInsurance;

    private Integer isForceInsurance;

    private Double insuranceRate;

    private Double lowestQuato;

    private Long companyId;
    
    // -----------------
    
    private String companyName;

    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsImmediatePay() {
        return isImmediatePay;
    }

    public void setIsImmediatePay(Integer isImmediatePay) {
        this.isImmediatePay = isImmediatePay;
    }

    public Integer getIsArrivePay() {
        return isArrivePay;
    }

    public void setIsArrivePay(Integer isArrivePay) {
        this.isArrivePay = isArrivePay;
    }

    public Integer getIsAdvancePay() {
        return isAdvancePay;
    }

    public void setIsAdvancePay(Integer isAdvancePay) {
        this.isAdvancePay = isAdvancePay;
    }

    public Integer getIsCommonReceipt() {
        return isCommonReceipt;
    }

    public void setIsCommonReceipt(Integer isCommonReceipt) {
        this.isCommonReceipt = isCommonReceipt;
    }

    public Double getCommonReceiptRate() {
        return commonReceiptRate;
    }

    public void setCommonReceiptRate(Double commonReceiptRate) {
        this.commonReceiptRate = commonReceiptRate;
    }

    public Integer getIsAddTaxReceipt() {
        return isAddTaxReceipt;
    }

    public void setIsAddTaxReceipt(Integer isAddTaxReceipt) {
        this.isAddTaxReceipt = isAddTaxReceipt;
    }

    public Double getAddTaxReceiptRate() {
        return addTaxReceiptRate;
    }

    public void setAddTaxReceiptRate(Double addTaxReceiptRate) {
        this.addTaxReceiptRate = addTaxReceiptRate;
    }

    public Integer getIsNoReceipt() {
        return isNoReceipt;
    }

    public void setIsNoReceipt(Integer isNoReceipt) {
        this.isNoReceipt = isNoReceipt;
    }

    public Integer getIsSupportInsurance() {
        return isSupportInsurance;
    }

    public void setIsSupportInsurance(Integer isSupportInsurance) {
        this.isSupportInsurance = isSupportInsurance;
    }

    public Integer getIsForceInsurance() {
        return isForceInsurance;
    }

    public void setIsForceInsurance(Integer isForceInsurance) {
        this.isForceInsurance = isForceInsurance;
    }

    public Double getInsuranceRate() {
        return insuranceRate;
    }

    public void setInsuranceRate(Double insuranceRate) {
        this.insuranceRate = insuranceRate;
    }

    public Double getLowestQuato() {
        return lowestQuato;
    }

    public void setLowestQuato(Double lowestQuato) {
        this.lowestQuato = lowestQuato;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
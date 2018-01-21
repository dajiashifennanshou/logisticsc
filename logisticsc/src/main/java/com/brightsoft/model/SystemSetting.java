package com.brightsoft.model;

import java.io.Serializable;

public class SystemSetting implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer immediatePay;

    private Integer afterPay;

    private Integer advancePay;

    private Integer commonInvoice;

    private Double commonInvoiceTax;

    private Integer additionalInvoice;

    private Double additionalInvoiceTax;

    private Integer noInvoice;

    private Double advancePayRate;

    private Integer isInsurance;

    private Double insuranceRate;

    private Double insuranceLowest;

    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getImmediatePay() {
        return immediatePay;
    }

    public void setImmediatePay(Integer immediatePay) {
        this.immediatePay = immediatePay;
    }

    public Integer getAfterPay() {
        return afterPay;
    }

    public void setAfterPay(Integer afterPay) {
        this.afterPay = afterPay;
    }

    public Integer getAdvancePay() {
        return advancePay;
    }

    public void setAdvancePay(Integer advancePay) {
        this.advancePay = advancePay;
    }

    public Integer getCommonInvoice() {
        return commonInvoice;
    }

    public void setCommonInvoice(Integer commonInvoice) {
        this.commonInvoice = commonInvoice;
    }

    public Double getCommonInvoiceTax() {
        return commonInvoiceTax;
    }

    public void setCommonInvoiceTax(Double commonInvoiceTax) {
        this.commonInvoiceTax = commonInvoiceTax;
    }

    public Integer getAdditionalInvoice() {
        return additionalInvoice;
    }

    public void setAdditionalInvoice(Integer additionalInvoice) {
        this.additionalInvoice = additionalInvoice;
    }

    public Double getAdditionalInvoiceTax() {
        return additionalInvoiceTax;
    }

    public void setAdditionalInvoiceTax(Double additionalInvoiceTax) {
        this.additionalInvoiceTax = additionalInvoiceTax;
    }

    public Integer getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(Integer noInvoice) {
        this.noInvoice = noInvoice;
    }

    public Double getAdvancePayRate() {
        return advancePayRate;
    }

    public void setAdvancePayRate(Double advancePayRate) {
        this.advancePayRate = advancePayRate;
    }

    public Integer getIsInsurance() {
        return isInsurance;
    }

    public void setIsInsurance(Integer isInsurance) {
        this.isInsurance = isInsurance;
    }

    public Double getInsuranceRate() {
        return insuranceRate;
    }

    public void setInsuranceRate(Double insuranceRate) {
        this.insuranceRate = insuranceRate;
    }

    public Double getInsuranceLowest() {
        return insuranceLowest;
    }

    public void setInsuranceLowest(Double insuranceLowest) {
        this.insuranceLowest = insuranceLowest;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
package com.brightsoft.model;

import java.io.Serializable;

public class PosOrderCancelRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String employeeId;

    private String posSn;

    private String orderNumber;

    private Double amount;

    private String posRequestId;

    private String referNo;

    private String payTypeCode;

    private String payMethod;

    private String chequeNo;

    private String bankCardNo;

    private String bankCardName;

    private String bankCardType;

    private String bankOrderNo;

    private String yeepayOrderNo;

    private String customerNo;

    private String posMenue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public String getPosSn() {
        return posSn;
    }

    public void setPosSn(String posSn) {
        this.posSn = posSn == null ? null : posSn.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPosRequestId() {
        return posRequestId;
    }

    public void setPosRequestId(String posRequestId) {
        this.posRequestId = posRequestId == null ? null : posRequestId.trim();
    }

    public String getReferNo() {
        return referNo;
    }

    public void setReferNo(String referNo) {
        this.referNo = referNo == null ? null : referNo.trim();
    }

    public String getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode == null ? null : payTypeCode.trim();
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo == null ? null : chequeNo.trim();
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName == null ? null : bankCardName.trim();
    }

    public String getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(String bankCardType) {
        this.bankCardType = bankCardType == null ? null : bankCardType.trim();
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo == null ? null : bankOrderNo.trim();
    }

    public String getYeepayOrderNo() {
        return yeepayOrderNo;
    }

    public void setYeepayOrderNo(String yeepayOrderNo) {
        this.yeepayOrderNo = yeepayOrderNo == null ? null : yeepayOrderNo.trim();
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }

    public String getPosMenue() {
        return posMenue;
    }

    public void setPosMenue(String posMenue) {
        this.posMenue = posMenue == null ? null : posMenue.trim();
    }
}
package com.brightsoft.model;

import java.io.Serializable;

public class PosOrderSignRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String employeeId;

    private String posSn;

    private String orderNo;

    private String payTypeCode;

    private String payTypeMsg;

    private String payMethod;

    private Double amount;

    private String signSelfFlag;

    private String signName;

    private String signTel;

    private String customerNo;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode == null ? null : payTypeCode.trim();
    }

    public String getPayTypeMsg() {
        return payTypeMsg;
    }

    public void setPayTypeMsg(String payTypeMsg) {
        this.payTypeMsg = payTypeMsg == null ? null : payTypeMsg.trim();
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSignSelfFlag() {
        return signSelfFlag;
    }

    public void setSignSelfFlag(String signSelfFlag) {
        this.signSelfFlag = signSelfFlag == null ? null : signSelfFlag.trim();
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName == null ? null : signName.trim();
    }

    public String getSignTel() {
        return signTel;
    }

    public void setSignTel(String signTel) {
        this.signTel = signTel == null ? null : signTel.trim();
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo == null ? null : customerNo.trim();
    }
}
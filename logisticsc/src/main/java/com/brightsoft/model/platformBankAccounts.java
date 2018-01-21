package com.brightsoft.model;

import java.util.Date;

public class platformBankAccounts {
    private Long id;

    private String customernumber;

    private String requestid;

    private String ledgerno;

    private String amount;

    private String sourceledgerno;

    private String hmac;

    private Integer orderType;

    private String orderNumber;

    private Date time;

    private Integer state;

    private String suername;
    
    private Long tmsUserId;
    
    

    public Long getTmsUserId() {
		return tmsUserId;
	}

	public void setTmsUserId(Long tmsUserId) {
		this.tmsUserId = tmsUserId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(String customernumber) {
        this.customernumber = customernumber == null ? null : customernumber.trim();
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid == null ? null : requestid.trim();
    }

    public String getLedgerno() {
        return ledgerno;
    }

    public void setLedgerno(String ledgerno) {
        this.ledgerno = ledgerno == null ? null : ledgerno.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getSourceledgerno() {
        return sourceledgerno;
    }

    public void setSourceledgerno(String sourceledgerno) {
        this.sourceledgerno = sourceledgerno == null ? null : sourceledgerno.trim();
    }

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac == null ? null : hmac.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSuername() {
        return suername;
    }

    public void setSuername(String suername) {
        this.suername = suername == null ? null : suername.trim();
    }
}
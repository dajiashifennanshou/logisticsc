package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class platformBankSplit implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String customernumber;

    private String requestid;

    private String orderrequestid;

    private String divideinfo;

    private String callbackurl;
    
    private Integer orderType;
    
    private String orderNumber;

    private Date time;

    private Integer state;

    private String suername;
    
    private Long tmsUserId;
    
    private String amount;
    
    
    
    
    public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Long getTmsUserId() {
		return tmsUserId;
	}

	public void setTmsUserId(Long tmsUserId) {
		this.tmsUserId = tmsUserId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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

    public String getOrderrequestid() {
        return orderrequestid;
    }

    public void setOrderrequestid(String orderrequestid) {
        this.orderrequestid = orderrequestid == null ? null : orderrequestid.trim();
    }

    public String getDivideinfo() {
        return divideinfo;
    }

    public void setDivideinfo(String divideinfo) {
        this.divideinfo = divideinfo == null ? null : divideinfo.trim();
    }

    public String getCallbackurl() {
        return callbackurl;
    }

    public void setCallbackurl(String callbackurl) {
        this.callbackurl = callbackurl == null ? null : callbackurl.trim();
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
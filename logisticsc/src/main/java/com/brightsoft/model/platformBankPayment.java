package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class platformBankPayment implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String customernumber;

    private String requestid;

    private String amount;

    private String assure;

    private String productname;

    private String productcat;

    private String productdesc;

    private String divideinfo;

    private String callbackurl;

    private String webcallbackurl;

    private String bankid;

    private String period;

    private String memo;

    private String payproducttype;

    private String orderNumber;

    private Integer orderType;

    private Date time;

    private Integer state;

    private Long userId;
    
    private String startTime;//起始时间
    
	private String endTime;//结束时间
	    
	private String condition;//查询输入条件
	
	private String orderTypes;
	
	private String loginName;
	
	
	
	
    public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(String orderTypes) {
		this.orderTypes = orderTypes;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getAssure() {
        return assure;
    }

    public void setAssure(String assure) {
        this.assure = assure == null ? null : assure.trim();
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public String getProductcat() {
        return productcat;
    }

    public void setProductcat(String productcat) {
        this.productcat = productcat == null ? null : productcat.trim();
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc == null ? null : productdesc.trim();
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

    public String getWebcallbackurl() {
        return webcallbackurl;
    }

    public void setWebcallbackurl(String webcallbackurl) {
        this.webcallbackurl = webcallbackurl == null ? null : webcallbackurl.trim();
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid == null ? null : bankid.trim();
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getPayproducttype() {
        return payproducttype;
    }

    public void setPayproducttype(String payproducttype) {
        this.payproducttype = payproducttype == null ? null : payproducttype.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
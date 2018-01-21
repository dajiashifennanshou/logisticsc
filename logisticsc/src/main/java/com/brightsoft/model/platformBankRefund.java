package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class platformBankRefund implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String customernumber;

    private String requestid;

    private String orderrequestid;

    private String amount;

    private String divideinfo;

    private String confirm;

    private String memo;

    private String orderNumber;
    
    private Integer orderType;

    private Date time;

    private Integer state;

    private String username;
    
    private Integer refundType;

    private String startTime;//起始时间
    
   	private String endTime;//结束时间
   	    
   	private String condition;//查询输入条件
   	
   	private String orderTypes;
   	
   	private String refundTypes;
   	
    private Long userId;
    
    private String loginName;
    
    private String bankaccountnumber;
    

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getBankaccountnumber() {
		return bankaccountnumber;
	}

	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRefundType() {
		return refundType;
	}

	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}

	public String getRefundTypes() {
		return refundTypes;
	}

	public void setRefundTypes(String refundTypes) {
		this.refundTypes = refundTypes;
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

	public String getOrderTypes() {
		return orderTypes;
	}

	public void setOrderTypes(String orderTypes) {
		this.orderTypes = orderTypes;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getDivideinfo() {
        return divideinfo;
    }

    public void setDivideinfo(String divideinfo) {
        this.divideinfo = divideinfo == null ? null : divideinfo.trim();
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm == null ? null : confirm.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}
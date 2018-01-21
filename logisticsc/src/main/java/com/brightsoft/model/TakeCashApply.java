package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class TakeCashApply implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userId;

    private Double money;

    private Integer status;

    private Date applyTime;

    private Date auditTime;

    private Integer auditPerson;
    
    // -------------
    
    private String statusName;
    
    private String userName;
    
    private String mobile;

    public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(Integer auditPerson) {
        this.auditPerson = auditPerson;
    }
}
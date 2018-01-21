package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class ReceiveMoneyOrderRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long receiveMoneyOrderId;

    private Double receiveMoney;

    private Integer receiveMoneyType;
    
    private String operatePerson;

    private Date operateTime;
    
    private Integer isCancel;
    
    private String payPerson;
    
    private String payPersonMobile;
    
    private Integer isAgencyFund;

    public Integer getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Integer isCancel) {
		this.isCancel = isCancel;
	}

	public Integer getIsAgencyFund() {
		return isAgencyFund;
	}

	public void setIsAgencyFund(Integer isAgencyFund) {
		this.isAgencyFund = isAgencyFund;
	}

	public String getPayPerson() {
		return payPerson;
	}

	public void setPayPerson(String payPerson) {
		this.payPerson = payPerson;
	}

	public String getPayPersonMobile() {
		return payPersonMobile;
	}

	public void setPayPersonMobile(String payPersonMobile) {
		this.payPersonMobile = payPersonMobile;
	}

	public Integer getReceiveMoneyType() {
		return receiveMoneyType;
	}

	public void setReceiveMoneyType(Integer receiveMoneyType) {
		this.receiveMoneyType = receiveMoneyType;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceiveMoneyOrderId() {
        return receiveMoneyOrderId;
    }

    public void setReceiveMoneyOrderId(Long receiveMoneyOrderId) {
        this.receiveMoneyOrderId = receiveMoneyOrderId;
    }

    public Double getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson == null ? null : operatePerson.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
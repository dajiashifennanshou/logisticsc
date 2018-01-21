package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class ReceivablePayBillRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long billId;

    private String receiveCompany;

    private String receivePerson;

    private String receivePersonPhone;

    private Double receiveMoney;

    private Integer isCompleted;
    
    private Long operatePerson;
    
    private Date operateTime;
    
    private String costSubject;
    
    private String departNumber;
    
    private Double total;

    public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getDepartNumber() {
		return departNumber;
	}

	public void setDepartNumber(String departNumber) {
		this.departNumber = departNumber;
	}

	public String getCostSubject() {
		return costSubject;
	}

	public void setCostSubject(String costSubject) {
		this.costSubject = costSubject;
	}

	public Long getOperatePerson() {
		return operatePerson;
	}

	public void setOperatePerson(Long operatePerson) {
		this.operatePerson = operatePerson;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public String getReceiveCompany() {
        return receiveCompany;
    }

    public void setReceiveCompany(String receiveCompany) {
        this.receiveCompany = receiveCompany == null ? null : receiveCompany.trim();
    }

    public String getReceivePerson() {
        return receivePerson;
    }

    public void setReceivePerson(String receivePerson) {
        this.receivePerson = receivePerson == null ? null : receivePerson.trim();
    }

    public String getReceivePersonPhone() {
        return receivePersonPhone;
    }

    public void setReceivePersonPhone(String receivePersonPhone) {
        this.receivePersonPhone = receivePersonPhone == null ? null : receivePersonPhone.trim();
    }

    public Double getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(Double receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }
}
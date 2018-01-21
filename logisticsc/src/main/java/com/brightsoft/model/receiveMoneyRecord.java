package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class receiveMoneyRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String wayBillNumber;

    private Integer costType;

    private String payCompany;

    private String payPerson;

    private Double shouldReceiveMoney;

    private Integer payMethod;

    private Double actualReceiveMoney;

    private Integer isCompleted;

    private Long outletsId;

    private Long operatePerson;

    private Date operateTime;
    
    // -----------------------
    
    private String departNumber;

    public String getDepartNumber() {
		return departNumber;
	}

	public void setDepartNumber(String departNumber) {
		this.departNumber = departNumber;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWayBillNumber() {
        return wayBillNumber;
    }

    public void setWayBillNumber(String wayBillNumber) {
        this.wayBillNumber = wayBillNumber == null ? null : wayBillNumber.trim();
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public String getPayCompany() {
        return payCompany;
    }

    public void setPayCompany(String payCompany) {
        this.payCompany = payCompany == null ? null : payCompany.trim();
    }

    public String getPayPerson() {
        return payPerson;
    }

    public void setPayPerson(String payPerson) {
        this.payPerson = payPerson == null ? null : payPerson.trim();
    }

    public Double getShouldReceiveMoney() {
        return shouldReceiveMoney;
    }

    public void setShouldReceiveMoney(Double shouldReceiveMoney) {
        this.shouldReceiveMoney = shouldReceiveMoney;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Double getActualReceiveMoney() {
        return actualReceiveMoney;
    }

    public void setActualReceiveMoney(Double actualReceiveMoney) {
        this.actualReceiveMoney = actualReceiveMoney;
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Long getOutletsId() {
        return outletsId;
    }

    public void setOutletsId(Long outletsId) {
        this.outletsId = outletsId;
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
}
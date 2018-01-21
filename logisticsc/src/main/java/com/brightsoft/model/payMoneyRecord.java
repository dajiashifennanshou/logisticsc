package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class payMoneyRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String wayBillNumber;

    private Integer costType;

    private String receiveCompany;

    private String receivePerson;

    private Double shouldPayMoney;

    private Double actualPayMoney;

    private Integer isCompleted;

    private Long outletsId;

    private Long operatePerson;

    private Date operateTime;

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

    public Double getShouldPayMoney() {
        return shouldPayMoney;
    }

    public void setShouldPayMoney(Double shouldPayMoney) {
        this.shouldPayMoney = shouldPayMoney;
    }

    public Double getActualPayMoney() {
        return actualPayMoney;
    }

    public void setActualPayMoney(Double actualPayMoney) {
        this.actualPayMoney = actualPayMoney;
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
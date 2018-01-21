package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class InsteadCollectMoney implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String wayBillNumber;

    private String collectMoneyPerson;

    private String collectMoneyCompany;

    private String payMoneyPerson;

    private String payMoneyCompany;

    private Double insteadCollectMoney;

    private Double actualCollectMoney;

    private Double transferredMoney;

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

    public String getCollectMoneyPerson() {
        return collectMoneyPerson;
    }

    public void setCollectMoneyPerson(String collectMoneyPerson) {
        this.collectMoneyPerson = collectMoneyPerson == null ? null : collectMoneyPerson.trim();
    }

    public String getCollectMoneyCompany() {
        return collectMoneyCompany;
    }

    public void setCollectMoneyCompany(String collectMoneyCompany) {
        this.collectMoneyCompany = collectMoneyCompany == null ? null : collectMoneyCompany.trim();
    }

    public String getPayMoneyPerson() {
        return payMoneyPerson;
    }

    public void setPayMoneyPerson(String payMoneyPerson) {
        this.payMoneyPerson = payMoneyPerson == null ? null : payMoneyPerson.trim();
    }

    public String getPayMoneyCompany() {
        return payMoneyCompany;
    }

    public void setPayMoneyCompany(String payMoneyCompany) {
        this.payMoneyCompany = payMoneyCompany == null ? null : payMoneyCompany.trim();
    }

    public Double getInsteadCollectMoney() {
        return insteadCollectMoney;
    }

    public void setInsteadCollectMoney(Double insteadCollectMoney) {
        this.insteadCollectMoney = insteadCollectMoney;
    }

    public Double getActualCollectMoney() {
        return actualCollectMoney;
    }

    public void setActualCollectMoney(Double actualCollectMoney) {
        this.actualCollectMoney = actualCollectMoney;
    }

    public Double getTransferredMoney() {
        return transferredMoney;
    }

    public void setTransferredMoney(Double transferredMoney) {
        this.transferredMoney = transferredMoney;
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
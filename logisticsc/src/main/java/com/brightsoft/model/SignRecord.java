package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class SignRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String wayBillNumber;

    private Date signTime;

    private String signPerson;

    private String idCard;

    private String phone;

    private String address;

    private Double receiveMoney;

    private Integer isCompleted;

    private Integer isAdvanceMoney;
    
    // -----------------
    
    private String signTimeStr;

    public String getSignTimeStr() {
		return signTimeStr;
	}

	public void setSignTimeStr(String signTimeStr) {
		this.signTimeStr = signTimeStr;
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

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getSignPerson() {
        return signPerson;
    }

    public void setSignPerson(String signPerson) {
        this.signPerson = signPerson == null ? null : signPerson.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public Integer getIsAdvanceMoney() {
        return isAdvanceMoney;
    }

    public void setIsAdvanceMoney(Integer isAdvanceMoney) {
        this.isAdvanceMoney = isAdvanceMoney;
    }
}
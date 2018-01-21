package com.brightsoft.model;

import java.io.Serializable;

public class lineAdvanceMoneyRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String companyName;

    private Long lineId;

    private String advanceMoneyCompany;

    private Double advanceRatio;

    private Double advanceMoney;

    private String contactPerson;

    private String phone;

    private String telephone;

    private String email;

    private String qq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getAdvanceMoneyCompany() {
        return advanceMoneyCompany;
    }

    public void setAdvanceMoneyCompany(String advanceMoneyCompany) {
        this.advanceMoneyCompany = advanceMoneyCompany == null ? null : advanceMoneyCompany.trim();
    }

    public Double getAdvanceRatio() {
        return advanceRatio;
    }

    public void setAdvanceRatio(Double advanceRatio) {
        this.advanceRatio = advanceRatio;
    }

    public Double getAdvanceMoney() {
        return advanceMoney;
    }

    public void setAdvanceMoney(Double advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson == null ? null : contactPerson.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }
}
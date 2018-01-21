package com.brightsoft.model;

import java.io.Serializable;

/**
 * 公司临时表
 * @author ThinkPad
 *
 */
public class PlatformUserTemporaryCompany implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String companyName;

    private String companyAddress;

    private String legalPerson;

    private String legalMobile;
    
    private String contacts;

    private String contactsMobile;
    
    private String QQ;

    private String postCode;

    private String companyPhone;

    private String companyFax;

    private String companyTaxNo;

    private String financeEmail;

    private String companyInfo;

    private String logo;

    private String businessLicense;

    private String companyPhoto;

    private String legalPhoto;

    private String cardPhoto;

    private String companyCode;

    
    public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsMobile() {
		return contactsMobile;
	}

	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}

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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getLegalMobile() {
        return legalMobile;
    }

    public void setLegalMobile(String legalMobile) {
        this.legalMobile = legalMobile == null ? null : legalMobile.trim();
    }


	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone == null ? null : companyPhone.trim();
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax == null ? null : companyFax.trim();
    }

    public String getCompanyTaxNo() {
        return companyTaxNo;
    }

    public void setCompanyTaxNo(String companyTaxNo) {
        this.companyTaxNo = companyTaxNo == null ? null : companyTaxNo.trim();
    }

    public String getFinanceEmail() {
        return financeEmail;
    }

    public void setFinanceEmail(String financeEmail) {
        this.financeEmail = financeEmail == null ? null : financeEmail.trim();
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo == null ? null : companyInfo.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getCompanyPhoto() {
        return companyPhoto;
    }

    public void setCompanyPhoto(String companyPhoto) {
        this.companyPhoto = companyPhoto == null ? null : companyPhoto.trim();
    }

    public String getLegalPhoto() {
        return legalPhoto;
    }

    public void setLegalPhoto(String legalPhoto) {
        this.legalPhoto = legalPhoto == null ? null : legalPhoto.trim();
    }

    public String getCardPhoto() {
        return cardPhoto;
    }

    public void setCardPhoto(String cardPhoto) {
        this.cardPhoto = cardPhoto == null ? null : cardPhoto.trim();
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }
}
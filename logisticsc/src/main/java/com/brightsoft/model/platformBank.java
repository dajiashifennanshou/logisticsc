package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class platformBank implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String requestid;

    private String bindmobile;

    private String customertype;

    private String signedname;

    private String linkman;

    private String idcard;

    private String businesslicence;

    private String legalperson;

    private String minsettleamount;

    private String riskreserveday;

    private String bankaccountnumber;
    
    private String provincename;
    
    private String cityname;
    
    private String bankheadname;

    private String bankname;

    private String accountname;

    private String bankaccounttype;

    private String bankprovince;

    private String bankcity;

    private String manualsettle;

    private String deposit;

    private String email;

    private String idCardFront;

    private String idCcardBack;

    private String bankCardFront;

    private String bankCardBack;

    private String persouPhoto;

    private String bussinessLicense;

    private String bussinessCertficates;

    private String organizationCode;

    private String taxRegistron;

    private String bankAccountLicence;

    private Integer state;

    private Date bankTime;

    private Long userId;
    
    private Integer isQualifications;
    
    

	public Integer getIsQualifications() {
		return isQualifications;
	}

	public void setIsQualifications(Integer isQualifications) {
		this.isQualifications = isQualifications;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getBankheadname() {
		return bankheadname;
	}

	public void setBankheadname(String bankheadname) {
		this.bankheadname = bankheadname;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid == null ? null : requestid.trim();
    }

    public String getBindmobile() {
        return bindmobile;
    }

    public void setBindmobile(String bindmobile) {
        this.bindmobile = bindmobile == null ? null : bindmobile.trim();
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype == null ? null : customertype.trim();
    }

    public String getSignedname() {
        return signedname;
    }

    public void setSignedname(String signedname) {
        this.signedname = signedname == null ? null : signedname.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getBusinesslicence() {
        return businesslicence;
    }

    public void setBusinesslicence(String businesslicence) {
        this.businesslicence = businesslicence == null ? null : businesslicence.trim();
    }

    public String getLegalperson() {
        return legalperson;
    }

    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson == null ? null : legalperson.trim();
    }

    public String getMinsettleamount() {
        return minsettleamount;
    }

    public void setMinsettleamount(String minsettleamount) {
        this.minsettleamount = minsettleamount == null ? null : minsettleamount.trim();
    }

    public String getRiskreserveday() {
        return riskreserveday;
    }

    public void setRiskreserveday(String riskreserveday) {
        this.riskreserveday = riskreserveday == null ? null : riskreserveday.trim();
    }

    public String getBankaccountnumber() {
        return bankaccountnumber;
    }

    public void setBankaccountnumber(String bankaccountnumber) {
        this.bankaccountnumber = bankaccountnumber == null ? null : bankaccountnumber.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname == null ? null : accountname.trim();
    }

    public String getBankaccounttype() {
        return bankaccounttype;
    }

    public void setBankaccounttype(String bankaccounttype) {
        this.bankaccounttype = bankaccounttype == null ? null : bankaccounttype.trim();
    }

    public String getBankprovince() {
        return bankprovince;
    }

    public void setBankprovince(String bankprovince) {
        this.bankprovince = bankprovince == null ? null : bankprovince.trim();
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity == null ? null : bankcity.trim();
    }

    public String getManualsettle() {
        return manualsettle;
    }

    public void setManualsettle(String manualsettle) {
        this.manualsettle = manualsettle == null ? null : manualsettle.trim();
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit == null ? null : deposit.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront == null ? null : idCardFront.trim();
    }

    public String getIdCcardBack() {
        return idCcardBack;
    }

    public void setIdCcardBack(String idCcardBack) {
        this.idCcardBack = idCcardBack == null ? null : idCcardBack.trim();
    }

    public String getBankCardFront() {
        return bankCardFront;
    }

    public void setBankCardFront(String bankCardFront) {
        this.bankCardFront = bankCardFront == null ? null : bankCardFront.trim();
    }

    public String getBankCardBack() {
        return bankCardBack;
    }

    public void setBankCardBack(String bankCardBack) {
        this.bankCardBack = bankCardBack == null ? null : bankCardBack.trim();
    }

    public String getPersouPhoto() {
        return persouPhoto;
    }

    public void setPersouPhoto(String persouPhoto) {
        this.persouPhoto = persouPhoto == null ? null : persouPhoto.trim();
    }

    public String getBussinessLicense() {
        return bussinessLicense;
    }

    public void setBussinessLicense(String bussinessLicense) {
        this.bussinessLicense = bussinessLicense == null ? null : bussinessLicense.trim();
    }

    public String getBussinessCertficates() {
        return bussinessCertficates;
    }

    public void setBussinessCertficates(String bussinessCertficates) {
        this.bussinessCertficates = bussinessCertficates == null ? null : bussinessCertficates.trim();
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode == null ? null : organizationCode.trim();
    }

    public String getTaxRegistron() {
        return taxRegistron;
    }

    public void setTaxRegistron(String taxRegistron) {
        this.taxRegistron = taxRegistron == null ? null : taxRegistron.trim();
    }

    public String getBankAccountLicence() {
        return bankAccountLicence;
    }

    public void setBankAccountLicence(String bankAccountLicence) {
        this.bankAccountLicence = bankAccountLicence == null ? null : bankAccountLicence.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getBankTime() {
        return bankTime;
    }

    public void setBankTime(Date bankTime) {
        this.bankTime = bankTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class PlatformBank implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
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
	private String id_card_front;
	private String id_ccard_back;
	private String bank_card_front;
	private String bank_card_back;
	private String persou_photo;
	private String bussiness_license;
	private String bussiness_certficates;
	private String organization_code;
	private String tax_registron;
	private String bank_account_licence;
	private Integer state;
	private String bank_time;
	private BigInteger user_id;
	private Integer is_qualifications;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getRequestid() {
		return requestid;
	}
	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}
	public String getBindmobile() {
		return bindmobile;
	}
	public void setBindmobile(String bindmobile) {
		this.bindmobile = bindmobile;
	}
	public String getCustomertype() {
		return customertype;
	}
	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}
	public String getSignedname() {
		return signedname;
	}
	public void setSignedname(String signedname) {
		this.signedname = signedname;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getBusinesslicence() {
		return businesslicence;
	}
	public void setBusinesslicence(String businesslicence) {
		this.businesslicence = businesslicence;
	}
	public String getLegalperson() {
		return legalperson;
	}
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	public String getMinsettleamount() {
		return minsettleamount;
	}
	public void setMinsettleamount(String minsettleamount) {
		this.minsettleamount = minsettleamount;
	}
	public String getRiskreserveday() {
		return riskreserveday;
	}
	public void setRiskreserveday(String riskreserveday) {
		this.riskreserveday = riskreserveday;
	}
	public String getBankaccountnumber() {
		return bankaccountnumber;
	}
	public void setBankaccountnumber(String bankaccountnumber) {
		this.bankaccountnumber = bankaccountnumber;
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
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getBankaccounttype() {
		return bankaccounttype;
	}
	public void setBankaccounttype(String bankaccounttype) {
		this.bankaccounttype = bankaccounttype;
	}
	public String getBankprovince() {
		return bankprovince;
	}
	public void setBankprovince(String bankprovince) {
		this.bankprovince = bankprovince;
	}
	public String getBankcity() {
		return bankcity;
	}
	public void setBankcity(String bankcity) {
		this.bankcity = bankcity;
	}
	public String getManualsettle() {
		return manualsettle;
	}
	public void setManualsettle(String manualsettle) {
		this.manualsettle = manualsettle;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId_card_front() {
		return id_card_front;
	}
	public void setId_card_front(String id_card_front) {
		this.id_card_front = id_card_front;
	}
	public String getId_ccard_back() {
		return id_ccard_back;
	}
	public void setId_ccard_back(String id_ccard_back) {
		this.id_ccard_back = id_ccard_back;
	}
	public String getBank_card_front() {
		return bank_card_front;
	}
	public void setBank_card_front(String bank_card_front) {
		this.bank_card_front = bank_card_front;
	}
	public String getBank_card_back() {
		return bank_card_back;
	}
	public void setBank_card_back(String bank_card_back) {
		this.bank_card_back = bank_card_back;
	}
	public String getPersou_photo() {
		return persou_photo;
	}
	public void setPersou_photo(String persou_photo) {
		this.persou_photo = persou_photo;
	}
	public String getBussiness_license() {
		return bussiness_license;
	}
	public void setBussiness_license(String bussiness_license) {
		this.bussiness_license = bussiness_license;
	}
	public String getBussiness_certficates() {
		return bussiness_certficates;
	}
	public void setBussiness_certficates(String bussiness_certficates) {
		this.bussiness_certficates = bussiness_certficates;
	}
	public String getOrganization_code() {
		return organization_code;
	}
	public void setOrganization_code(String organization_code) {
		this.organization_code = organization_code;
	}
	public String getTax_registron() {
		return tax_registron;
	}
	public void setTax_registron(String tax_registron) {
		this.tax_registron = tax_registron;
	}
	public String getBank_account_licence() {
		return bank_account_licence;
	}
	public void setBank_account_licence(String bank_account_licence) {
		this.bank_account_licence = bank_account_licence;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getBank_time() {
		return bank_time;
	}
	public void setBank_time(String bank_time) {
		this.bank_time = bank_time;
	}
	public BigInteger getUser_id() {
		return user_id;
	}
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	public Integer getIs_qualifications() {
		return is_qualifications;
	}
	public void setIs_qualifications(Integer is_qualifications) {
		this.is_qualifications = is_qualifications;
	}
	
}


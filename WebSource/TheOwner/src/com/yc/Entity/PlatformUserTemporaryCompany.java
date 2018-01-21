package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
/**
 * 临时公司信息 表
 * @Author:luojing
 * @2016年8月16日 上午10:51:57
 */
public class PlatformUserTemporaryCompany implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String company_name;
	private String company_address;
	private String legal_person;
	private String legal_mobile;
	private String contacts;
	private String contacts_mobile;
	private String qq;
	private String post_code;
	private String company_phone;
	private String company_fax;
	private String company_tax_no;
	private String finance_email;
	private String company_info;
	private String logo;
	private String business_license;
	private String company_photo;
	private String legal_photo;
	private String card_photo;
	private String company_code;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setCompany_name(String company_name){
		this.company_name=company_name;
	}
	public String getCompany_name(){
		return company_name;
	}
	public void setCompany_address(String company_address){
		this.company_address=company_address;
	}
	public String getCompany_address(){
		return company_address;
	}
	public void setLegal_person(String legal_person){
		this.legal_person=legal_person;
	}
	public String getLegal_person(){
		return legal_person;
	}
	public void setLegal_mobile(String legal_mobile){
		this.legal_mobile=legal_mobile;
	}
	public String getLegal_mobile(){
		return legal_mobile;
	}
	public void setContacts(String contacts){
		this.contacts=contacts;
	}
	public String getContacts(){
		return contacts;
	}
	public void setContacts_mobile(String contacts_mobile){
		this.contacts_mobile=contacts_mobile;
	}
	public String getContacts_mobile(){
		return contacts_mobile;
	}
	public void setQq(String qq){
		this.qq=qq;
	}
	public String getQq(){
		return qq;
	}
	public void setPost_code(String post_code){
		this.post_code=post_code;
	}
	public String getPost_code(){
		return post_code;
	}
	public void setCompany_phone(String company_phone){
		this.company_phone=company_phone;
	}
	public String getCompany_phone(){
		return company_phone;
	}
	public void setCompany_fax(String company_fax){
		this.company_fax=company_fax;
	}
	public String getCompany_fax(){
		return company_fax;
	}
	public void setCompany_tax_no(String company_tax_no){
		this.company_tax_no=company_tax_no;
	}
	public String getCompany_tax_no(){
		return company_tax_no;
	}
	public void setFinance_email(String finance_email){
		this.finance_email=finance_email;
	}
	public String getFinance_email(){
		return finance_email;
	}
	public void setCompany_info(String company_info){
		this.company_info=company_info;
	}
	public String getCompany_info(){
		return company_info;
	}
	public void setLogo(String logo){
		this.logo=logo;
	}
	public String getLogo(){
		return logo;
	}
	public void setBusiness_license(String business_license){
		this.business_license=business_license;
	}
	public String getBusiness_license(){
		return business_license;
	}
	public void setCompany_photo(String company_photo){
		this.company_photo=company_photo;
	}
	public String getCompany_photo(){
		return company_photo;
	}
	public void setLegal_photo(String legal_photo){
		this.legal_photo=legal_photo;
	}
	public String getLegal_photo(){
		return legal_photo;
	}
	public void setCard_photo(String card_photo){
		this.card_photo=card_photo;
	}
	public String getCard_photo(){
		return card_photo;
	}
	public void setCompany_code(String company_code){
		this.company_code=company_code;
	}
	public String getCompany_code(){
		return company_code;
	}
}


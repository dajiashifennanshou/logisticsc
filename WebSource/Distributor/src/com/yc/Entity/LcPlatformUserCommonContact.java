package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class LcPlatformUserCommonContact implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String contacts_name;
	private String phone_number;
	private String telephone;
	private String address;
	private String province;
	private String city;
	private String county;
	private Integer state;
	private String create_time;
	private Integer contacts_type;
	private String company_name;
	private BigInteger user_id;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setContacts_name(String contacts_name){
		this.contacts_name=contacts_name;
	}
	public String getContacts_name(){
		return contacts_name;
	}
	public void setPhone_number(String phone_number){
		this.phone_number=phone_number;
	}
	public String getPhone_number(){
		return phone_number;
	}
	public void setTelephone(String telephone){
		this.telephone=telephone;
	}
	public String getTelephone(){
		return telephone;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setProvince(String province){
		this.province=province;
	}
	public String getProvince(){
		return province;
	}
	public void setCity(String city){
		this.city=city;
	}
	public String getCity(){
		return city;
	}
	public void setCounty(String county){
		this.county=county;
	}
	public String getCounty(){
		return county;
	}
	public void setState(Integer state){
		this.state=state;
	}
	public Integer getState(){
		return state;
	}
	public void setCreate_time(String create_time){
		this.create_time=create_time;
	}
	public String getCreate_time(){
		return create_time;
	}
	public void setContacts_type(Integer contacts_type){
		this.contacts_type=contacts_type;
	}
	public Integer getContacts_type(){
		return contacts_type;
	}
	public void setCompany_name(String company_name){
		this.company_name=company_name;
	}
	public String getCompany_name(){
		return company_name;
	}
	public void setUser_id(BigInteger user_id){
		this.user_id=user_id;
	}
	public BigInteger getUser_id(){
		return user_id;
	}
}


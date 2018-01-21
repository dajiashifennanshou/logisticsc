package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class TmsOutletsInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String outlets_number;
	private String name;
	private BigInteger type;
	private BigInteger nature;
	private String province;
	private String city;
	private String county;
	private String address;
	private String contact_person;
	private String mobile;
	private String phone;
	private String email;
	private String remark;
	private Integer status;
	private String create_time;
	private BigInteger create_person_id;
	private BigInteger company_id;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getOutlets_number() {
		return outlets_number;
	}
	public void setOutlets_number(String outlets_number) {
		this.outlets_number = outlets_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getType() {
		return type;
	}
	public void setType(BigInteger type) {
		this.type = type;
	}
	public BigInteger getNature() {
		return nature;
	}
	public void setNature(BigInteger nature) {
		this.nature = nature;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public BigInteger getCreate_person_id() {
		return create_person_id;
	}
	public void setCreate_person_id(BigInteger create_person_id) {
		this.create_person_id = create_person_id;
	}
	public BigInteger getCompany_id() {
		return company_id;
	}
	public void setCompany_id(BigInteger company_id) {
		this.company_id = company_id;
	}
	
}


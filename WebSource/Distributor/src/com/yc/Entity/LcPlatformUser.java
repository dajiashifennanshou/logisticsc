package com.yc.Entity; 

import java.io.Serializable;
import java.math.*;

/**
 * 平台用户实体
 * Author:luojing
 * 2016年6月27日 下午4:33:07
 */
public class LcPlatformUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String login_name;
	private String password;
	private String mobile;
	private String email;
	private String address;
	private String true_name;
	private Integer user_type;
	private String cred_type;
	private String cred_number;
	private String cash_password;
	private Integer status;
	private Double balance;
	private Integer points;
	private String salesman_no;
	private String the_selesman_no;
	private BigInteger company_id;
	private BigInteger temporary_company_id;
	private Integer vehicle_type;
	private String dot_address;
	private Integer user_status;
	private String create_time;
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTrue_name() {
		return true_name;
	}
	public void setTrue_name(String true_name) {
		this.true_name = true_name;
	}
	public Integer getUser_type() {
		return user_type;
	}
	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}
	public String getCred_type() {
		return cred_type;
	}
	public void setCred_type(String cred_type) {
		this.cred_type = cred_type;
	}
	public String getCred_number() {
		return cred_number;
	}
	public void setCred_number(String cred_number) {
		this.cred_number = cred_number;
	}
	public String getCash_password() {
		return cash_password;
	}
	public void setCash_password(String cash_password) {
		this.cash_password = cash_password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getSalesman_no() {
		return salesman_no;
	}
	public void setSalesman_no(String salesman_no) {
		this.salesman_no = salesman_no;
	}
	public String getThe_selesman_no() {
		return the_selesman_no;
	}
	public void setThe_selesman_no(String the_selesman_no) {
		this.the_selesman_no = the_selesman_no;
	}
	public BigInteger getCompany_id() {
		return company_id;
	}
	public void setCompany_id(BigInteger company_id) {
		this.company_id = company_id;
	}
	public BigInteger getTemporary_company_id() {
		return temporary_company_id;
	}
	public void setTemporary_company_id(BigInteger temporary_company_id) {
		this.temporary_company_id = temporary_company_id;
	}
	public Integer getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(Integer vehicle_type) {
		this.vehicle_type = vehicle_type;
	}
	public String getDot_address() {
		return dot_address;
	}
	public void setDot_address(String dot_address) {
		this.dot_address = dot_address;
	}
	public Integer getUser_status() {
		return user_status;
	}
	public void setUser_status(Integer user_status) {
		this.user_status = user_status;
	}
}


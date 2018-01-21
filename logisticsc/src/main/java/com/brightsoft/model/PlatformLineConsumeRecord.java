package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 我的预充值消费记录 实体类
 * @author ThinkPad
 *
 */
public class PlatformLineConsumeRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private String companyName;
	
	private String startProvince;
	
	private String startCity;
	
	private String endProvince;
	
	private String endCity;
	
	private String consumeUser;

	private String chargeUser;
	
	private Double money;
	
	private Date consumeTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStartProvince() {
		return startProvince;
	}

	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndProvince() {
		return endProvince;
	}

	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public String getConsumeUser() {
		return consumeUser;
	}

	public void setConsumeUser(String consumeUser) {
		this.consumeUser = consumeUser;
	}

	public String getChargeUser() {
		return chargeUser;
	}

	public void setChargeUser(String chargeUser) {
		this.chargeUser = chargeUser;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}

	
}

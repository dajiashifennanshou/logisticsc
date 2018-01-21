package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 线路预存额
 * @author ThinkPad
 *
 */
public class PlatformLineStorage implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private Long lineId;
	
	private Long companyId;
	
	private Long moneyId;
	
	private String companyName;
	
	private String startProvince;
	
	private String startCity;
	
	private String endProvince;
	
	private String endCity;
	
	private Double money;
	
	private Date operTime;
	
	public Long getMoneyId() {
		return moneyId;
	}

	public void setMoneyId(Long moneyId) {
		this.moneyId = moneyId;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long userId) {
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	
	
}

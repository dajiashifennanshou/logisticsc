package com.brightsoft.model;

import java.io.Serializable;

public class PlatformHomeCount implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer finalPriceCount;
	private Integer orderCount;
	private Integer userCompanyCount;
	private Integer userProviderCount;
	public Integer getFinalPriceCount() {
		return finalPriceCount;
	}
	public void setFinalPriceCount(Integer finalPriceCount) {
		this.finalPriceCount = finalPriceCount;
	}
	public Integer getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
	public Integer getUserCompanyCount() {
		return userCompanyCount;
	}
	public void setUserCompanyCount(Integer userCompanyCount) {
		this.userCompanyCount = userCompanyCount;
	}
	public Integer getUserProviderCount() {
		return userProviderCount;
	}
	public void setUserProviderCount(Integer userProviderCount) {
		this.userProviderCount = userProviderCount;
	}
	
	
}

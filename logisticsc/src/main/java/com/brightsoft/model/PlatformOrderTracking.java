package com.brightsoft.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单跟踪 显示数据表
 * @author ThinkPad
 *
 */
public class PlatformOrderTracking implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String condition;//查询输入条件
	
	private String orderNumber;

	 private String wayBillNumber;
	
	 private String startProvince;
	
	 private String startCity;
	 
	 private String startCounty;
	    
	 private String endProvince;
	
	 private String endCity;
	 
	 private String endCounty;
	 
	 private String companyName;
	 
	 List<PlatformOrderFollow> follows = new ArrayList<PlatformOrderFollow>();

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
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

	public String getStartCounty() {
		return startCounty;
	}

	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
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

	public String getEndCounty() {
		return endCounty;
	}

	public void setEndCounty(String endCounty) {
		this.endCounty = endCounty;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<PlatformOrderFollow> getFollows() {
		return follows;
	}

	public void setFollows(List<PlatformOrderFollow> follows) {
		this.follows = follows;
	}
			 
}

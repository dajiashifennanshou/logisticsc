package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 商铺用户评价
 * @author ThinkPad
 *
 */
public class PlatformUserEvaluation implements Serializable{

	private static final long serialVersionUID = 1L;

	private String evaluateContent;

	  private Date evaluateTime;

	  private Integer evaluateLevel;
	  
	  private String loginName;
	  
	  private Long companyId;
	  
	  private String startProvince;

	  private String startCity;
	 
	  private String startCounty;
	    
	  private String endProvince;

	  private String endCity;
	 
	  private String endCounty;

	public String getEvaluateContent() {
		return evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}

	public Integer getEvaluateLevel() {
		return evaluateLevel;
	}

	public void setEvaluateLevel(Integer evaluateLevel) {
		this.evaluateLevel = evaluateLevel;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	  
	  
}

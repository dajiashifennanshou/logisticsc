package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import com.brightsoft.utils.DateTools;

/**
 * 订单评价 列表
 * @author ThinkPad
 *
 */
public class PlatformOrderMineEvaluation implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long evaId;
	
    private String evaluateContent;

    private Date evaluateTime;

    private Integer evaluateLevel;

    private Long userId;

    private Integer state;
    
    private String orderNumber;

    private String wayBillNumber;
    
    private String startProvince;

    private String startCity;
    private String startCounty;
    
    private String endProvince;

    private String endCity;
    private String endCounty;
    private String companyName;
    
    private String startTime;//起始时间
    
    private String endTime;//结束时间
    
    private String condition;//查询输入条件
    
    private String evaluateTimeStr;
    
    private Integer status;
    
    private String companyCode;
    
    

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEvaluateTimeStr() {
		return evaluateTimeStr;
	}

	public void setEvaluateTimeStr(String evaluateTimeStr) {
		this.evaluateTimeStr = evaluateTimeStr;
	}

	public Long getEvaId() {
		return evaId;
	}

	public void setEvaId(Long evaId) {
		this.evaId = evaId;
	}

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
		this.evaluateTimeStr = DateTools.dateToStr2(evaluateTime);
		this.evaluateTime = evaluateTime;
	}

	public Integer getEvaluateLevel() {
		return evaluateLevel;
	}

	public void setEvaluateLevel(Integer evaluateLevel) {
		this.evaluateLevel = evaluateLevel;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
    
}

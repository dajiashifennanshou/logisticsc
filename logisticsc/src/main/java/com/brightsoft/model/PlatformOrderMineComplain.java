package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import com.brightsoft.utils.DateTools;

/**
 * 订单投诉表 列表
 * @author ThinkPad
 *
 */
public class PlatformOrderMineComplain implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long complainId;
	
	private String complaintContent;

	private Date complaintTime;

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
    
    private Integer status;
    
    private String startTime;//起始时间
    
    private String endTime;//结束时间
	    
	private String condition;//查询输入条件
	
	private String complaintTimeStr;
	
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

	public String getComplaintTimeStr() {
		return complaintTimeStr;
	}

	public void setComplaintTimeStr(String complaintTimeStr) {
		this.complaintTimeStr = complaintTimeStr;
	}

	public Long getComplainId() {
		return complainId;
	}

	public void setComplainId(Long complainId) {
		this.complainId = complainId;
	}

	public String getComplaintContent() {
		return complaintContent;
	}

	public void setComplaintContent(String complaintContent) {
		this.complaintContent = complaintContent;
	}

	public Date getComplaintTime() {
		return complaintTime;
	}

	public void setComplaintTime(Date complaintTime) {
		this.complaintTimeStr = DateTools.dateToStr2(complaintTime);
		this.complaintTime = complaintTime;
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

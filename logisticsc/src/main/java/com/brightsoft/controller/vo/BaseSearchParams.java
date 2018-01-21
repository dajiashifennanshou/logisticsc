package com.brightsoft.controller.vo;

/**
 * 查询 条件 基础类
 * @author yangshoubao
 *
 */
public class BaseSearchParams {

	private String status;
	
	private String startTime;
	
	private String endTime;
	
	private String condition;
	
	private Integer start;
	
	private Integer limit;
	
	private Long companyId;
	
	private Long outletsId;
	private String outletsIds;
	public String getOutletsIds() {
		return outletsIds;
	}

	public void setOutletsIds(String outletsIds) {
		this.outletsIds = outletsIds;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getOutletsId() {
		return outletsId;
	}

	public void setOutletsId(Long outletsId) {
		this.outletsId = outletsId;
	}
	
}

package com.brightsoft.controller.vo;

/**
 * 查询 条件
 * @author yangshoubao
 *
 */
public class SearchParams {

	private String status,outletsIds;
	
	private String startTime;
	
	private String endTime;
	
	private String subject;
	
	private String condition;
	
	private Integer start;
	
	private Integer limit;
	
	private Long outletsId;
	
	private Long startOutlets;
	
	private Long targetOutlets;
	
	private Integer endOutlets;
	
	private Integer abnormalType;
	
	private  Integer publishType;
	
	private String insComTag[];
	
	private String insType[];
	
	private Integer insStatus[];
	
	private String startProvince;
	
	private String startCity;
	
	private String startCounty;
	
	private String endProvince;
	
	private String endCity;
	
	private String endCounty;
	
	private int newsType; //新闻类型
	
	private String outletsName;
	
	
	
	public String getOutletsIds() {
		return outletsIds;
	}

	public void setOutletsIds(String outletsIds) {
		this.outletsIds = outletsIds;
	}

	public int getNewsType() {
		return newsType;
	}

	public void setNewsType(int newsType) {
		this.newsType = newsType;
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

	public String[] getInsComTag() {
		return insComTag;
	}

	public void setInsComTag(String[] insComTag) {
		this.insComTag = insComTag;
	}

	public String[] getInsType() {
		return insType;
	}

	public void setInsType(String[] insType) {
		this.insType = insType;
	}

	public Integer[] getInsStatus() {
		return insStatus;
	}

	public void setInsStatus(Integer[] insStatus) {
		this.insStatus = insStatus;
	}

	public Integer getPublishType() {
		return publishType;
	}

	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}

	public Long getStartOutlets() {
		return startOutlets;
	}

	public void setStartOutlets(Long startOutlets) {
		this.startOutlets = startOutlets;
	}

	public Long getTargetOutlets() {
		return targetOutlets;
	}

	public void setTargetOutlets(Long targetOutlets) {
		this.targetOutlets = targetOutlets;
	}

	public Long getOutletsId() {
		return outletsId;
	}

	public void setOutletsId(Long outletsId) {
		this.outletsId = outletsId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public Integer getEndOutlets() {
		return endOutlets;
	}

	public void setEndOutlets(Integer endOutlets) {
		this.endOutlets = endOutlets;
	}

	public Integer getAbnormalType() {
		return abnormalType;
	}

	public void setAbnormalType(Integer abnormalType) {
		this.abnormalType = abnormalType;
	}

	public String getOutletsName() {
		return outletsName;
	}

	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}
	
}

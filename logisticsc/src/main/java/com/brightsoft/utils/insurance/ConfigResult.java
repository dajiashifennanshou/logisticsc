package com.brightsoft.utils.insurance;

import java.util.List;

public class ConfigResult {

	private String serverCode;
	private String backInsValue;
	private Long backInsTime;
	private String userKey;
	private List<InsCompany> insCompanyList;
	private List<InsType> insTypeList;
	private List<InsTsType> insTsTypeList;
	public List<InsTsType> getInsTsTypeList() {
		return insTsTypeList;
	}
	public void setInsTsTypeList(List<InsTsType> insTsTypeList) {
		this.insTsTypeList = insTsTypeList;
	}
	public String getServerCode() {
		return serverCode;
	}
	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}
	public String getBackInsValue() {
		return backInsValue;
	}
	public void setBackInsValue(String backInsValue) {
		this.backInsValue = backInsValue;
	}
	public Long getBackInsTime() {
		return backInsTime;
	}
	public void setBackInsTime(Long backInsTime) {
		this.backInsTime = backInsTime;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public List<InsCompany> getInsCompanyList() {
		return insCompanyList;
	}
	public void setInsCompanyList(List<InsCompany> insCompanyList) {
		this.insCompanyList = insCompanyList;
	}
	public List<InsType> getInsTypeList() {
		return insTypeList;
	}
	public void setInsTypeList(List<InsType> insTypeList) {
		this.insTypeList = insTypeList;
	}
}

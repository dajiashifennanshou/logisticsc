package com.brightsoft.controller.vo;


public class InsuranceBackData {

	private int backInsStatus;//状态码
	private String backInsValue;//投保失败说明
	private String userKey;//代理商key
	private int backInsTime;
	private String insOrderNum;//返回代理商提交时的订单号
	private String insSysBaodan;//返回伊恩方内部订单号，及投保单号
	private String insLastBaodan;//返回正式保单号，保险公司正式保单
	private String insFileUrl;//返回保单下载地址

	public int getBackInsTime() {
		return backInsTime;
	}
	public void setBackInsTime(int backInsTime) {
		this.backInsTime = backInsTime;
	}
	public String getInsSysBaodan() {
		return insSysBaodan;
	}
	public void setInsSysBaodan(String insSysBaodan) {
		this.insSysBaodan = insSysBaodan;
	}
	public String getInsLastBaodan() {
		return insLastBaodan;
	}
	public void setInsLastBaodan(String insLastBaodan) {
		this.insLastBaodan = insLastBaodan;
	}
	public int getBackInsStatus() {
		return backInsStatus;
	}
	public void setBackInsStatus(int backInsStatus) {
		this.backInsStatus = backInsStatus;
	}
	public String getBackInsValue() {
		return backInsValue;
	}
	public void setBackInsValue(String backInsValue) {
		this.backInsValue = backInsValue;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getInsOrderNum() {
		return insOrderNum;
	}
	public void setInsOrderNum(String insOrderNum) {
		this.insOrderNum = insOrderNum;
	}
	
	public String getInsFileUrl() {
		return insFileUrl;
	}
	public void setInsFileUrl(String insFileUrl) {
		this.insFileUrl = insFileUrl;
	}
}

package com.brightsoft.utils.insurance;

public class OrderResult {
	private int serverCode;
	private String backInsValue;
	private int backInsTime;
	private String userKey;
	public int getServerCode() {
		return serverCode;
	}
	public void setServerCode(int serverCode) {
		this.serverCode = serverCode;
	}
	public String getBackInsValue() {
		return backInsValue;
	}
	public void setBackInsValue(String backInsValue) {
		this.backInsValue = backInsValue;
	}
	public int getBackInsTime() {
		return backInsTime;
	}
	public void setBackInsTime(int backInsTime) {
		this.backInsTime = backInsTime;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
}

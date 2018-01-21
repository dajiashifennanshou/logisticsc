package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class PlatformUserMailbox implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String loginName;
	 
	 private String email;
	 
	 private String eamilCode;
	 
	 private Date   eamilCodeTime;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEamilCode() {
		return eamilCode;
	}

	public void setEamilCode(String eamilCode) {
		this.eamilCode = eamilCode;
	}

	public Date getEamilCodeTime() {
		return eamilCodeTime;
	}

	public void setEamilCodeTime(Date eamilCodeTime) {
		this.eamilCodeTime = eamilCodeTime;
	}
	 
	 
}

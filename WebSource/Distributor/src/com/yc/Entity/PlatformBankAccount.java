package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class PlatformBankAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String customernumber;
	private String ledgerno;
	private String requestid;
	private String hmac;
	private String time;
	private BigInteger user_id;

	public PlatformBankAccount(BigInteger user_id) {
		super();
		this.user_id = user_id;
	}
	public PlatformBankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setCustomernumber(String customernumber){
		this.customernumber=customernumber;
	}
	public String getCustomernumber(){
		return customernumber;
	}
	public void setLedgerno(String ledgerno){
		this.ledgerno=ledgerno;
	}
	public String getLedgerno(){
		return ledgerno;
	}
	public void setRequestid(String requestid){
		this.requestid=requestid;
	}
	public String getRequestid(){
		return requestid;
	}
	public void setHmac(String hmac){
		this.hmac=hmac;
	}
	public String getHmac(){
		return hmac;
	}
	public void setTime(String time){
		this.time=time;
	}
	public String getTime(){
		return time;
	}
	public void setUser_id(BigInteger user_id){
		this.user_id=user_id;
	}
	public BigInteger getUser_id(){
		return user_id;
	}
}


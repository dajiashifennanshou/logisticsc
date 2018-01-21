package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class LcPlatformStoreRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private BigInteger line_id;
	private BigInteger user_id;
	private Integer state;
	private String collection_time;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setLine_id(BigInteger line_id){
		this.line_id=line_id;
	}
	public BigInteger getLine_id(){
		return line_id;
	}
	public void setUser_id(BigInteger user_id){
		this.user_id=user_id;
	}
	public BigInteger getUser_id(){
		return user_id;
	}
	public void setState(Integer state){
		this.state=state;
	}
	public Integer getState(){
		return state;
	}
	public void setCollection_time(String collection_time){
		this.collection_time=collection_time;
	}
	public String getCollection_time(){
		return collection_time;
	}
}


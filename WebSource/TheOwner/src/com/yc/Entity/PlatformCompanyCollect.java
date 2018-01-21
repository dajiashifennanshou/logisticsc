package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class PlatformCompanyCollect implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private BigInteger userId;
	private BigInteger comId;
	private Integer status;
	private String createTime;
	private PlatformUserCompany com;
	public PlatformUserCompany getCom() {
		return com;
	}
	public void setCom(PlatformUserCompany com) {
		this.com = com;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setUserId(BigInteger userId){
		this.userId=userId;
	}
	public BigInteger getUserId(){
		return userId;
	}
	public void setComId(BigInteger comId){
		this.comId=comId;
	}
	public BigInteger getComId(){
		return comId;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
}


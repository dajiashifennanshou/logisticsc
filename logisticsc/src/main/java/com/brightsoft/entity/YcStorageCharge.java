package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcStorageCharge
	ID    BIGINT(20)
	BRANCHNO    VARCHAR(50)
	ZONENO    VARCHAR(50)
	ZONEAREA    FLOAT(12,31)
	CHARGENORM    FLOAT(12,31)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcStorageCharge implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String branchNo;
	private String zoneNo;
	private Float zoneArea;
	private Float chargeNorm;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	
	private String branchName;

	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setBranchNo(String branchNo){
		this.branchNo=branchNo;
	}
	public String getBranchNo(){
		return branchNo;
	}
	public void setZoneNo(String zoneNo){
		this.zoneNo=zoneNo;
	}
	public String getZoneNo(){
		return zoneNo;
	}
	public void setZoneArea(Float zoneArea){
		this.zoneArea=zoneArea;
	}
	public Float getZoneArea(){
		return zoneArea;
	}
	public void setChargeNorm(Float chargeNorm){
		this.chargeNorm=chargeNorm;
	}
	public Float getChargeNorm(){
		return chargeNorm;
	}
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	public String getCreateUser(){
		return createUser;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime=updateTime;
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}
	public String getUpdateUser(){
		return updateUser;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}


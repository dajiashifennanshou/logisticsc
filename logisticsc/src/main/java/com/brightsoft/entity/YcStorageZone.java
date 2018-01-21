package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcStorageZone
	ID    BIGINT(20)
	STORAGENO    VARCHAR(50)
	ZONENO    VARCHAR(50)
	ZONESTATUS    INT(11)
	ZONEAREA    FLOAT(12,31)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcStorageZone implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String storageNo;
	private String zoneNo;
	private Integer zoneStatus;
	private Float zoneArea;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String zoneName;
	
	private String remark;
	
	
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
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
	public void setStorageNo(String storageNo){
		this.storageNo=storageNo;
	}
	public String getStorageNo(){
		return storageNo;
	}
	public void setZoneNo(String zoneNo){
		this.zoneNo=zoneNo;
	}
	public String getZoneNo(){
		return zoneNo;
	}
	public void setZoneStatus(Integer zoneStatus){
		this.zoneStatus=zoneStatus;
	}
	public Integer getZoneStatus(){
		return zoneStatus;
	}
	public void setZoneArea(Float zoneArea){
		this.zoneArea=zoneArea;
	}
	public Float getZoneArea(){
		return zoneArea;
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


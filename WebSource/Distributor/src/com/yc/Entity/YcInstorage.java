package com.yc.Entity; 

import java.io.Serializable;
import java.math.*;


/** YcInstorage
	ID    BIGINT(20)
	WAYBILLNO    VARCHAR(50)
	WAYBILLSOURCE    INT(5)
	INTYPE    INT(5)
	BRANCHNO    VARCHAR(50)
	ZONENO    VARCHAR(50)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcInstorage implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String waybillNo;
	private Integer waybillSource;
	private Integer inType;
	private String branchNo;
	private String zoneNo;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	
	private String branchName;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setWaybillNo(String waybillNo){
		this.waybillNo=waybillNo;
	}
	public String getWaybillNo(){
		return waybillNo;
	}
	public void setWaybillSource(Integer waybillSource){
		this.waybillSource=waybillSource;
	}
	public Integer getWaybillSource(){
		return waybillSource;
	}
	public void setInType(Integer inType){
		this.inType=inType;
	}
	public Integer getInType(){
		return inType;
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
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}


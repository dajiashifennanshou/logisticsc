package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcDealer
	ID    BIGINT(20)
	DEALERNAME    VARCHAR(50)
	BRANCHNO    VARCHAR(50)
	ZONENO    VARCHAR(50)
	ZONEAREA    FLOAT(12,31)
	PHONE    VARCHAR(30)
	TELEPHONE    VARCHAR(30)
	ADDRESS    VARCHAR(50)
	STARTJOINTIME    DATETIME(19)
	ENDJOINTIME    DATETIME(19)
	JOINCOST    FLOAT(12,31)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcDealer implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String dealerName;
	private String branchNo;
	private String zoneNo;
	private Float zoneArea;
	private String phone;
	private String telephone;
	private String address;
	private String startJoinTime;
	private String endJoinTime;
	private Float joinCost;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private Integer status;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setDealerName(String dealerName){
		this.dealerName=dealerName;
	}
	public String getDealerName(){
		return dealerName;
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
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setTelephone(String telephone){
		this.telephone=telephone;
	}
	public String getTelephone(){
		return telephone;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setStartJoinTime(String startJoinTime){
		this.startJoinTime=startJoinTime;
	}
	public String getStartJoinTime(){
		return startJoinTime;
	}
	public void setEndJoinTime(String endJoinTime){
		this.endJoinTime=endJoinTime;
	}
	public String getEndJoinTime(){
		return endJoinTime;
	}
	public void setJoinCost(Float joinCost){
		this.joinCost=joinCost;
	}
	public Float getJoinCost(){
		return joinCost;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}


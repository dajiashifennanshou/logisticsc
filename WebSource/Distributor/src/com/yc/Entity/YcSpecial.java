package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** YcSpecial
	ID    BIGINT(20)
	COMPANYCODE    VARCHAR(50)
	COMPANYNAME    VARCHAR(50)
	BRANCHNO    VARCHAR(50)
	ZONENO    VARCHAR(50)
	BRANCHCODE    VARCHAR(50)
	BRANCHNAME    VARCHAR(50)
	ZONEAREA    FLOAT(12,31)
	STATUS    INT(11)
	BRANCHZONE    VARCHAR(500)
	LINKMAN    VARCHAR(20)
	PHONE    VARCHAR(30)
	TELEPHONE    VARCHAR(30)
	EMAIL    VARCHAR(30)
	ADDRESS    VARCHAR(100)
	STARTJOINTIME    DATETIME(19)
	ENDJOINTIME    DATETIME(19)
	JOINCOST    FLOAT(12,31)
	PAYORDERNUM    VARCHAR(50)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcSpecial implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String companyCode;
	private String companyName;
	private String branchNo;
	private String zoneNo;
	private String branchCode;
	private String branchName;
	private Float zoneArea;
	private Integer status;
	private String branchzone;
	private String linkman;
	private String phone;
	private String telephone;
	private String email;
	private String address;
	private String startJoinTime;
	private String endJoinTime;
	private Float joinCost;
	private String payOrderNum;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setCompanyCode(String companyCode){
		this.companyCode=companyCode;
	}
	public String getCompanyCode(){
		return companyCode;
	}
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}
	public String getCompanyName(){
		return companyName;
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
	public void setBranchCode(String branchCode){
		this.branchCode=branchCode;
	}
	public String getBranchCode(){
		return branchCode;
	}
	public void setBranchName(String branchName){
		this.branchName=branchName;
	}
	public String getBranchName(){
		return branchName;
	}
	public void setZoneArea(Float zoneArea){
		this.zoneArea=zoneArea;
	}
	public Float getZoneArea(){
		return zoneArea;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setBranchzone(String branchzone){
		this.branchzone=branchzone;
	}
	public String getBranchzone(){
		return branchzone;
	}
	public void setLinkman(String linkman){
		this.linkman=linkman;
	}
	public String getLinkman(){
		return linkman;
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
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return email;
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
	public void setPayOrderNum(String payOrderNum){
		this.payOrderNum=payOrderNum;
	}
	public String getPayOrderNum(){
		return payOrderNum;
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


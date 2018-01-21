package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;

public class YcJoinInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private BigInteger joinerId;
	private String branchNo;
	private Integer joinType;
	private String zoneNo;
	private Float apolyArea;
	private Integer applyStatus;
	private Integer days;
	private String startUseTime;
	private String endUseTime;
	private String dealerMsg;
	private Float prepaid;
	private Float surplus;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private String joinName;
	//加盟商名字和电话，通过joinerid查询，在service查询时
	private String joiner;
	private String joinPhone;

	
	
	public String getJoiner() {
		return joiner;
	}
	public void setJoiner(String joiner) {
		this.joiner = joiner;
	}
	public String getJoinPhone() {
		return joinPhone;
	}
	public void setJoinPhone(String joinPhone) {
		this.joinPhone = joinPhone;
	}
	public String getJoinName() {
		return joinName;
	}
	public void setJoinName(String joinName) {
		this.joinName = joinName;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setJoinerId(BigInteger joinerId){
		this.joinerId=joinerId;
	}
	public BigInteger getJoinerId(){
		return joinerId;
	}
	public void setBranchNo(String branchNo){
		this.branchNo=branchNo;
	}
	public String getBranchNo(){
		return branchNo;
	}
	public void setJoinType(Integer joinType){
		this.joinType=joinType;
	}
	public Integer getJoinType(){
		return joinType;
	}
	public void setZoneNo(String zoneNo){
		this.zoneNo=zoneNo;
	}
	public String getZoneNo(){
		return zoneNo;
	}
	public void setApolyArea(Float apolyArea){
		this.apolyArea=apolyArea;
	}
	public Float getApolyArea(){
		return apolyArea;
	}
	public void setApplyStatus(Integer applyStatus){
		this.applyStatus=applyStatus;
	}
	public Integer getApplyStatus(){
		return applyStatus;
	}
	public void setStartUseTime(String startUseTime){
		this.startUseTime=startUseTime;
	}
	public String getStartUseTime(){
		return startUseTime;
	}
	public void setEndUseTime(String endUseTime){
		this.endUseTime=endUseTime;
	}
	public String getEndUseTime(){
		return endUseTime;
	}
	public void setDealerMsg(String dealerMsg){
		this.dealerMsg=dealerMsg;
	}
	public String getDealerMsg(){
		return dealerMsg;
	}
	public void setPrepaid(Float prepaid){
		this.prepaid=prepaid;
	}
	public Float getPrepaid(){
		return prepaid;
	}
	public void setSurplus(Float surplus){
		this.surplus=surplus;
	}
	public Float getSurplus(){
		return surplus;
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
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
}


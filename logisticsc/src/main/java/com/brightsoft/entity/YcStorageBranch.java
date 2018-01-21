package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcStorageBranch
	ID    BIGINT(20)
	BRANCHNO    VARCHAR(30)
	BRANCHNAME    VARCHAR(50)
	JOINTYPE    INT(5)
	BRANCHAREA    FLOAT(12,31)
	PROVINCE    VARCHAR(20)
	CITY    VARCHAR(20)
	COUNTY    VARCHAR(20)
	TOWN    VARCHAR(20)
	BRANCHTYPE    INT(5)
	MASTERID    BIGINT(20)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcStorageBranch implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String branchNo;
	private String branchName;
	private Integer joinType;
	private Float branchArea;
	private Float useArea;
	private String province;
	private String city;
	private String county;
	private String town;
	private Integer branchType;
	private String createTime;
	private String masterName;
	private String masterPhone;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	
	private String employeeName;
	private String phone;

	
	
	
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public String getMasterPhone() {
		return masterPhone;
	}
	public void setMasterPhone(String masterPhone) {
		this.masterPhone = masterPhone;
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
	public void setBranchName(String branchName){
		this.branchName=branchName;
	}
	public String getBranchName(){
		return branchName;
	}
	public void setJoinType(Integer joinType){
		this.joinType=joinType;
	}
	public Integer getJoinType(){
		return joinType;
	}
	public void setBranchArea(Float branchArea){
		this.branchArea=branchArea;
	}
	public Float getBranchArea(){
		return branchArea;
	}
	public void setProvince(String province){
		this.province=province;
	}
	public String getProvince(){
		return province;
	}
	public void setCity(String city){
		this.city=city;
	}
	public String getCity(){
		return city;
	}
	public void setCounty(String county){
		this.county=county;
	}
	public String getCounty(){
		return county;
	}
	public void setTown(String town){
		this.town=town;
	}
	public String getTown(){
		return town;
	}
	public void setBranchType(Integer branchType){
		this.branchType=branchType;
	}
	public Integer getBranchType(){
		return branchType;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Float getUseArea() {
		return useArea;
	}
	public void setUseArea(Float useArea) {
		this.useArea = useArea;
	}
}


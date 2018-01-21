package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** YcJoinInfo
	ID    BIGINT(20)
	JOINERID    BIGINT(20)
	BRANCHNO    VARCHAR(50)
	JIONTYPE    INT(2)
	ZONENO    VARCHAR(50)
	APOLYAREA    FLOAT(12,31)
	APPLYSTATUS    INT(11)
	STARTUSETIME    DATETIME(19)
	ENDUSETIME    DATETIME(19)
	DEALERMSG    VARCHAR(500)
	PREPAID    FLOAT(12,31)
	SURPLUS    FLOAT(12,31)
	CREATETIME    DATETIME(19)
	CREATEUSER    DATETIME(19)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcJoinInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String joinName;
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
	
	private String branchName;
	
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public YcJoinInfo(BigInteger joinerId) {
		super();
		this.joinerId = joinerId;
	}
	public String getJoinName() {
		return joinName;
	}

	public void setJoinName(String joinName) {
		this.joinName = joinName;
	}

	public YcJoinInfo() {
		super();
		// TODO Auto-generated constructor stub
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


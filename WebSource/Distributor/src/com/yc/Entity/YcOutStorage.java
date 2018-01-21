package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** YcOutStorage
	ID    BIGINT(20)
	ZONENO    VARCHAR(50)
	DEALERID    BIGINT(20)
	BRACHNO    VARCHAR(50)
	DELIVERYNO    VARCHAR(50)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcOutStorage implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String zoneNo;
	private BigInteger dealerId;
	private Integer jionType;
	private String branchNo;
	private String deliveryNo;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private String stowageNo;
	private float goodsNum;
	private Integer outType;
	

	
	public String getStowageNo() {
		return stowageNo;
	}
	public void setStowageNo(String stowageNo) {
		this.stowageNo = stowageNo;
	}
	public Integer getOutType() {
		return outType;
	}
	public void setOutType(Integer outType) {
		this.outType = outType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public float getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(float goodsNum) {
		this.goodsNum = goodsNum;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setZoneNo(String zoneNo){
		this.zoneNo=zoneNo;
	}
	public String getZoneNo(){
		return zoneNo;
	}
	public void setDealerId(BigInteger dealerId){
		this.dealerId=dealerId;
	}
	public BigInteger getDealerId(){
		return dealerId;
	}
	public void setBranchNo(String branchNo){
		this.branchNo=branchNo;
	}
	public String getBranchNo(){
		return branchNo;
	}
	public void setDeliveryNo(String deliveryNo){
		this.deliveryNo=deliveryNo;
	}
	public String getDeliveryNo(){
		return deliveryNo;
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
	public Integer getJionType() {
		return jionType;
	}
	public void setJionType(Integer jionType) {
		this.jionType = jionType;
	}
}


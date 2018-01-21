package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcInstallCharge
	ID    BIGINT(20)
	INSTALLCHARGE    VARCHAR(50)
	DELIVERCHARGE    VARCHAR(50)
	BRANCHNO    VARCHAR(50)
	DISCOUNT    FLOAT(12,31)
	FIRSTLVCODE    INT(11)
	FIRSTLVNAME    VARCHAR(50)
	FIRSTLVEXPLAN    VARCHAR(50)
	FIRSTLVTIPS    VARCHAR(50)
	TWOLVCODE    INT(11)
	TOWLVNAME    VARCHAR(50)
	TWOLVEXPLAN    VARCHAR(50)
	TWOLVTIPS    VARCHAR(50)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcInstallCharge implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private float installCharge;
	private float deliverCharge;
	private String branchNo;
	private Float discount;
	private Integer firstLvCode;
	private String firstLvName;
	private String firstLvExplan;
	private String firstLvTips;
	private Integer twoLvCode;
	private String towLvName;
	private String twoLvExplan;
	private String twoLvTips;
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
	
	public float getInstallCharge() {
		return installCharge;
	}
	public void setInstallCharge(float installCharge) {
		this.installCharge = installCharge;
	}
	
	public float getDeliverCharge() {
		return deliverCharge;
	}
	public void setDeliverCharge(float deliverCharge) {
		this.deliverCharge = deliverCharge;
	}
	public void setBranchNo(String branchNo){
		this.branchNo=branchNo;
	}
	public String getBranchNo(){
		return branchNo;
	}
	public void setDiscount(Float discount){
		this.discount=discount;
	}
	public Float getDiscount(){
		return discount;
	}
	public void setFirstLvCode(Integer firstLvCode){
		this.firstLvCode=firstLvCode;
	}
	public Integer getFirstLvCode(){
		return firstLvCode;
	}
	public void setFirstLvName(String firstLvName){
		this.firstLvName=firstLvName;
	}
	public String getFirstLvName(){
		return firstLvName;
	}
	public void setFirstLvExplan(String firstLvExplan){
		this.firstLvExplan=firstLvExplan;
	}
	public String getFirstLvExplan(){
		return firstLvExplan;
	}
	public void setFirstLvTips(String firstLvTips){
		this.firstLvTips=firstLvTips;
	}
	public String getFirstLvTips(){
		return firstLvTips;
	}
	public void setTwoLvCode(Integer twoLvCode){
		this.twoLvCode=twoLvCode;
	}
	public Integer getTwoLvCode(){
		return twoLvCode;
	}

	public String getTowLvName() {
		return towLvName;
	}
	public void setTowLvName(String towLvName) {
		this.towLvName = towLvName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setTwoLvExplan(String twoLvExplan){
		this.twoLvExplan=twoLvExplan;
	}
	public String getTwoLvExplan(){
		return twoLvExplan;
	}
	public void setTwoLvTips(String twoLvTips){
		this.twoLvTips=twoLvTips;
	}
	public String getTwoLvTips(){
		return twoLvTips;
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


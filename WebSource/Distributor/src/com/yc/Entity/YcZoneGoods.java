package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** YcZoneGoods
	ID    BIGINT(20)
	ZONENO    VARCHAR(50)
	BRANCHNO    VARCHAR(50)
	DEALERID    BIGINT(20)
	GOODSNUM    INT(5)
	INZONESTATUS    INT(5)
	GOODSID    BIGINT(20)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcZoneGoods implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String zoneNo;
	private String branchNo;
	private BigInteger dealerId;
	private Integer goodsNum;
	private Integer inZoneStatus;
	private BigInteger goodsId;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private String wayBillNo;
	private Integer wayBillStatus;
	
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWayBillNo() {
		return wayBillNo;
	}
	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}
	public Integer getWayBillStatus() {
		return wayBillStatus;
	}
	public void setWayBillStatus(Integer wayBillStatus) {
		this.wayBillStatus = wayBillStatus;
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
	public void setBranchNo(String branchNo){
		this.branchNo=branchNo;
	}
	public String getBranchNo(){
		return branchNo;
	}
	public void setDealerId(BigInteger dealerId){
		this.dealerId=dealerId;
	}
	public BigInteger getDealerId(){
		return dealerId;
	}
	public void setGoodsNum(Integer goodsNum){
		this.goodsNum=goodsNum;
	}
	public Integer getGoodsNum(){
		return goodsNum;
	}
	public void setInZoneStatus(Integer inZoneStatus){
		this.inZoneStatus=inZoneStatus;
	}
	public Integer getInZoneStatus(){
		return inZoneStatus;
	}
	public void setGoodsId(BigInteger goodsId){
		this.goodsId=goodsId;
	}
	public BigInteger getGoodsId(){
		return goodsId;
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


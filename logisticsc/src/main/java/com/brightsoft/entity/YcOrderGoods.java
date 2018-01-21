package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcOrderGoods
	ID    BIGINT(20)
	DELIVERYNO    VARCHAR(50)
	ZONEGOODSID    BIGINT(20)
	SUM    INT(11)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcOrderGoods implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String deliveryNo;
	private BigInteger zoneGoodsId;
	private Float installCost;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private String zoneNo;
	private String wayBillNo;

	
	public String getWayBillNo() {
		return wayBillNo;
	}
	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}
	public String getZoneNo() {
		return zoneNo;
	}
	public void setZoneNo(String zoneNo) {
		this.zoneNo = zoneNo;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setDeliveryNo(String deliveryNo){
		this.deliveryNo=deliveryNo;
	}
	public String getDeliveryNo(){
		return deliveryNo;
	}
	public void setZoneGoodsId(BigInteger zoneGoodsId){
		this.zoneGoodsId=zoneGoodsId;
	}
	public BigInteger getZoneGoodsId(){
		return zoneGoodsId;
	}
	
	public Float getInstallCost() {
		return installCost;
	}
	public void setInstallCost(Float installCost) {
		this.installCost = installCost;
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


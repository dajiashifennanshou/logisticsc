package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** YcGoods
	ID    BIGINT(20)
	GOODSNAM    VARCHAR
	GOODSBRAND    VARCHAR
	PARK    INT(2)
	GOODSTYPE    VARCHAR
	VENDER    VARCHAR(100)
	WEIGHT    FLOAT(12,31)
	VOLUME    FLOAT(12,31)
	ELSEEXPLAN    VARCHAR(200)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcGoods implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String goodsName;
	private String goodsBrand;
	private Integer park;
	private String goodsType;
	private String vender;
	private Float weight;
	private Float volume;
	private String elseExplan;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private String zoneNo;
	private Integer goodsNum;
	private String wayBillNo;
	private Integer inStorageStatus;
	private Integer outStorageStatus;
	private float installCost;
	private float deliveryCost;
	
	
	
	

	
	
	public float getDeliveryCost() {
		return deliveryCost;
	}
	public void setDeliveryCost(float deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	public float getInstallCost() {
		return installCost;
	}
	public void setInstallCost(float installCost) {
		this.installCost = installCost;
	}
	public Integer getOutStorageStatus() {
		return outStorageStatus;
	}
	public void setOutStorageStatus(Integer outStorageStatus) {
		this.outStorageStatus = outStorageStatus;
	}
	public String getWayBillNo() {
		return wayBillNo;
	}
	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}
	public Integer getInStorageStatus() {
		return inStorageStatus;
	}
	public void setInStorageStatus(Integer inStorageStatus) {
		this.inStorageStatus = inStorageStatus;
	}
	public String getZoneNo() {
		return zoneNo;
	}
	public void setZoneNo(String zoneNo) {
		this.zoneNo = zoneNo;
	}
	
	
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setGoodsName(String goodsNam){
		this.goodsName=goodsNam;
	}
	public String getGoodsName(){
		return goodsName;
	}
	public void setGoodsBrand(String goodsBrand){
		this.goodsBrand=goodsBrand;
	}
	public String getGoodsBrand(){
		return goodsBrand;
	}
	public void setPark(Integer park){
		this.park=park;
	}
	public Integer getPark(){
		return park;
	}
	public void setGoodsType(String goodsType){
		this.goodsType=goodsType;
	}
	public String getGoodsType(){
		return goodsType;
	}
	public void setVender(String vender){
		this.vender=vender;
	}
	public String getVender(){
		return vender;
	}
	public void setWeight(Float weight){
		this.weight=weight;
	}
	public Float getWeight(){
		return weight;
	}
	public void setVolume(Float volume){
		this.volume=volume;
	}
	public Float getVolume(){
		return volume;
	}
	public void setElseExplan(String elseExplan){
		this.elseExplan=elseExplan;
	}
	public String getElseExplan(){
		return elseExplan;
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


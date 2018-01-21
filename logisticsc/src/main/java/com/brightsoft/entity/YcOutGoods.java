package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcOutGoods
	ID    BIGINT(20)
	OUTSTORAGEID    BIGINT(20)
	GOODSNAME    VARCHAR(50)
	GOODSBRAND    VARCHAR(30)
	PARK    INT(2)
	GOODSTYPE    VARCHAR(20)
	VENDER    VARCHAR(100)
	WEIGHT    FLOAT(12,31)
	VOLUME    FLOAT(12,31)
	ELSEEXPLAN    VARCHAR(200)
	REMARK    VARCHAR(500)
	GOODSCOUNT    INT(11)
	GROUPCOUNT    INT(11)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
*/
public class YcOutGoods implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private BigInteger dealerId;
	private String goodsName;
	private String goodsBrand;
	private Integer park;
	private String goodsType;
	private String vender;
	private Float weight;
	private Float volume;
	private String elseExplan;
	private String remark;
	private Integer goodsCount;
	private Integer groupCount;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String stowageNo;
	private String wailBillNo;

	
	
	
	public String getWailBillNo() {
		return wailBillNo;
	}
	public void setWailBillNo(String wailBillNo) {
		this.wailBillNo = wailBillNo;
	}
	public String getStowageNo() {
		return stowageNo;
	}
	public void setStowageNo(String stowageNo) {
		this.stowageNo = stowageNo;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	
	public BigInteger getDealerId() {
		return dealerId;
	}
	public void setDealerId(BigInteger dealerId) {
		this.dealerId = dealerId;
	}
	public void setGoodsName(String goodsName){
		this.goodsName=goodsName;
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
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setGoodsCount(Integer goodsCount){
		this.goodsCount=goodsCount;
	}
	public Integer getGoodsCount(){
		return goodsCount;
	}
	public void setGroupCount(Integer groupCount){
		this.groupCount=groupCount;
	}
	public Integer getGroupCount(){
		return groupCount;
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
}


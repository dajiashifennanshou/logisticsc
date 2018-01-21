package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** YcInGoods
	ID    BIGINT(20)
	INSTORAGEID    BIGINT(20)
	GOODSNAME    VARCHAR(100)
	GOODSBRAND    VARCHAR(30)
	GOODSTYPE    INT(2)
	GOODSCOUNT    INT(10)
	GROUPCOUNT    INT(10)
	PACK    VARCHAR(20)
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
public class YcInGoods implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private BigInteger dealerId;
	private BigInteger inStorageId;
	private String goodsName;
	private String goodsBrand;
	private String goodsType;
	private Integer goodsCount;
	private Integer groupCount;
	private String pack;
	private String vender;
	private Float weight;
	private Float volume;
	private String elseExplan;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;

	
	public BigInteger getDealerId() {
		return dealerId;
	}
	public void setDealerId(BigInteger dealerId) {
		this.dealerId = dealerId;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setInStorageId(BigInteger inStorageId){
		this.inStorageId=inStorageId;
	}
	public BigInteger getInStorageId(){
		return inStorageId;
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
	public void setGoodsType(String goodsType){
		this.goodsType=goodsType;
	}
	public String getGoodsType(){
		return goodsType;
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
	public void setPack(String pack){
		this.pack=pack;
	}
	public String getPack(){
		return pack;
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


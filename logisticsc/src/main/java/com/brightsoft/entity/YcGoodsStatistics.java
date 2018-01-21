package com.brightsoft.entity;

import java.math.BigInteger;

public class YcGoodsStatistics {
	private BigInteger id;
	private String branchNo;
	private String zoneNo;
	private BigInteger dealerId;
	private Integer goodsNum;
	private Integer inZoneStatus;
	private BigInteger goodsId;
	private String createTime;
	private String goodsName;
	private String goodsBrand;
	private Integer park;
	private String goodsType;
	private String vender;
	private Float weight;
	private Float volume;
	private String elseExplan;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public String getZoneNo() {
		return zoneNo;
	}
	public void setZoneNo(String zoneNo) {
		this.zoneNo = zoneNo;
	}
	public BigInteger getDealerId() {
		return dealerId;
	}
	public void setDealerId(BigInteger dealerId) {
		this.dealerId = dealerId;
	}
	public Integer getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	public Integer getInZoneStatus() {
		return inZoneStatus;
	}
	public void setInZoneStatus(Integer inZoneStatus) {
		this.inZoneStatus = inZoneStatus;
	}
	public BigInteger getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(BigInteger goodsId) {
		this.goodsId = goodsId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsBrand() {
		return goodsBrand;
	}
	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}
	public Integer getPark() {
		return park;
	}
	public void setPark(Integer park) {
		this.park = park;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getVender() {
		return vender;
	}
	public void setVender(String vender) {
		this.vender = vender;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	public Float getVolume() {
		return volume;
	}
	public void setVolume(Float volume) {
		this.volume = volume;
	}
	public String getElseExplan() {
		return elseExplan;
	}
	public void setElseExplan(String elseExplan) {
		this.elseExplan = elseExplan;
	}
}

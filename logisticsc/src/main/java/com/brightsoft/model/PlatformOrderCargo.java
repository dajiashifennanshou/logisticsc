package com.brightsoft.model;

import java.io.Serializable;

/**
 * 订单货物表
 * @author ThinkPad
 *
 */
public class PlatformOrderCargo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String brand;

    private String model;
    
    private Integer goodsType;
    
    private Integer packageType;

    private Integer number;

    private Integer setNumber;

    private Double singleWeight;

    private Double singleVolume;
    
    private Integer countCostMode;
    
    private Double price;

    private Long orderId;
    
    // ----- 附加字段
    
    private Integer isCommon;
    
    private String packageTypeName;
    
    private String countCostModeName;
    
    private Double totalWeight;
    
    private Double totalVolume;

    
 

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Double getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(Double totalVolume) {
		this.totalVolume = totalVolume;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getPackageType() {
		return packageType;
	}

	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getSetNumber() {
		return setNumber;
	}

	public void setSetNumber(Integer setNumber) {
		this.setNumber = setNumber;
	}

	public Double getSingleWeight() {
		return singleWeight;
	}

	public void setSingleWeight(Double singleWeight) {
		this.singleWeight = singleWeight;
	}

	public Double getSingleVolume() {
		return singleVolume;
	}

	public void setSingleVolume(Double singleVolume) {
		this.singleVolume = singleVolume;
	}

	public Integer getCountCostMode() {
		return countCostMode;
	}

	public void setCountCostMode(Integer countCostMode) {
		this.countCostMode = countCostMode;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getIsCommon() {
		return isCommon;
	}

	public void setIsCommon(Integer isCommon) {
		this.isCommon = isCommon;
	}

	public String getPackageTypeName() {
		return packageTypeName;
	}

	public void setPackageTypeName(String packageTypeName) {
		this.packageTypeName = packageTypeName;
	}

	public String getCountCostModeName() {
		return countCostModeName;
	}

	public void setCountCostModeName(String countCostModeName) {
		this.countCostModeName = countCostModeName;
	}

}
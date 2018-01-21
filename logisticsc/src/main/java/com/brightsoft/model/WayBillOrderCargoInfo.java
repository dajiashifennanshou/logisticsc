package com.brightsoft.model;

import java.io.Serializable;

public class WayBillOrderCargoInfo implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String brand;

    private String model;

    private Integer packageType;

    private Integer number;

    private Integer setNumber;

    private Double totalWeight;

    private Double totalVolume;

    private Integer countCostMode;

    private Double price;

    private Long wayBillOrderId;
    
    private String packageTypeName;
    private Integer goodsType;

    
    
    public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public String getPackageTypeName() {
		return packageTypeName;
	}

	public void setPackageTypeName(String packageTypeName) {
		this.packageTypeName = packageTypeName;
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
        this.name = name == null ? null : name.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
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

    public Integer getCountCostMode() {
        return countCostMode;
    }

    public void setCountCostMode(Integer countCostMode) {
        this.countCostMode = countCostMode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getWayBillOrderId() {
        return wayBillOrderId;
    }

    public void setWayBillOrderId(Long wayBillOrderId) {
        this.wayBillOrderId = wayBillOrderId;
    }
}
package com.brightsoft.model;

import java.io.Serializable;

public class PlatformUserCompaninfo implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Integer staffNumber;

    private String region;

    private String warehouseInfo;

    private String carriageGoods;

    private String brandName;

    private String vehicleInfo;

    private Double annualMoney;

    private String serviceInfo;

    private Long companyId;
    /************************/
    private Long companyInfoId;

	public Long getCompanyInfoId() {
		return companyInfoId;
	}

	public void setCompanyInfoId(Long companyInfoId) {
		this.companyInfoId = companyInfoId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getWarehouseInfo() {
        return warehouseInfo;
    }

    public void setWarehouseInfo(String warehouseInfo) {
        this.warehouseInfo = warehouseInfo == null ? null : warehouseInfo.trim();
    }

    public String getCarriageGoods() {
        return carriageGoods;
    }

    public void setCarriageGoods(String carriageGoods) {
        this.carriageGoods = carriageGoods == null ? null : carriageGoods.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo == null ? null : vehicleInfo.trim();
    }

    public Double getAnnualMoney() {
        return annualMoney;
    }

    public void setAnnualMoney(Double annualMoney) {
        this.annualMoney = annualMoney;
    }

    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo == null ? null : serviceInfo.trim();
    }

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
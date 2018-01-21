package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class DepartList implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String departNumber;

    private Integer status;

    private Long startOutlets;

    private String startOutletsName;

    private String wayOutlets;

    private String wayOutletsName;

    private Long targetOutlets;

    private String targetOutletsName;

    private Date startTime;

    private Date endTime;
    
    private Long driver;

    private String driverName;

    private String driverPhone;
    
    private String driverBankName;
    
    private String driverBankNumber;

    private String vehicleNumber;

    private String hangVehicleNumber;

    private Integer vehicleType;

    private Double shouldPayDriverCost;

    private Double nowPayDriverCost;

    private Double arrivePayDriverCost;

    private Double backPayDriverCost;

    private Double loadCost;

    private Double unloadCost;

    private Double shuntCost;

    private Double insuranceMoney;

    private Double insuranceCost;

    private Integer isYearInsurance;

    private String remark;

    private Long operatePerson;

    private Date operateTime;

    private String wayBillNumbers;
    
    private Double receivableTotal; // 应收运费合计
    
    private Double actualReceiveTotal; // 实收运费合计
    
    private Double agencyFundTotal; // 代收货款合计
    
    private Integer isCompleted;
    
    private Integer agencyFundIsCompleted;
    
    private String vehicleTypeName;
    
    // -------------- 附加信息
    
    private String statusName;
    
    private String startTimeStr;
    
    private String endTimeStr;
    
    private String operatePersonName;
    
    private String companyName;
    
    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getAgencyFundIsCompleted() {
		return agencyFundIsCompleted;
	}

	public void setAgencyFundIsCompleted(Integer agencyFundIsCompleted) {
		this.agencyFundIsCompleted = agencyFundIsCompleted;
	}

	public String getOperatePersonName() {
		return operatePersonName;
	}

	public void setOperatePersonName(String operatePersonName) {
		this.operatePersonName = operatePersonName;
	}

	public Double getAgencyFundTotal() {
		return agencyFundTotal;
	}

	public void setAgencyFundTotal(Double agencyFundTotal) {
		this.agencyFundTotal = agencyFundTotal;
	}

	public String getDriverBankName() {
		return driverBankName;
	}

	public void setDriverBankName(String driverBankName) {
		this.driverBankName = driverBankName;
	}

	public String getDriverBankNumber() {
		return driverBankNumber;
	}

	public void setDriverBankNumber(String driverBankNumber) {
		this.driverBankNumber = driverBankNumber;
	}

	public String getVehicleTypeName() {
		return vehicleTypeName;
	}

	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}

	public Double getReceivableTotal() {
		return receivableTotal;
	}

	public void setReceivableTotal(Double receivableTotal) {
		this.receivableTotal = receivableTotal;
	}

	public Double getActualReceiveTotal() {
		return actualReceiveTotal;
	}

	public void setActualReceiveTotal(Double actualReceiveTotal) {
		this.actualReceiveTotal = actualReceiveTotal;
	}

	public Integer getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Integer isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public Long getDriver() {
		return driver;
	}

	public void setDriver(Long driver) {
		this.driver = driver;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartNumber() {
        return departNumber;
    }

    public void setDepartNumber(String departNumber) {
        this.departNumber = departNumber == null ? null : departNumber.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getStartOutlets() {
        return startOutlets;
    }

    public void setStartOutlets(Long startOutlets) {
        this.startOutlets = startOutlets;
    }

    public String getStartOutletsName() {
        return startOutletsName;
    }

    public void setStartOutletsName(String startOutletsName) {
        this.startOutletsName = startOutletsName == null ? null : startOutletsName.trim();
    }

    public String getWayOutlets() {
        return wayOutlets;
    }

    public void setWayOutlets(String wayOutlets) {
        this.wayOutlets = wayOutlets;
    }

    public String getWayOutletsName() {
        return wayOutletsName;
    }

    public void setWayOutletsName(String wayOutletsName) {
        this.wayOutletsName = wayOutletsName == null ? null : wayOutletsName.trim();
    }

    public Long getTargetOutlets() {
        return targetOutlets;
    }

    public void setTargetOutlets(Long targetOutlets) {
        this.targetOutlets = targetOutlets;
    }

    public String getTargetOutletsName() {
        return targetOutletsName;
    }

    public void setTargetOutletsName(String targetOutletsName) {
        this.targetOutletsName = targetOutletsName == null ? null : targetOutletsName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone == null ? null : driverPhone.trim();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber == null ? null : vehicleNumber.trim();
    }

    public String getHangVehicleNumber() {
        return hangVehicleNumber;
    }

    public void setHangVehicleNumber(String hangVehicleNumber) {
        this.hangVehicleNumber = hangVehicleNumber == null ? null : hangVehicleNumber.trim();
    }

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Double getShouldPayDriverCost() {
        return shouldPayDriverCost;
    }

    public void setShouldPayDriverCost(Double shouldPayDriverCost) {
        this.shouldPayDriverCost = shouldPayDriverCost;
    }

    public Double getNowPayDriverCost() {
        return nowPayDriverCost;
    }

    public void setNowPayDriverCost(Double nowPayDriverCost) {
        this.nowPayDriverCost = nowPayDriverCost;
    }

    public Double getArrivePayDriverCost() {
        return arrivePayDriverCost;
    }

    public void setArrivePayDriverCost(Double arrivePayDriverCost) {
        this.arrivePayDriverCost = arrivePayDriverCost;
    }

    public Double getBackPayDriverCost() {
        return backPayDriverCost;
    }

    public void setBackPayDriverCost(Double backPayDriverCost) {
        this.backPayDriverCost = backPayDriverCost;
    }

    public Double getLoadCost() {
        return loadCost;
    }

    public void setLoadCost(Double loadCost) {
        this.loadCost = loadCost;
    }

    public Double getUnloadCost() {
        return unloadCost;
    }

    public void setUnloadCost(Double unloadCost) {
        this.unloadCost = unloadCost;
    }

    public Double getShuntCost() {
        return shuntCost;
    }

    public void setShuntCost(Double shuntCost) {
        this.shuntCost = shuntCost;
    }

    public Double getInsuranceMoney() {
        return insuranceMoney;
    }

    public void setInsuranceMoney(Double insuranceMoney) {
        this.insuranceMoney = insuranceMoney;
    }

    public Double getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(Double insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public Integer getIsYearInsurance() {
        return isYearInsurance;
    }

    public void setIsYearInsurance(Integer isYearInsurance) {
        this.isYearInsurance = isYearInsurance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(Long operatePerson) {
        this.operatePerson = operatePerson;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getWayBillNumbers() {
        return wayBillNumbers;
    }

    public void setWayBillNumbers(String wayBillNumbers) {
        this.wayBillNumbers = wayBillNumbers == null ? null : wayBillNumbers.trim();
    }
}
package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class VehicleInfo implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String plateNumber;

    private String trailerNumber;

    private Integer vehicleType;

    private Integer vehicleLong;

    private Double vehicleVolume;

    private Double vehicleWeight;

    private String engineNumber;

    private String vehicleFrameNumber;

    private String transportLicenseNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    private String insurancePolicyNo;

    private String insuranceCompany;

    private String cooperationWay;

    private String ownerName;

    private String ownerIdNumber;

    private String ownerDphoneNumber;

    private String ownerAddress;

    private String ownerCompany;

    private String ownerCompanyAddress;

    private Long outletsId;

    private Long driverId;

    private Integer status;

    private Date createTime;

    private Long createPersionId;
    
//    private Long lineId;
    
    /**************************/
//    private LineInfo lineInfo;
    
    private OutletsInfo outletsInfo;
    
    private PlatformUserCompany platformUserCompany;
    
    /*********字典表值**********/
    private String vehicleTypeVal;
    
    private String vehicleLongVal;
    
    private String cooperationWayVal;
    
    /**************/
    private String condition;
    
    private DriverInfo driverInfo;

	public OutletsInfo getOutletsInfo() {
		return outletsInfo;
	}

	public void setOutletsInfo(OutletsInfo outletsInfo) {
		this.outletsInfo = outletsInfo;
	}

	public PlatformUserCompany getPlatformUserCompany() {
		return platformUserCompany;
	}

	public void setPlatformUserCompany(PlatformUserCompany platformUserCompany) {
		this.platformUserCompany = platformUserCompany;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber == null ? null : plateNumber.trim();
    }

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public void setTrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber == null ? null : trailerNumber.trim();
    }

    public Integer getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getVehicleLong() {
		return vehicleLong;
	}

	public void setVehicleLong(Integer vehicleLong) {
		this.vehicleLong = vehicleLong;
	}

	public Double getVehicleVolume() {
        return vehicleVolume;
    }

    public void setVehicleVolume(Double vehicleVolume) {
        this.vehicleVolume = vehicleVolume;
    }

    public Double getVehicleWeight() {
        return vehicleWeight;
    }

    public void setVehicleWeight(Double vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber == null ? null : engineNumber.trim();
    }

    public String getVehicleFrameNumber() {
        return vehicleFrameNumber;
    }

    public void setVehicleFrameNumber(String vehicleFrameNumber) {
        this.vehicleFrameNumber = vehicleFrameNumber == null ? null : vehicleFrameNumber.trim();
    }

    public String getTransportLicenseNo() {
        return transportLicenseNo;
    }

    public void setTransportLicenseNo(String transportLicenseNo) {
        this.transportLicenseNo = transportLicenseNo == null ? null : transportLicenseNo.trim();
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getInsurancePolicyNo() {
        return insurancePolicyNo;
    }

    public void setInsurancePolicyNo(String insurancePolicyNo) {
        this.insurancePolicyNo = insurancePolicyNo == null ? null : insurancePolicyNo.trim();
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany == null ? null : insuranceCompany.trim();
    }

    public String getCooperationWay() {
        return cooperationWay;
    }

    public void setCooperationWay(String cooperationWay) {
        this.cooperationWay = cooperationWay == null ? null : cooperationWay.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getOwnerIdNumber() {
        return ownerIdNumber;
    }

    public void setOwnerIdNumber(String ownerIdNumber) {
        this.ownerIdNumber = ownerIdNumber == null ? null : ownerIdNumber.trim();
    }

    public String getOwnerDphoneNumber() {
        return ownerDphoneNumber;
    }

    public void setOwnerDphoneNumber(String ownerDphoneNumber) {
        this.ownerDphoneNumber = ownerDphoneNumber == null ? null : ownerDphoneNumber.trim();
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress == null ? null : ownerAddress.trim();
    }

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany == null ? null : ownerCompany.trim();
    }

    public String getOwnerCompanyAddress() {
        return ownerCompanyAddress;
    }

    public void setOwnerCompanyAddress(String ownerCompanyAddress) {
        this.ownerCompanyAddress = ownerCompanyAddress == null ? null : ownerCompanyAddress.trim();
    }

    public Long getOutletsId() {
        return outletsId;
    }

    public void setOutletsId(Long outletsId) {
        this.outletsId = outletsId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatePersionId() {
        return createPersionId;
    }

    public void setCreatePersionId(Long createPersionId) {
        this.createPersionId = createPersionId;
    }

	public DriverInfo getDriverInfo() {
		return driverInfo;
	}

	public void setDriverInfo(DriverInfo driverInfo) {
		this.driverInfo = driverInfo;
	}

	public String getVehicleTypeVal() {
		return vehicleTypeVal;
	}

	public void setVehicleTypeVal(String vehicleTypeVal) {
		this.vehicleTypeVal = vehicleTypeVal;
	}

	public String getVehicleLongVal() {
		return vehicleLongVal;
	}

	public void setVehicleLongVal(String vehicleLongVal) {
		this.vehicleLongVal = vehicleLongVal;
	}

	public String getCooperationWayVal() {
		return cooperationWayVal;
	}

	public void setCooperationWayVal(String cooperationWayVal) {
		this.cooperationWayVal = cooperationWayVal;
	}
}
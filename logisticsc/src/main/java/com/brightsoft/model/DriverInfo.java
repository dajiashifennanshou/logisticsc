package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class DriverInfo implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String driverName;

    private Integer driverAge;

    private String driverLicenseType;

    private String driverLicenseNo;

    private String idCard;

    private String phoneNumber;

    private String bankName;

    private String bankNumber;

    private Date createTime;

    private String address;

    private Long createPersonId;

    private Long outletsId;
    
    private Integer status;

    /*****************************/
    private PlatformUserCompany platformUserCompany;
    
    private OutletsInfo outletsInfo;
    /*************驾驶证类型***************/
    private String driverLicenseTypeVal;
    /**********查询条件********/
    private String condition;
    
    public PlatformUserCompany getPlatformUserCompany() {
		return platformUserCompany;
	}

	public void setPlatformUserCompany(PlatformUserCompany platformUserCompany) {
		this.platformUserCompany = platformUserCompany;
	}

	public OutletsInfo getOutletsInfo() {
		return outletsInfo;
	}

	public void setOutletsInfo(OutletsInfo outletsInfo) {
		this.outletsInfo = outletsInfo;
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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public Integer getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(Integer driverAge) {
        this.driverAge = driverAge;
    }

    public String getDriverLicenseType() {
        return driverLicenseType;
    }

    public void setDriverLicenseType(String driverLicenseType) {
        this.driverLicenseType = driverLicenseType == null ? null : driverLicenseType.trim();
    }

    public String getDriverLicenseNo() {
        return driverLicenseNo;
    }

    public void setDriverLicenseNo(String driverLicenseNo) {
        this.driverLicenseNo = driverLicenseNo == null ? null : driverLicenseNo.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber == null ? null : bankNumber.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(Long createPersonId) {
        this.createPersonId = createPersonId;
    }

    public Long getOutletsId() {
        return outletsId;
    }

    public void setOutletsId(Long outletsId) {
        this.outletsId = outletsId;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDriverLicenseTypeVal() {
		return driverLicenseTypeVal;
	}

	public void setDriverLicenseTypeVal(String driverLicenseTypeVal) {
		this.driverLicenseTypeVal = driverLicenseTypeVal;
	}
}
package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class LadingOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String driverName;

    private String driverMobile;

    private String licensePlateNo;

    private String orderNumber;
    
    private String wayBillNumber;

    private Long startOutlets;
    
    private String startOutletsName;

    private Long targetOutlets;
    
    private String targetOutletsName;

    private String targetProvince;

    private String targetCity;

    private String targetCounty;

    private String consignor;

    private String consignorCompany;

    private String consignorMobile;

    private String consignorAddress;

    private String consignee;

    private String consigneeCompany;

    private String consigneeMobile;

    private String consigneeAddress;

    private Integer receiveType;

    private Integer receiptSlip;

    private Integer receiptSlipNum;

    private String cityDriverName;

    private String cityDriverPhone;
    
    private String cityVehicleNumber;

    private Integer payMethod;

    private Double total;

    private Integer status;
    
    private Date ladingOrderTime;
    
    private Long ladingOrderUser;
    
    private String remark;
    
    private Double agencyFund;
    
    private Double agencyFundPoundage;

    private Double insuranceMoney;

    private Double insurance;

    private Double takeCargoCost;

    private Double loadUnloadCost;

    private Double transferCost;

    private Double otherCost;
    
    private Double freight;

    private Double advanceCost;

    private Double immediatePay;

    private Double arrivePay;
    
    private Double backPay;

    private Integer isWaitPay;
    
    private Integer exceptionStatus;
    
    private String statusName;
    
    // ----- 附加 ------
    
    private String payMethodName;
    
    private Long operatePerson;
    
    private String operatePersonName;
    
    private String targetProvinceName;
    
    private String targetCityName;
    
    private String targetCountyName;
    
    private String companyName;
    
    private Integer receiptType;
    
    private Date deliveryDate;
    
    private String deliveryStartTime;
    
    private String deliveryEndTime;
    
    private String ladingOrderPerson;
    
    private String exceptionStatusName;
    
    private boolean exceptionRegister;
    
    private boolean exceptionHandler;

	public String getExceptionStatusName() {
		return exceptionStatusName;
	}

	public void setExceptionStatusName(String exceptionStatusName) {
		this.exceptionStatusName = exceptionStatusName;
	}

	public boolean isExceptionRegister() {
		return exceptionRegister;
	}

	public void setExceptionRegister(boolean exceptionRegister) {
		this.exceptionRegister = exceptionRegister;
	}

	public boolean isExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(boolean exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public Integer getExceptionStatus() {
		return exceptionStatus;
	}

	public void setExceptionStatus(Integer exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
	}

	public String getLadingOrderPerson() {
		return ladingOrderPerson;
	}

	public void setLadingOrderPerson(String ladingOrderPerson) {
		this.ladingOrderPerson = ladingOrderPerson;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryStartTime() {
		return deliveryStartTime;
	}

	public void setDeliveryStartTime(String deliveryStartTime) {
		this.deliveryStartTime = deliveryStartTime;
	}

	public String getDeliveryEndTime() {
		return deliveryEndTime;
	}

	public void setDeliveryEndTime(String deliveryEndTime) {
		this.deliveryEndTime = deliveryEndTime;
	}

	public Integer getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(Integer receiptType) {
		this.receiptType = receiptType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTargetProvinceName() {
		return targetProvinceName;
	}

	public void setTargetProvinceName(String targetProvinceName) {
		this.targetProvinceName = targetProvinceName;
	}

	public String getTargetCityName() {
		return targetCityName;
	}

	public void setTargetCityName(String targetCityName) {
		this.targetCityName = targetCityName;
	}

	public String getTargetCountyName() {
		return targetCountyName;
	}

	public void setTargetCountyName(String targetCountyName) {
		this.targetCountyName = targetCountyName;
	}

	public Long getOperatePerson() {
		return operatePerson;
	}

	public void setOperatePerson(Long operatePerson) {
		this.operatePerson = operatePerson;
	}

	public String getOperatePersonName() {
		return operatePersonName;
	}

	public void setOperatePersonName(String operatePersonName) {
		this.operatePersonName = operatePersonName;
	}

	public String getPayMethodName() {
		return payMethodName;
	}

	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
	}

	public Double getAgencyFund() {
		return agencyFund;
	}

	public void setAgencyFund(Double agencyFund) {
		this.agencyFund = agencyFund;
	}

	public Double getAgencyFundPoundage() {
		return agencyFundPoundage;
	}

	public void setAgencyFundPoundage(Double agencyFundPoundage) {
		this.agencyFundPoundage = agencyFundPoundage;
	}

	public Double getInsuranceMoney() {
		return insuranceMoney;
	}

	public void setInsuranceMoney(Double insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}

	public Double getTakeCargoCost() {
		return takeCargoCost;
	}

	public void setTakeCargoCost(Double takeCargoCost) {
		this.takeCargoCost = takeCargoCost;
	}

	public Double getLoadUnloadCost() {
		return loadUnloadCost;
	}

	public void setLoadUnloadCost(Double loadUnloadCost) {
		this.loadUnloadCost = loadUnloadCost;
	}

	public Double getTransferCost() {
		return transferCost;
	}

	public void setTransferCost(Double transferCost) {
		this.transferCost = transferCost;
	}

	public Double getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(Double otherCost) {
		this.otherCost = otherCost;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getAdvanceCost() {
		return advanceCost;
	}

	public void setAdvanceCost(Double advanceCost) {
		this.advanceCost = advanceCost;
	}

	public Double getImmediatePay() {
		return immediatePay;
	}

	public void setImmediatePay(Double immediatePay) {
		this.immediatePay = immediatePay;
	}

	public Double getArrivePay() {
		return arrivePay;
	}

	public void setArrivePay(Double arrivePay) {
		this.arrivePay = arrivePay;
	}

	public Double getBackPay() {
		return backPay;
	}

	public void setBackPay(Double backPay) {
		this.backPay = backPay;
	}

	public Integer getIsWaitPay() {
		return isWaitPay;
	}

	public void setIsWaitPay(Integer isWaitPay) {
		this.isWaitPay = isWaitPay;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStartOutletsName() {
		return startOutletsName;
	}

	public void setStartOutletsName(String startOutletsName) {
		this.startOutletsName = startOutletsName;
	}

	public String getTargetOutletsName() {
		return targetOutletsName;
	}

	public void setTargetOutletsName(String targetOutletsName) {
		this.targetOutletsName = targetOutletsName;
	}

	public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}

	public String getCityVehicleNumber() {
		return cityVehicleNumber;
	}

	public void setCityVehicleNumber(String cityVehicleNumber) {
		this.cityVehicleNumber = cityVehicleNumber;
	}

	public Date getLadingOrderTime() {
		return ladingOrderTime;
	}

	public void setLadingOrderTime(Date ladingOrderTime) {
		this.ladingOrderTime = ladingOrderTime;
	}

	public Long getLadingOrderUser() {
		return ladingOrderUser;
	}

	public void setLadingOrderUser(Long ladingOrderUser) {
		this.ladingOrderUser = ladingOrderUser;
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

    public String getDriverMobile() {
        return driverMobile;
    }

    public void setDriverMobile(String driverMobile) {
        this.driverMobile = driverMobile == null ? null : driverMobile.trim();
    }

    public String getLicensePlateNo() {
        return licensePlateNo;
    }

    public void setLicensePlateNo(String licensePlateNo) {
        this.licensePlateNo = licensePlateNo == null ? null : licensePlateNo.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getStartOutlets() {
        return startOutlets;
    }

    public void setStartOutlets(Long startOutlets) {
        this.startOutlets = startOutlets;
    }

    public Long getTargetOutlets() {
        return targetOutlets;
    }

    public void setTargetOutlets(Long targetOutlets) {
        this.targetOutlets = targetOutlets;
    }

    public String getTargetProvince() {
		return targetProvince;
	}

	public void setTargetProvince(String targetProvince) {
		this.targetProvince = targetProvince;
	}

	public String getTargetCity() {
        return targetCity;
    }

    public void setTargetCity(String targetCity) {
        this.targetCity = targetCity == null ? null : targetCity.trim();
    }

    public String getTargetCounty() {
        return targetCounty;
    }

    public void setTargetCounty(String targetCounty) {
        this.targetCounty = targetCounty == null ? null : targetCounty.trim();
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor == null ? null : consignor.trim();
    }

    public String getConsignorCompany() {
        return consignorCompany;
    }

    public void setConsignorCompany(String consignorCompany) {
        this.consignorCompany = consignorCompany == null ? null : consignorCompany.trim();
    }

    public String getConsignorMobile() {
        return consignorMobile;
    }

    public void setConsignorMobile(String consignorMobile) {
        this.consignorMobile = consignorMobile == null ? null : consignorMobile.trim();
    }

    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress == null ? null : consignorAddress.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getConsigneeCompany() {
        return consigneeCompany;
    }

    public void setConsigneeCompany(String consigneeCompany) {
        this.consigneeCompany = consigneeCompany == null ? null : consigneeCompany.trim();
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile == null ? null : consigneeMobile.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public Integer getReceiptSlip() {
        return receiptSlip;
    }

    public void setReceiptSlip(Integer receiptSlip) {
        this.receiptSlip = receiptSlip;
    }

    public Integer getReceiptSlipNum() {
        return receiptSlipNum;
    }

    public void setReceiptSlipNum(Integer receiptSlipNum) {
        this.receiptSlipNum = receiptSlipNum;
    }

    public String getCityDriverName() {
        return cityDriverName;
    }

    public void setCityDriverName(String cityDriverName) {
        this.cityDriverName = cityDriverName == null ? null : cityDriverName.trim();
    }

    public String getCityDriverPhone() {
        return cityDriverPhone;
    }

    public void setCityDriverPhone(String cityDriverPhone) {
        this.cityDriverPhone = cityDriverPhone == null ? null : cityDriverPhone.trim();
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
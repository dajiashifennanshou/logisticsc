package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
public class PlatformOrder implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private Integer evaluateLevel;
    private String orderNumber;

    private String wayBillNumber;

    private String consignorName;

    private String consignorProvince;

    private String consignorCity;

    private String consignorCounty;

    private String consignorAddress;

    private String consignorPhoneNumber;

    private String consignorTelephone;
    
    private String driverName;
    
    private String driverPhone;
    
    private String vehicleNumber;
    
    private Long vehicleType;

    private String consigneeName;

    private String consigneeProvince;

    private String consigneeCity;

    private String consigneeCounty;

    private String consigneeAddress;

    private String consigneePhoneNumber;

    private String consigneeTelephone;
    
    private Integer isCommonConsignor;
    
    private Integer isCommonDriver;
    
    private Integer isCommonConsignee;

    private Long sendCargoType;

    private Date deliveryDate;

    private String deliveryStartTime;

    private String deliveryEndTime;

    private Long receiveCargoType;

    private Integer isInsurance;

    private Double insuranceMoney;

    private Integer receiptType;

    private Integer receiptTitle;

    private String receiptCompanyName;

    private Integer payType;

    private Double totalWeight;

    private Double totalVolume;
    
    private Double totalWorth;

    private Double advancePrice;

    private Double finalPrice;
    
    private Double paidPrice;

    private Integer state;

    private Date orderTime;

    private Long userId;

    private Long tmsLineId;

    private Integer isDelete;
    
    private Integer isDraft;
    
    private Integer isWaitPay;
    
    private Double estimateWeight;
    
    private Double estimateVolume;
    
    private Double estimateFreight;
    
    private Integer isConfirm;
    
    private Integer isPayment;
    
    private Double takeCargoCost;
    
    private Double estimateTotal;
    
    private Double totalCost;
    
    private Double prepaidCost; // 已付费用
    
    private Double unPrepaidCost; // 未付费用
    
    private String operatePerson;
    
    // ----------- 附加字段
    private String deliveryDateStr;

    private String deliveryStartTimeStr;

    private String deliveryEndTimeStr;
    
	private Long startOutlets;
    
    private String startOutletsName;
    
    private Long targetOutlets;
    
    private String targetOutletsName;
    
    private String statusName;
    
    private String vehicleTypeVal;
    
    private String consignorProvinceVal;
    
    private String consignorCityVal;
    
    private String consignorCountyVal;
    
    private String consigneeProvinceVal;
    
    private String consigneeCityVal;
    
    private String consigneeCountyVal;
    
    private List<PlatformOrderCargo> platformOrderCargos;
    
    private PlatformOrderBill platformOrderBill;
    
    private PlatformOrderAdditionalServer platformOrderAdditionalServer;
    
    private PlatformOrderAgencyFund platformOrderAgencyFund;
    
    private PlatformUserCompany platformUserCompany;
    
    private LineInfo lineInfo;
    
    private PlatformUser platformUser;
    
    private User tmsUser;
    
    private String payTypeName;
    
    private String requestid;
    
    private String ledgerno;
    
    private Integer orderType;
    
    private String amount;
    
    private Integer splitState;
    
    private List<SplitState> splitStateList;
    
    private String outletsName;
    
    private Integer exceptionStatus;
    
    private boolean exceptionRegister;
    
    private boolean exceptionHandler;
    
    public Integer getExceptionStatus() {
		return exceptionStatus;
	}

	public void setExceptionStatus(Integer exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
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

	public Double getPrepaidCost() {
		return prepaidCost;
	}

	public void setPrepaidCost(Double prepaidCost) {
		this.prepaidCost = prepaidCost;
	}

	public Double getUnPrepaidCost() {
		return unPrepaidCost;
	}

	public void setUnPrepaidCost(Double unPrepaidCost) {
		this.unPrepaidCost = unPrepaidCost;
	}

	public Integer getSplitState() {
		return splitState;
	}

	public void setSplitState(Integer splitState) {
		this.splitState = splitState;
	}

    public List<SplitState> getSplitStateList() {
		return splitStateList;
	}

	public void setSplitStateList(List<SplitState> splitStateList) {
		this.splitStateList = splitStateList;
	}

	public String getOutletsName() {
		return outletsName;
	}

	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public String getLedgerno() {
		return ledgerno;
	}

	public void setLedgerno(String ledgerno) {
		this.ledgerno = ledgerno;
	}

	public User getTmsUser() {
		return tmsUser;
	}

	public void setTmsUser(User tmsUser) {
		this.tmsUser = tmsUser;
	}

	public Integer getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(Integer isPayment) {
		this.isPayment = isPayment;
	}

	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getEstimateTotal() {
		return estimateTotal;
	}

	public void setEstimateTotal(Double estimateTotal) {
		this.estimateTotal = estimateTotal;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public Double getTakeCargoCost() {
		return takeCargoCost;
	}

	public void setTakeCargoCost(Double takeCargoCost) {
		this.takeCargoCost = takeCargoCost;
	}

	public Integer getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Integer isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Double getEstimateWeight() {
		return estimateWeight;
	}

	public void setEstimateWeight(Double estimateWeight) {
		this.estimateWeight = estimateWeight;
	}

	public Double getEstimateVolume() {
		return estimateVolume;
	}

	public void setEstimateVolume(Double estimateVolume) {
		this.estimateVolume = estimateVolume;
	}

	public Double getEstimateFreight() {
		return estimateFreight;
	}

	public void setEstimateFreight(Double estimateFreight) {
		this.estimateFreight = estimateFreight;
	}

	public Integer getIsWaitPay() {
		return isWaitPay;
	}

	public void setIsWaitPay(Integer isWaitPay) {
		this.isWaitPay = isWaitPay;
	}

	public Integer getIsCommonConsignor() {
		return isCommonConsignor;
	}

	public void setIsCommonConsignor(Integer isCommonConsignor) {
		this.isCommonConsignor = isCommonConsignor;
	}

	public Integer getIsCommonDriver() {
		return isCommonDriver;
	}

	public void setIsCommonDriver(Integer isCommonDriver) {
		this.isCommonDriver = isCommonDriver;
	}

	public Integer getIsCommonConsignee() {
		return isCommonConsignee;
	}

	public void setIsCommonConsignee(Integer isCommonConsignee) {
		this.isCommonConsignee = isCommonConsignee;
	}

	public Integer getEvaluateLevel() {
		return evaluateLevel;
	}

	public void setEvaluateLevel(Integer evaluateLevel) {
		this.evaluateLevel = evaluateLevel;
	}

	public String getConsigneeProvinceVal() {
		return consigneeProvinceVal;
	}

	public void setConsigneeProvinceVal(String consigneeProvinceVal) {
		this.consigneeProvinceVal = consigneeProvinceVal;
	}

	public String getConsigneeCityVal() {
		return consigneeCityVal;
	}

	public void setConsigneeCityVal(String consigneeCityVal) {
		this.consigneeCityVal = consigneeCityVal;
	}

	public String getConsigneeCountyVal() {
		return consigneeCountyVal;
	}

	public void setConsigneeCountyVal(String consigneeCountyVal) {
		this.consigneeCountyVal = consigneeCountyVal;
	}

	public Double getPaidPrice() {
		return paidPrice;
	}

	public void setPaidPrice(Double paidPrice) {
		this.paidPrice = paidPrice;
	}

	public PlatformUser getPlatformUser() {
		return platformUser;
	}

	public void setPlatformUser(PlatformUser platformUser) {
		this.platformUser = platformUser;
	}

	public PlatformUserCompany getPlatformUserCompany() {
		return platformUserCompany;
	}

	public void setPlatformUserCompany(PlatformUserCompany platformUserCompany) {
		this.platformUserCompany = platformUserCompany;
	}

	public LineInfo getLineInfo() {
		return lineInfo;
	}

	public void setLineInfo(LineInfo lineInfo) {
		this.lineInfo = lineInfo;
	}
    public Integer getIsDraft() {
 		return isDraft;
 	}

 	public void setIsDraft(Integer isDraft) {
 		this.isDraft = isDraft;
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

	public String getVehicleTypeVal() {
		return vehicleTypeVal;
	}

	public void setVehicleTypeVal(String vehicleTypeVal) {
		this.vehicleTypeVal = vehicleTypeVal;
	}

	public String getConsignorProvinceVal() {
		return consignorProvinceVal;
	}

	public void setConsignorProvinceVal(String consignorProvinceVal) {
		this.consignorProvinceVal = consignorProvinceVal;
	}

	public String getConsignorCityVal() {
		return consignorCityVal;
	}

	public void setConsignorCityVal(String consignorCityVal) {
		this.consignorCityVal = consignorCityVal;
	}

	public String getConsignorCountyVal() {
		return consignorCountyVal;
	}

	public void setConsignorCountyVal(String consignorCountyVal) {
		this.consignorCountyVal = consignorCountyVal;
	}

	public List<PlatformOrderCargo> getPlatformOrderCargos() {
		return platformOrderCargos;
	}

	public void setPlatformOrderCargos(List<PlatformOrderCargo> platformOrderCargos) {
		this.platformOrderCargos = platformOrderCargos;
	}

	public PlatformOrderBill getPlatformOrderBill() {
		return platformOrderBill;
	}

	public void setPlatformOrderBill(PlatformOrderBill platformOrderBill) {
		this.platformOrderBill = platformOrderBill;
	}

	public PlatformOrderAdditionalServer getPlatformOrderAdditionalServer() {
		return platformOrderAdditionalServer;
	}

	public void setPlatformOrderAdditionalServer(PlatformOrderAdditionalServer platformOrderAdditionalServer) {
		this.platformOrderAdditionalServer = platformOrderAdditionalServer;
	}

	public PlatformOrderAgencyFund getPlatformOrderAgencyFund() {
		return platformOrderAgencyFund;
	}

	public void setPlatformOrderAgencyFund(PlatformOrderAgencyFund platformOrderAgencyFund) {
		this.platformOrderAgencyFund = platformOrderAgencyFund;
	}

	public Double getTotalWorth() {
		return totalWorth;
	}

	public void setTotalWorth(Double totalWorth) {
		this.totalWorth = totalWorth;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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

	private List<PlatformOrderCargo> orderCargos;

    public List<PlatformOrderCargo> getOrderCargos() {
		return orderCargos;
	}

	public void setOrderCargos(List<PlatformOrderCargo> orderCargos) {
		this.orderCargos = orderCargos;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber == null ? null : wayBillNumber.trim();
	}

	public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName == null ? null : consignorName.trim();
    }

    public String getConsignorProvince() {
        return consignorProvince;
    }

    public void setConsignorProvince(String consignorProvince) {
        this.consignorProvince = consignorProvince == null ? null : consignorProvince.trim();
    }

    public String getConsignorCity() {
        return consignorCity;
    }

    public void setConsignorCity(String consignorCity) {
        this.consignorCity = consignorCity == null ? null : consignorCity.trim();
    }

    public String getConsignorCounty() {
        return consignorCounty;
    }

    public void setConsignorCounty(String consignorCounty) {
        this.consignorCounty = consignorCounty == null ? null : consignorCounty.trim();
    }

    public String getConsignorAddress() {
        return consignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        this.consignorAddress = consignorAddress == null ? null : consignorAddress.trim();
    }

    public String getConsignorPhoneNumber() {
        return consignorPhoneNumber;
    }

    public void setConsignorPhoneNumber(String consignorPhoneNumber) {
        this.consignorPhoneNumber = consignorPhoneNumber == null ? null : consignorPhoneNumber.trim();
    }

    public String getConsignorTelephone() {
        return consignorTelephone;
    }

    public void setConsignorTelephone(String consignorTelephone) {
        this.consignorTelephone = consignorTelephone == null ? null : consignorTelephone.trim();
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    public String getConsigneeProvince() {
        return consigneeProvince;
    }

    public void setConsigneeProvince(String consigneeProvince) {
        this.consigneeProvince = consigneeProvince == null ? null : consigneeProvince.trim();
    }

    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity == null ? null : consigneeCity.trim();
    }

    public String getConsigneeCounty() {
        return consigneeCounty;
    }

    public void setConsigneeCounty(String consigneeCounty) {
        this.consigneeCounty = consigneeCounty == null ? null : consigneeCounty.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public String getConsigneePhoneNumber() {
        return consigneePhoneNumber;
    }

    public void setConsigneePhoneNumber(String consigneePhoneNumber) {
        this.consigneePhoneNumber = consigneePhoneNumber == null ? null : consigneePhoneNumber.trim();
    }

    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }

    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone == null ? null : consigneeTelephone.trim();
    }

    public Long getSendCargoType() {
        return sendCargoType;
    }

    public void setSendCargoType(Long sendCargoType) {
        this.sendCargoType = sendCargoType;
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

    public Long getReceiveCargoType() {
        return receiveCargoType;
    }

    public void setReceiveCargoType(Long receiveCargoType) {
        this.receiveCargoType = receiveCargoType;
    }

    public Integer getIsInsurance() {
        return isInsurance;
    }

    public void setIsInsurance(Integer isInsurance) {
        this.isInsurance = isInsurance;
    }

    public Double getInsuranceMoney() {
        return insuranceMoney;
    }

    public void setInsuranceMoney(Double insuranceMoney) {
        this.insuranceMoney = insuranceMoney;
    }

    public Integer getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }

    public Integer getReceiptTitle() {
        return receiptTitle;
    }

    public void setReceiptTitle(Integer receiptTitle) {
        this.receiptTitle = receiptTitle;
    }

    public String getReceiptCompanyName() {
        return receiptCompanyName;
    }

    public void setReceiptCompanyName(String receiptCompanyName) {
        this.receiptCompanyName = receiptCompanyName == null ? null : receiptCompanyName.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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

    public Double getAdvancePrice() {
        return advancePrice;
    }

    public void setAdvancePrice(Double advancePrice) {
        this.advancePrice = advancePrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTmsLineId() {
        return tmsLineId;
    }

    public void setTmsLineId(Long tmsLineId) {
        this.tmsLineId = tmsLineId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Long getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Long vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getDeliveryDateStr() {
		return deliveryDateStr;
	}

	public void setDeliveryDateStr(String deliveryDateStr) {
		this.deliveryDateStr = deliveryDateStr;
	}

	public String getDeliveryStartTimeStr() {
		return deliveryStartTimeStr;
	}

	public void setDeliveryStartTimeStr(String deliveryStartTimeStr) {
		this.deliveryStartTimeStr = deliveryStartTimeStr;
	}

	public String getDeliveryEndTimeStr() {
		return deliveryEndTimeStr;
	}

	public void setDeliveryEndTimeStr(String deliveryEndTimeStr) {
		this.deliveryEndTimeStr = deliveryEndTimeStr;
	}
    
}
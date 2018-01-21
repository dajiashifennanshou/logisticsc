package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WayBillOrder implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

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
    
    private Integer sendType;

    private Integer receiveType;

    private Integer receiptSlip;

    private Integer receiptSlipNum;
    
    private Integer receiptStatus;

    private String cityDriverName;

    private String cityDriverPhone;

    private String cityVehicleNumber;

    private String payMethod;

    private Double total;

    private Integer status;
    
    private Integer signStatus;
    
    private Date signTime;

    private Date wayBillOrderTime;

    private Long wayBillOrderUser;

    private String remark;
    
    private Integer exceptionStatus;
    
    private String exceptionStatusName;
    
    // ---------------附加 字段
    private String statusName;
    
    private String receiptStatusName;
    
    private Double freight; // 运费
    
    private Double agencyFund; // 代收货款
    
    private Double advanceCost; // 垫付费用
    
    private Double actualCollectMoney;
    
    private Double transferredMoney;
    
    private String collectMoneyPerson;
    
    private String money;
    
    private String payMethodName;
    
    private String companyName;
    
    private String targetProvinceName;
    
    private String targetCityName;
    
    private String targetCountyName;
    
    private String targetAddress;
    
    private Integer isOutStore;
    
    private Long receiveMoneyOrderId;
    
    private List<WayBillOrderCargoInfo> wayBillOrderCargoInfos;
    
    private List<WayBillOrderCostInfo> wayBillOrderCostInfos;
    
    // ------运单外包信息-------------
    
    private String outWayBillNumber;

    private Double outSourceCost;

    private Double currentPay;

    private Double outBackPay;
    
    // ------运单中转信息 -------------
    
    private String transferDepartNumber;
    
    private String transferWayBillNumber;
    
    // ----------- 修改 -------------------
    
    private Double agencyFundPoundage;

    private Double insuranceMoney;

    private Double insurance;

    private Double takeCargoCost;

    private Double loadUnloadCost;

    private Double transferCost;

    private Double otherCost;

    private Double advancePayment;

    private Double immediatePay;

    private Double arrivePay;
    
    private Double backPay;

    private Integer isWaitPay;
    
    // ------- 发车单信息--------
    private String departNumber;
    
    private Date startTime;
    
    private Date endTime;
    
    // -------- 货物信息 -----------
    
    private String cargoBrand;
    
    private String cargoName;
    
    private Integer cargoNumber;
    
    private Integer cargoSetNumber;
    
    private boolean exceptionRegister;
    
    private boolean exceptionHandler;
    
    private Date outTime; // 出库时间
    
    private Long operatePerson;
    
    private String operatePersonName;
    
    private String signPerson;
    
    private String signPersonPhone;
    
    private Integer receiptType;
    
    private Date receiveOrderTime;
    
    private String wayBillPerson;
    
	public String getSignPersonPhone() {
		return signPersonPhone;
	}

	public void setSignPersonPhone(String signPersonPhone) {
		this.signPersonPhone = signPersonPhone;
	}

	public Integer getCargoSetNumber() {
		return cargoSetNumber;
	}

	public void setCargoSetNumber(Integer cargoSetNumber) {
		this.cargoSetNumber = cargoSetNumber;
	}

	public String getTargetAddress() {
		return targetAddress;
	}

	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}

	public String getWayBillPerson() {
		return wayBillPerson;
	}

	public void setWayBillPerson(String wayBillPerson) {
		this.wayBillPerson = wayBillPerson;
	}

	public Date getReceiveOrderTime() {
		return receiveOrderTime;
	}

	public void setReceiveOrderTime(Date receiveOrderTime) {
		this.receiveOrderTime = receiveOrderTime;
	}

	public Integer getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(Integer receiptType) {
		this.receiptType = receiptType;
	}

	public String getTransferWayBillNumber() {
		return transferWayBillNumber;
	}

	public void setTransferWayBillNumber(String transferWayBillNumber) {
		this.transferWayBillNumber = transferWayBillNumber;
	}

	public String getSignPerson() {
		return signPerson;
	}

	public void setSignPerson(String signPerson) {
		this.signPerson = signPerson;
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

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Integer getCargoNumber() {
		return cargoNumber;
	}

	public void setCargoNumber(Integer cargoNumber) {
		this.cargoNumber = cargoNumber;
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

	public String getExceptionStatusName() {
		return exceptionStatusName;
	}

	public void setExceptionStatusName(String exceptionStatusName) {
		this.exceptionStatusName = exceptionStatusName;
	}

	public Integer getExceptionStatus() {
		return exceptionStatus;
	}

	public void setExceptionStatus(Integer exceptionStatus) {
		this.exceptionStatus = exceptionStatus;
	}

	public Long getReceiveMoneyOrderId() {
		return receiveMoneyOrderId;
	}

	public void setReceiveMoneyOrderId(Long receiveMoneyOrderId) {
		this.receiveMoneyOrderId = receiveMoneyOrderId;
	}

	public String getTransferDepartNumber() {
		return transferDepartNumber;
	}

	public void setTransferDepartNumber(String transferDepartNumber) {
		this.transferDepartNumber = transferDepartNumber;
	}

	public Integer getIsOutStore() {
		return isOutStore;
	}

	public void setIsOutStore(Integer isOutStore) {
		this.isOutStore = isOutStore;
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

	public String getCargoBrand() {
		return cargoBrand;
	}

	public void setCargoBrand(String cargoBrand) {
		this.cargoBrand = cargoBrand;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getDepartNumber() {
		return departNumber;
	}

	public void setDepartNumber(String departNumber) {
		this.departNumber = departNumber;
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

	public Double getOutBackPay() {
		return outBackPay;
	}

	public void setOutBackPay(Double outBackPay) {
		this.outBackPay = outBackPay;
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

	public Double getAdvancePayment() {
		return advancePayment;
	}

	public void setAdvancePayment(Double advancePayment) {
		this.advancePayment = advancePayment;
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

	public Integer getIsWaitPay() {
		return isWaitPay;
	}

	public void setIsWaitPay(Integer isWaitPay) {
		this.isWaitPay = isWaitPay;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getReceiptStatusName() {
		return receiptStatusName;
	}

	public void setReceiptStatusName(String receiptStatusName) {
		this.receiptStatusName = receiptStatusName;
	}

	public Integer getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(Integer receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public String getPayMethodName() {
		return payMethodName;
	}

	public void setPayMethodName(String payMethodName) {
		this.payMethodName = payMethodName;
	}

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public String getOutWayBillNumber() {
		return outWayBillNumber;
	}

	public void setOutWayBillNumber(String outWayBillNumber) {
		this.outWayBillNumber = outWayBillNumber;
	}

	public Double getOutSourceCost() {
		return outSourceCost;
	}

	public void setOutSourceCost(Double outSourceCost) {
		this.outSourceCost = outSourceCost;
	}

	public Double getCurrentPay() {
		return currentPay;
	}

	public void setCurrentPay(Double currentPay) {
		this.currentPay = currentPay;
	}

	public Double getBackPay() {
		return backPay;
	}

	public void setBackPay(Double backPay) {
		this.backPay = backPay;
	}

	public List<WayBillOrderCargoInfo> getWayBillOrderCargoInfos() {
		return wayBillOrderCargoInfos;
	}

	public void setWayBillOrderCargoInfos(
			List<WayBillOrderCargoInfo> wayBillOrderCargoInfos) {
		this.wayBillOrderCargoInfos = wayBillOrderCargoInfos;
	}

	public List<WayBillOrderCostInfo> getWayBillOrderCostInfos() {
		return wayBillOrderCostInfos;
	}

	public void setWayBillOrderCostInfos(
			List<WayBillOrderCostInfo> wayBillOrderCostInfos) {
		this.wayBillOrderCostInfos = wayBillOrderCostInfos;
	}

	public Double getAgencyFund() {
		return agencyFund;
	}

	public void setAgencyFund(Double agencyFund) {
		this.agencyFund = agencyFund;
	}

	public Double getAdvanceCost() {
		return advanceCost;
	}

	public void setAdvanceCost(Double advanceCost) {
		this.advanceCost = advanceCost;
	}

	public Double getActualCollectMoney() {
		return actualCollectMoney;
	}

	public void setActualCollectMoney(Double actualCollectMoney) {
		this.actualCollectMoney = actualCollectMoney;
	}

	public Double getTransferredMoney() {
		return transferredMoney;
	}

	public void setTransferredMoney(Double transferredMoney) {
		this.transferredMoney = transferredMoney;
	}

	public String getCollectMoneyPerson() {
		return collectMoneyPerson;
	}

	public void setCollectMoneyPerson(String collectMoneyPerson) {
		this.collectMoneyPerson = collectMoneyPerson;
	}

	public Integer getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
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

    public String getCityVehicleNumber() {
        return cityVehicleNumber;
    }

    public void setCityVehicleNumber(String cityVehicleNumber) {
        this.cityVehicleNumber = cityVehicleNumber == null ? null : cityVehicleNumber.trim();
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
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

    public Date getWayBillOrderTime() {
        return wayBillOrderTime;
    }

    public void setWayBillOrderTime(Date wayBillOrderTime) {
        this.wayBillOrderTime = wayBillOrderTime;
    }

    public Long getWayBillOrderUser() {
        return wayBillOrderUser;
    }

    public void setWayBillOrderUser(Long wayBillOrderUser) {
        this.wayBillOrderUser = wayBillOrderUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
}
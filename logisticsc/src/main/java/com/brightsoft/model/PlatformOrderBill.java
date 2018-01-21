package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 账单信息表
 * @author ThinkPad
 *
 */
public class PlatformOrderBill implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String orderNumber;

    private Double freight;
    
    private Double agencyFundPoundage;

    private Double loadUnloadCharge;
    
    private Double addedServiceCharge;

    private Double insurance;

    private Double totalCost;

    private Double prepaidCost;

    private Double otherCost;

    private Date payDate;

    private Integer state;
    
    private String startOutlets;
    
    private String endOutlets;
    
    private String companyName;
    
    private Date createTime;
    
    private Integer isPayment;
    
    private String wayBillNumber;//分页
    
    private String startTime; //开始时间
    
    private String endTime; //结束时间
    
    private Long userId;
    
    // ------ 附加 ------
    
    private Double agencyFund;
    
    private Double insuranceMoney;
    
    private Double takeCargoCost;
    
    private Double sendCargoCost;
    
    private Double loadCargoCost;
    
    private Double unloadCargoCost;
    
    private Double transferCost;
    
    private Double estimateTakeCargoCost;
    
    private Double estimateSendCargoCost;
    
    private Double estimateLoadCargoCost;
    
    private Double estimateUnloadCargoCost;
    
    private Double estimateFreight;

    private Double estimateTotalCost;
    
    private Integer isConfirm;
    
    

	public Integer getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(Integer isPayment) {
		this.isPayment = isPayment;
	}

	public Double getEstimateSendCargoCost() {
		return estimateSendCargoCost;
	}

	public void setEstimateSendCargoCost(Double estimateSendCargoCost) {
		this.estimateSendCargoCost = estimateSendCargoCost;
	}

	public Double getEstimateLoadCargoCost() {
		return estimateLoadCargoCost;
	}

	public void setEstimateLoadCargoCost(Double estimateLoadCargoCost) {
		this.estimateLoadCargoCost = estimateLoadCargoCost;
	}

	public Double getEstimateUnloadCargoCost() {
		return estimateUnloadCargoCost;
	}

	public void setEstimateUnloadCargoCost(Double estimateUnloadCargoCost) {
		this.estimateUnloadCargoCost = estimateUnloadCargoCost;
	}

	public Double getEstimateTotalCost() {
		return estimateTotalCost;
	}

	public void setEstimateTotalCost(Double estimateTotalCost) {
		this.estimateTotalCost = estimateTotalCost;
	}

	public Double getEstimateTakeCargoCost() {
		return estimateTakeCargoCost;
	}

	public void setEstimateTakeCargoCost(Double estimateTakeCargoCost) {
		this.estimateTakeCargoCost = estimateTakeCargoCost;
	}

	public Double getEstimateFreight() {
		return estimateFreight;
	}

	public void setEstimateFreight(Double estimateFreight) {
		this.estimateFreight = estimateFreight;
	}

	public Integer getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Integer isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Double getAgencyFund() {
		return agencyFund;
	}

	public void setAgencyFund(Double agencyFund) {
		this.agencyFund = agencyFund;
	}

	public Double getInsuranceMoney() {
		return insuranceMoney;
	}

	public void setInsuranceMoney(Double insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}

	public Double getTakeCargoCost() {
		return takeCargoCost;
	}

	public void setTakeCargoCost(Double takeCargoCost) {
		this.takeCargoCost = takeCargoCost;
	}

	public Double getSendCargoCost() {
		return sendCargoCost;
	}

	public void setSendCargoCost(Double sendCargoCost) {
		this.sendCargoCost = sendCargoCost;
	}

	public Double getLoadCargoCost() {
		return loadCargoCost;
	}

	public void setLoadCargoCost(Double loadCargoCost) {
		this.loadCargoCost = loadCargoCost;
	}

	public Double getUnloadCargoCost() {
		return unloadCargoCost;
	}

	public void setUnloadCargoCost(Double unloadCargoCost) {
		this.unloadCargoCost = unloadCargoCost;
	}

	public Double getTransferCost() {
		return transferCost;
	}

	public void setTransferCost(Double transferCost) {
		this.transferCost = transferCost;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getLoadUnloadCharge() {
		return loadUnloadCharge;
	}

	public void setLoadUnloadCharge(Double loadUnloadCharge) {
		this.loadUnloadCharge = loadUnloadCharge;
	}

	public Double getAgencyFundPoundage() {
		return agencyFundPoundage;
	}

	public void setAgencyFundPoundage(Double agencyFundPoundage) {
		this.agencyFundPoundage = agencyFundPoundage;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartOutlets() {
		return startOutlets;
	}

	public void setStartOutlets(String startOutlets) {
		this.startOutlets = startOutlets;
	}

	public String getEndOutlets() {
		return endOutlets;
	}

	public void setEndOutlets(String endOutlets) {
		this.endOutlets = endOutlets;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
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

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getAddedServiceCharge() {
        return addedServiceCharge;
    }

    public void setAddedServiceCharge(Double addedServiceCharge) {
        this.addedServiceCharge = addedServiceCharge;
    }

    public Double getInsurance() {
        return insurance;
    }

    public void setInsurance(Double insurance) {
        this.insurance = insurance;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getPrepaidCost() {
        return prepaidCost;
    }

    public void setPrepaidCost(Double prepaidCost) {
        this.prepaidCost = prepaidCost;
    }

    public Double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
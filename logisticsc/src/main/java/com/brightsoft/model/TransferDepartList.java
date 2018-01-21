package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TransferDepartList implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String transferDepartNumber;

    private Date transferTime;

    private Integer status;

    private Long startOutlets;

    private String startSitePerson;

    private String startSitePhone;

    private String carriageCompany;

    private String endSitePerson;

    private String endSitePhone;

    private Double transferCost;

    private Double currentPay;

    private Double backPay;

    private String remark;

    private String wayBillNumbers;
    
    private List<WayBillOrder> wayBillOrders;
    
    private String transferTimeStr;

    public String getTransferTimeStr() {
		return transferTimeStr;
	}

	public void setTransferTimeStr(String transferTimeStr) {
		this.transferTimeStr = transferTimeStr;
	}

	public List<WayBillOrder> getWayBillOrders() {
		return wayBillOrders;
	}

	public void setWayBillOrders(List<WayBillOrder> wayBillOrders) {
		this.wayBillOrders = wayBillOrders;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransferDepartNumber() {
        return transferDepartNumber;
    }

    public void setTransferDepartNumber(String transferDepartNumber) {
        this.transferDepartNumber = transferDepartNumber == null ? null : transferDepartNumber.trim();
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
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

    public String getStartSitePerson() {
        return startSitePerson;
    }

    public void setStartSitePerson(String startSitePerson) {
        this.startSitePerson = startSitePerson == null ? null : startSitePerson.trim();
    }

    public String getStartSitePhone() {
        return startSitePhone;
    }

    public void setStartSitePhone(String startSitePhone) {
        this.startSitePhone = startSitePhone == null ? null : startSitePhone.trim();
    }

    public String getCarriageCompany() {
        return carriageCompany;
    }

    public void setCarriageCompany(String carriageCompany) {
        this.carriageCompany = carriageCompany == null ? null : carriageCompany.trim();
    }

    public String getEndSitePerson() {
        return endSitePerson;
    }

    public void setEndSitePerson(String endSitePerson) {
        this.endSitePerson = endSitePerson == null ? null : endSitePerson.trim();
    }

    public String getEndSitePhone() {
        return endSitePhone;
    }

    public void setEndSitePhone(String endSitePhone) {
        this.endSitePhone = endSitePhone == null ? null : endSitePhone.trim();
    }

    public Double getTransferCost() {
		return transferCost;
	}

	public void setTransferCost(Double transferCost) {
		this.transferCost = transferCost;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getWayBillNumbers() {
        return wayBillNumbers;
    }

    public void setWayBillNumbers(String wayBillNumbers) {
        this.wayBillNumbers = wayBillNumbers == null ? null : wayBillNumbers.trim();
    }
}
package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class OutDepartList implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String outDepartNumber;

    private Date outSourceTime;

    private Integer status;

    private Long startOutlets;

    private String startSitePerson;

    private String startSitePhone;

    private String carriageCompany;

    private String endSitePerson;

    private String endSitePhone;

    private Double outSourceCost;

    private Double currentPay;

    private Double backPay;

    private String remark;
    
    private String wayBillNumbers;
    
    private Long operatePerson;
    
    private String destination;
    
    private String operatePersonName;
    
    private String startOutletsName;
    
    // -------------------------
    
    private String outSourceTimeStr;

    public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
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

	public String getStartOutletsName() {
		return startOutletsName;
	}

	public void setStartOutletsName(String startOutletsName) {
		this.startOutletsName = startOutletsName;
	}

	public String getOutSourceTimeStr() {
		return outSourceTimeStr;
	}

	public void setOutSourceTimeStr(String outSourceTimeStr) {
		this.outSourceTimeStr = outSourceTimeStr;
	}

	public String getWayBillNumbers() {
		return wayBillNumbers;
	}

	public void setWayBillNumbers(String wayBillNumbers) {
		this.wayBillNumbers = wayBillNumbers;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutDepartNumber() {
        return outDepartNumber;
    }

    public void setOutDepartNumber(String outDepartNumber) {
        this.outDepartNumber = outDepartNumber == null ? null : outDepartNumber.trim();
    }

    public Date getOutSourceTime() {
        return outSourceTime;
    }

    public void setOutSourceTime(Date outSourceTime) {
        this.outSourceTime = outSourceTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
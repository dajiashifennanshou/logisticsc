package com.brightsoft.model;

import java.io.Serializable;

public class WayBillOutSourceRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String outDepartNumber;
    
    private String wayBillNumber;

    private String outWayBillNumber;

    private Double outSourceCost;

    private Double currentPay;

    private Double outBackPay;

    public String getOutDepartNumber() {
		return outDepartNumber;
	}

	public void setOutDepartNumber(String outDepartNumber) {
		this.outDepartNumber = outDepartNumber;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWayBillNumber() {
        return wayBillNumber;
    }

    public void setWayBillNumber(String wayBillNumber) {
        this.wayBillNumber = wayBillNumber == null ? null : wayBillNumber.trim();
    }

    public String getOutWayBillNumber() {
        return outWayBillNumber;
    }

    public void setOutWayBillNumber(String outWayBillNumber) {
        this.outWayBillNumber = outWayBillNumber == null ? null : outWayBillNumber.trim();
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

	public Double getOutBackPay() {
		return outBackPay;
	}

	public void setOutBackPay(Double outBackPay) {
		this.outBackPay = outBackPay;
	}

}
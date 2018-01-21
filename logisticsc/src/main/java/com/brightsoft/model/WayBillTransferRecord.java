package com.brightsoft.model;

import java.io.Serializable;

public class WayBillTransferRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String transferDepartNumber;

    private String wayBillNumber;

    private String transferWayBillNumber;

    private Double transferCost;

    private Double currentPay;

    private Double outBackPay;

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

    public String getWayBillNumber() {
        return wayBillNumber;
    }

    public void setWayBillNumber(String wayBillNumber) {
        this.wayBillNumber = wayBillNumber == null ? null : wayBillNumber.trim();
    }

    public String getTransferWayBillNumber() {
        return transferWayBillNumber;
    }

    public void setTransferWayBillNumber(String transferWayBillNumber) {
        this.transferWayBillNumber = transferWayBillNumber == null ? null : transferWayBillNumber.trim();
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

    public Double getOutBackPay() {
        return outBackPay;
    }

    public void setOutBackPay(Double outBackPay) {
        this.outBackPay = outBackPay;
    }
}
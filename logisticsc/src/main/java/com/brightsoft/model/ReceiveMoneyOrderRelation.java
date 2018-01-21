package com.brightsoft.model;

import java.io.Serializable;

public class ReceiveMoneyOrderRelation implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long receiveMoneyOrderId;

    private String wayBillNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceiveMoneyOrderId() {
        return receiveMoneyOrderId;
    }

    public void setReceiveMoneyOrderId(Long receiveMoneyOrderId) {
        this.receiveMoneyOrderId = receiveMoneyOrderId;
    }

    public String getWayBillNumber() {
        return wayBillNumber;
    }

    public void setWayBillNumber(String wayBillNumber) {
        this.wayBillNumber = wayBillNumber == null ? null : wayBillNumber.trim();
    }
}
package com.brightsoft.model;

import java.io.Serializable;

public class DepartureList implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String wayBillNo;

    private Long orderId;

    private Integer wayBillState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo == null ? null : wayBillNo.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getWayBillState() {
        return wayBillState;
    }

    public void setWayBillState(Integer wayBillState) {
        this.wayBillState = wayBillState;
    }
}
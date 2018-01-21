package com.brightsoft.model;

import java.io.Serializable;

public class WayBillOrderCostInfo implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String code;
    
    private String name;

    private Double money;

    private Long wayBillOrderId;

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getWayBillOrderId() {
        return wayBillOrderId;
    }

    public void setWayBillOrderId(Long wayBillOrderId) {
        this.wayBillOrderId = wayBillOrderId;
    }
}
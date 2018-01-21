package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class PlatformOrderBack implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String orderNumber;

    private String money;

    private Integer refundType;

    private Date time;

    private Integer state;

    private Long userId;
    
    /********************/
    private PlatformUser platformUser;
    
    private PlatformOrder platformOrder;

    public PlatformUser getPlatformUser() {
		return platformUser;
	}

	public void setPlatformUser(PlatformUser platformUser) {
		this.platformUser = platformUser;
	}

	public PlatformOrder getPlatformOrder() {
		return platformOrder;
	}

	public void setPlatformOrder(PlatformOrder platformOrder) {
		this.platformOrder = platformOrder;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
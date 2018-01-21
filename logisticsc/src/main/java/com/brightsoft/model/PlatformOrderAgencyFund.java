package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 代收货款
 * @author ThinkPad
 *
 */
public class PlatformOrderAgencyFund implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String orderNumber;

    private Double agencyFund;

    private Double receivedFund;

    private Double uncollectedFund;

    private Date operateTime;

    private Integer state;

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

    public Double getAgencyFund() {
        return agencyFund;
    }

    public void setAgencyFund(Double agencyFund) {
        this.agencyFund = agencyFund;
    }

    public Double getReceivedFund() {
        return receivedFund;
    }

    public void setReceivedFund(Double receivedFund) {
        this.receivedFund = receivedFund;
    }

    public Double getUncollectedFund() {
        return uncollectedFund;
    }

    public void setUncollectedFund(Double uncollectedFund) {
        this.uncollectedFund = uncollectedFund;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 订单跟踪表
 * @author ThinkPad
 *
 */
public class PlatformOrderFollow implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String orderNumber;
    
    private String wayBillNumber;
    
    private Integer status;
    
    private Date handleTime;

    private String handleInfo;

    private String operatePerson;
    
    private String statusName;
    
    
    public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getWayBillNumber() {
		return wayBillNumber;
	}

	public void setWayBillNumber(String wayBillNumber) {
		this.wayBillNumber = wayBillNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleInfo() {
        return handleInfo;
    }

    public void setHandleInfo(String handleInfo) {
        this.handleInfo = handleInfo == null ? null : handleInfo.trim();
    }

    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson == null ? null : operatePerson.trim();
    }
}
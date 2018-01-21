package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class WayBillOrderFollow implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String wayBillNumber;

    private Integer status;

    private Date handleTime;

    private String handleInfo;

    private Long operatePerson;

    private String operatePersonName;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        this.operatePersonName = operatePersonName == null ? null : operatePersonName.trim();
    }
}
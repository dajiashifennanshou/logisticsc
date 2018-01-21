package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class WayBillOutStoreRecord implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long wayBillOrderId;

    private String driverName;

    private String driverPhone;

    private String vehicleNumber;

    private String operatePerson;

    private Date operateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWayBillOrderId() {
        return wayBillOrderId;
    }

    public void setWayBillOrderId(Long wayBillOrderId) {
        this.wayBillOrderId = wayBillOrderId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone == null ? null : driverPhone.trim();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber == null ? null : vehicleNumber.trim();
    }

    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson == null ? null : operatePerson.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
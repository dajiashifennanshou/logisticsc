package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class DeliveryBill implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long wayBillId;

    private Long driverId;

    private Long vehicleId;

    private Integer status;

    private Date billTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(Long wayBillId) {
        this.wayBillId = wayBillId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBillTime() {
        return billTime;
    }

    public void setBillTime(Date billTime) {
        this.billTime = billTime;
    }
}
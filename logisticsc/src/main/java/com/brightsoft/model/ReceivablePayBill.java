package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class ReceivablePayBill implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String costSubject;

    private Integer isCompleted;

    private String departNumber;

    private Double money;

    private String outDepartNumber;

    private String transferDepartNumber;

    private Date createTime;
    
    private Long outletsId;
    
    private Double total;
    
    private Double currentPay;
    
    private Double arrivePay;
    
    private Double backPay;
    
    // ------- 附加 -------
    
    private Long startOutlets;
    
    private String startOutletsName;
    
    private Long targetOutlets;
    
    private String targetOutletsName;
    
    private String receivePerson;

    private String receivePersonPhone;
    
    private String destination;
    
    private String carriageCompany;
    
    private String driverName;

    private String driverPhone;

    private String vehicleNumber;

    public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getCarriageCompany() {
		return carriageCompany;
	}

	public void setCarriageCompany(String carriageCompany) {
		this.carriageCompany = carriageCompany;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getReceivePersonPhone() {
		return receivePersonPhone;
	}

	public void setReceivePersonPhone(String receivePersonPhone) {
		this.receivePersonPhone = receivePersonPhone;
	}

	public Long getStartOutlets() {
		return startOutlets;
	}

	public void setStartOutlets(Long startOutlets) {
		this.startOutlets = startOutlets;
	}

	public String getStartOutletsName() {
		return startOutletsName;
	}

	public void setStartOutletsName(String startOutletsName) {
		this.startOutletsName = startOutletsName;
	}

	public Long getTargetOutlets() {
		return targetOutlets;
	}

	public void setTargetOutlets(Long targetOutlets) {
		this.targetOutlets = targetOutlets;
	}

	public String getTargetOutletsName() {
		return targetOutletsName;
	}

	public void setTargetOutletsName(String targetOutletsName) {
		this.targetOutletsName = targetOutletsName;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getCurrentPay() {
		return currentPay;
	}

	public void setCurrentPay(Double currentPay) {
		this.currentPay = currentPay;
	}

	public Double getArrivePay() {
		return arrivePay;
	}

	public void setArrivePay(Double arrivePay) {
		this.arrivePay = arrivePay;
	}

	public Double getBackPay() {
		return backPay;
	}

	public void setBackPay(Double backPay) {
		this.backPay = backPay;
	}

	public Long getOutletsId() {
		return outletsId;
	}

	public void setOutletsId(Long outletsId) {
		this.outletsId = outletsId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCostSubject() {
        return costSubject;
    }

    public void setCostSubject(String costSubject) {
        this.costSubject = costSubject == null ? null : costSubject.trim();
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getDepartNumber() {
        return departNumber;
    }

    public void setDepartNumber(String departNumber) {
        this.departNumber = departNumber == null ? null : departNumber.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getOutDepartNumber() {
        return outDepartNumber;
    }

    public void setOutDepartNumber(String outDepartNumber) {
        this.outDepartNumber = outDepartNumber == null ? null : outDepartNumber.trim();
    }

    public String getTransferDepartNumber() {
        return transferDepartNumber;
    }

    public void setTransferDepartNumber(String transferDepartNumber) {
        this.transferDepartNumber = transferDepartNumber == null ? null : transferDepartNumber.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
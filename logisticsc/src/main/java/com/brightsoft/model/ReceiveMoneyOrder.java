package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class ReceiveMoneyOrder implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String orderNumber;

    private Integer costType;

    private Double money;

    private Double posMoney;

    private Double cashMoney;
    
    private String payPerson;

    private String payPersonMobile;

    private String remark;

    private Long operatePerson;

    private String operatePersonMobile;

    private Date operateTime;
    
    private Integer status;
    
    private Long outletsId;
    
    // -------
    
    private String wayBillNumbers;
    
    private Double cashReceivedMoney;
    
    private Double posReceivedMoney;
    
    private String consignor;
    
    private String consignorMobile;
    
    private String consignee;
    
    private String consigneeMobile;

	public String getConsignor() {
		return consignor;
	}

	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}

	public String getConsignorMobile() {
		return consignorMobile;
	}

	public void setConsignorMobile(String consignorMobile) {
		this.consignorMobile = consignorMobile;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public Double getCashReceivedMoney() {
		return cashReceivedMoney;
	}

	public void setCashReceivedMoney(Double cashReceivedMoney) {
		this.cashReceivedMoney = cashReceivedMoney;
	}

	public Double getPosReceivedMoney() {
		return posReceivedMoney;
	}

	public void setPosReceivedMoney(Double posReceivedMoney) {
		this.posReceivedMoney = posReceivedMoney;
	}

	public String getPayPerson() {
		return payPerson;
	}

	public void setPayPerson(String payPerson) {
		this.payPerson = payPerson;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOutletsId() {
		return outletsId;
	}

	public void setOutletsId(Long outletsId) {
		this.outletsId = outletsId;
	}

	public String getWayBillNumbers() {
		return wayBillNumbers;
	}

	public void setWayBillNumbers(String wayBillNumbers) {
		this.wayBillNumbers = wayBillNumbers;
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

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getPosMoney() {
        return posMoney;
    }

    public void setPosMoney(Double posMoney) {
        this.posMoney = posMoney;
    }

    public Double getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(Double cashMoney) {
        this.cashMoney = cashMoney;
    }

    public String getPayPersonMobile() {
        return payPersonMobile;
    }

    public void setPayPersonMobile(String payPersonMobile) {
        this.payPersonMobile = payPersonMobile == null ? null : payPersonMobile.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(Long operatePerson) {
        this.operatePerson = operatePerson;
    }

    public String getOperatePersonMobile() {
        return operatePersonMobile;
    }

    public void setOperatePersonMobile(String operatePersonMobile) {
        this.operatePersonMobile = operatePersonMobile == null ? null : operatePersonMobile.trim();
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
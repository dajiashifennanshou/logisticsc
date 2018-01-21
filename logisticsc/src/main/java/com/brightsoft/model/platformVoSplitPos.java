package com.brightsoft.model;

import java.util.Date;
import java.util.List;

/**
 * pos机分账表
 * @author ThinkPad
 *
 */
public class platformVoSplitPos {
	
	//订单
	private Integer orderState;
	
	private Integer paymentOrderType;
	
	//pos机订单
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
    
    private Date asTime;
    
    private Integer status;
    
    private Long outletsId;
    
    private String statuss;
    
    private Integer statusys;
	
	//TMS用户
	private String tmsLoginName;
	
	//网点名称
	private String outletsName;
	
	//公司信息
	private String companyCode;
	
	private String companyName;
	
	//子账户
	private String ledgerno;
	
	private String amount;
	
	
	//转账状态
	List<platformBankAccounts> accounts;
	
	//查询条件
	private String startTime;
	
	private String endTime;
	
	private Long userId;
	
	private Long tmsUserId;
	
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Date getAsTime() {
		return asTime;
	}

	public void setAsTime(Date asTime) {
		this.asTime = asTime;
	}

	public Long getTmsUserId() {
		return tmsUserId;
	}

	public void setTmsUserId(Long tmsUserId) {
		this.tmsUserId = tmsUserId;
	}

	public Integer getStatusys() {
		return statusys;
	}

	public void setStatusys(Integer statusys) {
		this.statusys = statusys;
	}

	public List<platformBankAccounts> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<platformBankAccounts> accounts) {
		this.accounts = accounts;
	}

	public String getStatuss() {
		return statuss;
	}

	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getPaymentOrderType() {
		return paymentOrderType;
	}

	public void setPaymentOrderType(Integer paymentOrderType) {
		this.paymentOrderType = paymentOrderType;
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

	public String getPayPerson() {
		return payPerson;
	}

	public void setPayPerson(String payPerson) {
		this.payPerson = payPerson;
	}

	public String getPayPersonMobile() {
		return payPersonMobile;
	}

	public void setPayPersonMobile(String payPersonMobile) {
		this.payPersonMobile = payPersonMobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		this.operatePersonMobile = operatePersonMobile;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLedgerno() {
		return ledgerno;
	}

	public void setLedgerno(String ledgerno) {
		this.ledgerno = ledgerno;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getOrderState() {
		return orderState;
	}

	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}

	public String getTmsLoginName() {
		return tmsLoginName;
	}

	public void setTmsLoginName(String tmsLoginName) {
		this.tmsLoginName = tmsLoginName;
	}

	public String getOutletsName() {
		return outletsName;
	}

	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

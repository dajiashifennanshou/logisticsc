package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** YcReceivableOrder
	ID    BIGINT(20)
	ORDER_NUMBER    VARCHAR(20)
	COST_TYPE    INT(11)
	MONEY    DOUBLE(22,31)
	POS_MONEY    DOUBLE(22,31)
	CASH_MONEY    DOUBLE(22,31)
	PAY_PERSON    VARCHAR(20)
	PAY_PERSON_MOBILE    VARCHAR(11)
	REMARK    VARCHAR(255)
	OPERATE_PERSON    BIGINT(20)
	OPERATE_PERSON_MOBILE    VARCHAR(255)
	OPERATE_TIME    DATETIME(19)
	STATUS    INT(11)
	OUTLETS_ID    BIGINT(20)
*/
public class YcReceivableOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String orderNumber;
	private Integer costType;
	private Double money;
	private Double posMoney;
	private Double cashMoney;
	private String payPerson;
	private String payPersonMobile;
	private String remark;
	private Integer status;
	private BigInteger outletsId;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setOrderNumber(String orderNumber){
		this.orderNumber=orderNumber;
	}
	public String getOrderNumber(){
		return orderNumber;
	}
	public void setCostType(Integer costType){
		this.costType=costType;
	}
	public Integer getCostType(){
		return costType;
	}
	public void setMoney(Double money){
		this.money=money;
	}
	public Double getMoney(){
		return money;
	}
	public void setPosMoney(Double posMoney){
		this.posMoney=posMoney;
	}
	public Double getPosMoney(){
		return posMoney;
	}
	public void setCashMoney(Double cashMoney){
		this.cashMoney=cashMoney;
	}
	public Double getCashMoney(){
		return cashMoney;
	}
	public void setPayPerson(String payPerson){
		this.payPerson=payPerson;
	}
	public String getPayPerson(){
		return payPerson;
	}
	public void setPayPersonMobile(String payPersonMobile){
		this.payPersonMobile=payPersonMobile;
	}
	public String getPayPersonMobile(){
		return payPersonMobile;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}

	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setOutletsId(BigInteger outletsId){
		this.outletsId=outletsId;
	}
	public BigInteger getOutletsId(){
		return outletsId;
	}
}


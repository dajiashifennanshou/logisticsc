package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcCustomerManage
	ID    BIGINT(20)
	ORDERNO    VARCHAR(50)
	CUSTOMERNAME    VARCHAR(50)
	PHONE    VARCHAR(30)
	TELEPHONE    VARCHAR(30)
	ADDRESS    VARCHAR(50)
	COUNT    INT(11)
	DELIVERYCOST    FLOAT(12,31)
	DEALERID    BIGINT(20)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcCustomerManage implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String orderNo;
	private String customerName;
	private String phone;
	private String telephone;
	private String address;
	private Integer COUNT;
	private Float deliveryCost;
	private BigInteger dealerId;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	public String getOrderNo(){
		return orderNo;
	}
	public void setCustomerName(String customerName){
		this.customerName=customerName;
	}
	public String getCustomerName(){
		return customerName;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setTelephone(String telephone){
		this.telephone=telephone;
	}
	public String getTelephone(){
		return telephone;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setCOUNT(Integer COUNT){
		this.COUNT=COUNT;
	}
	public Integer getCOUNT(){
		return COUNT;
	}
	public void setDeliveryCost(Float deliveryCost){
		this.deliveryCost=deliveryCost;
	}
	public Float getDeliveryCost(){
		return deliveryCost;
	}
	public void setDealerId(BigInteger dealerId){
		this.dealerId=dealerId;
	}
	public BigInteger getDealerId(){
		return dealerId;
	}
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}
	public String getCreateUser(){
		return createUser;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime=updateTime;
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}
	public String getUpdateUser(){
		return updateUser;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
}


package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** YcStowageDelivery
	ID    BIGINT(20)
	STOWAGENO    VARCHAR(50)
	DELIVERNO    VARCHAR(50)
	EMPLOYEEID    BIGINT(20)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcStowageDelivery implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String stowageNo;
	private String deliverNo;
	private BigInteger dealerId;
	private String employeeId;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;

	
	
	public BigInteger getDealerId() {
		return dealerId;
	}
	public void setDealerId(BigInteger dealerId) {
		this.dealerId = dealerId;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setStowageNo(String stowageNo){
		this.stowageNo=stowageNo;
	}
	public String getStowageNo(){
		return stowageNo;
	}
	public void setDeliverNo(String deliverNo){
		this.deliverNo=deliverNo;
	}
	public String getDeliverNo(){
		return deliverNo;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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


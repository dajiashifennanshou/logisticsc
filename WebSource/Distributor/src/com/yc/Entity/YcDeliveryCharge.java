package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** YcDeliveryCharge
	ID    INT(11)
	BRANCHNO    VARCHAR(50)
	DELIVERYCOST    FLOAT(12,31)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
	CARTYPE    INT(6)
*/
public class YcDeliveryCharge implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String branchNo;
	private Float deliveryCost;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private Integer carType;

	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setBranchNo(String branchNo){
		this.branchNo=branchNo;
	}
	public String getBranchNo(){
		return branchNo;
	}
	public void setDeliveryCost(Float deliveryCost){
		this.deliveryCost=deliveryCost;
	}
	public Float getDeliveryCost(){
		return deliveryCost;
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
	public void setCarType(Integer carType){
		this.carType=carType;
	}
	public Integer getCarType(){
		return carType;
	}
}


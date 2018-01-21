package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcZoneCharge
	ID    BIGINT(20)
	CHARGENORM    VARCHAR(50)
	PROVINCE    VARCHAR(20)
	CITY    VARCHAR(20)
	COUNTY    VARCHAR(20)
	TOWN    VARCHAR(20)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcZoneCharge implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String chargeNorm;
	private String province;
	private String city;
	private String county;
	private String town;
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
	public void setChargeNorm(String chargeNorm){
		this.chargeNorm=chargeNorm;
	}
	public String getChargeNorm(){
		return chargeNorm;
	}
	public void setProvince(String province){
		this.province=province;
	}
	public String getProvince(){
		return province;
	}
	public void setCity(String city){
		this.city=city;
	}
	public String getCity(){
		return city;
	}
	public void setCounty(String county){
		this.county=county;
	}
	public String getCounty(){
		return county;
	}
	public void setTown(String town){
		this.town=town;
	}
	public String getTown(){
		return town;
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


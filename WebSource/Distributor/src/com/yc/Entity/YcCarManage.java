package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;


/** YcCarManage
	ID    BIGINT(20)
	CARNO    VARCHAR(20)
	TRAILERNO    VARCHAR(20)
	CARTYPE    VARCHAR(30)
	CARLENGTH    FLOAT(12,31)
	WEIGHT    VARCHAR(30)
	VOLUME    VARCHAR(30)
	DRIVERID    VARCHAR(20)
	CREATETIME    DATETIME(19)
	CREATUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcCarManage implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String carNo;
	private String trailerNo;
	private Integer carType;
	private Float carLength;
	private String weight;
	private String volume;
	private String driverId;
	private String createTime;
	private String creatUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private String dreverName;
	private String dreverPhone;
	private Integer carStatus;

	
	
	
	public Integer getCarStatus() {
		return carStatus;
	}
	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}
	public String getDreverName() {
		return dreverName;
	}
	public void setDreverName(String dreverName) {
		this.dreverName = dreverName;
	}
	public String getDreverPhone() {
		return dreverPhone;
	}
	public void setDreverPhone(String dreverPhone) {
		this.dreverPhone = dreverPhone;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setCarNo(String carNo){
		this.carNo=carNo;
	}
	public String getCarNo(){
		return carNo;
	}
	public void setTrailerNo(String trailerNo){
		this.trailerNo=trailerNo;
	}
	public String getTrailerNo(){
		return trailerNo;
	}
	
	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	public void setCarLength(Float carLength){
		this.carLength=carLength;
	}
	public Float getCarLength(){
		return carLength;
	}
	public void setWeight(String weight){
		this.weight=weight;
	}
	public String getWeight(){
		return weight;
	}
	public void setVolume(String volume){
		this.volume=volume;
	}
	public String getVolume(){
		return volume;
	}
	public void setDriverId(String driverId){
		this.driverId=driverId;
	}
	public String getDriverId(){
		return driverId;
	}
	public void setCreateTime(String createTime){
		this.createTime=createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setCreatUser(String creatUser){
		this.creatUser=creatUser;
	}
	public String getCreatUser(){
		return creatUser;
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


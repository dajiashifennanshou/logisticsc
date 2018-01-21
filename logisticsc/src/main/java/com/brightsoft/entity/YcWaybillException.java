package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcWaybillException
	ID    BIGINT(20)
	EXTYPE    INT(11)
	EXSTATUS    INT(11)
	COMMENT    VARCHAR(500)
	WAYBILLNO    VARCHAR(50)
	ORDERNO    VARCHAR(50)
	EXSOURCE    INT(11)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcWaybillException implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private Integer exType;
	private Integer exStatus;
	private String COMMENT;
	private String waybillNo;
	private String orderNo;
	private Integer exSource;
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
	public void setExType(Integer exType){
		this.exType=exType;
	}
	public Integer getExType(){
		return exType;
	}
	public void setExStatus(Integer exStatus){
		this.exStatus=exStatus;
	}
	public Integer getExStatus(){
		return exStatus;
	}
	public void setCOMMENT(String COMMENT){
		this.COMMENT=COMMENT;
	}
	public String getCOMMENT(){
		return COMMENT;
	}
	public void setWaybillNo(String waybillNo){
		this.waybillNo=waybillNo;
	}
	public String getWaybillNo(){
		return waybillNo;
	}
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	public String getOrderNo(){
		return orderNo;
	}
	public void setExSource(Integer exSource){
		this.exSource=exSource;
	}
	public Integer getExSource(){
		return exSource;
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


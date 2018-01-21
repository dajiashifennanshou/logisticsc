package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;

/** YcStowage
	ID    BIGINT(20)
	STOWAGENO    VARCHAR(50)
	STOWAGESTATUS    INT(11)
	CARNO    VARCHAR(20)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcStowage implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String stowageNo;
	private Integer stowageStatus;
	private String carNo;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private String branchNo;
	private String driverInfo;
	
	
	
	public String getDriverInfo() {
		return driverInfo;
	}
	public void setDriverInfo(String driverInfo) {
		this.driverInfo = driverInfo;
	}
	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getStowageNo() {
		return stowageNo;
	}
	public void setStowageNo(String stowageNo) {
		this.stowageNo = stowageNo;
	}
	public Integer getStowageStatus() {
		return stowageStatus;
	}
	public void setStowageStatus(Integer stowageStatus) {
		this.stowageStatus = stowageStatus;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}


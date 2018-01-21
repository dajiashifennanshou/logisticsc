package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcVender
	ID    BIGINT(20)
	VENDERNAME    VARCHAR(50)
	BRANDNAME    VARCHAR(50)
	PHONE    VARCHAR(30)
	TELEPHONE    VARCHAR(30)
	ADDRESS    VARCHAR(50)
	CREATETIME    DATETIME(19)
	CREATUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	REMARK    VARCHAR(500)
	UPDATEUSER    VARCHAR(20)
*/
public class YcVender implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String venderName;
	private String brandName;
	private String phone;
	private String telephone;
	private String address;
	private String createTime;
	private String creatUser;
	private String updateTime;
	private String remark;
	private String updateUser;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setVenderName(String venderName){
		this.venderName=venderName;
	}
	public String getVenderName(){
		return venderName;
	}
	public void setBrandName(String brandName){
		this.brandName=brandName;
	}
	public String getBrandName(){
		return brandName;
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
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}
	public String getUpdateUser(){
		return updateUser;
	}
}


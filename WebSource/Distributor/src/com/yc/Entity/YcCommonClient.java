package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/** YcCommonClient
	ID    BIGINT(20)
	DEALERID    BIGINT(20)
	TYPE    INT(11)
	CLIENTNAME    VARCHAR(30)
	PHONE    VARCHAR(20)
	TELPHONE    VARCHAR(20)
	ADDRESS    VARCHAR(30)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(100)
*/
public class YcCommonClient implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private BigInteger dealerId;
	private Integer type;
	private String clientName;
	private String phone;
	private String telphone;
	private String address;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private List<YcDeliveryOrder> list;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setDealerId(BigInteger dealerId){
		this.dealerId=dealerId;
	}
	public BigInteger getDealerId(){
		return dealerId;
	}
	public void setType(Integer type){
		this.type=type;
	}
	public Integer getType(){
		return type;
	}
	public void setClientName(String clientName){
		this.clientName=clientName;
	}
	public String getClientName(){
		return clientName;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setTelphone(String telphone){
		this.telphone=telphone;
	}
	public String getTelphone(){
		return telphone;
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
	public List<YcDeliveryOrder> getList() {
		return list;
	}
	public void setList(List<YcDeliveryOrder> list) {
		this.list = list;
	}
}


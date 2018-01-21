package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;


/** YcDeliveryOrder
	ID    BIGINT(20)
	BRANCHNO    VARCHAR(50)
	DELIVERYNO    VARCHAR(50)
	SUBSCRIBETIME    DATETIME(19)
	DEALERID    BIGINT(20)
	CONSIGNEENAME    VARCHAR(20)
	CONSIGNEEPHONE    VARCHAR(30)
	CONSIGNEEADDR    VARCHAR(600)
	CONSIGNEETELEPHONE    VARCHAR(30)
	ORDERSTATUS    INT(11)
	ZONECOST    FLOAT(12,31)
	DELIVERYCOST    FLOAT(12,31)
	INSTALLCOST    FLOAT(12,31)
	OTHERCOST    FLOAT(12,31)
	SUMCOST    FLOAT(12,31)
	PAYTYPE    INT(11)
	PAIDCOST    FLOAT(12,31)
	AGENTPAIDCOST    FLOAT(12,31)
	SATISFACTION    INT(11)
	EVALUATETYPE    INT(2)
	CUSTOMERSUG    VARCHAR(500)
	SIGNER    VARCHAR(50)
	SIGNTIME    DATETIME(19)
	CREATETIME    DATETIME(19)
	CREATEUSER    VARCHAR(20)
	UPDATETIME    DATETIME(19)
	UPDATEUSER    VARCHAR(20)
	REMARK    VARCHAR(500)
*/
public class YcDeliveryOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String branchNo;
	private String deliveryNo;
	private String stowageNo;
	private String subscribeTime;
	private BigInteger dealerId;
	private String consigneeName;
	private String consigneePhone;
	private String consigneeAddr;
	private String consigneeTelephone;
	private Integer orderStatus;
	private Float zoneCost;
	private Float deliveryCost;
	private Float installCost;
	private Float otherCost;
	private Float sumCost;
	private Integer payType;
	private Float paidCost;
	private Float agentPaidCost;
	private Integer satisfaction;
	private Integer evaluateStatus;
	private String customerSug;
	private String evaluateTime;
	private String signer;
	private String signTime;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private String installInfo;
	private String deliveryInfo;
	private String dealerName;
	
	
	
	
	public String getEvaluateTime() {
		return evaluateTime;
	}
	public void setEvaluateTime(String evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
	public String getStowageNo() {
		return stowageNo;
	}
	public void setStowageNo(String stowageNo) {
		this.stowageNo = stowageNo;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getInstallInfo() {
		return installInfo;
	}
	public void setInstallInfo(String installInfo) {
		this.installInfo = installInfo;
	}
	public String getDeliveryInfo() {
		return deliveryInfo;
	}
	public void setDeliveryInfo(String deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setBranchNo(String branchNo){
		this.branchNo=branchNo;
	}
	public String getBranchNo(){
		return branchNo;
	}
	public void setDeliveryNo(String deliveryNo){
		this.deliveryNo=deliveryNo;
	}
	public String getDeliveryNo(){
		return deliveryNo;
	}
	public void setSubscribeTime(String subscribeTime){
		this.subscribeTime=subscribeTime;
	}
	public String getSubscribeTime(){
		return subscribeTime;
	}
	public void setDealerId(BigInteger dealerId){
		this.dealerId=dealerId;
	}
	public BigInteger getDealerId(){
		return dealerId;
	}
	public void setConsigneeName(String consigneeName){
		this.consigneeName=consigneeName;
	}
	public String getConsigneeName(){
		return consigneeName;
	}
	public void setConsigneePhone(String consigneePhone){
		this.consigneePhone=consigneePhone;
	}
	public String getConsigneePhone(){
		return consigneePhone;
	}
	public void setConsigneeAddr(String consigneeAddr){
		this.consigneeAddr=consigneeAddr;
	}
	public String getConsigneeAddr(){
		return consigneeAddr;
	}
	public void setConsigneeTelephone(String consigneeTelephone){
		this.consigneeTelephone=consigneeTelephone;
	}
	public String getConsigneeTelephone(){
		return consigneeTelephone;
	}
	public void setOrderStatus(Integer orderStatus){
		this.orderStatus=orderStatus;
	}
	public Integer getOrderStatus(){
		return orderStatus;
	}
	public void setZoneCost(Float zoneCost){
		this.zoneCost=zoneCost;
	}
	public Float getZoneCost(){
		return zoneCost;
	}
	public void setDeliveryCost(Float deliveryCost){
		this.deliveryCost=deliveryCost;
	}
	public Float getDeliveryCost(){
		return deliveryCost;
	}
	public void setInstallCost(Float installCost){
		this.installCost=installCost;
	}
	public Float getInstallCost(){
		return installCost;
	}
	public void setOtherCost(Float otherCost){
		this.otherCost=otherCost;
	}
	public Float getOtherCost(){
		return otherCost;
	}
	public void setSumCost(Float sumCost){
		this.sumCost=sumCost;
	}
	public Float getSumCost(){
		return sumCost;
	}
	public void setPayType(Integer payType){
		this.payType=payType;
	}
	public Integer getPayType(){
		return payType;
	}
	public void setPaidCost(Float paidCost){
		this.paidCost=paidCost;
	}
	public Float getPaidCost(){
		return paidCost;
	}
	public void setAgentPaidCost(Float agentPaidCost){
		this.agentPaidCost=agentPaidCost;
	}
	public Float getAgentPaidCost(){
		return agentPaidCost;
	}
	public void setSatisfaction(Integer satisfaction){
		this.satisfaction=satisfaction;
	}
	public Integer getSatisfaction(){
		return satisfaction;
	}
	public Integer getEvaluateStatus() {
		return evaluateStatus;
	}
	public void setEvaluateStatus(Integer evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}
	public void setCustomerSug(String customerSug){
		this.customerSug=customerSug;
	}
	public String getCustomerSug(){
		return customerSug;
	}
	public void setSigner(String signer){
		this.signer=signer;
	}
	public String getSigner(){
		return signer;
	}
	public void setSignTime(String signTime){
		this.signTime=signTime;
	}
	public String getSignTime(){
		return signTime;
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


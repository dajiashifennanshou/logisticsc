package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
public class YcDeliveryOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String branchNo;
	private String deliveryNo;
	private String wayBillNo;
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
	private String signer;
	private String signTime;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String remark;
	private Float orderSource;
	private String evaluateTime;
	
	private Integer insdelPayStatus;//加盟商支付安装配送费的状态/与支付类型相关
	private Integer agentCostStatus;//代收货款的支付状态/与支付类型相关
	
	private Integer waybillSource;
	private List<YcGoods> lstGoods;
	//是否异常
	private Integer isExcepiton;
	public String getEvaluateTime() {
		return evaluateTime;
	}
	public void setEvaluateTime(String evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
	public Integer getInsdelPayStatus() {
		return insdelPayStatus;
	}
	public void setInsdelPayStatus(Integer insdelPayStatus) {
		this.insdelPayStatus = insdelPayStatus;
	}
	public Integer getAgentCostStatus() {
		return agentCostStatus;
	}
	public void setAgentCostStatus(Integer agentCostStatus) {
		this.agentCostStatus = agentCostStatus;
	}
	public Integer getWaybillSource() {
		return waybillSource;
	}
	public void setWaybillSource(Integer waybillSource) {
		this.waybillSource = waybillSource;
	}
	public String getWayBillNo() {
		return wayBillNo;
	}
	public void setWayBillNo(String wayBillNo) {
		this.wayBillNo = wayBillNo;
	}
	public Integer getIsExcepiton() {
		return isExcepiton;
	}
	public void setIsExcepiton(Integer isExcepiton) {
		this.isExcepiton = isExcepiton;
	}
	public List<YcGoods> getLstGoods() {
		return lstGoods;
	}
	public void setLstGoods(List<YcGoods> lstGoods) {
		this.lstGoods = lstGoods;
	}
	private List<YcGoods> goodsInfo;

	public List<YcGoods> getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(List<YcGoods> goodsInfo) {
		this.goodsInfo = goodsInfo;
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
	public Float getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(Float orderSource) {
		this.orderSource = orderSource;
	}
}


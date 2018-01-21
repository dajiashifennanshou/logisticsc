package com.xsl.distributor.lerist.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lerist on 2016/7/14, 0014.
 */

public class OrderInfo {

    /**
     * id : 245
     * branchNo : YC-00007
     * deliveryNo : PS-2016072016460466
     * dealerId : 169
     * consigneeName : 王知
     * consigneePhone : 18980292570
     * consigneeAddr : 陕西省西安市未央区某路258号
     * orderStatus : 0
     * deliveryCost : 0.0
     * installCost : 0.0
     * sumCost : 0.0
     * agentPaidCost : 12.0
     * satisfaction : 3
     * evaluateStatus : 0
     * createTime : 2016-07-20 16:46:05.0
     * createUser : Feng
     * goodsInfo : []
     */

    private int id;
    private String branchNo;
    private String deliveryNo;
    private int dealerId;
    private String consigneeName;
    private String consigneePhone;
    private String consigneeAddr;
    private int orderStatus;
    private double deliveryCost;
    private double installCost;
    private double sumCost;
    private double agentPaidCost;
    private int satisfaction;
    private int evaluateStatus;
    private String createTime;
    private String createUser;
    private List<GoodsInfo> goodsInfo = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneeAddr() {
        return consigneeAddr;
    }

    public void setConsigneeAddr(String consigneeAddr) {
        this.consigneeAddr = consigneeAddr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public double getInstallCost() {
        return installCost;
    }

    public void setInstallCost(double installCost) {
        this.installCost = installCost;
    }

    public double getSumCost() {
        return sumCost;
    }

    public void setSumCost(double sumCost) {
        this.sumCost = sumCost;
    }

    public double getAgentPaidCost() {
        return agentPaidCost;
    }

    public void setAgentPaidCost(double agentPaidCost) {
        this.agentPaidCost = agentPaidCost;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public int getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(int evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
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

    public List<GoodsInfo> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<GoodsInfo> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }
}

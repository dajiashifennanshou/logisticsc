package com.xsl.distributor.ws.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/13 0013.
 * 安装配送费
 */
public class OrderConstListBean implements Parcelable{

    /**
     * consigneeAddr : 你好呀，这是我的地址
     * consigneeName : 雪雪雪
     * agentPaidCost : 0.0
     * paidCost : 0.0
     * installCost : 0.0
     * deliveryNo : PS-2016071310525956
     * createUser : Feng
     * sumCost : 0.0
     * branchNo : YC-59
     * id : 64
     * createTime : 1468378380000
     * deliveryCost : 120.0
     * dealerId : 27
     * satisfaction : 3
     * orderStatus : 1
     * consigneePhone : 13524954089
     * evaluateStatus : 0
     * subscribeTime : 2016-07-11 00:00:00.0
     */

    private String consigneeAddr;
    private String consigneeName;
    private double agentPaidCost;
    private double paidCost;
    private double installCost;
    private String deliveryNo;
    private String createUser;
    private double sumCost;
    private String branchNo;
    private int id;
    private String createTime;
    private double deliveryCost;
    private int dealerId;
    private int satisfaction;
    private int orderStatus;
    private String consigneePhone;
    private int evaluateStatus;
    private String subscribeTime;
    public OrderConstListBean(){}
    protected OrderConstListBean(Parcel in) {
        consigneeAddr = in.readString();
        consigneeName = in.readString();
        agentPaidCost = in.readDouble();
        paidCost = in.readDouble();
        installCost = in.readDouble();
        deliveryNo = in.readString();
        createUser = in.readString();
        sumCost = in.readDouble();
        branchNo = in.readString();
        id = in.readInt();
        createTime = in.readString();
        deliveryCost = in.readDouble();
        dealerId = in.readInt();
        satisfaction = in.readInt();
        orderStatus = in.readInt();
        consigneePhone = in.readString();
        evaluateStatus = in.readInt();
        subscribeTime = in.readString();
    }

    public static final Creator<OrderConstListBean> CREATOR = new Creator<OrderConstListBean>() {
        @Override
        public OrderConstListBean createFromParcel(Parcel in) {
            return new OrderConstListBean(in);
        }

        @Override
        public OrderConstListBean[] newArray(int size) {
            return new OrderConstListBean[size];
        }
    };

    public String getConsigneeAddr() {
        return consigneeAddr;
    }

    public void setConsigneeAddr(String consigneeAddr) {
        this.consigneeAddr = consigneeAddr;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public double getAgentPaidCost() {
        return agentPaidCost;
    }

    public void setAgentPaidCost(double agentPaidCost) {
        this.agentPaidCost = agentPaidCost;
    }

    public double getPaidCost() {
        return paidCost;
    }

    public void setPaidCost(double paidCost) {
        this.paidCost = paidCost;
    }

    public double getInstallCost() {
        return installCost;
    }

    public void setInstallCost(double installCost) {
        this.installCost = installCost;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public double getSumCost() {
        return sumCost;
    }

    public void setSumCost(double sumCost) {
        this.sumCost = sumCost;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public int getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(int evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(consigneeAddr);
        dest.writeString(consigneeName);
        dest.writeDouble(agentPaidCost);
        dest.writeDouble(paidCost);
        dest.writeDouble(installCost);
        dest.writeString(deliveryNo);
        dest.writeString(createUser);
        dest.writeDouble(sumCost);
        dest.writeString(branchNo);
        dest.writeInt(id);
        dest.writeString(createTime);
        dest.writeDouble(deliveryCost);
        dest.writeInt(dealerId);
        dest.writeInt(satisfaction);
        dest.writeInt(orderStatus);
        dest.writeString(consigneePhone);
        dest.writeInt(evaluateStatus);
        dest.writeString(subscribeTime);
    }
}

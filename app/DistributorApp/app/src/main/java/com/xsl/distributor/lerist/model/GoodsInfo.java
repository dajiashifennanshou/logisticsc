package com.xsl.distributor.lerist.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lerist on 2016/7/14, 0014.
 */

public class GoodsInfo implements Parcelable{

    /**
     * id : 27
     * goodsName : 实木电视柜一套
     * goodsBrand : 北美之恋
     * park : 41
     * goodsType : BM1800
     * vender :
     * weight : 20.0
     * volume : 20.0
     * elseExplan :
     * createTime : 1468377230000
     * createUser : 刘德华
     * remark : 123
     * goodsNum : 2
     * wayBillNo : 2
     * inStorageStatus : 0
     * outStorageStatus : 1
     * installCost : 0.0
     */

    private int id;
    private String goodsName;
    private String goodsBrand;
    private int park;
    private String goodsType;
    private String vender;
    private double weight;
    private double volume;
    private String elseExplan;
    private String createTime;
    private String createUser;
    private String remark;
    private int goodsNum;
    private String wayBillNo;
    private int inStorageStatus;
    private int outStorageStatus;
    private double installCost;

    public GoodsInfo() {
    }

    protected GoodsInfo(Parcel in) {
        id = in.readInt();
        goodsName = in.readString();
        goodsBrand = in.readString();
        park = in.readInt();
        goodsType = in.readString();
        vender = in.readString();
        weight = in.readDouble();
        volume = in.readDouble();
        elseExplan = in.readString();
        createTime = in.readString();
        createUser = in.readString();
        remark = in.readString();
        goodsNum = in.readInt();
        wayBillNo = in.readString();
        inStorageStatus = in.readInt();
        outStorageStatus = in.readInt();
        installCost = in.readDouble();
    }

    public static final Creator<GoodsInfo> CREATOR = new Creator<GoodsInfo>() {
        @Override
        public GoodsInfo createFromParcel(Parcel in) {
            return new GoodsInfo(in);
        }

        @Override
        public GoodsInfo[] newArray(int size) {
            return new GoodsInfo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public int getPark() {
        return park;
    }

    public void setPark(int park) {
        this.park = park;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getElseExplan() {
        return elseExplan;
    }

    public void setElseExplan(String elseExplan) {
        this.elseExplan = elseExplan;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo;
    }

    public int getInStorageStatus() {
        return inStorageStatus;
    }

    public void setInStorageStatus(int inStorageStatus) {
        this.inStorageStatus = inStorageStatus;
    }

    public int getOutStorageStatus() {
        return outStorageStatus;
    }

    public void setOutStorageStatus(int outStorageStatus) {
        this.outStorageStatus = outStorageStatus;
    }

    public double getInstallCost() {
        return installCost;
    }

    public void setInstallCost(double installCost) {
        this.installCost = installCost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(goodsName);
        dest.writeString(goodsBrand);
        dest.writeInt(park);
        dest.writeString(goodsType);
        dest.writeString(vender);
        dest.writeDouble(weight);
        dest.writeDouble(volume);
        dest.writeString(elseExplan);
        dest.writeString(createTime);
        dest.writeString(createUser);
        dest.writeString(remark);
        dest.writeInt(goodsNum);
        dest.writeString(wayBillNo);
        dest.writeInt(inStorageStatus);
        dest.writeInt(outStorageStatus);
        dest.writeDouble(installCost);
    }
}

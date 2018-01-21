package com.xsl.distributor.lerist.model;

import java.util.List;

/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class GoodsOutInfo {

    /**
     * deliveryNo : PS-2016071310525956
     * createTime : 1468378380000
     * goods : [{"id":26,"goodsName":"木架沙发一套","goodsBrand":"北美之恋","park":43,"goodsType":"BM左妃+单+三","vender":"","elseExplan":"","createTime":"2016-07-13 10:33:50.0","createUser":"刘德华","remark":"123","goodsNum":3,"wayBillNo":"2","inStorageStatus":0,"outStorageStatus":1,"installCost":0}]
     */

    private String deliveryNo;
    private String createTime;
    /**
     * id : 26
     * goodsName : 木架沙发一套
     * goodsBrand : 北美之恋
     * park : 43
     * goodsType : BM左妃+单+三
     * vender :
     * elseExplan :
     * createTime : 2016-07-13 10:33:50.0
     * createUser : 刘德华
     * remark : 123
     * goodsNum : 3
     * wayBillNo : 2
     * inStorageStatus : 0
     * outStorageStatus : 1
     * installCost : 0.0
     */

    private List<GoodsBean> goods;

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        private int id;
        private String goodsName;
        private String goodsBrand;
        private int park;
        private String goodsType;
        private String vender;
        private String elseExplan;
        private String createTime;
        private String createUser;
        private String remark;
        private int goodsNum;
        private String wayBillNo;
        private int inStorageStatus;
        private int outStorageStatus;
        private double installCost;

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
    }
}

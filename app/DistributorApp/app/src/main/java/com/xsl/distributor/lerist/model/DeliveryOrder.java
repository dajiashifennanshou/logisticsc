package com.xsl.distributor.lerist.model;

import java.util.List;

/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class DeliveryOrder{


    /**
     * id : 69
     * branchNo : YC-00007
     * deliveryNo : PS-64
     * wayBillNo : 5695949160708001
     * dealerId : 27
     * consigneeName : 王志宇
     * consigneePhone : 15292455091
     * consigneeAddr : 江苏省苏州市金阊区浙江省温州市鹿城区大洋街2205号
     * orderStatus : 0
     * deliveryCost : 10.0
     * installCost : 0.0
     * otherCost : 0.0
     * sumCost : 0.0
     * agentPaidCost : 2.0
     * satisfaction : 3
     * evaluateStatus : 0
     * lstGoods : [{"id":26,"goodsName":"木架沙发一套","goodsBrand":"北美之恋","park":43,"goodsType":"BM左妃+单+三","vender":"","weight":20,"volume":20,"elseExplan":"","createTime":"2016-07-13 10:33:50.0","createUser":"刘德华","remark":"123","goodsNum":3,"wayBillNo":"5695949160708001","inStorageStatus":0,"outStorageStatus":1,"installCost":0},{"id":27,"goodsName":"实木电视柜一套","goodsBrand":"北美之恋","park":41,"goodsType":"BM1800","vender":"","weight":20,"volume":20,"elseExplan":"","createTime":"2016-07-13 10:33:50.0","createUser":"刘德华","remark":"123","goodsNum":2,"wayBillNo":"5695949160708001","inStorageStatus":0,"outStorageStatus":1,"installCost":0}]
     * isExcepiton : 1
     * customerSug "wwwww"
     */

    private int id;
    private String branchNo;
    private String deliveryNo;
    private String wayBillNo;
    private int dealerId;
    private String consigneeName;
    private String consigneePhone;
    private String consigneeAddr;
    private int orderStatus;
    private double deliveryCost;
    private double installCost;
    private double otherCost;
    private double sumCost;
    private double agentPaidCost;
    private int satisfaction;
    private int evaluateStatus;
    private int isExcepiton;
    private String customerSug;



    /**
     * id : 26
     * goodsName : 木架沙发一套
     * goodsBrand : 北美之恋
     * park : 43
     * goodsType : BM左妃+单+三
     * vender :
     * weight : 20.0
     * volume : 20.0
     * elseExplan :
     * createTime : 2016-07-13 10:33:50.0
     * createUser : 刘德华
     * remark : 123
     * goodsNum : 3
     * wayBillNo : 5695949160708001
     * inStorageStatus : 0
     * outStorageStatus : 1
     * installCost : 0.0
     */

    private List<LstGoods> lstGoods;

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

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo;
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

    public double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(double otherCost) {
        this.otherCost = otherCost;
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
    public String getCustomerSug() {
        return customerSug;
    }

    public void setCustomerSug(String customerSug) {
        this.customerSug = customerSug;
    }

    public int getIsExcepiton() {
        return isExcepiton;
    }

    public void setIsExcepiton(int isExcepiton) {
        this.isExcepiton = isExcepiton;
    }

    public List<LstGoods> getLstGoods() {
        return lstGoods;
    }

    public void setLstGoods(List<LstGoods> lstGoods) {
        this.lstGoods = lstGoods;
    }

    public static class LstGoods {
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
    }
}

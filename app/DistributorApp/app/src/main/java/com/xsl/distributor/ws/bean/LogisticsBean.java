package com.xsl.distributor.ws.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class LogisticsBean {
    /**订单号*/
    private String order_num;
    /**状态*/
    private String status;
    /**姓名*/
    private String name;
    /**电话号码*/
    private String number;
    /**地址*/
    private String address;
    /**商品名称*/
    private List<String>goods;
    /**代收款*/
    private String daishou_money;
    /**配送费*/
    private String logistics_money;
    /**收款状态*/
    private String money_status;

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getMoney_status() {
        return money_status;
    }

    public void setMoney_status(String money_status) {
        this.money_status = money_status;
    }

    public String getLogistics_money() {
        return logistics_money;
    }

    public void setLogistics_money(String logistics_money) {
        this.logistics_money = logistics_money;
    }

    public String getDaishou_money() {
        return daishou_money;
    }

    public void setDaishou_money(String daishou_money) {
        this.daishou_money = daishou_money;
    }

    public List<String> getGoods() {
        return goods;
    }

    public void setGoods(List<String> goods) {
        this.goods = goods;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

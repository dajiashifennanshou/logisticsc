package com.xsl.distributor.ws.bean;

/**
 * Created by Administrator on 2016/7/25 0025.
 * 安装配送费
 */
public class KindOfPriceBean {

    /**
     * installCost : 0.0
     * deliveryCost : 0.0
     */

    private double installCost;
    private double deliveryCost;

    public double getInstallCost() {
        return installCost;
    }

    public void setInstallCost(double installCost) {
        this.installCost = installCost;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}

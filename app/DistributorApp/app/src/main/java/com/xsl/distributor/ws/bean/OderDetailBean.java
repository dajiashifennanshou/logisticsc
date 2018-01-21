package com.xsl.distributor.ws.bean;

import java.util.List;


/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class OderDetailBean {
    /**商家名*/
    private String orderName;
    /**订单状态*/
    private String orderStatus;
    /**公司图标*/
    private  String companyIcon;
    /**发货人姓名*/
    private String senderName;
    /**发货状态，自送网店，客户自提*/
    private String sendOrderStatus;
    /**发货地址*/
    private String senderAddress;
    /**发货网店*/
    private String senderNet;
    /**收货人姓名*/
    private  String receiverName;
    /**收货状态，客户自提等*/
    private String receiveOrderStatus;
    /**收货人地址*/
    private String receiverAddress;
    /**收货网点*/
    private String receiverNet;
    /**配送司机*/
    private String dreverName;
    /**驾照*/
    private String licences;
    /**回单*/
    private String receipt;
    /**等待发货*/
    private String isGoingSend;
    /**代收货款*/
    private String daishouMoney;
    /**装货*/
    private String isShipments;
    /**卸货*/
    private String isUnloading;
    /**综合服务信息*/
    private String infoService;
    /**专线投保*/
    private String isSafest;
    /**投保金额*/
    private String safestMoney;
    /**发票信息*/
    private String isNeedInvoice;
    /**支付方式*/
    private String payWay;
    /**货物信息*/
    private List<LogisticDetailBean>goods_info;
    /**费用明细*/
    private String moneyDetail;


}

package com.xsl.distributor.lerist.model;

/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class UriConstants {
        public static final String HOST = "http://www.camc-logistics.com/Distributor";
//    public static final String HOST = "http://app.xslwl56.com:8081/Distributor";
    //登录
    public static final String LOGIN = HOST + "/app/dealerLogin.yc";
    //注册
    public static final String REGISTER = HOST + "/app/registerDealer.yc";
    //注册极光推送
    public static final String REGISTER_JPUSH = HOST + "/app/editJpushUserInfo.yc";
    //修改密码
    public static final String UPDATEPWD = HOST + "/app/modYcDealer.yc";
    //获取验证码
    public static final String SENDCODE = HOST + "/app/validCode.yc";
    //找回密码验证码
    public static final String SENDCODE_FORGETPWD = HOST + "/app/getCode.yc";
    //搜索
    public static final String SEARCH = HOST + "/app/getYcDeliveryOrderList.yc";

    //配送单列表
    public static final String DELIVERYORDER_LIST = HOST + "/app/getYcDeliveryOrderList.yc";
    //配送单详情
    public static final String DELIVERYORDER_DETAIL = HOST + "/app/getYcDeliveryOrderSingle.yc";

    //常用客户
    public static final String COMMONCLIENT = HOST + "/app/getCommonClient.yc";
    //入库列表
    public static final String GOODSIN = HOST + "/app/getInStorageList.yc";
    //出库列表
    public static final String GOODSOUT = HOST + "/app/getOutGoodsList.yc";
    //我的云仓
    public static final String GOODSLIST = HOST + "/app/getPageGoodsByDealerId.yc";
    //添加配送单
    public static final String DELIVERYORDER_ADD = HOST + "/app/addYcDeliveryOrder.yc";

    /**
     * 获取安装配送费用
     */
    public static final String RUNNING_MONEY = HOST + "/app/getOrderCostList.yc";
    /**
     * 获取专线运输费用
     */
    public static final String DEDICATED_MONEY = HOST + "/app/getSpecialTransportation.yc";
    /**
     * 获取专线运输详情
     */
    public static final String DEDICATED_MONEY_DETAIL = HOST + "/app/getUserOrderDetails.yc";
    /**
     * 极光推送上传数据接口
     */
    public static final String UPDATE_JPUSH_ID = HOST + "/app/editJpushUserInfo.yc";
    /**
     * 加盟云仓
     */
    public static final String ADD_JION = HOST + "/app/applyJoin.yc";
    /**
     * 获取云仓网点
     */
    public static final String GET_STORAGE_BRANCH = HOST + "/app/getStorageBranch.yc";

    /**
     * 开户行所在省
     */
    public static final String GET_PROVINCE = HOST + "/app/getProvinceName.yc";
    /**
     * 开户行所在市
     */
    public static final String GET_CITY = HOST + "/app/getCityName.yc";
    /**
     * 开户银行
     */
    public static final String GET_BANK = HOST + "/app/getHeadName.yc";
    /**
     * 开户支行
     */
    public static final String GET_BANK_BRANCH = HOST + "/app/getBranchName.yc";

    //上传银行卡等照片
    public static final String BANK_UPLOADIMG = HOST + "/app/uploadBankImg.yc";
    //绑定银行卡
    public static final String BANK_BINDING = HOST + "/app/bankBinding.yc";
    /**
     * 查询首页公告消息
     */
    public static final String CHECK_BULLETIN = HOST + "/app/getBulletinInfo.yc";
    /**
     * 安装配送费
     */
    public static final String GET_KIND_OF_PRICE = HOST + "/app/getYcInstallCostBy.yc";
}

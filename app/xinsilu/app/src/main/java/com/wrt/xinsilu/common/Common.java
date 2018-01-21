package com.wrt.xinsilu.common;

/**
 * Created by Administrator on 2016/6/28 0028.
 * 通用配置
 */
public class Common {

    /**
     * 服务器地址
     */
//    public static final String URL = "http://192.168.0.2:8080/TheOwner/";
//    public static final String URL = "http://192.168.0.117:8080/TheOwner/";
    public static final String URL = "http://www.camc-logistics.com/TheOwner/";
    /**
     * 登陆
     */
    public static final String LOGIN = URL + "app/consignerLogin.yc";
    /**
     * 注册
     */
    public static final String REGISTER = URL + "app/consignerRegister.yc";
    /**
     * 获取验证码
     */
    public static final String GET_SMS = URL + "app/getCode.yc";
    /**
     * 获取验证码（验证手机号是否注册）
     */
    public static final String GET_VALID_CODE = URL + "app/validCode.yc";
    /**
     * 修改密码接口
     */
    public static final String UPDATE_PWD = URL + "app/updatePassword.yc";
    /**
     * 获取地址
     */
    public static final String GET_XZQH = URL + "app/getXZQH.yc";
    /**
     * 附近物流商
     */
    public static final String NEAR_LOGISTICS = URL + "app/getNearbyLineInfo.yc";
    /**
     * 发货路线查询
     */
    public static final String LINE_INFO = URL + "app/getLineInfo.yc";
    /**
     * 添加订单接口
     */
    public static final String ADD_ORDER = URL + "app/addOrder.yc";
    /**
     * 查询物流商
     */
    public static final String GET_COMPANY_INFO = URL + "app/getCompanyInfo.yc";
    /**
     * 物流商信息详情
     */
    public static final String GET_COMPANY_DETAIL = URL + "app/getCompanyDetails.yc";
    /**
     * 常用物流商
     */
    public static final String GET_COMMON_COMPANY = URL + "app/getCompanyCollect.yc";
    /**
     * 查询评价详情
     */
    public static final String COMMENT_DETAIL = URL + "app/getEvaluateInfo.yc";
    /**
     * 查看个人信息
     */
    public static final String GET_USER = URL + "app/getUser.yc";
    /**
     * 查询个人订单
     */
    public static final String GET_USER_ORDER = URL + "app/getUserOrder.yc";
    /**
     * 查询个人订单详情
     */
    public static final String GET_USER_ORDER_DETAIL = URL + "app/getUserOrderDetails.yc";
    /**
     * 订单评价接口
     */
    public static final String GET_ORDER_COMMENT_DETAIL = URL + "app/addEvaluateInfo.yc";
    /**
     * 常用联系人
     */
    public static final String COMMON_CONTACTS = URL + "app/getCommonContact.yc";
    /**
     * 添加常用联系人
     */
    public static final String ADD_COMMON_CONTACTS = URL + "app/addCommonContact.yc";
    /**
     * 删除常用联系人
     */
    public static final String DELETE_COMMON_CONTACTS = URL + "app/delCommonContact.yc";
    /**
     * 常用司机
     */
    public static final String COMMON_DRIVER = URL + "app/getCommonDriver.yc";
    /**
     * 添加常用司机
     */
    public static final String ADD_COMMON_DRIVER = URL + "app/addCommonDriver.yc";
    /**
     * 删除常用司机
     */
    public static final String DELETE_COMMON_DRIVER = URL + "app/delCommonDriver.yc";
    /**
     * 查询收藏
     */
    public static final String MY_COLLECT_INFO = URL + "app/getCollectInfo.yc";
    /**
     * 添加收藏物流商
     */
    public static final String ADD_COLLECT = URL + "app/addCollect.yc";
    /**
     * 删除收藏路线
     */
    public static final String DELETE_COLLECT = URL + "app/delCollect.yc";
    /**
     * 删除收藏物流商
     */
    public static final String DELETE_COMPANY_COLLECT = URL + "app/delCompanyCollect.yc";
    /**
     * 收藏物流商
     */
    public static final String ADD_COMPANY_COLLECT = URL + "app/addCompanyCollect.yc";
    /**
     * 申请企业贷主
     */
    public static final String APPLY_ENTER_PRISE = URL + "app/applyEnterprise.yc";
    /**
     * 批量删除收藏线路
     */
    public static final String MULTI_DEL_COLLECT = "app/multiDelCollect.yc";
    /**
     * 批量删除收藏物流商
     */
    public static final String MULTI_DEL_COMPANY_COLLECT = "app/multiDelCompanyCollect.yc";

    /**
     * 获取包装类型
     */
    public static final String GET_PACKTYPE = URL + "app/getPackType.yc";

    /**
     * 取消订单
     */
    public static final String CANCEL_ORDER = URL + "app/cancelOrder.yc";

    /**
     * 订单支付
     */
    public static final String ORDER_PAY = URL + "app/orderPay.yc";

    /**
     * 更新极光推送id
     */
    public static final String UPDATE_JPUSH = URL + "app/editJpushUserInfo.yc";

    /**
     * 修改个人信息
     */
    public static final String UPDATE_USER = URL + "app/updateUser.yc";
    /**
     * 查询公司信息
     */
    public static final String GET_USER_COMPANY = URL + "app/getUserCompany.yc";
    /**
     * 获取网点地址
     */
    public static final String GET_OUTLES_ADDRESS = URL + "app/getOutlesAddress.yc";
    /**
     * 获取公司信息图片
     */
    public static final String GET_COMPANY_IMG = URL + "app/getImg.yc?url=";

    /**
     * 获取增值服务
     */
    public static final String GET_ADDSERVICE = URL + "app/AdditionalConf.yc";


    public static final String[] str = new String[]{"营业执照", "公司照片", "法人身份证", "名片照片", "公司logo"};
    public static final String[] company_info = new String[]{"公司名称：", "公司地址：", "法定代表人：", "法定人电话：", "联系人：", "联系人电话：", "组织代码：",
            "邮政编码：", "公司电话：", "公司传真：", "税务登记号：", "财务邮箱：", "联系QQ：", "公司介绍："};
    public static final String[] company_hint = new String[]{"请填写公司名称", "请填写公司地址", "请填写法定代表人", "请填写法定人电话", "请填写联系人姓名", "请填写联系人电话", "请填写组织代码",
            "请填写邮政编码", "请填写公司电话", "请填写公司传真", "请填写税务登记号", "请填写财务邮箱", "请填写联系QQ号码", "请填写公司介绍"};

}

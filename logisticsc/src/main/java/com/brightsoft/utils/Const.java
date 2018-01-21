package com.brightsoft.utils;

public class Const {

	//状态 0 不可用 1可用
	public static final int STATE_NO=0;
	public static final int STATE_YES=1;
	
	//状态 0 不可用 1可用
	public static final int MENU_LEVEL_0=0;
	public static final int MENU_LEVEL_1=1;
	public static final int MENU_LEVEL_2=2;
	public static final int MENU_LEVEL_3=3;
	
	//货运交易系统用户状态 0不可用 1可用
	public static final int PLATFORMUSER_STATUS_0 =0;
	public static final int PLATFORMUSER_STATUS_1 =1;
	
	//货运交易系统用户类型 1=个人 2=企业货主 3=专线 4=物流提供商 5=网点
	public static final int PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR =1;
	public static final int PLATFORM_USER_TYPE_ENTERPRISE_CONSIGNOR =2;
	public static final int PLATFORM_USER_TYPE_LINE_COMPANY =3;
	public static final int PLATFORM_USER_TYPE_LINE_PROVIDER =4;
	public static final int PLATFORM_USER_TYPE_DOT=5;
	
	//货运交易系统常用联系人类型 0发货人 1收货人
	public static final int PLATFORMUSER_COMMONCONTACT_CONTACTSTYPE_0 =0;
	public static final int PLATFORMUSER_COMMONCONTACT_CONTACTSTYPE_1 =1;
	
	//货运交易系统评价状态
	public static final int PLATFORMUSER_EVALUATION_STATE_INVALID =0;
	public static final int PLATFORMUSER_EVALUATION_STATE_VALID =1;
	
	//用户状态 0 不可用 1可用
	public static final int PLATFORM_USER_STATUS_INVALID=0;
	public static final int PLATFORM_USER_STATUS_VALID=1;
	
	//专线营运系统用户消息订阅 0是没有选中 1是选中
	public static final int TMS_MESSAGE_CGECKED_0=0;
	public static final int TMS_MESSAGE_CGECKED_1=1;
	//专线营运系统用户类型
	public static final int TMS_USER_TYPE_ENTERPRISE = 0;//专线
	public static final int TMS_USER_TYPE_LOGISTICSC = 1;//物流商
	public static final int TMS_USER_TYPE_OUTLETS = 2;//网点管理员
	public static final int TMS_USER_TYPE_OPERATOR = 3;//普通操作员
	
	//专线营运系统线路状态
	public static final int TMS_LINE_STATUS_INVALID = 0;
	public static final int TMS_LINE_STATUS_VALID = 1;
	
	//专线营运系统提送货状态
	public static final int TMS_OUTLETS_CONF_STATUS_INVALID = 0;
	public static final int TMS_OUTLETS_CONF_STATUS_VALID = 1;

	//专线营运系统车辆状态
	public static final int TMS_VEHICLE_STATUS_INVALID = 0;
	public static final int TMS_VEHICLE_STATUS_VALID = 1;
		
	//专线营运系统司机状态
	public static final int TMS_DRIVER_STATUS_INVALID = 0;
	public static final int TMS_DRIVER_STATUS_VALID = 1;
	
	//专线营运系统消息通知类型
	public static final int TMS_NOTICE_TYPE_ENTERPRISE = 1;
	public static final int TMS_NOTICE_TYPE_PLATFORM = 2;
	
	//专线营运系统反馈状态
	public static final int TMS_FEEDBACK_STATUS_INVALID = 0;
	public static final int TMS_FEEDBACK_STATUS_VALID = 1;
	
	//专线营运系统用户状态0不可用1可用
	public static final int TMS_USER_STATUS_INVALID = 0;
	public static final int TMS_USER_STATUS_VALID = 1;
		
	//专线营运系统预存费比例状态
	/*public static final int TMS_DEPOSIT_RATIO_STATUS_NOT_PUBLISHED = 1;//未发布
	public static final int TMS_DEPOSIT_RATIO_STATUS_CHECKING = 2;//审核中
	public static final int TMS_DEPOSIT_RATIO_STATUS_PUBLISHED = 3;//已发布
	public static final int TMS_DEPOSIT_RATIO_STATUS_PUBLISHED_FAILED = 4;//发布失败
	public static final int TMS_DEPOSIT_RATIO_STATUS_TIME_OUT = 5;//过期
*/	
	//专线营运系统异常状态
	public static final int TMS_ABNORMAL_STATUS_CLOSED = 0;//异常已处理
	public static final int TMS_ABNORMAL_STATUS_NOT_HANDLED = 1;//异常未被处理
	public static final int TMS_ABNORMAL_STATUS_HANDLED = 2;//异常已处理
	
	//广告状态
	public static final int TMS_AD_STATUS_INVALID = 0;//不可用
	public static final int TMS_AD_STATUS_VALID = 1;//可用
	public static final int TMS_AD_STATUS_REVOKE = 2;//已撤销
	public static final int TMS_AD_STATUS_TIME_OUT = 3;//已过期
	
	//货运交易系统公司状态 0不可用 1可用
	public static final int PLATFORMUSER_COMPANY_STATUS_0=0;
	public static final int PLATFORMUSER_COMPANY_STATUS_1=1;

//	//货运交易系统用户消费记录 0充值记录 1转账记录 2提现记录
//	public static final int PLATFORMUSER_USER_CONSUME_TYPE_EECHARGE=0;
//	public static final int PLATFORMUSER_USER_CONSUME_TYPE_TRANSFE_RACCOUNTS=1;
//	public static final int PLATFORMUSER_USER_CONSUME_TYPE_WITHDRAWALS=2;
	
	//货运交易系统我的常发货物 0不可用 1可用
	public static final int PLATFORMUSER_USER_CONSUME_COMMON_CARGO_STATE_0=0;
	public static final int PLATFORMUSER_USER_CONSUME_COMMON_CARGO_STATE_1=1;
	
	//货运交易系统订单评价 1已评价 2已回复评价
	public static final int PLATFORMUSER_ORDER_EVALUATION_STATE_NOT_REPLIED=1;
	public static final int PLATFORMUSER_ORDER_EVALUATION_STATE_REPLIED=2;
	//货运交易系统订单评价 0未评价 1已评价
	public static final int  PLATFORMUSER_ORDER_EVALUATION_0=0;
	public static final int  PLATFORMUSER_ORDER_EVALUATION_1=1;
	//货运交易系统订单评价 0未投诉 1已投诉
	public static final int PLATFORMUSER_ORDER_COMPLAIN_0=0;
	public static final int PLATFORMUSER_ORDER_COMPLAIN_1=1;
	//货运交易系统订单评价回复0未查看1已查看
	public static final int PLATFORMUSER_ORDER_EVALUATION_REPLY_STATE_INVALID =0;
	public static final int PLATFORMUSER_ORDER_EVALUATION_REPLY_STATE_VALID=1;
	
	//货运交易系统订单投诉 1已投诉 2已回复投诉 3已反馈投诉
	public static final int PLATFORMUSER_ORDER_COMPLAIN_STATE_NOT_REPLIED = 1;
	public static final int PLATFORMUSER_ORDER_COMPLAIN_STATE_REPLIED = 2;
	public static final int PLATFORMUSER_ORDER_COMPLAIN_STATE_FEEDBACKED = 3;

	//货运交易系统订单投诉 1未查看 2已查看
	public static final int PLATFORMUSER_ORDER_COMPLAIN_STATE_NOT_HANDLED = 1;
	public static final int PLATFORMUSER_ORDER_COMPLAIN_STATE_HANDLED = 2;
					
	
	//货运交易系统订单投诉0不可用1可用
	public static final int PLATFORMUSER_ORDER_COMPLAIN_STATE_INVALID =0;
	public static final int PLATFORMUSER_ORDER_COMPLAIN_STATE_VALID=1;
	
	//货运交易系统代收货物款状态 0未完成 1完成
	public static final int PLATFORMUSER_ORDER_AGENCY_FUND_STATE_0 = 0;
	public static final int PLATFORMUSER_ORDER_AGENCY_FUND_STATE_1 = 1;
	
	// 货运交易系统账单状态 0未完成 1已完成
	public static final int PLATFORMUSER_ORDER_BILL_UNFINISHED = 0;
	public static final int PLATFORMUSER_ORDER_BILL_FINISHED = 1;
	
	//货运交易系统线路收藏  0不可用 1可用
	public static final int PLATFORMUSER_STORE_RECORD_STATE_0 =0;
	public static final int PLATFORMUSER_STORE_RECORD_STATE_1 =1;
	// 是否代收货款 
	public static final int PLATFORM_ORDER_IS_COLLECTION_DELIVERY = 1;
	public static final int PLATFORM_ORDER_NOT_COLLECTION_DELIVERY = 0;
	
	//货运交易系统公司资质图片上传类型 0=logo 1=营业执照 2=公司照片 3= 法定人身份证照片 4=名片照片
	public static final int PLATFORM_USER_COMPANY_IMG_TYPE_0=0;
	public static final int PLATFORM_USER_COMPANY_IMG_TYPE_1=1;
	public static final int PLATFORM_USER_COMPANY_IMG_TYPE_2=2;
	public static final int PLATFORM_USER_COMPANY_IMG_TYPE_3=3;
	public static final int PLATFORM_USER_COMPANY_IMG_TYPE_4=4;
	
	//货运交易系统我的常用联系人状态 0=不可用 1=可用
	public static final int PLATFORM_USER__COMMON_CONTACT_0 =0;
	public static final int PLATFORM_USER__COMMON_CONTACT_1 =1;
	
	//货运交易系统银行卡状态 0 = 可用 1= =不可用
	public static final int PLATFORM_USER_BANK_CARD_0 =0;
	public static final int PLATFORM_USER_BANK_CARD_1 =1;
	
	//货运交易系统公司 是否推荐 0为推荐 1为不推荐 默认为0
	public static final int PLATFORM_USER_COMPANY_RECOMMENDED_0=0;
	public static final int PLATFORM_USER_COMPANY_RECOMMENDED_1=1;
	
	//货运交易系统用户状态 0为禁用 1为启用
	public static final int PLATFORMUSER_USERSTATUS_FORBID = 0;
	public static final int	PLATFORMUSER_USERSTATUS_ENABLE = 1;
	
	//专线营运系统用户状态 0 为禁用1为启用
	public static final int TMSUSER_USERSTATUS_FORBID = 0;
	public static final int TMSUSER_USERSTATUS_ENABLE = 1;
	
	//专线营运系统角色状态 0 为禁用1为启用
	public static final int TMS_ROLE_STATUS_FORBID = 0;
	public static final int TMS_ROLE_STATUS_ENABLE = 1;
	
	//货运交易系统用户角色 -1为专线 0 为物流商
	public static final long PLATFORM_USER_ROLE_LINE = -1l;
	public static final long PLATFORM_USER_ROLE_LOGISTICSC = 0l;
	
	//广告发布 0货运交易系统 1专线营运系统
	public static final int AD_PUBLISH_TYPE_PLATFORM = 0;
	public static final int AD_PUBLISH_TYPE_TMS = 1;
	
	//广告发布 0货运交易系统 1专线营运系统
	public static final int INFO_PUBLISH_TYPE_PLATFORM = 0;
	public static final int INFO_PUBLISH_TYPE_TMS = 1;
	
	//帮助中心 状态是否可以 0不可用 1可用
	public static final int PLATFORM_HELP_STATE_0=0;
	public static final int PLATFORM_HELP_STATE_1=1;
	
	//帮助内容中心 状态是否可以 0不可用 1可用
	public static final int PLATFORM_HELPCONTENT_STATE_0=0;
	public static final int PLATFORM_HELPCONTENT_STATE_1=1;
	
	//专线营运系统 新闻  0 公司新闻  1物流资讯
	public static final int TMS_NEWS_TYPE_0=0;
	public static final int TMS_NEWS_TYPE_1=1;
	
	//专线营运系统角色
	public static final int TMS_ROLE_OWNER_SYS_LOGISTICSC = 0;//物流商
	public static final int TMS_ROLE_OWNER_SYS_SPECIAL_LINE = 1;//专线
	public static final int TMS_ROLE_OWNER_SYS_LOGISTICSC_OUTLETS = 2;//专线网点
	public static final int TMS_ROLE_OWNER_SYS_KD = 3;//开单员
	public static final int TMS_ROLE_OWNER_SYS_FINANCE = 4;//财务员
	public static final int TMS_ROLE_OWNER_CUSTOMER = 5;//专线营运系统用户自定义
	public static final int TMS_ROLE_OWNER_SYS_LINE_OUTLETS = 6;//物流商
	public static final int TMS_ROLE_OWNER_SYS_CUSTOMER = 7;//货运交易系统自定义添加
	
	//网点状态
	public static final int TMS_OUTLETS_STATUS_DISABLED = 0;//禁用
	public static final int TMS_OUTLETS_STATUS_ENNABLE = 1;//可用
	
	//货运交易系统短信发送类型 0 =验证码 1=初始密码 2=申请通过
	public static final int PLATFORM_SENDTYPE_0=0;
	public static final int PLATFORM_SENDTYPE_1=1;
	public static final int PLATFORM_SENDTYPE_2=2;
	
	//货运交易系统忘记密码 发送初始密码类型 0=手机 1=邮箱
//	public static final int PLATFORM_FORGETPASSWORD_0=0;
//	public static final int PLATFORM_FORGETPASSWORD_1=1;
	
	//货运交易系统支付订单类型和分账类型  0 保险 1预约费 2运费3保证金 4 pos机
	public static final int PLATFORM_BANK_ORDER_TYPE_0=0;
	public static final int PLATFORM_BANK_ORDER_TYPE_1=1;
	public static final int PLATFORM_BANK_ORDER_TYPE_2=2;
	public static final int PLATFORM_BANK_ORDER_TYPE_3=3;
	public static final int PLATFORM_BANK_ORDER_TYPE_4=4;
	
	//货运交易系统支付订单类型状态 0已成功 1未成功
	public static final int PLATFORM_BANK_PAYMENT_STATE_0=0;
	public static final int PLATFORM_BANK_PAYMENT_STATE_1=1;
	
//	//申请退款记录类型 0 预约费 1运费 2订单
//	public static final int PLATFORM_ORDER_BACK_TYPE_0=0;
//	public static final int PLATFORM_ORDER_BACK_TYPE_1=1;
//	public static final int PLATFORM_ORDER_BACK_TYPE_2=2;
//	//申请退款记录状态 0 未完成 1已退款 2已取消
//	public static final int PLATFORM_ORDER_BACK_STATE_0=0;
//	public static final int PLATFORM_ORDER_BACK_STATE_1=1;
//	public static final int PLATFORM_ORDER_BACK_STATE_2=2;
	
	//货运交易系统用户绑定银行卡信息 0成功 1未成功
	public static final int PLATFORM_ORDER_BANK_STATE_0=0;
	public static final int PLATFORM_ORDER_BANK_STATE_1=1;
	
	//货运交易系统上传资质是否冻结中 0成功 1未成功
	public static final int PLATFORM_ORDER_BANK_QS_0=0;
	public static final int PLATFORM_ORDER_BANK_QS_1=1;
	
	
	//分账状态 0已成功 1未成功 2审核中
	public static final int PLATFORM_ORDER_BANK_SPLIT_STATE_0=0;
	public static final int PLATFORM_ORDER_BANK_SPLIT_STATE_1=1;
	public static final int PLATFORM_ORDER_BANK_SPLIT_STATE_2=2;
	
	//退款记录状态 0成功 1失败
	public static final int PLATFORM_ORDER_BANK_REFUND_0=0;
	public static final int PLATFORM_ORDER_BANK_REFUND_1=1;
	
	//POS机 ServiceCode
	public static final String POS_SERVICE_CODE_LOGIN="COD201";
	public static final String POS_SERVICE_CODE_ORDER="COD402";
	public static final String POS_SERVICE_CODE_PAY="COD403";
	public static final String POS_SERVICE_CODE_SIGN="COD404";
	public static final String POS_SERVICE_CODE_REIMBURSE="COD407";
	public static final String POS_SERVICE_CODE_CANCEL_ORDER="COD406";
	public static final String POS_SRC_SYS_ID="yeepay";
	
	//货运交易系统退款订单类型  0 保险 1预约费 2运费
	public static final int PLATFORM_ORDER_BANK_REFUND_ORDER_TYPE_0=0;
	public static final int PLATFORM_ORDER_BANK_REFUND_ORDER_TYPE_1=1;
	public static final int PLATFORM_ORDER_BANK_REFUND_ORDER_TYPE_2=2;

	//系统退款类型 0用户退款 1物流提供商退款 2货运交易系统退款
	public static final int PLATFORM_ORDER_BANK_REFUND_REFUND_TYPE_0=0;
	public static final int PLATFORM_ORDER_BANK_REFUND_REFUND_TYPE_1=1;
	public static final int PLATFORM_ORDER_BANK_REFUND_REFUND_TYPE_2=2;
	
	//角色所属系统 0专线营运系统用户自定义 1货运交易系统创建
	public static final int TMS_ROLE_PLATFORM_TYPE_CUSTOMER=0;
	public static final int TMS_ROLE_PLATFORM_TYPE_SYSTEM=1;
	
	//分账记录 0 成功 1失败 2审核中
	public static final int PLATFORM_POS_BANK_STATE_0=0;
	public static final int PLATFORM_POS_BANK_STATE_1=1;
	public static final int PLATFORM_POS_BANK_STATE_2=2;
	
	//分账记录类型 0=pos机
	public  static final int PLATFORM_POS_BANK_ORDERTYPE_0=0;
	
	 //分账配置表类型 0 = 运费分账 1=pos转账
	public static final int PLATFORM_BANK_SPLIT_TYPE_0=0;
	public static final int PLATFORM_BANK_SPLIT_TYPE_1=1;
}

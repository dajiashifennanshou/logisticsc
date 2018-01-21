package com.yc.Canstant;
/**
 * 静态变量设置类
 * Author:luojing
 * 2016年5月10日
 */
public class Constant {
	
	public static String SIGN = "4d3c9ab95185d2d1b0562054f04f4021";
	public static String SIGN_DES = "0y32xh5DXpWw0xWDHvLLf+0+RG21xkIwM7L6wOIhlxoHjw0I2YiGeg==";
	
	//平台用户绑定银行卡信息 0成功 1未成功
	public static final int PLATFORM_ORDER_BANK_STATE_0=0;
	public static final int PLATFORM_ORDER_BANK_STATE_1=1;
	
	//平台上传资质是否冻结中 0成功 1未成功
	public static final int PLATFORM_ORDER_BANK_QS_0=0;
	public static final int PLATFORM_ORDER_BANK_QS_1=1;
	
	//支付状态 0成功 1未成功
	public static final int PLATFORM_PAYMENT_STATE_0=0;
	public static final int PLATFORM_PAYMENT_STATE_1=1;
	
	//支付方式 0现付  1到付  3回付   4 月结
	public static final int TMS_PAY_METHOD_0=0;
	public static final int TMS_PAY_METHOD_1=1;
	public static final int TMS_PAY_METHOD_3=3;
	public static final int TMS_PAY_METHOD_4=4;
	
	
	//平台支付订单类型和分账类型  0 保险 1预约费 2运费3保证金 4 pos机,5:安装配送费用
	public static final int PLATFORM_BANK_ORDER_TYPE_0=0;
	public static final int PLATFORM_BANK_ORDER_TYPE_1=1;
	public static final int PLATFORM_BANK_ORDER_TYPE_2=2;
	public static final int PLATFORM_BANK_ORDER_TYPE_3=3;
	public static final int PLATFORM_BANK_ORDER_TYPE_4=4;
	public static final int PLATFORM_BANK_ORDER_TYPE_5=5;
	
	/**
	 * token 过期重新登录
	 */
	public static Integer APP_Permission = -1;
	/**
	 * 成功
	 */
	public static Integer APP_SUCCESS = 10000;
	/**
	 * 失败
	 */
	public static Integer APP_ERROR = 9999;
	
}

/**
 * 
 */
package com.yc.Canstant;
/**
 * 静态变量设置类
 * Author:FENG
 * 2016年5月10日
 */
public class Constant {
	
	public static final String SIGN = "4d3c9ab95185d2d1b0562054f04f4021";
	/**
	 * 没有访问权限
	 */
	public static final Integer APP_Permission = -1;
	/**
	 * 成功
	 */
	public static final Integer APP_SUCCESS = 10000;
	/**
	 * 失败
	 */
	public static final Integer APP_ERROR = 9999;
	
	//平台用户状态 0不可用 1可用
	public static final int PLATFORMUSER_STATUS_0 =0;
	public static final int PLATFORMUSER_STATUS_1 =1;
	
	//平台用户类型 1=个人 2=企业货主 3=专线 4=物流提供商 5=网点
	public static final int PLATFORM_USER_TYPE_PERSONAL_CONSIGNOR =1;
	public static final int PLATFORM_USER_TYPE_ENTERPRISE_CONSIGNOR =2;
	public static final int PLATFORM_USER_TYPE_LINE_COMPANY =3;
	public static final int PLATFORM_USER_TYPE_LINE_PROVIDER =4;
	public static final int PLATFORM_USER_TYPE_DOT=5;
	
	//平台线路收藏  0不可用 1可用
	public static final int PLATFORMUSER_STORE_RECORD_STATE_0 =0;
	public static final int PLATFORMUSER_STORE_RECORD_STATE_1 =1;
	
	//平台物流商收藏  0不可用 1可用
	public static final int PLATFORMUSER_COM_COLLECT_0 =0;
	public static final int PLATFORMUSER_COM_COLLECT_1 =1;
	
	//线路是否收藏:0收藏  1未收藏
	public static final int LINE_IS_COLLECT_0 =0;
	public static final int LINE_IS_COLLECT_1 =1;
	
	//支付状态 0成功 1未成功
	public static final int PLATFORM_PAYMENT_STATE_0=0;
	public static final int PLATFORM_PAYMENT_STATE_1=1;
	
	//增值服务状态 0存在 1不存在
	public static final int ADDITIONALSERVER_STATE_0=0;
	public static final int ADDITIONALSERVER_STATE_1=1;
	
	//订单支付类型0保险   1预约  2运费  3保证金
	public static final int PLATFORM_ORDER_TYPE_0=0;
	public static final int PLATFORM_ORDER_TYPE_1=1;
	public static final int PLATFORM_ORDER_TYPE_2=2;
	public static final int PLATFORM_ORDER_TYPE_3=3;
}

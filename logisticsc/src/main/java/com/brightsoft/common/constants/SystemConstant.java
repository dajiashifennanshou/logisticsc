package com.brightsoft.common.constants;

/**
 * 
 * 系统常量
 */
public interface SystemConstant {

	/** 行政区划 省级父ID */
	String PROVINCE_PID = "100000";
	
	/** session中的用户 变量 */
	String USER_SESSION = "user_session";
	
	/** session中的运营用户 变量 */
	String YYPT_USER_SESSION = "user";
	
	/** session中的TMS用户变量 */
	String TMS_USER_SESSION = "tms_user_session";
	
	/** session中的专线营运系统 sys用户*/
	String TMS_SYS_USER_SESSION = "tms_sys_user_session";
	
	/** 货运交易系统订单号 前缀 */
	String PLATFORM_ORDER_NUMBER_PREFIX = "56";
	
	/** 专线营运系统发车单号 前缀 */
	String DEPARTURE_ORDER_NUMBER_PREFIX = "FC";
	
	/** TMS中转发车单号 前缀 */
	String TRANSFER_ORDER_NUMBER_PREFIX = "ZZ";
}

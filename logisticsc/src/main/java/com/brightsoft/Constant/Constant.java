/**
 * 
 */
package com.brightsoft.Constant;
/**
 * 静态变量设置类
 * Author:FENG
 * 2016年5月10日
 */
public class Constant {
	
	
	
	public static String FULL_PATH;
	public static String FULL_PATHBEGIN="http:";
	/**
	 * 失败及成功的返回code
	 */
	public static Integer RESULT_SUCCESS_CODE=0;
	public static Integer RESULT_ERROR_CODE=1;
	
	//添加信息
	public static String ADD_MSG_001="添加成功";
	public static String ADD_MSG_002="添加失败";
	
	//修改信息
	public static String UPDATE_MSG_001="修改成功";
	public static String UPDATE_MSG_002="修改失败";
	
	//删除信息
	public static String DEL_MSG_001="删除成功";
	public static String DEL_MSG_002="删除失败";
	
	//查询
	public static String GET_MSG_001="查询成功";
	public static String GET_MSG_002="查询失败";
	/**
	 * 从session里获取branchNo的key
	 */
	public static String BRANCHNOKEY="branch_No";
	/**
	 * 获取登陆用户
	 */
	public static String YCLOGINUSER="yc_login_user";
	
	/**************************app接口*********************************/
	
	/**
	 * 没有访问权限
	 */
	public static Integer APP_Permission = -1;
	/**
	 * 成功
	 */
	public static Integer APP_SUCCESS = 10000;
	
	/**
	 * 异常
	 */
	public static Integer APP_Exception = 10001;
	
	/**
	 * 失败
	 */
	public static Integer APP_ERROR_01 = 9999;
	
	/**
	 * 失败,重复添加
	 */
	public static Integer APP_ERROR_02 = 9998;
	
	
	
	
	/*****************************************************************/
	
	/**
	 * 系统操作人员,当前登录人员名称
	 */
	public static String SYSTEM_OPERSTION_PERSON="Feng";
	/**
	 * 当前云仓编号
	 */
	public static String NOW_BRANCHNO="YC-00007";
	public static enum StowageStatus{
		Hnsdf,asfds;
	}
	
	
}

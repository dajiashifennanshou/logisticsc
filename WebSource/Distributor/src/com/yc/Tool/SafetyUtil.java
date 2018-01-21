package com.yc.Tool;
/**
 *Good Luck
 *
*/

/**
 * 用于安全性校验的类
 */
public class SafetyUtil {
   
	/**
	 * 用于验证登录的账户和密码，防止错误输入
	 * Author:FENG
	 * 2015年5月27日
	 * @param strs
	 * @return
	 */
	public static Boolean VerifyLogin(String[] strs){
		Boolean b=true;
		for(String str:strs){
			Integer i=str.indexOf("-")+
			str.indexOf("/")+
			str.indexOf("\\")+
			str.indexOf(",")+
			str.indexOf(".")+
			str.indexOf("'")+
			str.indexOf("\"");
			if(i>0){
				b=false;
				break;
			}
		}
		return b;
	}
}
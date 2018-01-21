package com.brightsoft.utils.yc;

import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;



/**
 * 工具类
 */
public class CommonLIB {
	/**
	 * 判断是否有值
	 * 
	 * @param sStr
	 * @return
	 */
	public static boolean CheckHaveValue(String sStr) {
		if ((sStr != null) && (sStr.length() > 0)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取字符串
	 * 
	 * @param obj
	 * @param sDefaultValue
	 * @return
	 */
	public static String GetString(Object obj, String sDefaultValue) {
		if (obj != null) {
			return obj.toString();
		}

		return sDefaultValue;
	}

	/**
	 * 获取整数
	 * 
	 * @param obj
	 * @param nDefaultValue
	 * @return
	 */
	public static int GetInt(Object obj, int nDefaultValue) {
		if (obj != null) {
			String str = obj.toString();

			if ((str.length() > 0) && (Pattern.compile("^[-]?[0-9]*$").matcher(str).matches()) && (
					(str.length() < 10) || ((str.length() == 10) && (str.charAt(0) == '1')) || ((str.length() == 11) && (str.charAt(0) == '-') && (str.charAt(1) == '1')))) {
				return Integer.parseInt(str);
			}

		}

		return nDefaultValue;
	}

	/**
	 * 获取当前请求地址
	 * 
	 * @param request
	 * @return
	 */
	public static String GetCurUrl(HttpServletRequest request) {
		String sRequestUrl = request.getServletPath();
		String sRequestQueryString = request.getQueryString();

		if (sRequestUrl == null) {
			sRequestUrl = "";
		}

		if (sRequestQueryString == null)
			sRequestQueryString = "";
		else {
			sRequestQueryString = "?" + sRequestQueryString;
		}

		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + sRequestUrl + sRequestQueryString;
	}

//	/**
//	 * 判断是否编辑页
//	 * 
//	 * @param request
//	 * @return
//	 */
//	public static int ISPageUpd(HttpServletRequest request) {
//		String m = CommonLIB.GetString(request.getParameter("m"), "");
//		if (m.equals(Constant.PageShow)) {
//			return 0;
//		}
//
//		return 1;
//	}
//
//	/**
//	 * 设置成功提示文本
//	 * 
//	 * @param request
//	 *            请求
//	 * @param message
//	 *            返回信息
//	 * @param navTabId
//	 *            欲刷新tab的标识
//	 * @param callbackType
//	 *            回调方式
//	 * @return
//	 */
//	public static String SetSucceedAlertInfo(HttpServletRequest request, String message,
//			String navTabId, String callbackType) {
//		return CommonLIB.SetAlertInfo(request, DWZStatusCode.ok, message, navTabId, callbackType, "");
//	}
//
//	/**
//	 * 设置失败提示文本
//	 * 
//	 * @param request
//	 *            请求
//	 * @param message
//	 *            返回信息
//	 * @param navTabId
//	 *            欲刷新tab的标识
//	 * @return
//	 */
//	public static String SetFailedAlertInfo(HttpServletRequest request, String message) {
//		return CommonLIB.SetAlertInfo(request, DWZStatusCode.error, message, "", "", "");
//	}

	
	/**
	 * 返回六位数字随机数
	 */
	public static String Random6Number(){
		Random random = new Random();
		int x = random.nextInt(899999);
		x = x+100000;
		return String.valueOf(x);
	}
}

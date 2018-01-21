package com.brightsoft.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类
 * @author 
 *
 */
public class DateTools {
	
	/**格式化日期：年月日时分秒毫秒*/
	public static final String PATTERN_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

	/**
	 * 获取当前日期年月日时分秒
	 * @return
	 */
	public static Timestamp getYMDHMS(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 获取当前日期年月日
	 * @return
	 */
	public static Date getYMD(){
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * 字符串转换日期类型
	 * @return
	 */
	public static java.util.Date string2Date(String dateStr){
		java.util.Date target = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(StringUtils.isNotBlank(dateStr)){
			try {
				target = sdf.parse(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return target;
	}
	
	/**
	 * 获取当前日期 字符串
	 * @param format 日期格式
	 * @return
	 */
	public static String getCurrentDateStr(String format){
		java.util.Date date = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	   * 将时间格式时间转换为字符串 
	   * 
	   * @param dateDate
	   * @param k
	   * @return
	   */
	public static String dateToStrCustomer(java.util.Date dateDate,String pattern) {
	   SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	   String dateString = formatter.format(dateDate);
	   return dateString;
	}
	/**
	   * 将短时间格式时间转换为字符串 yyyy-MM-dd
	   * 
	   * @param dateDate
	   * @param k
	   * @return
	   */
	public static String dateToStr(java.util.Date dateDate) {
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	   String dateString = formatter.format(dateDate);
	   return dateString;
	}
	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd 
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr2(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
	
	public static String getCurrentDateStr() {
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		   String dateString = formatter.format(new java.util.Date());
		   return dateString;
		}
	
	public static void main(String[] args) {
		System.out.println(dateToStr(new java.util.Date(System.currentTimeMillis())));
	}
}

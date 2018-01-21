/**
 * 
 */
package com.yc.Tool;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换帮助类
 * @author FENG
 *
 */
public class DateUtil {
	/***
	 * 获取系统时间(年-月-日 时:分:秒)
	 * @Title: getDateTimeFormatString
	 * @Description: TODO 获取系统时间(年-月-日 时:分:秒)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getDateTimeFormatString() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 获取毫秒数
	 * Author:FENG
	 * 2016年7月8日
	 * @return
	 */
	public static long getMiller(){
		Date date = new Date();
		return date.getTime();
	}
	/**
	 * 获取日期(年月日时分秒)
	 * @Title: getDateTime
	 * @Description: TODO 获取日期(年月日时分秒)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(date);
	}

	/**
	 * 将日期字符转换为long型毫秒数
	 * @Title: getTime2Long
	 * @Description: TODO 将日期字符转换为long型毫秒数
	 * @param @param date
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static String getTime2Long(String date) {
		long time = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d = sdf.parse(date);
			time = d.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time+"";
	}
	
	/**
	 * 将毫秒数格式(开始时间) 时间条件
	 */
	public static String startTime(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date date = new Date(time);
		String d = sdf.format(date);
		return d;
	}
	
	/**
	 * 将毫秒数格式(结束时间)时间条件
	 */
	public static String endTime(long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Date date = new Date(time);
		String d = sdf.format(date);
		return d;
	}
	
	/**
	 * 将日期字符转换为getTimeInteger毫秒数
	 * @Title: getTime2Long
	 * @Description: TODO 将日期字符转换为long型毫秒数
	 * @param @param date
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static Integer getTimeInteger(String date) {
		long time = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d = sdf.parse(date);
			time = d.getTime()/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int) time;
	}
	
	public static void main(String[] args) throws ParseException {
		System.out.println(DateUtil.getDateTime());
	}
}

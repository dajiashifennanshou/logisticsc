/**
 * 
 */
package com.brightsoft.utils.yc;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	 * 获取日期(年月日时分秒)
	 * @Title: getDateTime
	 * @Description: TODO 获取日期(年月日时分秒)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
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
	 * 将日期字符转换为long型毫秒数
	 * @Title: getTime2Long
	 * @Description: TODO 将日期字符转换为long型毫秒数
	 * @param @param date
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static long getTime2Long(String date) {
		long time = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d = sdf.parse(date);
			time = d.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	/**
	 * 将yyyy-MM-dd HH:mm:ss格式的字符串时间转换为timestamp格式
	 * Author:FENG
	 * 2015年5月31日
	 * @param date
	 * @return
	 */
	public static Timestamp getTime2Timestamp(String date) {
		long time = 0l;
		if(date!=null&&!"".equals(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date d = sdf.parse(date);
				time = d.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Timestamp(time);
		}else{
			return null;
		}
	}
	/**
	 * 将字符串时间转换为timestamp时间
	 * Author:FENG
	 * 2015年5月31日
	 * @param time
	 * @param dataFrmat
	 * @return
	 * @throws ParseException
	 */
	public static Timestamp stringToTime(String time,SimpleDateFormat dataFrmat) throws ParseException{
		Timestamp ts = new Timestamp(dataFrmat.parse(time).getTime());
		return ts;
	}
	/**
	 * 返回timestamp格式的当前时间
	 * Author:FENG
	 * 2015年5月31日
	 * @param date
	 * @return
	 */
	public static Timestamp dateToTime(Date date){
		Timestamp ts = new Timestamp(date.getTime());
		return ts;
	}
	
	public static String getTimeFormat() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * 返回N月后的日期
	 * @Author:luojing
	 * @param date 几个月
	 * @2016年7月7日 下午1:45:45
	 */
	public static String MonthAfterDate(Integer mouth){
		Calendar c = Calendar.getInstance();
		c.add(c.MONTH, mouth);
		c.add(c.DATE, -1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(c.getTime());
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.MonthAfterDate(2));
	}
}

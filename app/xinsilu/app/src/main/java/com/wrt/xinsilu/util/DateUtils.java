package com.wrt.xinsilu.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期/时间工具类
 * @author wangsong
 * @date 2016-3-29
 */
public class DateUtils {
	/** 程序开始时间 */
	public static long startTime;

	/** 设置程序执行时间 */
	public static void setCommandStart() {
		startTime = System.currentTimeMillis();
	}

	/** 获取程序运行时间 */
	public static long getCommandEnd() {
		return System.currentTimeMillis() - startTime;
	}

	/**
	 * 获取默认日期yyyyMMdd
	 * @return String
	 */
	public static String getDateDefault() {
		return getDateDefault(new Date());
	}

	/**
	 * 获取默认日期yyyyMMdd
	 * @param date Date
	 * @return String
	 */
	public static String getDateDefault(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	/**
	 * 获取日期yyyy-MM-dd
	 * @return String
	 */
	public static String getDate() {
		return getDate(new Date());
	}

	/**
	 * 获取日期yyyy-MM-dd
	 * @param date Date
	 * @return String
	 */
	public static String getDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 获取默认时间HHmmss
	 * @return String
	 */
	public static String getTimeDefault() {
		return getTimeDefault(new Date());
	}

	/**
	 * 获取默认时间HHmmss
	 * @param date Date
	 * @return String
	 */
	public static String getTimeDefault(Date date) {
		return new SimpleDateFormat("HHmmss").format(date);
	}

	/**
	 * 获取时间HH:mm:ss
	 * @return String
	 */
	public static String getTime() {
		return getTime(new Date());
	}

	/**
	 * 获取时间HH:mm:ss
	 * @param date Date
	 * @return String
	 */
	public static String getTime(Date date) {
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}

	/**
	 * 获取默认日期时间yyyyMMddHHmmss
	 * @return String
	 */
	public static String getDateTimeDefault() {
		return getDateTimeDefault(new Date());
	}

	/**
	 * 获取默认日期时间yyyyMMddHHmmss
	 * @param date Date
	 * @return String
	 */
	public static String getDateTimeDefault(Date date) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}

	/**
	 * 获取日期时间 yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getDateTime() {
		return getDateTime(new Date());
	}

	/**
	 * 获取日期时间 yyyy-MM-dd HH:mm:ss
	 * @param date Date
	 * @return String
	 */
	public static String getDateTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 获取日期时间 yyyy年MM月dd日
	 * @param date
	 * @return
	 * @throws ParseException
     */
	public static String getDate(long date) throws ParseException {
		return new SimpleDateFormat("yyyy年MM月dd日").format(date);
	}

	/**
	 * 获取过去N天日期
	 * @param lastDay 0,当前数据
	 * @return Date
	 */
	public static Date getLastDate(int lastDay) {
		Date date = new Date();
		date.setTime(date.getTime() - 86400000 * lastDay);
		return date;
	}

	/**
	 * 获取二个日期时间差
	 * @datetime 2016-3-25 下午2:14:02
	 * @param startDate 开始时间
	 * @param splitStr
	 * @return
	 */
	public static String getTimeDifference(Date startDate, String[] splitStr) {
		return getTimeDifference(startDate, new Date(), splitStr);
	}

	/**
	 * 获取二点时间差
	 * @datetime 2015-4-25 下午2:24:27
	 * @param startDate
	 * @param endDate
	 * @return long SS(毫秒)
	 */
	public static long getTimeDifference(Date startDate, Date endDate) {
		return  endDate.getTime() - startDate.getTime();
	}

	/**
	 * 获取二点时间差
	 * @datetime 2015-4-25 下午2:26:04
	 * @param startDate
	 * @return long SS(毫秒)
	 */
	public static long getTimeDifference(Date startDate) {
		return  new Date().getTime() - startDate.getTime();
	}
	/**
	 * 获取二个日期时间差
	 * @datetime 2015-4-25 下午2:02:27
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param splitStr 分隔符数组new String[]{"时","分","秒"} or String[]{":"}
	 * @return
	 */
	public static String getTimeDifference(Date startDate, Date endDate,
										   String[] splitStr) {
		long count = getTimeDifference(startDate, endDate);
		DecimalFormat df2 = new DecimalFormat("00");
		long h = 0;
		long m = 0;
		long s = 0;
		if (count > 0) {
			h = count / 3600000;
			m = (count - h * 3600000) / 60000;
			s = (count - h * 3600000 - m * 60000) / 1000;

		} else {
			count = -count;
			h = -(count / 3600000);
			m = (count - h * 3600000) / 60000;
			s = (count - h * 3600000 - m * 60000) / 1000;
		}
		if (splitStr != null) {
			if (splitStr.length == 3) {
				return df2.format(h) + splitStr[0] + df2.format(m)
						+ splitStr[1] + df2.format(s) + splitStr[2];
			} else if (splitStr.length == 1) {
				return df2.format(h) + splitStr[0] + df2.format(m)
						+ splitStr[0] + df2.format(s);
			}
		}
		return df2.format(h) + df2.format(m) + df2.format(s);
	}
	/**
	 * 获取星期时间，例如，今天是星期一
	 * @param position
	 * @return
	 */
	public static String dateToWeek(int position) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		Date currentDate = new Date();
		int b = currentDate.getDay();
		Date fdate;
		List<String> list = new ArrayList<String>();
		Long fTime = currentDate.getTime();
		for (int a = 0; a < 7; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			list.add(sdf.format(fdate));
		}
		return list.get(position);
	}
}

package com.xsl.lerist.llibrarys.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 *
 * @author liux ()
 * @version 1.0
 * @created 2012-3-21
 */
public class StringUtils {
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    // private final static SimpleDateFormat dateFormater = new
    // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // private final static SimpleDateFormat dateFormater2 = new
    // SimpleDateFormat("yyyy-MM-dd");

    private final static ThreadLocal<SimpleDateFormat> dateFormaterToyear = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("MM-dd HH:mm");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 字符串转JSON
     *
     * @param json
     * @return
     * @throws JSONException
     */
    public static JSONObject toJSONObject(String json) throws JSONException {
        if (!isEmpty(json)) {
            if (json.indexOf("[") == 0) {
                json = json.substring(1, json.length());
            }
            if (json.lastIndexOf("]") == json.length()) {
                json = json.substring(0, json.length() - 1);
            }
            return new JSONObject(json);
        }
        return null;
    }

    /**
     * 字符串转JSON
     *
     * @param json
     * @return
     * @throws JSONException
     */
    public static JSONArray toJSONArray(String json) throws JSONException {
        if (!isEmpty(json)) {
            // if (json.indexOf("[") == 0) {
            // json = json.substring(1, json.length());
            // }
            // if (json.lastIndexOf("]") == json.length()) {
            // json = json.substring(0, json.length() - 1);
            // }
        }
        return new JSONArray(json);
    }


    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate, String format) {
        try {
            return new SimpleDateFormat(format).parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字符串转位毫秒时间
     *
     * @param sdate
     * @return
     */
    public static long toTimeMilliseconds(String sdate, String format) {
        Date date = toDate(sdate, format);
        if (date != null) {
            return date.getTime();
        }
        return 0;
    }

    /**
     * 返回指定格式时间字符串
     *
     * @param timeMilliseconds
     * @param format
     * @return
     */
    public static String toTimeStr(long timeMilliseconds, String format) {
        return new SimpleDateFormat(format).format(timeMilliseconds);
    }

    /**
     * 时间戳转换
     *
     * @param timestampString
     * @return
     */
    public static String TimeStamp2Date(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        return dateFormater.get().format(new Date(timestamp));
    }

    public static int toAge(long timeMilliseconds) {
        return toAge(timeMilliseconds, true);
    }

    public static int toAge(long timeMilliseconds, boolean isWithMonthDay) {
        if (timeMilliseconds < 1000) {
            return 0;
        }
        int age = 0;
        try {
            int toyear = Integer.parseInt(toTimeStr(System.currentTimeMillis(), "yyyy"));
            int year = Integer.parseInt(toTimeStr(timeMilliseconds, "yyyy"));
            age = toyear - year;
            if (isWithMonthDay) {
                int tomonth = Integer.parseInt(toTimeStr(System.currentTimeMillis(), "MM"));
                int month = Integer.parseInt(toTimeStr(timeMilliseconds, "MM"));
                int today = Integer.parseInt(toTimeStr(System.currentTimeMillis(), "dd"));
                int day = Integer.parseInt(toTimeStr(timeMilliseconds, "dd"));
                if (month > tomonth) --age;
                else if (month == tomonth) {
                    if (day > today) {
                        --age;
                    }
                }
            }
        } catch (NumberFormatException e) {
        }
        return age;
    }

    public static int getYear(long timeMilliseconds) {
        if (timeMilliseconds < 1000) {
            return 0;
        }

        try {
            return Integer.parseInt(toTimeStr(timeMilliseconds, "yyyy"));
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    public static int getMonth(long timeMilliseconds) {

        try {
            return Integer.parseInt(toTimeStr(timeMilliseconds, "MM"));
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    public static int getDay(long timeMilliseconds) {
        try {
            return Integer.parseInt(toTimeStr(timeMilliseconds, "dd"));
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    public static String toTime(long timeMilliseconds, String format) {
        try {
            String timeStr = new SimpleDateFormat(format).format(timeMilliseconds);
            return timeStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toFriendlyTime(long timeMilliseconds) {
        return toFriendlyTime(timeMilliseconds, null, null);
    }

    public static String toFriendlyTime(long timeMilliseconds, String format) {
        return toFriendlyTime(timeMilliseconds, 2, null, format);
    }

    public static String toFriendlyTime(long timeMilliseconds, String toyearFromat, String format) {
        return toFriendlyTime(timeMilliseconds, 2, toyearFromat, format);
    }

    /**
     * 以友好的方式显示时间
     *
     * @param timeMilliseconds
     * @param inDays           几天内友好显示
     * @param format           完整格式
     * @return
     */
    public static String toFriendlyTime(long timeMilliseconds, int inDays, String toyearFormat, String format) {
        if (timeMilliseconds < 1000) {
            return "";
        }

        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        if (isToday(timeMilliseconds)) {
            int hour = (int) ((cal.getTimeInMillis() - timeMilliseconds) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - timeMilliseconds) / 60000, 1)
                        + "分钟前";
            else
//                ftime = hour + "小时前";
                ftime = "今天 " + new SimpleDateFormat("HH:mm").format(timeMilliseconds);
            return ftime;
        }

        long lt = timeMilliseconds / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - timeMilliseconds) / 3600000);
            if (hour == 0)
                ftime = Math.max(
                        (cal.getTimeInMillis() - timeMilliseconds) / 60000, 1)
                        + "分钟前";
            else
//                ftime = hour + "小时前";
                ftime = "今天 " + new SimpleDateFormat("HH:mm").format(timeMilliseconds);
        } else if (days == 1) {
            ftime = "昨天 " + new SimpleDateFormat("HH:mm").format(timeMilliseconds);
        } else if (days == 2) {
            ftime = "前天 " + new SimpleDateFormat("HH:mm").format(timeMilliseconds);
        } else if (days > 2 && days <= inDays) {
            ftime = days + "天前 " + new SimpleDateFormat("HH:mm").format(timeMilliseconds);
        } else if (days > inDays) {
            if (toyearFormat != null) {
                if (isToyear(timeMilliseconds)) {
                    ftime = new SimpleDateFormat(toyearFormat).format(timeMilliseconds);
                } else {
                    if (format == null) {
                        ftime = dateFormater.get().format(timeMilliseconds);
                    } else {
                        ftime = new SimpleDateFormat(format).format(timeMilliseconds);
                    }
                }
            } else {
                if (format == null) {
                    ftime = dateFormater.get().format(timeMilliseconds);
                } else {
                    ftime = new SimpleDateFormat(format).format(timeMilliseconds);
                }
            }
        }

        return ftime;
    }

    /**
     * 判断给定时间是否为今日
     *
     * @param timeMilliseconds
     * @return boolean
     */
    public static boolean isToday(long timeMilliseconds) {
        boolean b = false;
        Date time = new Date(timeMilliseconds);
        Date today = new Date();
        if (time != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String nowDate = simpleDateFormat.format(today);
            String timeDate = simpleDateFormat.format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 判断给定时间是否为今年
     *
     * @param timeMilliseconds
     * @return boolean
     */
    public static boolean isToyear(long timeMilliseconds) {
        boolean b = false;
        Date time = new Date(timeMilliseconds);
        Date today = new Date();
        if (time != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            String nowDate = simpleDateFormat.format(today);
            String timeDate = simpleDateFormat.format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 以友好的方式显示距离
     *
     * @return
     */
    public static String toFriendlyDistance(double distance) {
        String dis = "";
        if (distance >= 1000) {
            if (distance / 1000 >= 1000) {
                dis = "1000+KM";
            } else if (distance / 1000 >= 100) {
                dis = "100+KM";
            } else {
                dis = String.format("%.1f", (distance / 1000)) + "KM";
            }
        } else {
            dis = (int) Math.round(distance) + "M";
        }
//		Log.v("以友好的方式显示距离", distance + "   " + dis);
        return dis;
    }


    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 全部为空
     *
     * @param inputs
     * @return
     */
    public static boolean isAllEmpty(String... inputs) {
        for (String input :
                inputs) {
            if (!isEmpty(input))
                return false;
        }
        return true;
    }

    public static boolean isNotEmpty(String input) {
        return !isEmpty(input);
    }

    /**
     * 全部不为空
     *
     * @param inputs
     * @return
     */
    public static boolean isAllNotEmpty(String... inputs) {
        for (String input :
                inputs) {
            if (isEmpty(input))
                return false;
        }
        return true;
    }

    /**
     * 存在空
     *
     * @param inputs
     * @return
     */
    public static boolean isExistEmpty(String... inputs) {
        for (String input :
                inputs) {
            if (isEmpty(input))
                return true;
        }
        return false;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 验证手机号是否有效
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (mobiles == null || mobiles.length() < 11) {
            return false;
        }
        Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 隐式显示电话号码
     *
     * @param phone
     * @return
     */
    public static String hidePhone(String phone) {
        if (phone == null) return null;
        if (phone.length() >= 11) {
            String one = phone.substring(0, 3);
            String two = "****";
            String three = phone.substring(phone.length() - 4);
            return one + two + three;
        }
        return phone;
    }

    /**
     * 分割字符串
     *
     * @param str            源字符串
     * @param splitStr       分割符
     * @param returnPosition 返回分割后的某一段
     * @return
     */
    public static String split(String str, String splitStr, int returnPosition) {
        if (isEmpty(str)) return "";

        String[] split = str.split(splitStr);
        if (split.length > returnPosition) {
            return split[returnPosition];
        } else {
            return str;
        }
    }

    /**
     * 解析成时间字符串
     *
     * @param timeMillisecond
     * @return
     */
    public static String pareTimeStr(long timeMillisecond) {

        return pareTimeStr(timeMillisecond, null);
    }

    /**
     * 解析成时间字符串
     *
     * @param timeMillisecond
     * @param format          匹配格式
     * @return
     */
    public static String pareTimeStr(long timeMillisecond, String format) {

        if (timeMillisecond <= 0) return "";

        if (format == null) {
            //用于倒计时
            if (timeMillisecond < 3600 * 1000) {
                format = "mm:ss";
            } else {
                format = "HH:mm:ss";
            }
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(timeMillisecond);
    }

    /**
     * 获取一个length长度的随机数字字符串
     *
     * @param length
     * @return
     */
    public static String getRandomNumber(int length) {
        String str = "";
        String timeMillisStr = System.currentTimeMillis() + "";

        while (length > timeMillisStr.length()) {
            timeMillisStr += timeMillisStr;
        }
        return timeMillisStr.substring(timeMillisStr.length() - length, timeMillisStr.length());
    }

    /**
     * 首字母打写
     *
     * @param str
     * @return
     */
    public static String toInitialUpperCase(String str) {
        if (StringUtils.isEmpty(str)) return str;

        String initial = str.substring(0, 1).toUpperCase();
        String newStr = str.replaceFirst(str.substring(0, 1), initial);
        return newStr;
    }
}


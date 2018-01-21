package com.brightsoft.utils.dateConvertor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

public class DateFormat implements Converter<String, Date>  {
	 
    private static final List<String> formarts = new ArrayList<String>(4);
    static{
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        if(source.matches("^\\d{4}-\\d{1,2}$")){ 
            return parseDate(source, formarts.get(0));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(source, formarts.get(1));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(2));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(3));
        }else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }
 
    /**
     * 功能描述：格式化日期
     * 
     * @param dateStr
     *            String 字符型日期
     * @param format
     *            String 格式
     * @return Date 日期
     */
    public  Date parseDate(String dateStr, String format) {
        Date date=null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            date = (Date) dateFormat.parse(dateStr);
        } catch (Exception e) {
        }
        return date;
    }
    public static void main(String[] args) {
//        System.err.println(new DateConverter().convert("2014-04"));
    }
}
/*public class DateFormat implements Converter<String, Date> {

	private static final String PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss" ;
	private static final String PATTERN_YMD = "yyyy-MM-dd" ;

	public Date convert(String source) {
		// TODO Auto-generated method stub
		Date date = null;
		if(StringUtils.isNotBlank(source)){
			
			SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YMDHMS);
			sdf.setLenient(false);
			try {
				return sdf.parse(source);
			} catch (ParseException e) {
				sdf = new SimpleDateFormat(PATTERN_YMD);
				try {
					return sdf.parse(source);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return null;
	}

}
*/
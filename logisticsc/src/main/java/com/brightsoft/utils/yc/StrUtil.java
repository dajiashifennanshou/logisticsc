package com.brightsoft.utils.yc;

import org.objectweb.asm.Type;

public class StrUtil {
	/**
	 * 获取一个字符串，若此字符串无效，则返回默认值
	 * Author:FENG
	 * 2016年5月10日
	 * @param s 要验证的字符串
	 * @param defaultV 若字符串无效代替的默认值
	 * @return 
	 */
	public static String getString(String s,String defaultV){
		if(VString(s)){
			return s;
		}
		return defaultV;
		
	}
	/**
	 * 验证两个字符串是否相等
	 * Author:FENG
	 * 2016年6月23日
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static Boolean equalsString(String str1,String str2){
		if(str1==str2 || str1.equals(str2)){
			return true;
		}
		return false;
	}
	/**
	 * 验证字符串是否正确
	 * Author:FENG
	 * 2016年5月10日
	 * @param s 要验证的字符串
	 * @return true:有效字符串
	 */
	public static Boolean VString(String s){
		if(s==null || s=="null" || s=="" || "".equals(s)){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 验证数据
	 * Author:FENG
	 * 2016年5月13日
	 * @param o
	 * @return
	 */
	public static Boolean VObject(Object o){
		if(o==null)return false;
		String type=Type.getType(o.getClass()).toString();
		String vs=o.toString();
		if(StrIndex(type, "Integer")>=0){
			Integer vin=Integer.parseInt(vs);
			if(vin==null || vin<0){
				return false;
			}
			else{
				return true;
			}
		}else if(StrIndex(type,"String")>=0){
			return VString(vs);
		}
		return false;
	}
	/**
	 * 获取指定字符串在目标字符串里的index
	 * Author:FENG
	 * 2016年5月13日
	 * @param s
	 * @param ele
	 * @return
	 */
	public static Integer StrIndex(String s,String ele){
		return s.indexOf(ele);
	}
}

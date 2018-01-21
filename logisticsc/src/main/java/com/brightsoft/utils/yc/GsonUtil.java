package com.brightsoft.utils.yc;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class GsonUtil {
	
	private static Gson gson=new Gson();
	/**
	 * 对象转换为json
	 * Author:FENG
	 * 2016年5月10日
	 * @param <T>
	 * @param o
	 * @return
	 */
	public static <T>  String toJson(Object o,Class<T> c){
		String json=gson.toJson(o,c);
		return json;
	}
	/**
	 * json转换为对象
	 * Author:FENG
	 * 2016年5月10日
	 * @param json
	 * @param classofT
	 * @return
	 */
	public static <T> T fromJson(String json,Class<T> classofT){
		return gson.fromJson(json, classofT);
	}
	/** 
     * json字符串转成对象 
     * @param str   
     * @param type  
     * @return  
     */  
    public static <T> T fromJsonList(String str, Type type) {  
        Gson gson = new Gson();  
        return gson.fromJson(str, type);  
    }  

}

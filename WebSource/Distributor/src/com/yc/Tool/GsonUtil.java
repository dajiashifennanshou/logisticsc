package com.yc.Tool;

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
}

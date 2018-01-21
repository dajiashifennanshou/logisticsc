/**
 * 
 */
package com.brightsoft.Constant;
/**
 * 静态变量设置类
 * Author:FENG
 * 2016年5月10日
 */
public enum ErrorCode {
	
	NOTLOGIN("你还未登录",10001);
	
	private String name;
	private Integer value;
	
	ErrorCode(String name, Integer value){
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public Integer getValue() {
		return value;
	}
}

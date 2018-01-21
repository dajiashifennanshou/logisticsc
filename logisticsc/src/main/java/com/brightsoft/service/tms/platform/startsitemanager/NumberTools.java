package com.brightsoft.service.tms.platform.startsitemanager;

public class NumberTools {

	// 获取测试的单号
	public static String getTestNumber(){
		return (int)((Math.random()*9+1)*100000) + "";
	}
}

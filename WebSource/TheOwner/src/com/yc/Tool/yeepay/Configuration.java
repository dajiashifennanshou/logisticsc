package com.yc.Tool.yeepay;

import java.util.ResourceBundle;

/**
 * 访问配置文件 - 单例
 * @author: yingjie.wang    
 * @since : 2015-09-30 10:17
 */

public class Configuration {
	
	private static ResourceBundle rb    			= null;
	private volatile static Configuration instance	= null;
	
	private Configuration(String configFile) {
		rb = ResourceBundle.getBundle(configFile);
	}
	
	public static Configuration getInstance(String configFile) {
		if(instance == null) {
			synchronized(Configuration.class) {
				if(instance == null) {
					instance = new Configuration(configFile);
				}
			}
		}
		return instance;
	}
	
	public String getValue(String key) {
		return (rb.getString(key));
	}
}

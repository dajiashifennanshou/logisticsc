package com.brightsoft.utils.yeepay;

import java.util.ResourceBundle;


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

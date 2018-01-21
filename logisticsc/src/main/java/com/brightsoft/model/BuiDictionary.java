package com.brightsoft.model;

import java.io.Serializable;

/**
 * 
 * bui字典
 *
 */
public class BuiDictionary implements Serializable{

	private static final long serialVersionUID = 1L;

	private int value;//bui对应字段
	
	private String text;//bui对应字段

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}

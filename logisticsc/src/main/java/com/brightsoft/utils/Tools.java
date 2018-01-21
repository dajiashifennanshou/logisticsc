package com.brightsoft.utils;

import org.apache.commons.lang.StringUtils;

public class Tools {

	/**
	 * 用户，公司编码生成
	 * @param source
	 * @return
	 */
	public static String serialNumberTranslate(String source){
		String desStr = "";
		if(StringUtils.isBlank(source)){
			desStr = "0001";
		}else{
			int number = Integer.parseInt(source);
			number += 1;
			desStr = number+"";
			System.out.println(4-desStr.length());
			String s = "";
			for(int i=0;i<4-desStr.length();i++){
				s = s+0;
			}
			System.out.println(s);
			desStr = s+desStr;
		}
		return desStr;
	}
	
	public static void main(String[] args) {
		System.out.println(serialNumberTranslate("124"));
	}
}

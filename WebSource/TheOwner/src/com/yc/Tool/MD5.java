package com.yc.Tool;

import java.security.MessageDigest;

public class MD5 {
	
	private static String MD5="MD5";
	/**
     * MD5加密
     * @Author:luojing
     * @2016年6月30日 上午10:32:33
     * @return
     */
	public static String MD5(String str){
		try {
			MessageDigest md = MessageDigest.getInstance(MD5);
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String[] args) {
		System.out.println(MD5("111111")+"--9db06bcff9248837f86d1a6bcf41c9e7");
	}
}

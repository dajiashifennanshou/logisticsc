package com.brightsoft.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	// 全局数组
    private final static String[] STRDIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"                       };

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte){
        int iRet = bByte;

        if(iRet < 0){
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return STRDIGITS[iD1] + STRDIGITS[iD2];
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte){
        StringBuilder sBuffer = new StringBuilder();
        for(int i = 0; i < bByte.length; i++){
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String getMD5Code(String strObj){
        String resultString = null;
        try{
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        }
        catch(NoSuchAlgorithmException ex){
        }
        return resultString;
    }

    public static void main(String[] args) {
        MD5 getMD5 = new MD5();
        String pwd =  getMD5.getMD5Code("123456");
        System.out.println(pwd);
        String pwd2=  getMD5.getMD5Code("123456");
        if(pwd2.equals(pwd)){
        	System.out.println("成功");
        }else {
        	System.out.println("失败");
		}
    }
}

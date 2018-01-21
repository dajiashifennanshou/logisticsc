package com.yc.Tool.yeepay;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.misc.BASE64Encoder;

public class YeePayTool {
	

	/**
	 * 商户订单号
	 * @Author:luojing
	 * @2016年8月9日 下午4:57:14
	 */
	public static String getRequestId(){
		SimpleDateFormat dateFormat	= new SimpleDateFormat("yyMMddHHmmssSSS");
		return "MO" + dateFormat.format(new Date());
	}

	 public static String writeToBase64(String fileName) throws IOException {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        File imgFile = new File(fileName);
        byte[] data = null;
        // 读取图片字节数组
        InputStream in = new FileInputStream(imgFile);
        data = new byte[in.available()];
        in.read(data);
        in.close();
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String result = encoder.encode(data);
        return result;// 返回Base64编码过的字节数组字符串
    }
	 
	public static String formatStr(String text) {
		return text == null ? "" : text.trim();
	}
	
	public static byte[] hex2byte(String s) {
        byte[] src = s.toLowerCase().getBytes();
        byte[] ret = new byte[src.length / 2];
        for (int i = 0; i < src.length; i += 2) {
            byte hi = src[i];
            byte low = src[i + 1];
            hi = (byte) ((hi >= 'a' && hi <= 'f') ? 0x0a + (hi - 'a')
                    : hi - '0');
            low = (byte) ((low >= 'a' && low <= 'f') ? 0x0a + (low - 'a')
                    : low - '0');
            ret[i / 2] = (byte) (hi << 4 | low);
        }
        return ret;
    }
}

package com.yc.Tool;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密工具
 * Author:luojing
 * 2016年6月30日 上午10:25:09
 */
public class DES {
	
	private final static String DES = "DES";
	
	private final static String KEY = "WRTXSL12";
	
    /**
     * 根据键值进行加密
     * @param data 
     * @param key  加密键byte数组
     * @Author:luojing
     * @2016年6月30日 上午10:32:33
     */
    public static String encrypt(String data) throws Exception {
        byte[] bt = encrypt(data.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }
    
    /**
     * 根据键值进行加密
     * @param data
     * @Author:luojing
     * @2016年6月30日 上午10:32:33
     * @param key  加密键byte数组
     */
    private static byte[] encrypt(byte[] data) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(KEY.getBytes());
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        return cipher.doFinal(data);
    }
    /**
     * 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @Author:luojing
     * @2016年6月30日 上午10:32:33
     */
    public static String decrypt(String data) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf);
        return new String(bt);
    }
 
    /**
     * 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @Author:luojing
     * @2016年6月30日 上午10:32:33
     */
    private static byte[] decrypt(byte[] data) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(KEY.getBytes());
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        return cipher.doFinal(data);
    }
    
    /**
     * 对象解密
     * @Author:luojing
     * @2016年7月2日 下午1:51:03
     */
    public static Object ObjectDecrypt(Object obj) throws Exception{
    	Class<? extends Object> cls = obj.getClass();
    	for(Field f:cls.getDeclaredFields()){
    		f.setAccessible(true);
    		Object value = f.get(obj);//得到加密参数
    		if(StrUtil.VObject(value)){//值不为空,密码不需要des解密
    			if(!"password".equals(f.getName())){
    				f.set(obj, decrypt(String.valueOf(value)));//加密重新赋值
    			}
			}
    	}
    	return obj;
    }
	 
    public static void main(String[] args) throws Exception {
    	System.out.println(encrypt("阿斯顿发射点"));
    	System.out.println(decrypt(encrypt("阿斯顿发射点")));
    }
}

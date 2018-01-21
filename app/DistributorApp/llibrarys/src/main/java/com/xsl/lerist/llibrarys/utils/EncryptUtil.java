package com.xsl.lerist.llibrarys.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * 加密工具
 * Author:luojing
 * 2016年6月30日 上午10:25:09
 */
public class EncryptUtil {
	private final static String DES = "DES";
	
	private final static String KEY = "WRTXSL01";
	 
    public static void main(String[] args) throws Exception {
        String data = "123456";
        System.err.println(encrypt(data, KEY));
        System.err.println(decrypt(encrypt(data, KEY), KEY));
        System.out.println(MD5(data));
    }

    public static String getKEY() {
        return KEY;
    }

    /**
     * 根据键值进行加密
     * @param data 
     * @param key  加密键byte数组
     * @Author:luojing
     * @2016年6月30日 上午10:32:33
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = new String(Base64.encode(bt));
        return strs;
    }
    
    /**
     * 根据键值进行加密
     * @param data
     * @Author:luojing
     * @2016年6月30日 上午10:32:33
     * @param key  加密键byte数组
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
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
    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        byte[] buf = Base64.decode(data);
        byte[] bt = decrypt(buf,key.getBytes());
        return new String(bt);
    }
 
    /**
     * 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @Author:luojing
     * @2016年6月30日 上午10:32:33
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
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
     * MD5加密
     * @Author:luojing
     * @2016年6月30日 上午10:32:33
     * @return
     */
	public static String MD5(String str) throws Exception {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
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
}

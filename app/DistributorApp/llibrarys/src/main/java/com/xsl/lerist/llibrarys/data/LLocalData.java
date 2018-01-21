package com.xsl.lerist.llibrarys.data;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.xsl.lerist.llibrarys.utils.EncryptUtil;

import net.grandcentrix.tray.TrayPreferences;


/**
 * Created by Lerist on 2016/7/7, 0007.
 */

public class LLocalData extends TrayPreferences {
    public static final String DEFAULT_NAME = "appdata";
    public static final String CONFIG = "config";
    public static final String PREFERENCES = "preferences";
    private static final int VERSION = 1;

    private final static String KEY = "WRTXSL01";


    public LLocalData(Context context) {
        this(context, DEFAULT_NAME);
    }

    public LLocalData(Context context, String sharedPreferencesName) {
        super(context, sharedPreferencesName, VERSION);
    }

    /**
     * 写入对象数据
     *
     * @param key
     * @param value
     * @param isEncode 是否加密,以toString作为加密源
     */
    public void put(String key, Object value, boolean isEncode) {
        String valueStr = value + "";
        if (isEncode && value != null) {
            try {
                valueStr = EncryptUtil.encrypt(valueStr, KEY);
                put(key, valueStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void put(Object o) {
        if (o == null) return;
        put(o.getClass().getSimpleName(), JSON.toJSONString(o));
    }

    public <E extends Object> E get(Class<E> c) {
        String s = get(c.getSimpleName(), false);
        if (s != null) {
            E e = JSON.parseObject(s, c);
            return e;
        }
        return null;
    }

    /**
     * 读取数据
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return get(key, false);
    }

    /**
     * 读取可能加密的数据
     *
     * @param key
     * @param isDecode 是否解密
     * @return
     */
    public String get(String key, boolean isDecode) {
        if (isDecode) {
            String string = getString(key, null);
            try {
                return EncryptUtil.decrypt(string, KEY);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return getString(key, null);
    }

}

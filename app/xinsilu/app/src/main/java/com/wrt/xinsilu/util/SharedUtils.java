package com.wrt.xinsilu.util;

import android.content.Context;

/**
 * * * * * * * * * * * * * * * * * * * * * * *
 * Created by wangsong
 * Date: 15/10/16
 * 已初始化好，直接调用里面方法即可，根据参数进行保存和取值
 * * * * * * * * * * * * * * * * * * * * * * *
 **/
public class SharedUtils {
    /**
     * 获取保存的int型数值失败,返回-1
     */
    public static final int RETURN_INT_FAIL = -1;
    /**
     *  获取保存的boolean型数值失败，返回false
     */
    public static final boolean RETURN_BOOLEAN_FAIL = false;
    /**
     * 获取保存的String型数值失败，返回“”
     */
    public static final String RETURN_STRING_FAIL = "";

    /**
     * 获取xml文件保存的int型值
     * @param ctx
     * @param name xml文件名
     * @param key 标签名
     * @return 没有该标签则返回-1；
     */
    public static int getInt(Context ctx,String name, String key) {
        return ctx.getSharedPreferences(name,Context.MODE_PRIVATE).getInt(key, RETURN_INT_FAIL);
    }

    /**
     * 保存int型值
     * @param ctx
     * @param name xml文件名
     * @param key 标签名
     * @param value 保存的值
     */
    public static void saveInt(Context ctx,String name, String key, int value) {
        ctx.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putInt(key,value).commit();
    }

    /**
     * 获取boolean值
     * @param ctx
     * @param name xml文件名
     * @param key 标签名
     * @return 没有该标签返回false
     */
    public static boolean getBoolean(Context ctx,String name, String key) {
        return  ctx.getSharedPreferences(name, Context.MODE_PRIVATE).getBoolean(key,RETURN_BOOLEAN_FAIL);
    }

    /**
     * 保存boolean型值
     * @param ctx
     * @param name xml文件名
     * @param key 标签名
     * @param value 保存的值
     */
    public static void saveBoolean(Context ctx,String name, String key, boolean value) {
        ctx.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putBoolean(key,value).commit();
    }

    /**
     * 获取String类型的值
     * @param ctx
     * @param name xml文件名
     * @param key 标签名
     * @return 没有该标签返回“”；
     */
    public static String getString(Context ctx,String name, String key) {
        return  ctx.getSharedPreferences(name, Context.MODE_PRIVATE).getString(key,RETURN_STRING_FAIL);
    }

    /**
     * 保存String型值
     * @param ctx
     * @param name xml文件名
     * @param key 标签名
     * @param defValue 保存的值
     */
    public static void saveString(Context ctx,String name, String key, String defValue) {
        ctx.getSharedPreferences(name, Context.MODE_PRIVATE).edit().putString(key, defValue).commit();
    }
}

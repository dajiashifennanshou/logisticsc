package com.xsl.lerist.llibrarys.activity;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Lerist on 2015/8/27, 0027.
 */
public class LActivityManager {
    private static ArrayList<Activity> activitys = new ArrayList<>();

    /**
     * 添加Activity到管理器
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activitys.add(activity);
    }

    /**
     * 从Activity管理器中移除Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activitys.remove(activity);
    }

    public static void removeActivity(int index) {
        activitys.remove(index);
    }

    /**
     * 获取某一位置的Activity
     *
     * @param index
     * @return
     */
    public static Activity getActivity(int index) {
        try {
            return activitys.get(index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Activity getTopActivity() {
        return getActivity(activitys.size() - 1);
    }

    /**
     * 获取Activity实例
     *
     * @param activityClass
     * @return
     */
    public static <A extends Activity> A getActivity(Class<A> activityClass) {
        try {
            for (Activity a :
                    activitys) {
                if (a.getClass().getSimpleName().equals(activityClass.getSimpleName())) {
                    return (A) a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * finish某一位置上的Activity
     *
     * @param index
     */
    public static void finishIndexActivity(int index) {
        Activity activity = getActivity(index);
        if (activity != null) {
            activity.finish();
        }
        removeActivity(index);
    }

    /**
     * finish掉管理器中所有Activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activitys) {
            activity.finish();
        }
        activitys.clear();
    }

    /**
     * Acticity是否被创建
     *
     * @param activityClass
     * @return
     */
    public static boolean isCreated(Class activityClass) {

        for (Activity a :
                activitys) {
            if (a.getClass().getSimpleName().equals(activityClass.getSimpleName())) {
                return true;
            }
        }
        return false;
    }
}

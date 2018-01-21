package com.xsl.lerist.llibrarys.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lerist on 2016/4/27, 0027.
 */
public class ListUtils {
    public static <E extends Object> boolean isEmpty(List<E> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static <E extends Object> boolean isNotEmpty(List<E> list) {
        return !isEmpty(list);
    }

    /**
     * 数组转换为ArrayList
     *
     * @param objects
     * @param <E>
     * @return
     */
    public static <E extends Object> ArrayList<E> toArrayList(E[] objects) {
        if (objects == null) return null;

        ArrayList<E> objectArrayList = new ArrayList<>();
        synchronized (objects) {
            for (E e : objects) {
                objectArrayList.add(e);
            }
        }
        return objectArrayList;
    }

    public static <E extends Object> ArrayList<E> toArrayList(List<E> objects) {
        if (objects == null) return null;

        ArrayList<E> objectArrayList = new ArrayList<>();
        synchronized (objects) {
            for (E e : objects) {
                objectArrayList.add(e);
            }
        }
        return objectArrayList;
    }

    public interface Comparator<C extends Object> {
        /**
         * @param obj1
         * @param obj2
         * @return [0]:obj1==obj2 , [>0]:obj1>obj2, [<0]:obj1<obj2
         */
        int onCompare(C obj1, C obj2);
    }

    public static Comparator<String> StringComparator = new Comparator<String>() {
        @Override
        public int onCompare(String obj1, String obj2) {
            return obj1.equals(obj2) ? 0 : -1;
        }
    };

    /**
     * 容器中是否包含某一值
     *
     * @param container
     * @param value
     * @param comparator 判断子对象是否相等逻辑
     * @param <E>
     * @return
     */
    public static <E extends Object> boolean isContains(List<E> container, E value, Comparator<E> comparator) {
        if (container == null || container.isEmpty()) return false;

        for (E e : container) {
            boolean isEqual = comparator.onCompare(e, value) == 0;
            if (isEqual) return true;
        }
        return false;
    }
}

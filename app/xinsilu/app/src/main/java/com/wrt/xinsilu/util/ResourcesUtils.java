package com.wrt.xinsilu.util;

import android.content.Context;
import android.view.View;

/**
 * Created by Constandine on 2016/4/1.
 */
public class ResourcesUtils {
    public static int getColor(View view, int resId) {
        return view.getResources().getColor(resId);
    }
    public static int getColor(Context context, int resId) {
        return context.getResources().getColor(resId);
    }

}

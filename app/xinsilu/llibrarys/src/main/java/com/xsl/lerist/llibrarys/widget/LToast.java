package com.xsl.lerist.llibrarys.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.xsl.lerist.llibrarys.R;
import com.xsl.lerist.llibrarys.utils.Lerist;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import carbon.widget.TextView;

/**
 * Created by Lerist on 2015/8/14, 0014.
 */
public class LToast {

    private static Toast toast;

    /**
     * Show the view or text notification for a short period of time.  This time
     * could be user-definable.  This is the default.
     */
    public static final int LENGTH_SHORT = 0;

    /**
     * Show the view or text notification for a long period of time.  This time
     * could be user-definable.
     */
    public static final int LENGTH_LONG = 1;

    @IntDef({LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public static void show(Context context, int resId) {
        show(context, context.getString(resId));
    }

    public static void show(Context context, int resId, int gravity) {
        show(context, context.getString(resId), gravity);
    }

    public static void show(final Context context, final String msg) {
        show(context, msg, Gravity.CENTER);
    }

    public static void show(final Context context, final String msg, final int gravity) {
        if (context == null) return;
        TextView textView = new TextView(context);
        textView.setText(msg);
        textView.setTextColor(0xffffffff);
        textView.setTextSize(14);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(Lerist.dip2px(context, 32), Lerist.dip2px(context, 12),
                Lerist.dip2px(context, 32), Lerist.dip2px(context, 12));
        textView.setBackgroundResource(R.color.translucent2);
        textView.setCornerRadius(Lerist.dip2px(context, 4));
        textView.setElevation(Lerist.dip2px(context, 4));
        show(context, textView, gravity);
    }


    public static void show(final Context context, final View view) {
        show(context, view, Gravity.TOP);
    }

    public static void show(final Context context, final View view, final int gravity) {
        show(context, view, LENGTH_LONG, gravity, 0, 60);
    }

    public static void show(final Context context, final View view, @Duration final int duration, final int gravity, final int xOffset, final int yOffset) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    if (toast == null) {
                        toast = new Toast(context);
                    }
                    if (view != null) toast.setView(view);
                    toast.setDuration(duration);
                    toast.setGravity(gravity, xOffset, yOffset);
                    toast.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

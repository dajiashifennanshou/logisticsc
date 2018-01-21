package com.xsl.lerist.llibrarys.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.xsl.lerist.llibrarys.R;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.utils.StringUtils;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by Lerist on 2015/3/12, 0012.
 */
public class LProgressDialog {

    private static MaterialDialog dialog;
    private static Context mContext;

    public static void show(Context context, String msg) {
        show(context, msg, null);
    }

    public static void show(final Context context, final String msg, final DialogInterface.OnDismissListener onDismissListener) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mContext = context;
                if (dialog == null) {
                    View view = View.inflate(context, R.layout.layout_progress_dialog, null);
                    if (StringUtils.isNotEmpty(msg)) {
                        TextView tv_text = (TextView) view.findViewById(R.id.l_progress_dialog_tv_text);
                        tv_text.setVisibility(View.VISIBLE);
                        tv_text.setText(msg);
                    }
                    dialog = new MaterialDialog(context)
                            .setCanceledOnTouchOutside(false)
                            .setBackgroundResource(R.color.transparent)
                            .setView(view)
                            .setOnDismissListener(onDismissListener)
                            .setMessage(msg);
                }
                dialog.show();
            }
        });
    }

    public static void dismiss() {
        if (dialog != null) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
                Lerist.runOnMainThread(mContext, new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
            dialog = null;
        }
    }

    /**
     * 设置是否可取消
     *
     * @param b
     */
    public static void setCancelable(boolean b) {
//        if (dialog != null)
//            dialog.setCancelable(b);
    }
}

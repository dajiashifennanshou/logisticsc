package com.xsl.distributor.lerist.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.xsl.distributor.R;

import butterknife.OnClick;

/**
 * Created by Lerist on 2016/7/13, 0013.
 */

public class UpdateDialog extends Dialog {

    private static UpdateDialog updateDialog;
    private OnUpdateListener onUpdateListener;

    public interface OnUpdateListener {
        void onUpdate();
    }

    public static void show(Context context) {
        show(context, null);
    }

    public static void show(Context context, final OnUpdateListener onUpdateListener) {
        if (updateDialog == null) {
            updateDialog = new UpdateDialog(context);
            updateDialog.setOnUpdateListener(onUpdateListener);
            updateDialog.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    updateDialog = null;
                }
            });
        }

        if (!updateDialog.isShowing()) updateDialog.show();
    }

    public UpdateDialog(Context context) {
        super(context, R.style.TranslucentAlertDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update);

    }

    @OnClick({R.id.d_update_btn_no, R.id.d_update_btn_yes})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.d_update_btn_no:
                dismiss();
                break;
            case R.id.d_update_btn_yes:
                if (onUpdateListener != null) onUpdateListener.onUpdate();
                break;
        }
    }

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        this.onUpdateListener = onUpdateListener;
    }
}

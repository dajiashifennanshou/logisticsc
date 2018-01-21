package com.xsl.distributor.lerist.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.xsl.distributor.R;

import butterknife.OnClick;

/**
 * Created by Lerist on 2016/7/13, 0013.
 */

public class PayFailureDialog extends Dialog {

    private static PayFailureDialog payFailureDialog;

    public static void show(Context context) {
        show(context, null);
    }

    public static void show(Context context, final OnDismissListener onDismissListener) {
        if (payFailureDialog == null) {
            payFailureDialog = new PayFailureDialog(context);
            payFailureDialog.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (onDismissListener != null) onDismissListener.onDismiss(dialog);
                    payFailureDialog = null;
                }
            });
        }

        if (!payFailureDialog.isShowing()) payFailureDialog.show();
    }

    public PayFailureDialog(Context context) {
        super(context, R.style.TranslucentAlertDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay_failure);
    }

    @OnClick(R.id.d_pay_failure_retry)
    public void onClick() {
    }
}

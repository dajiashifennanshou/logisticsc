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

public class PaySucceedDialog extends Dialog {

    private static PaySucceedDialog paySucceedDialog;

    public static void show(Context context) {
        if (paySucceedDialog == null) {
            paySucceedDialog = new PaySucceedDialog(context);
            paySucceedDialog.setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    paySucceedDialog = null;
                }
            });
        }

        if (!paySucceedDialog.isShowing()) paySucceedDialog.show();
    }

    public PaySucceedDialog(Context context) {
        super(context, R.style.TranslucentAlertDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay_succeed);
    }

    @OnClick(R.id.d_pay_succeed_ok)
    public void onClick() {
        dismiss();
    }
}

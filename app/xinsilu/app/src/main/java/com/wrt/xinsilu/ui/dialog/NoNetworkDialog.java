package com.wrt.xinsilu.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.wrt.xinsilu.R;


/**
 * Created by Lerist on 2016/7/13, 0013.
 */

public class NoNetworkDialog extends Dialog {

    public static NoNetworkDialog noNetworkDialog;

    public static synchronized void show(Context context) {
        if (noNetworkDialog != null) {
            noNetworkDialog.dismiss();
            noNetworkDialog = null;
        }

        noNetworkDialog = new NoNetworkDialog(context);
        noNetworkDialog.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                noNetworkDialog = null;
            }
        });

        try {
            noNetworkDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NoNetworkDialog(Context context) {
        super(context, R.style.TranslucentAlertDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_no_network);
    }
}

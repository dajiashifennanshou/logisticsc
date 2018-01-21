package com.xsl.distributor.lerist.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.ws.ui.activity.CloudStoreActivity;

/**
 * Created by Lerist on 2016/7/19, 0019.
 */

public class JoinStateDialog {

    private static AlertDialog mDialog;

    public static boolean show(final Context context, UserInfo.Join join, final boolean isFinish) {
        if (mDialog != null && mDialog.isShowing()) mDialog.dismiss();

        if (join == null || join.getJoinerId() == 0) {
            mDialog = new AlertDialog.Builder(context).setTitle("提示")
                    .setMessage("检测到您未加盟云仓，是否立即加盟？")
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            context.startActivity(new Intent(context, CloudStoreActivity.class));
                        }
                    })
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if (isFinish) {
                                ((Activity) context).finish();
                            }
                            mDialog = null;
                        }
                    })
                    .show();
            return true;
        } else {
            switch (join.getApplyStatus()) {
                case UserInfo.Join.APPLYSTATUS_NOTPASS:
                    //审核未通过
                    mDialog = new AlertDialog.Builder(context).setTitle("提示")
                            .setMessage("加盟申请审核未通过, 请重新提交加盟申请")
                            .setNegativeButton("暂不加盟", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("前往加盟", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    context.startActivity(new Intent(context, CloudStoreActivity.class));
                                }
                            })
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    if (isFinish) {
                                        ((Activity) context).finish();
                                    }
                                    mDialog = null;
                                }
                            })
                            .show();
                    return true;
                case UserInfo.Join.APPLYSTATUS_CHECKING:
                    //审核中
                    mDialog = new AlertDialog.Builder(context).setTitle("提示")
                            .setMessage("加盟申请正在审核, 请审核通过后再进行此操作")
                            .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    if (isFinish) {
                                        ((Activity) context).finish();
                                    }
                                    mDialog = null;
                                }
                            })
                            .show();
                    return true;
                case UserInfo.Join.APPLYSTATUS_PASS:
                    //审核通过
                    break;
            }

            return false;
        }
    }
}

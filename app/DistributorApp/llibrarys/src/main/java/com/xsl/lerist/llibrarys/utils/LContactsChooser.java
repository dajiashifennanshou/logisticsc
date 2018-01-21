package com.xsl.lerist.llibrarys.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;

import com.socks.library.KLog;
import com.xsl.lerist.llibrarys.model.Contacts;
import com.xsl.lerist.llibrarys.widget.LToast;

import java.util.ArrayList;

/**
 * Created by Lerist on 2016/4/13, 0013.
 */
public class LContactsChooser extends Activity {

    public static final int REQUEST_CODE = 1;
    private static final int REQUEST_PERMISSIONS_CODE = 200;
    public static Callbak mCallbak;

    public static void openContacts(final Context context, Callbak callbak) {
        mCallbak = callbak;
        Intent intent = new Intent(context, LContactsChooser.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public interface Callbak {
        void onSuccess(ArrayList<Contacts> selectedContactses);

        void onFailure();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_PERMISSIONS_CODE);
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        KLog.i(requestCode + " " + grantResults[0]);
        switch (requestCode) {
            case REQUEST_PERMISSIONS_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //已授权
                    init();
                } else {
                    //未授权
                    new AlertDialog.Builder(this).setTitle("提示")
                            .setMessage("未获取到[访问联系人]权限, 是否需要手动授权? " +
                                    "\n\n" +
                                    "启用 [权限管理]->[读取联系人] 权限\n")
                            .setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("去授权", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.fromParts("package", getPackageName(), null)));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private void init() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            LToast.show(this, "未安装联系人应用", Gravity.CENTER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KLog.i(requestCode + "   " + resultCode);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            try {
                ArrayList<Contacts> contactses = ContactsUtils.getContacts(this, data.getData());
                if (mCallbak != null) mCallbak.onSuccess(contactses);
            } catch (Exception e) {
                e.printStackTrace();
                if (mCallbak != null) mCallbak.onFailure();
            }
        } else {
            if (mCallbak != null) mCallbak.onFailure();
        }

        finish();
    }

}

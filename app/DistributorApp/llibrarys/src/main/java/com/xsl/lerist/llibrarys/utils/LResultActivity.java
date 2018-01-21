package com.xsl.lerist.llibrarys.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.socks.library.KLog;

public class LResultActivity extends Activity {

    public static final int REQUEST_CODE = 0;
    public static Callback mCallback;
    public static Intent mIntent;

    public static void startActivityForResult(Context context, Class c, Callback resultCallback) {
        startActivityForResult(context, new Intent(context, c), resultCallback);
    }

    public static void startActivityForResult(Context context, Intent intent, Callback resultCallback) {
        mCallback = resultCallback;
        mIntent = intent;
        Intent i = new Intent(context, LResultActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public interface Callback {
        void onSuccess(Intent result);

        void onFailure();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        if (mIntent != null) {
//            mIntent.putExtra("RequstClass", LResultActivity.class);
            startActivityForResult(mIntent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        KLog.e(requestCode + " " + resultCode + " " + data);
        if (requestCode == REQUEST_CODE && resultCode != RESULT_CANCELED) {
            if (mCallback != null) mCallback.onSuccess(data);
        } else {
            if (mCallback != null) mCallback.onFailure();
        }
        finish();
    }
}

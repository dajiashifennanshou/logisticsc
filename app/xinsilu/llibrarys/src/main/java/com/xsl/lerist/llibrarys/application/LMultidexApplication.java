package com.xsl.lerist.llibrarys.application;

import android.content.Context;

/**
 * Created by Lerist on 2015/11/13, 0013.
 */
public class LMultidexApplication extends LApplication {
    private static LMultidexApplication instance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}

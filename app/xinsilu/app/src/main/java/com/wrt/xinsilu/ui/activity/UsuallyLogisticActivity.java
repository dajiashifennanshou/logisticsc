package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;

import com.wrt.xinsilu.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 首页点击进来的常用物流商
 */
public class UsuallyLogisticActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usually_logistic);
        setTitle("常用物流商");
    }
}

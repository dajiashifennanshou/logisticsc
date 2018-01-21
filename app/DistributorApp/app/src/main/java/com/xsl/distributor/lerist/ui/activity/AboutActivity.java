package com.xsl.distributor.lerist.ui.activity;

import android.os.Bundle;

import com.xsl.distributor.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


public class AboutActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initView();
    }

    private void initView() {
        setTitle("关于云仓");
    }
}

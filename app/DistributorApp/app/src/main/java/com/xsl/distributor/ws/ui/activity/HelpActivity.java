package com.xsl.distributor.ws.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.fb.FeedbackAgent;
import com.xsl.distributor.R;
import com.xsl.lerist.llibrarys.widget.LWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class HelpActivity extends SwipeBackActivity {
    @BindView(R.id.title_back)
    ImageView titleBack;
    @BindView(R.id.title_titl)
    TextView titleTitl;
    @BindView(R.id.title_enter)
    TextView titleEnter;
    @BindView(R.id.help)
    LWebView help;
    WebSettings.ZoomDensity zoomDensity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        titleTitl.setText("帮助");
        titleEnter.setText("反馈");
        help.loadUrl("file:///android_asset/help.html");

//        WebSettings settings = help.getSettings();
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);

    }


    @OnClick({R.id.title_back, R.id.title_enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_enter:
                FeedbackAgent agent = new FeedbackAgent(context);
                agent.startFeedbackActivity();
//                finish();
//                startActivity(FeedbackActivity.class);

                break;
        }
    }
}

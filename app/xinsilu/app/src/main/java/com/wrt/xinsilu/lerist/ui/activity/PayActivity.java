package com.wrt.xinsilu.lerist.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class PayActivity extends SwipeBackActivity {

    @BindView(R.id.a_pay_lwv)
    LWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        setTitle("支付");

        String uri = getIntent().getStringExtra("uri");
        KLog.i(uri);
        if (StringUtils.isNotEmpty(uri)
                && uri.contains("https://")) {
            LProgressDialog.show(context, "");
            webView.loadUrl(uri);
        }

        webView.setOnLoadListener(new LWebView.OnLoadListener() {
            @Override
            public void onLoading(WebView webView, int progress) {
            }

            @Override
            public void onLoadFinish(WebView webView, int progress) {
                LProgressDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) webView.destroy();
    }
}

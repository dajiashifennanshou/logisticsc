package com.xsl.distributor.ws.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xsl.distributor.R;
import com.xsl.lerist.llibrarys.widget.LWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @author wangsong
 *         只需传一个url地址即可，后续课对原生下拉layout的更改
 * @since 2016-4-1
 */
public class WebActivity extends SwipeBackActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.a_pay_lwv)
    LWebView webView;
//    private WebView webView;
//    private ProgressBar progressBar;
//    private SwipeRefreshLayout swipeRefreshLayout;
    public static final String KEY_URL = "uri";
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        setTitle("资讯详情");

        initValue();
        initWebView();
    }


    protected void initValue() {
//        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
//        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        int color = getResources().getColor(R.color.colorPrimary);
//        progressBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        url = getIntent().getStringExtra(KEY_URL);
        webView.loadUrl(url);
    }


    /**
     * 初始化
     */
    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });
      /*  webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
//                if (newProgress != 100) {
//                    progressBar.setVisibility(View.VISIBLE);
//                    progressBar.setProgress(newProgress);
//                } else {
//                    progressBar.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                mToolbar.setTitle(title);
            }
        });*/
    }

    @Override
    public void onRefresh() {
//        webView.reload();
//        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}

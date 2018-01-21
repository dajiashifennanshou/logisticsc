package com.xsl.lerist.llibrarys.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created with IntelliJ IDEA.
 * User: Lerist.
 * 带动到底部和底部响应的WebView
 */
public class LWebView extends WebView {

    private View responceView;
    private OnLoadListener onLoadListener;

    public interface OnLoadListener {
        void onLoading(WebView webView, int progress);

        void onLoadFinish(WebView webView, int progress);
    }

    public LWebView(Context context) {
        super(context);
        init();
    }

    public LWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //启用支持javascript
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    if (onLoadListener!=null) onLoadListener.onLoadFinish(view, newProgress);
                } else {
                    // 加载中
                    if (onLoadListener!=null) onLoadListener.onLoading(view, newProgress);
                }

            }
        });
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    /**
     * @param direction -1 页面从上往下走。
     * @return
     */
    public boolean canScrollVertical(int direction) {
        final int offset = computeVerticalScrollOffset();
        final int range = computeVerticalScrollRange() - computeVerticalScrollExtent();
        if (range == 0) return false;
        else return (direction < 0) ? (offset > 0) : (offset < range - 1);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    float mLastMotionY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionY = event.getY();
                if (responceView != null) {
                    ((ViewParent) responceView).requestDisallowInterceptTouchEvent(true);
                }
                break;

            case MotionEvent.ACTION_MOVE: {

                float direction = mLastMotionY - event.getY();
                mLastMotionY = event.getY();

                Log.i("msg", "scroll: " + getScrollY() + "  MeasuredHeight:" + getMeasuredHeight() + " ContentHeight:" + getContentHeight() * getScale() + "   direction: " + direction);

                if ((isAtTop() && direction < 0)) {
                    Log.i("msg", "offset===========");
                    if (responceView != null) {
                        ((ViewParent) responceView).requestDisallowInterceptTouchEvent(false);
                        responceView.onTouchEvent(event);
                        return false;
                    }
                } else if ((isAtBottom() && direction > 0)) {
                    Log.i("msg", "offset===========");
                    if (responceView != null) {
                        ((ViewParent) responceView).requestDisallowInterceptTouchEvent(false);
                        responceView.onTouchEvent(event);
                        return false;
                    }
                } else {
                    if (responceView != null) {
                        //告诉responceView，我的事件自己处理
                        ((ViewParent) responceView).requestDisallowInterceptTouchEvent(true);
                    }
                }
            }
            break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (responceView != null) {
                    ((ViewParent) responceView).requestDisallowInterceptTouchEvent(false);
                }
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    /**
     * 设置滑动到顶部或底部响应的View
     *
     * @param responceView
     */
    public void setScrollResponceView(View responceView) {
        this.responceView = responceView;
    }

    /**
     * 在顶部
     *
     * @return
     */
    private boolean isAtTop() {
        return getScrollY() == 0;
    }

    /**
     * 在底部
     *
     * @return
     */
    private boolean isAtBottom() {
        return getHeight() + getScrollY() == getContentHeight() * getScale();
    }
}


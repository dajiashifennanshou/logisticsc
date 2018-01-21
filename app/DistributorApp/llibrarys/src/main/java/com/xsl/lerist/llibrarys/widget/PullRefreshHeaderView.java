//package com.xsl.lerist.llibrarys.widget;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.chanven.lib.cptr.PtrFrameLayout;
//import com.chanven.lib.cptr.PtrUIHandler;
//import com.chanven.lib.cptr.indicator.PtrIndicator;
//import com.xsl.lerist.llibrarys.R;
//import com.xsl.lerist.llibrarys.utils.StringUtils;
//
//import carbon.widget.FrameLayout;
//import carbon.widget.ProgressBar;
//
///**
// * Created by Lerist on 2016/7/25, 0025.
// */
//
//public class PullRefreshHeaderView extends FrameLayout implements PtrUIHandler {
//    private static final String TEXT_BEINGPREPARED = "松开立即刷新";
//    private static final String TEXT_PULLPREPARED = "下拉刷新";
//    private static final String TEXT_REFRESHING = "正在刷新";
//    private static final String TEXT_REFRESHFINISH = "刷新完成";
//    private static final String TEXT_REFRESHFAILED = "刷新失败";
//
//    private View rootView;
//    private ProgressBar pb_progress;
//    private ImageView iv_arrow;
//    private TextView tv_title;
//    private TextView tv_lasttime;
//
//
//    public PullRefreshHeaderView(Context context) {
//        this(context, null);
//    }
//
//    public PullRefreshHeaderView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public PullRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    public PullRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//
//        init();
//    }
//
//    private <T extends View> T find(int resId, Class<T> view) {
//        return (T) rootView.findViewById(resId);
//    }
//
//    private void init() {
//        rootView = inflate(getContext(), R.layout.view_pull_refresh_header, this);
//        pb_progress = find(R.id.v_pull_refresh_header_pb_progress, ProgressBar.class);
//        iv_arrow = find(R.id.v_pull_refresh_header_iv_arrow, ImageView.class);
//        tv_title = find(R.id.v_pull_refresh_header_tv_title, TextView.class);
//        tv_lasttime = find(R.id.v_pull_refresh_header_tv_lasttime, TextView.class);
//    }
//
//    public void setLastRefreshTime(long time) {
//        tv_lasttime.setText(StringUtils.toFriendlyTime(time));
//    }
//
//    /**
//     * 下拉状态
//     */
//    public void pullPrepared() {
//        tv_title.setText(TEXT_PULLPREPARED);
//        iv_arrow.setVisibility(VISIBLE);
//        pb_progress.setVisibility(GONE);
//        iv_arrow.animate().cancel();
//        iv_arrow.animate().rotation(0).setDuration(200).start();
//    }
//
//
//    /**
//     * 准备刷新状态
//     */
//    public void beingPrepared() {
//        tv_title.setText(TEXT_BEINGPREPARED);
//        iv_arrow.animate().cancel();
//        iv_arrow.setRotation(0);
//        iv_arrow.animate().rotation(180).setDuration(200).start();
//    }
//
//    public void setRefreshing(boolean isRefreshing) {
//        iv_arrow.setVisibility(isRefreshing ? GONE : VISIBLE);
//        pb_progress.setVisibility(isRefreshing ? VISIBLE : GONE);
//        tv_title.setText(isRefreshing ? TEXT_REFRESHING : TEXT_REFRESHFINISH);
//    }
//
//    public void setRefreshFailed(String failed) {
//        tv_title.setText(failed != null ? failed : TEXT_REFRESHFAILED);
//        iv_arrow.setVisibility(GONE);
//        pb_progress.setVisibility(GONE);
//    }
//
//    @Override
//    public void onUIReset(PtrFrameLayout frame) {
//        tv_title.setText(TEXT_PULLPREPARED);
//        iv_arrow.setVisibility(VISIBLE);
//        pb_progress.setVisibility(GONE);
//        iv_arrow.animate().cancel();
//        iv_arrow.setRotation(0);
//    }
//
//    @Override
//    public void onUIRefreshPrepare(PtrFrameLayout frame) {
//        tv_title.setText(TEXT_BEINGPREPARED);
//        iv_arrow.animate().cancel();
//        iv_arrow.setRotation(0);
//        iv_arrow.animate().rotation(180).setDuration(200).start();
//    }
//
//    @Override
//    public void onUIRefreshBegin(PtrFrameLayout frame) {
//        iv_arrow.setVisibility(GONE);
//        pb_progress.setVisibility(VISIBLE);
//        tv_title.setText(TEXT_REFRESHING);
//    }
//
//    @Override
//    public void onUIRefreshComplete(PtrFrameLayout frame) {
//        iv_arrow.setVisibility(VISIBLE);
//        pb_progress.setVisibility(GONE);
//        tv_title.setText(TEXT_REFRESHFINISH);
//        setLastRefreshTime(System.currentTimeMillis());
//    }
//
//    @Override
//    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//
//    }
//
//}

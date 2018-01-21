package com.xsl.lerist.llibrarys.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.socks.library.KLog;
import com.xsl.lerist.llibrarys.R;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.interfaces.LOnScrollListener;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LRecyclerView;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;

//import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;

/**
 * Created by Lerist on 2015/9/4, 0004.
 */
public abstract class LRecyclerViewFragment extends LFragment {

    private LRecyclerView recyclerView;
    private SpringView refreshLayout;
    private LRecyclerViewFragment mFragment;
    private RecyclerView.Adapter recyclerViewAdapter;
    private FloatingActionButton floatingActionButton;
    //    private VerticalRecyclerViewFastScroller fastScroller;
    private long lastScrollTime = 0;
    private Handler mHandler;
    private boolean isRefreshing;
    private boolean refreshEnabled = true;
    public OnListener onListener;
    private LOnScrollListener lOnScrollListener;
    private LinearLayout backgroudContainer;
    private LinearLayout headContainer;
    private LinearLayout floorContainer;

    public interface OnListener {
        void onRefresh();

        void onLoad();

        void onFinish();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragment = this;
        mHandler = new Handler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public LRecyclerViewFragment() {
    }

    private void initView(View view) {
        headContainer = (LinearLayout) view.findViewById(R.id.fgm_head_container);
        floorContainer = (LinearLayout) view.findViewById(R.id.fgm_floor_container);

        recyclerView = (LRecyclerView) view.findViewById(R.id.fgm_recyclerview);
        if (lOnScrollListener != null) {
            recyclerView.setLOnScrollListener(lOnScrollListener);
        }
        backgroudContainer = (LinearLayout) view.findViewById(R.id.fgm_backgroud_container);
        refreshLayout = (SpringView) view.findViewById(R.id.fgm_recyclerview_refresh);
//        fastScroller = find(R.id.fgm_recyclerview_fast_scroller, VerticalRecyclerViewFastScroller.class);
//        fastScroller.setVisibility(View.GONE);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fgm_recyclerview_fab);
        if (isRefreshEnabled()) {
            refreshLayout.setEnable(true);
//            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    mFragment.onRefresh();
//                }
//            });
        } else {
            refreshLayout.setEnable(false);
        }
//        refreshLayout.setNestedScrollingEnabled(true);
//        refreshLayout.setColorSchemeResources(getPullToRefreshColorResources());
//        refreshLayout.setDistanceToTriggerSync(30);
        //   refreshLayout.setProgressViewOffset(false, Lerist.getToolbarHeight(context), 350);


        recyclerViewAdapter = getRecyclerViewAdapter();
        if (recyclerViewAdapter != null) {
            recyclerView.setAdapter(recyclerViewAdapter);
        }

        if (getLayoutManager() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        } else {
            recyclerView.setLayoutManager(getLayoutManager());
        }
//        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(getItemAnimator() == null ? new FadeInAnimator() : getItemAnimator());
        recyclerView.setScrollBarStyle(getScrollBarStyle());
        if (fastScrollerEnabled()) {
//            fastScroller.setVisibility(View.VISIBLE);
//            fastScroller.setRecyclerView(recyclerView);
//            recyclerView.setVerticalScrollBarEnabled(false);
//            recyclerView.addOnScrollListener(fastScroller.getOnScrollListener());
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    lastScrollTime = System.currentTimeMillis();
//                    fastScroller.setAlpha(1);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
//                    fastScroller.setAlpha(1);
//                    fastScroller.animate().alpha(0).setDuration(1000).setStartDelay(2000).start();
                }
            });
        }


        if (getOnScrollListener() != null) {
            recyclerView.addOnScrollListener(getOnScrollListener());
        }

        refreshLayout.setHeader(new DefaultHeader(context));
        refreshLayout.setFooter(new DefaultFooter(context));
        refreshLayout.setGive(SpringView.Give.BOTH);
        refreshLayout.setType(SpringView.Type.FOLLOW);
        refreshLayout.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if (isRefreshing()==false) {
                    mFragment.onRefresh();
                }
            }

            @Override
            public void onLoadmore() {
                if (recyclerViewAdapter instanceof LRecyclerViewAdapter) {
                    LRecyclerViewAdapter.OnLoadListener onLoadListener = ((LRecyclerViewAdapter) recyclerViewAdapter).getOnLoadListener();
                    if (recyclerViewAdapter.getItemCount() >= 0 && onLoadListener != null) {
                        onLoadListener.onLoad(0);
                        return;
                    }
                }
                refreshLayout.onFinishFreshAndLoad();
            }
        });
    }

    public int getFirstVisibleItemPosition() {
        if (getLayoutManager() instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) getRecyclerView().getLayoutManager()).findFirstVisibleItemPosition();
        }
        return -1;
    }

    public FloatingActionButton getFloatingActionButton() {
        floatingActionButton.setVisibility(View.VISIBLE);
        return floatingActionButton;
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return null;
    }

    public RecyclerView.ItemAnimator getItemAnimator() {
        return null;
    }

    public int getScrollBarStyle() {
        return RecyclerView.VERTICAL;
    }

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract RecyclerView.Adapter getRecyclerViewAdapter();

    public void onRefresh() {
        setRefreshing(true);
        if (onListener != null) onListener.onRefresh();
    }

    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void scrollBy(int x, int y) {
        recyclerView.scrollBy(x, y);
    }

    /**
     * 滚动到某一位置
     *
     * @param position
     */
    public void scrollToPosition(int position) {
        recyclerView.smoothScrollToPosition(position);
    }

    /**
     * 返回到顶部
     */
    public void backHead() {
        scrollToPosition(0);
    }

    public LRecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 激活下拉刷新
     *
     * @return
     */
    public boolean isRefreshEnabled() {
        return refreshEnabled;
    }

    public void setRefreshEnabled(boolean enabled) {
        this.refreshEnabled = enabled;
        if (recyclerView != null) initView(getView());
    }

    public void setLOnScrollListener(LOnScrollListener lOnScrollListener) {
        this.lOnScrollListener = lOnScrollListener;
    }

    /**
     * 启用快速滑动条
     *
     * @return
     */
    public boolean fastScrollerEnabled() {
        return false;
    }

    /**
     * 刷新图标Color
     *
     * @return 循环颜色集
     */
    public int[] getPullToRefreshColorResources() {
        return new int[]{R.color.black};
    }

    /**
     * 设置刷新状态
     *
     * @param refreshing
     */
    public void setRefreshing(final boolean refreshing) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                isRefreshing = refreshing;
                if (refreshLayout != null && isRefreshEnabled()) {
//                    refreshLayout.setRefreshing(refreshing);
                    if (refreshing == false) {
                        refreshLayout.onFinishFreshAndLoad();
                    } else {
//                        refreshLayout.callFresh();
                    }
                }
            }
        });
    }

    /**
     * 设置刷新动画显示
     *
     * @param isShow
     */
    public void setRefreshAnim(final boolean isShow) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (refreshLayout != null) {
//                    refreshLayout.setRefreshing(isShow);
                }
            }
        });

    }

//    public SwipeRefreshLayout getSwipeRefreshLayout() {
//        return refreshLayout;
//    }

    public LinearLayout getBackgroudContainer() {
        return backgroudContainer;
    }

    /**
     * 设置加载状态
     *
     * @param loading
     */
    public void setLoading(boolean loading) {
        if (getRecyclerView() == null) {
            return;
        }

        if (loading == true) {
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                                         @Override
                                                         public void run() {
                                                             refreshLayout.onFinishFreshAndLoad();
                                                         }
                                                     }
            );

//            refreshLayout.loadMoreComplete(true);
        }

//        if (loading) {
//            MaterialLoadingView loadingView = new MaterialLoadingView(context);
//            loadingView.setColor(getResources().getColor(R.color.colorAccent));
//            loadingView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Lerist.dip2px(context, 64)));
//            getRecyclerView().addFootView(loadingView);
//        } else {
//            getRecyclerView().removeFootView();
//        }
    }

    public void setLoadFinish() {
        if (getRecyclerView() == null) {
            return;
        }
//        TextView textView = new TextView(context);
//        textView.setText("---- END ----");
//        textView.setTextSize(12);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(getResources().getColor(R.color.font_color_hint));
//        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Lerist.dip2px(context, 64)));
//        getRecyclerView().addFootView(textView);
    }

    public void setOnListener(OnListener onListener) {
        this.onListener = onListener;
    }

    public void showNoDataHint() {
        showNoDataHint(null, -1, -1);
    }

    public void showNoDataHint(String hint, int textColorId, float textSize) {
        final TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setText(StringUtils.isEmpty(hint) ? "暂无数据, 请下拉重试" : hint);
        if (textSize != -1)
            textView.setTextSize(textSize);
        if (textColorId != -1)
            textView.setTextColor(getResources().getColor(textColorId));
        int dp32 = Lerist.dip2px(context, 80);
        textView.setPadding(0,
                (getHeadContainer().getChildCount() > 0 ?
                        getHeadContainer().getHeight() + dp32 : dp32), 0, 0);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                getBackgroudContainer().removeAllViews();
                getBackgroudContainer().addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        });
    }

    public LinearLayout getHeadContainer() {
        return headContainer;
    }

    public LinearLayout getFloorContainer() {
        return floorContainer;
    }

    public void onFinish() {
        setLoading(false);
        setRefreshing(false);
        if (onListener != null) onListener.onFinish();
    }

    /**
     * 滑动时隐藏显示控件Listener
     */
    public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {

        private static final float HIDE_THRESHOLD = 10;
        private static final float SHOW_THRESHOLD = 70;

        private int mOffset = 0;
        private boolean mControlsVisible = true;
        private int mHideHeight;
        private int mTotalScrolledDistance = -1;

        public HidingScrollListener(int hideHeight) {
            mHideHeight = hideHeight;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (mTotalScrolledDistance != -1 && mTotalScrolledDistance < mHideHeight) {
                    setVisible();
                } else {
                    if (mControlsVisible) {
                        if (mOffset > HIDE_THRESHOLD) {
                            setInvisible();
                        } else {
                            setVisible();
                        }
                    } else {
                        if ((mHideHeight - mOffset) > SHOW_THRESHOLD) {
                            setVisible();
                        } else {
                            setInvisible();
                        }
                    }
                }
            }

        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            clipOffset();
            onMoved(mOffset);

            if ((mOffset < mHideHeight && dy > 0) || (mOffset > 0 && dy < 0)) {
                mOffset += dy;
            }
            if (mTotalScrolledDistance < 0) {
                mTotalScrolledDistance = 0;
            } else {
                mTotalScrolledDistance += dy;
            }
        }

        private void clipOffset() {
            if (mOffset > mHideHeight) {
                mOffset = mHideHeight;
            } else if (mOffset < 0) {
                mOffset = 0;
            }
        }

        private void setVisible() {
            if (mOffset >= 0) {
                onShow();
                mOffset = 0;
            }
            mControlsVisible = true;
        }

        private void setInvisible() {
            if (mOffset <= mHideHeight) {
                onHide();
                mOffset = mHideHeight;
            }
            mControlsVisible = false;
        }

        public abstract void onMoved(int distance);

        public abstract void onShow();

        public abstract void onHide();
    }
}

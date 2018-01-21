package com.xsl.lerist.llibrarys.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.xsl.lerist.llibrarys.interfaces.LOnScrollListener;
import com.xsl.lerist.llibrarys.utils.Lerist;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;


/**
 * Created by Lerist on 2015/9/9, 0009.
 */
public class LRecyclerView extends RecyclerView {
    private View responceView;
    private LOnScrollListener lOnScrollListener;
    private ArrayList<View> mHeaderViews = new ArrayList<>();

    private ArrayList<View> mFootViews = new ArrayList<>();
    private Adapter mAdapter;
    private boolean isCanScroll = true;
    private boolean isDisableClick = false;

    public LRecyclerView(Context context) {
        this(context, null);
    }

    public LRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (lOnScrollListener != null) {
                    if (dy > 0) {
                        lOnScrollListener.scrollUp(dy);
                    }
                    if (dy < 0) {
                        lOnScrollListener.scrollDown(dy);
                    }

                    if (isAtTop()) {
                        lOnScrollListener.atTop();
                    }

                    if (isAtBottom()) {
                        lOnScrollListener.atBottom();
                    }
                }
            }
        });
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (getItemAnimator() == null) {
            setItemAnimator(new FadeInAnimator());
        }

        if (mHeaderViews.isEmpty() && mFootViews.isEmpty()) {
            super.setAdapter(adapter);
        } else {
            adapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, adapter);
            super.setAdapter(adapter);
        }
        mAdapter = adapter;
    }

    public void addHeaderView(View view) {
        mHeaderViews.clear();
        mHeaderViews.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
                setAdapter(mAdapter);
            }
        }
    }

    public void addFootView(View view) {
        mFootViews.clear();
        mFootViews.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
                setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void removeHeaderView() {
        mHeaderViews.clear();
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
                setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void removeFootView() {
        mFootViews.clear();
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
                setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getVerticalFadingEdgeLength() {
        return 0;
    }

    public boolean isCanScroll() {
        return isCanScroll;
    }

    public void setCanScroll(boolean canScroll) {
        isCanScroll = canScroll;
    }

    public boolean isDisableClick() {
        return isDisableClick;
    }

    public void setDisableClick(boolean disableClick) {
        isDisableClick = disableClick;
    }

    //    @Override
//    public boolean onInterceptTouchEvent(MotionEvent e) {
//
//        if (!isCanScroll && e.getAction() != MotionEvent.ACTION_DOWN) {
//            //禁止滑动
//            ViewGroup parent = (ViewGroup) getParent();
//            if (parent != null) {
//                parent.requestDisallowInterceptTouchEvent(false);
//                parent.onTouchEvent(e);
//            }
//            return false;
//        }
//
//        return super.onInterceptTouchEvent(e);
//    }

    float mLastMotionY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (!isCanScroll) {
            //禁止滑动(保留点击事件)
            if (!(event.getAction() == MotionEvent.ACTION_DOWN
                    || event.getAction() == MotionEvent.ACTION_UP)) {
                return false;
            }
        }

        if (isDisableClick){
            //禁用点击
            return false;
        }
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

                //  Log.i("msg", "scroll: " + getScrollY() + "  MeasuredHeight:" + getMeasuredHeight() + " ContentHeight:" + getChildAt(0).getMeasuredHeight() + "   direction: " + direction);

                if ((isAtTop() && direction < 0)) {
                    //从顶部下滑
                    //将触摸事件交给父控件处理
                    if (responceView != null) {
                        ((ViewParent) responceView).requestDisallowInterceptTouchEvent(false);
                        responceView.onTouchEvent(event);
                        return false;
                    }
                } else if ((isAtBottom() && direction > 0)) {
                    //从底部上滑
                    //将触摸事件交给父控件处理
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
    public void setResponceView(View responceView) {
        this.responceView = responceView;
    }

    /**
     * 在顶部
     *
     * @return
     */
    public boolean isAtTop() {
//        return getScrollState() == RecyclerView.SCROLL_STATE_IDLE && getLastVisibleItemPosition(true) <= 1;
        return getLastVisibleItemPosition(true) <= 1;
    }

    /**
     * 在底部
     *
     * @return
     */
    public boolean isAtBottom() {
        if (getChildCount() <= 0) {
            return true;
        }
//        return getScrollState() == RecyclerView.SCROLL_STATE_IDLE && getLastVisibleItemPosition(false) >= getLayoutManager().getItemCount() - 1;
        return getLastVisibleItemPosition(false) >= getLayoutManager().getItemCount() - 1;
    }

    public int getLastVisibleItemPosition(boolean scrollDown) {
        int lastCompletelyVisibleItemPosition = 0;
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        if (layoutManager instanceof GridLayoutManager) {
            lastCompletelyVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] ints = new int[staggeredGridLayoutManager.getSpanCount()];
            staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(ints);
            lastCompletelyVisibleItemPosition = (int) (scrollDown ? Lerist.findMin(ints) : Lerist.findMax(ints));
        }
        return lastCompletelyVisibleItemPosition;
    }

    public void setLOnScrollListener(LOnScrollListener lOnScrollListener) {
        this.lOnScrollListener = lOnScrollListener;
    }

    public static class WrapContentLinearLayoutManager extends LinearLayoutManager {

        public WrapContentLinearLayoutManager(Context context) {
            super(context);
        }

        public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public WrapContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        public void onMeasure(Recycler recycler, State state, int widthSpec, int heightSpec) {
            if (getItemCount() <= 0) {
                super.onMeasure(recycler, state, widthSpec, heightSpec);
                return;
            }

            try {
                View view = recycler.getViewForPosition(0);
                if (view != null) {
                    measureChild(view, widthSpec, heightSpec);
                    int measuredWidth = MeasureSpec.getSize(widthSpec);
                    int measuredHeight = view.getMeasuredHeight();
                    setMeasuredDimension(measuredWidth, measuredHeight);
                }
            } catch (Exception e) {
                super.onMeasure(recycler, state, widthSpec, heightSpec);
            }
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                Log.e("probe", "meet a IOOBE in RecyclerView");
            }
        }
    }

    public static class WrapContentGridLayoutManager extends GridLayoutManager {


        public WrapContentGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        public WrapContentGridLayoutManager(Context context, int spanCount) {
            super(context, spanCount);
        }

        public WrapContentGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
            super(context, spanCount, orientation, reverseLayout);
        }

        @Override
        public void onMeasure(Recycler recycler, State state, int widthSpec, int heightSpec) {
            if (getItemCount() <= 0) {
                super.onMeasure(recycler, state, widthSpec, heightSpec);
                return;
            }
            try {
                View view = recycler.getViewForPosition(0);
                if (view != null) {
                    measureChild(view, widthSpec, heightSpec);
                    int measuredWidth = MeasureSpec.getSize(widthSpec);
                    int measuredHeight = view.getMeasuredHeight();
                    setMeasuredDimension(measuredWidth, measuredHeight);
                }
            } catch (Exception e) {
                super.onMeasure(recycler, state, widthSpec, heightSpec);
            }
        }
    }

    public class RecyclerWrapAdapter extends RecyclerView.Adapter {
        private RecyclerView.Adapter mAdapter;

        private ArrayList<View> mHeaderViews;

        private ArrayList<View> mFootViews;

        final ArrayList<View> EMPTY_INFO_LIST = new ArrayList<View>();

        private int mCurrentPosition;

        public RecyclerWrapAdapter(ArrayList<View> mHeaderViews, ArrayList<View> mFootViews, RecyclerView.Adapter mAdapter) {
            this.mAdapter = mAdapter;
            if (mHeaderViews == null) {
                this.mHeaderViews = EMPTY_INFO_LIST;
            } else {
                this.mHeaderViews = mHeaderViews;
            }
            if (mHeaderViews == null) {
                this.mFootViews = EMPTY_INFO_LIST;
            } else {
                this.mFootViews = mFootViews;
            }
        }

        public int getHeadersCount() {
            return mHeaderViews.size();
        }

        public int getFootersCount() {
            return mFootViews.size();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == RecyclerView.INVALID_TYPE) {
                return new HeaderViewHolder(mHeaderViews.get(0));
            } else if (viewType == RecyclerView.INVALID_TYPE - 1) {
                return new HeaderViewHolder(mFootViews.get(0));
            }
            return mAdapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            int numHeaders = getHeadersCount();
            if (position < numHeaders) {
                return;
            }
            int adjPosition = position - numHeaders;
            int adapterCount = 0;
            if (mAdapter != null) {
                adapterCount = mAdapter.getItemCount();
                if (adjPosition < adapterCount) {
                    mAdapter.onBindViewHolder(holder, adjPosition);
                    return;
                }
            }
        }

        @Override
        public int getItemCount() {
            if (mAdapter != null) {
                return getHeadersCount() + getFootersCount() + mAdapter.getItemCount();
            } else {
                return getHeadersCount() + getFootersCount();
            }
        }

        @Override
        public int getItemViewType(int position) {
            mCurrentPosition = position;
            int numHeaders = getHeadersCount();
            if (position < numHeaders) {
                return RecyclerView.INVALID_TYPE;
            }
            int adjPosition = position - numHeaders;
            int adapterCount = 0;
            if (mAdapter != null) {
                adapterCount = mAdapter.getItemCount();
                if (adjPosition < adapterCount) {
                    return mAdapter.getItemViewType(adjPosition);
                }
            }
            return RecyclerView.INVALID_TYPE - 1;
        }


        @Override
        public long getItemId(int position) {
            int numHeaders = getHeadersCount();
            if (mAdapter != null && position >= numHeaders) {
                int adjPosition = position - numHeaders;
                int adapterCount = mAdapter.getItemCount();
                if (adjPosition < adapterCount) {
                    return mAdapter.getItemId(adjPosition);
                }
            }
            return -1;
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {
            public HeaderViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    /**
     * 通用分割线
     */
    public static class RecycleViewDivider extends RecyclerView.ItemDecoration {

        private Paint mPaint;
        private Drawable mDivider;
        private int mDividerHeight = 2;//分割线高度，默认为1px
        private int mOrientation;//列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
        private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

        /**
         * 默认分割线：高度为2px，颜色为灰色
         *
         * @param context
         * @param orientation 列表方向
         */
        public RecycleViewDivider(Context context, int orientation) {
            if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
                throw new IllegalArgumentException("请输入正确的参数！");
            }
            mOrientation = orientation;

            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        }

        /**
         * 自定义分割线
         *
         * @param context
         * @param orientation 列表方向
         * @param drawableId  分割线图片
         */
        public RecycleViewDivider(Context context, int orientation, int drawableId) {
            this(context, orientation);
            mDivider = ContextCompat.getDrawable(context, drawableId);
            mDividerHeight = mDivider.getIntrinsicHeight();
        }

        /**
         * 自定义分割线
         *
         * @param context
         * @param orientation   列表方向
         * @param dividerHeight 分割线高度
         * @param dividerColor  分割线颜色
         */
        public RecycleViewDivider(Context context, int orientation, int dividerHeight, int dividerColor) {
            this(context, orientation);
            mDividerHeight = dividerHeight;
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setColor(dividerColor);
            mPaint.setStyle(Paint.Style.FILL);
        }


        //获取分割线尺寸
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 0, mDividerHeight);
        }

        //绘制分割线
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            if (mOrientation == LinearLayoutManager.VERTICAL) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

        //绘制横向 item 分割线
        private void drawHorizontal(Canvas canvas, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
            final int childSize = parent.getChildCount();
            for (int i = 0; i < childSize; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + layoutParams.bottomMargin;
                final int bottom = top + mDividerHeight;
                if (mDivider != null) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
                if (mPaint != null) {
                    canvas.drawRect(left, top, right, bottom, mPaint);
                }
            }
        }

        //绘制纵向 item 分割线
        private void drawVertical(Canvas canvas, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
            final int childSize = parent.getChildCount();
            for (int i = 0; i < childSize; i++) {
                final View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int left = child.getRight() + layoutParams.rightMargin;
                final int right = left + mDividerHeight;
                if (mDivider != null) {
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
                if (mPaint != null) {
                    canvas.drawRect(left, top, right, bottom, mPaint);
                }
            }
        }
    }
}
package com.xsl.lerist.llibrarys.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.nineoldandroids.view.ViewHelper;
import com.socks.library.KLog;

import java.lang.reflect.Field;

/**
 * Created by Lerist on 2015/9/10, 0010.
 */
public class LViewPager extends ViewPager {
    private boolean isCanScroll = true;
    private ViewGroup nestParent;

    public LViewPager(Context context) {
        super(context);
    }

    public LViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (getLayoutParams().height == WindowManager.LayoutParams.WRAP_CONTENT) {
            //实现WRAP_CONTENT
            int height = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                int h = child.getMeasuredHeight();
                if (h > height) height = h;
            }

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (isCanScroll) {
//            return super.dispatchTouchEvent(ev);
//        } else {
//            if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//                getParent().requestDisallowInterceptTouchEvent(false);
//                ((View) getParent()).onTouchEvent(ev);
//                return false;
//            } else {
//                getParent().requestDisallowInterceptTouchEvent(true);
//                return super.dispatchTouchEvent(ev);
//            }
//        }
//    }

    /**
     * 设置嵌套响应Touch事件的父控件
     *
     * @param nestParent
     */
    public void setNestParent(ViewGroup nestParent) {
        this.nestParent = nestParent;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {

        /* return false;//super.onTouchEvent(arg0); */
        if (!isCanScroll)
            return false;
        else {
        }

        return super.onTouchEvent(arg0);
    }

    GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            KLog.i(v + "  " + v1);
            if (Math.abs(v) > Math.abs(v1)) {
                //水平滑动时禁止父控件拦截触摸事件
                ViewGroup parent = (ViewGroup) getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                return true;
            } else {
                ViewGroup parent = (ViewGroup) getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    });

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (!isCanScroll)
            return false;
        else {
            try {
//                gestureDetector.onTouchEvent(arg0);
                return super.onInterceptTouchEvent(arg0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void nextPage() {
        if (getAdapter() == null) {
            return;
        }
        setCurrentItem((getCurrentItem() + 1) % getAdapter().getCount());
    }

    public void previousPage() {
        if (getAdapter() == null) {
            return;
        }
        setCurrentItem((getCurrentItem() + getAdapter().getCount() - 1) % getAdapter().getCount());
    }

    public boolean isFirstPage() {
        return getCurrentItem() == 0;
    }

    public boolean isLastPage() {
        return getCurrentItem() == getChildCount() - 1;
    }

    public boolean isCanScroll() {
        return isCanScroll;
    }

    public void setIsCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    /**
     * 设置切换滑动时间
     *
     * @param duration
     */
    public void setScrollDuration(int duration) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(this.getContext(),
                    new AccelerateInterpolator());
            field.set(this, scroller);
            scroller.setmDuration(duration);
        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), "", e);
        }
    }

    public class FixedSpeedScroller extends Scroller {
        private int mDuration = 500;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setmDuration(int time) {
            mDuration = time;
        }

        public int getmDuration() {
            return mDuration;
        }
    }

    /**
     * 渐变动画
     */
    public static class AlphaPageTransformer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View page, float position) {
            if (position <= -1) { // [-Infinity,-1]
                // This page is way off-screen to the left.
                page.setTranslationX(-page.getWidth());
                page.setAlpha(0);

            } else if (position < 0) { // (-1,0)
                // Use the default slide transition when moving to the left page
                page.setAlpha(1 + position);

                //Counteract the default slide transition
                page.setTranslationX(page.getWidth() * -position);

            } else if (position == 0) { // [0]
                // Use the default slide transition when moving to the left page
                page.setAlpha(1);

                //Counteract the default slide transition
                page.setTranslationX(0);

            } else if (position < 1) { // (0,1]
                // Fade the page out.
                page.setAlpha(1 - position);

                //Counteract the default slide transition
                page.setTranslationX(page.getWidth() * -position);

            } else { // [1,+Infinity]
                // This page is way off-screen to the right.
                page.setAlpha(0);
                page.setTranslationX(page.getWidth());

            }
        }
    }

    public static class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.5f;
        private static final float MIN_ALPHA = 0.2f;


        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            Log.e("TAG", view + " , " + position + "");

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) //a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                        / (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    public static class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    public static class RotateDownPageTransformer implements ViewPager.PageTransformer {

        private static final float ROT_MAX = 20.0f;
        private float mRot;


        public void transformPage(View view, float position) {

            Log.e("TAG", view + " , " + position + "");

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                ViewHelper.setRotation(view, 0);

            } else if (position <= 1) // a页滑动至b页 ； a页从 0.0 ~ -1 ；b页从1 ~ 0.0
            { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                if (position < 0) {

                    mRot = (ROT_MAX * position);
                    ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
                    ViewHelper.setPivotY(view, view.getMeasuredHeight());
                    ViewHelper.setRotation(view, mRot);
                } else {

                    mRot = (ROT_MAX * position);
                    ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
                    ViewHelper.setPivotY(view, view.getMeasuredHeight());
                    ViewHelper.setRotation(view, mRot);
                }

                // Scale the page down (between MIN_SCALE and 1)

                // Fade the page relative to its size.

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                ViewHelper.setRotation(view, 0);
            }
        }
    }

    public static class ParallaxTransformer implements ViewPager.PageTransformer {
        float parallaxCoefficient;
        float distanceCoefficient;

        public ParallaxTransformer(float parallaxCoefficient, float distanceCoefficient) {
            this.parallaxCoefficient = parallaxCoefficient;
            this.distanceCoefficient = distanceCoefficient;
        }

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void transformPage(View page, float position) {
            float scrollXOffset = page.getWidth() * parallaxCoefficient;

            ViewGroup pageViewWrapper = (ViewGroup) page;
//           @SuppressWarnings("SuspiciousMethodCalls")
//           int[] layer = mLayoutViewIdsMap.get(pageViewWrapper.getChildAt(0).getId());
            for (int i = 0; i < (pageViewWrapper.getChildCount() == 1
                    ? ((ViewGroup) pageViewWrapper.getChildAt(0)).getChildCount()
                    : pageViewWrapper.getChildCount()); i++) {
                View view = pageViewWrapper.getChildAt(i);
//               View view = page.findViewById(id);
                if (view != null) {
                    view.setTranslationX(scrollXOffset * position);
                }
                scrollXOffset *= distanceCoefficient;
            }
        }
    }
}

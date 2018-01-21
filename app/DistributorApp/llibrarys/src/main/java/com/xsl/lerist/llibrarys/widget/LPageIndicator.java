package com.xsl.lerist.llibrarys.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.socks.library.KLog;
import com.xsl.lerist.llibrarys.R;

import java.util.ArrayList;

import carbon.Carbon;
import carbon.drawable.ripple.RippleDrawable;
import carbon.widget.ImageView;

/**
 * Created by Lerist on 2015/11/14, 0014.
 */
public class LPageIndicator extends LinearLayout implements ViewPager.OnPageChangeListener, View.OnClickListener, LFragmentContainer.OnFragmentChangedListener {
    private ViewPager viewPager;
    private int[] normalResourceIds;
    private int[] focusResourceIds;
    private int normalBackgroudResourceId;
    private int focusBackgroudResourceId;
    private ArrayList<Indicator> indicators;
    private int childCount;
    private boolean isAutoScroll;
    private MyHandler handler = new MyHandler();
    private long delayTimeInMills;
    private int iconHeight = LayoutParams.WRAP_CONTENT;
    private int iconWidth = LayoutParams.WRAP_CONTENT;
    private ColorStateList normalTextColor;
    private ColorStateList focusTextColor;
    private int textSize = 12;
    private int textStyle = Typeface.NORMAL;
    private int ic_marginBottom = 0;
    private int in_paddingLeft;
    private int in_paddingTop;
    private int in_paddingRight;
    private int in_paddingBottom;
    private int in_marginLeft;
    private int in_marginTop;
    private int in_marginRight;
    private int in_marginBottom;
    private int in_padding = 0;
    private LayoutParams in_layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
    private LFragmentContainer fragmentContainer;
    private ColorStateList normalTintColor;
    private ColorStateList focusTintColor;
    private ColorStateList rippleColor;
    private IndicatorSelectListener indicatorSelectListener;
    private String[] texts;
    private int textNormalBackgroudResourceId;
    private int textFocusBackgroudResourceId;
    private int textPaddingLeft;
    private int textPaddingRight;
    private int textPaddingTop;
    private int textPaddingBottom;

    public interface IndicatorSelectListener {
        void indicatorSelected(int selectedIndex);
    }

    public LPageIndicator(Context context) {
        this(context, null);
    }

    public LPageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LPageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LPageIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LPageIndicator);

        iconHeight = a.getDimensionPixelSize(R.styleable.LPageIndicator_icon_height, iconHeight);
        iconWidth = a.getDimensionPixelSize(R.styleable.LPageIndicator_icon_width, iconWidth);
        textSize = a.getDimensionPixelSize(R.styleable.LPageIndicator_text_size, textSize);
        normalTextColor = a.getColorStateList(R.styleable.LPageIndicator_text_color_nor);
        focusTextColor = a.getColorStateList(R.styleable.LPageIndicator_text_color_foc);
        in_padding = a.getDimensionPixelSize(R.styleable.LPageIndicator_in_padding, in_padding);
        in_paddingLeft = a.getDimensionPixelSize(R.styleable.LPageIndicator_in_paddingLeft, in_padding);
        in_paddingTop = a.getDimensionPixelSize(R.styleable.LPageIndicator_in_paddingTop, in_padding);
        in_paddingRight = a.getDimensionPixelSize(R.styleable.LPageIndicator_in_paddingRight, in_padding);
        in_paddingBottom = a.getDimensionPixelSize(R.styleable.LPageIndicator_in_paddingBottom, in_padding);
        ic_marginBottom = a.getDimensionPixelSize(R.styleable.LPageIndicator_icon_marginBottom, ic_marginBottom);
        a.recycle();
    }

    public void setFragmentContainer(LFragmentContainer fragmentContainer) {
        this.fragmentContainer = fragmentContainer;
        childCount = fragmentContainer.getCount();
        fragmentContainer.addOnFragmentChangedListener(this);
        initIndicator();
    }

    public LFragmentContainer getFragmentContainer() {
        return fragmentContainer;
    }

    public void setIndicatorSelectListener(IndicatorSelectListener indicatorSelectListener) {
        this.indicatorSelectListener = indicatorSelectListener;
        initIndicator();
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.addOnPageChangeListener(this);
        childCount = viewPager.getAdapter().getCount();
        initIndicator();
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public int getChildCount() {
        return childCount;
    }

    public LPageIndicator setNormalResourceIds(int... norResIds) {
        this.normalResourceIds = norResIds;
        return this;
    }

    public LPageIndicator setFocusResourceIds(int... focResIds) {
        this.focusResourceIds = focResIds;
        return this;
    }

    public LPageIndicator setNormalBackgroudResourceId(int normalBackgroudResourceId) {
        this.normalBackgroudResourceId = normalBackgroudResourceId;
        return this;
    }

    public LPageIndicator setFocusBackgroudResourceId(int focusBackgroudResourceId) {
        this.focusBackgroudResourceId = focusBackgroudResourceId;
        return this;
    }

    public LPageIndicator setRippleColorId(int rippleColorId) {
        int color = getResources().getColor(rippleColorId);
        return setRippleColor(color);
    }

    public LPageIndicator setRippleColor(int rippleColor) {
        this.rippleColor = ColorStateList.valueOf(rippleColor);
        return this;
    }

    public LPageIndicator setNormalTintColor(int normalTintColor) {
        this.normalTintColor = ColorStateList.valueOf(normalTintColor);
        return this;
    }

    public LPageIndicator setFocusTintColor(int focusTintColor) {
        this.focusTintColor = ColorStateList.valueOf(focusTintColor);
        return this;
    }

    public LPageIndicator setNormalTintColorId(int normalTintColorId) {
        return setNormalTintColor(getResources().getColor(normalTintColorId));
    }

    public LPageIndicator setFocusTintColorId(int focusTintColorId) {
        return setFocusTintColor(getResources().getColor(focusTintColorId));
    }

    public LPageIndicator setTexts(String... texts) {
        this.texts = texts;
        return this;
    }

    public LPageIndicator setTextColorId(int normalTextColorId, int focusTextColorId) {
        return setTextColor(getResources().getColor(normalTextColorId), getResources().getColor(focusTextColorId));
    }

    public LPageIndicator setTextColor(int normalTextColor, int focusTextColor) {
        this.normalTextColor = ColorStateList.valueOf(normalTextColor);
        this.focusTextColor = ColorStateList.valueOf(focusTextColor);
        return this;
    }

    public LPageIndicator setTextStyle(int textStyle) {
        this.textStyle = textStyle;
        return this;
    }

    public LPageIndicator setTextSize(int textSizeSP) {
        this.textSize = textSizeSP;
        return this;
    }

    public LPageIndicator setTextPadding(int padding) {
        return setTextPadding(padding, padding, padding, padding);
    }

    public LPageIndicator setTextPadding(int left, int top, int right, int bottom) {
        this.textPaddingLeft = left;
        this.textPaddingRight = right;
        this.textPaddingTop = top;
        this.textPaddingBottom = bottom;
        return this;
    }

    public LPageIndicator setTextNormalBackgroudResourceId(int textNormalBackgroudResourceId) {
        this.textNormalBackgroudResourceId = textNormalBackgroudResourceId;
        return this;
    }

    public LPageIndicator setTextFocusBackgroudResourceId(int textFocusBackgroudResourceId) {
        this.textFocusBackgroudResourceId = textFocusBackgroudResourceId;
        return this;
    }

    public LPageIndicator setIconSize(int iconWidth, int iconHeight) {
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        return this;
    }

    public LPageIndicator setIcMarginBottom(int ic_marginBottom) {
        this.ic_marginBottom = ic_marginBottom;
        return this;
    }

    public LPageIndicator setInPaddingLeft(int in_paddingLeft) {
        this.in_paddingLeft = in_paddingLeft;
        return this;
    }

    public LPageIndicator setInPaddingTop(int in_paddingTop) {
        this.in_paddingTop = in_paddingTop;
        return this;
    }

    public LPageIndicator setInPaddingRight(int in_paddingRight) {
        this.in_paddingRight = in_paddingRight;
        return this;
    }

    public LPageIndicator setInPaddingBottom(int in_paddingBottom) {
        this.in_paddingBottom = in_paddingBottom;
        return this;
    }

    public LPageIndicator setInPadding(int in_padding) {
        this.in_padding = in_padding;
        this.in_paddingLeft = in_padding;
        this.in_paddingTop = in_padding;
        this.in_paddingRight = in_padding;
        this.in_paddingBottom = in_padding;
        return this;
    }

    public LPageIndicator setInMargin(int in_marginLeft, int in_marginTop, int in_marginRight, int in_marginBottom) {
        this.in_marginLeft = in_marginLeft;
        this.in_marginTop = in_marginTop;
        this.in_marginRight = in_marginRight;
        this.in_marginBottom = in_marginBottom;
        return this;
    }

    public LPageIndicator setInLayoutParams(LayoutParams in_layoutParams) {
        this.in_layoutParams = in_layoutParams;
        return this;
    }

    private void initIndicator() {
        indicators = new ArrayList<>();
        this.removeAllViews();
        if (childCount == 0) childCount = normalResourceIds.length;
        for (int i = 0; i < childCount; i++) {
            Indicator indicator = new Indicator(getContext());
            if (normalResourceIds != null) {
                indicator.setNormalImgResourceId(normalResourceIds.length > i ? normalResourceIds[i] : normalResourceIds[0]);
            }
            if (focusResourceIds != null) {
                indicator.setFocusImgResourceId(focusResourceIds.length > i ? focusResourceIds[i] : focusResourceIds[0]);
            }

            indicator.setImgSize(iconWidth, iconHeight);
            String pageTitle = null;
            if (texts == null || texts.length == 0) {
                if (getViewPager() != null) {
                    pageTitle = (String) getViewPager().getAdapter().getPageTitle(i);
                }

                if (getFragmentContainer() != null) {
                    pageTitle = getFragmentContainer().getTitle(i);
                }
            } else {
                pageTitle = texts[i];
            }

            if (pageTitle != null) {
                indicator.setFocusText(pageTitle, textSize, focusTextColor);
                indicator.setNormalText(pageTitle, textSize, normalTextColor);
            }

            indicator.setTextFocusBackgroudResourceId(textFocusBackgroudResourceId);
            indicator.setTextNormalBackgroudResourceId(textNormalBackgroudResourceId);
            indicator.setTextPadding(textPaddingLeft, textPaddingTop, textPaddingRight, textPaddingBottom);
            if (textStyle != 0) indicator.setTextStyle(textStyle);

            indicator.setFocusBackgroudResourceId(focusBackgroudResourceId);
            indicator.setNormalBackgroudResourceId(normalBackgroudResourceId);
            indicator.setFocusTintColor(focusTintColor);
            indicator.setNormalTintColor(normalTintColor);
            indicator.setRippleColor(rippleColor);

            this.addView(indicator, in_layoutParams);

            ((LayoutParams) indicator.getLayoutParams()).setMargins(in_marginLeft, in_marginTop, in_marginRight, in_marginBottom);
            indicator.setPadding(in_paddingLeft, in_paddingTop, in_paddingRight, in_paddingBottom);
            indicator.setImgMargin(0, 0, 0, ic_marginBottom);
            indicators.add(indicator);

            if (childCount > 0)
                refreshIndicator(0);
        }

        for (Indicator indicator : indicators) {
            indicator.setOnClickListener(this);
        }
    }

    public void refreshIndicator(int focPosition) {
        for (Indicator indicator : indicators) {
            indicator.setNormalState();
        }
        if (indicators.size() > focPosition) {
            indicators.get(focPosition).setFocusState();
        } else {
            KLog.e("indicators.size() > focPosition");
        }
    }

    public void startAutoScroll(int delayTimeInMills) {
        isAutoScroll = true;
        sendScrollMessage(delayTimeInMills);
    }

    /**
     * stop auto scroll
     */
    public void stopAutoScroll() {
        isAutoScroll = false;
        handler.removeMessages(0);
    }

    private void sendScrollMessage(long delayTimeInMills) {
        this.delayTimeInMills = delayTimeInMills;
        handler.removeMessages(0);
        handler.sendEmptyMessageDelayed(0, delayTimeInMills);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        refreshIndicator(position);
    }

    @Override
    public void onPageSelected(int position) {
        refreshIndicator(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        setIndicatorSeleted(indicators.indexOf(v));
    }

    public void setIndicatorSeleted(int index) {
        if (indicatorSelectListener != null) {
            refreshIndicator(index);
            indicatorSelectListener.indicatorSelected(index);
        }

        if (getFragmentContainer() != null) {
            getFragmentContainer().setVisibleFragmentIndex(index);
        }

        if (getViewPager() != null) {
            getViewPager().setCurrentItem(index);
        }
    }

    @Override
    public void onFragmentChangedBefore(Fragment fragment, int index) {

    }

    @Override
    public void onFragmentChanged(Fragment fragment, int index) {
        refreshIndicator(fragmentContainer.getCurrentVisibleFragmentIndex());
    }


    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    int currentItem = getViewPager().getCurrentItem();
                    int childCount = getViewPager().getAdapter().getCount();
                    getViewPager().setCurrentItem((currentItem + 1) % childCount, true);
                    sendScrollMessage(delayTimeInMills);
                default:
                    break;
            }
        }
    }

    class Indicator extends carbon.widget.LinearLayout {
        private ImageView imageView;
        private TextView textView;
        private int normalImageResourceId;
        private int focusImageResourceId;
        private int normalBackgroudResourceId;
        private int focusBackgroudResourceId;
        private String normalText;
        private String focusText;
        private ColorStateList normalTextColor;
        private ColorStateList focusTextColor;
        private ColorStateList normalTintColor;
        private ColorStateList focusTintColor;
        private int normalTextSize;
        private int focusTextSize;
        private boolean isFocusState;
        private int imgWidth;
        private int imgHeight;
        private int textNormalBackgroudResourceId;
        private int textFocusBackgroudResourceId;
        private int textPaddingLeft;
        private int textPaddingRight;
        private int textPaddingTop;
        private int textPaddingBottom;

        public Indicator(Context context) {
            super(context);
            imageView = new ImageView(context);
            textView = new TextView(context);

            init();
        }

        private void init() {
            this.addView(imageView);
            this.addView(textView);
            this.setOrientation(VERTICAL);
            this.setGravity(Gravity.CENTER);


        }

        /**
         * eg.  Typeface.BOLD
         *
         * @param textStyle
         */
        public void setTextStyle(int textStyle) {
            textView.setTypeface(Typeface.defaultFromStyle(textStyle));
        }

        public void setNormalImgResourceId(int norImgResId) {
            this.normalImageResourceId = norImgResId;
        }

        public void setFocusImgResourceId(int focImgResId) {
            this.focusImageResourceId = focImgResId;
        }

        public void setImgSize(int imgWidth, int imgHeight) {
            this.imgWidth = imgWidth;
            this.imgHeight = imgHeight;

            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = imgWidth;
            layoutParams.height = imgHeight;

            imageView.setLayoutParams(layoutParams);
            imageView.requestLayout();
        }

        public void setImgMargin(int l, int t, int r, int b) {
            MarginLayoutParams params = (MarginLayoutParams) imageView.getLayoutParams();
            params.setMargins(l, t, r, b);
            imageView.setLayoutParams(params);
            imageView.requestLayout();
        }

        public void setNormalTintColor(ColorStateList normalTintColor) {
            this.normalTintColor = normalTintColor;
        }

        public void setFocusTintColor(ColorStateList focusTintColor) {
            this.focusTintColor = focusTintColor;
        }

        /**
         * 设置常规状态下的文本参数
         *
         * @param normalText       文本内容
         * @param normalTextSizeSP 字体大小,单位SP
         * @param normalTextColor  文本颜色
         */
        public void setNormalText(String normalText, int normalTextSizeSP, ColorStateList normalTextColor) {
            this.normalText = normalText;
            this.normalTextSize = normalTextSizeSP;
            this.normalTextColor = normalTextColor;
        }

        /**
         * 设置焦点状态下的文本参数
         *
         * @param focusText       文本内容
         * @param focusTextSizeSP 字体大小,单位SP
         * @param focusTextColor  文本颜色
         */
        public void setFocusText(String focusText, int focusTextSizeSP, ColorStateList focusTextColor) {
            this.focusText = focusText;
            this.focusTextSize = focusTextSizeSP;
            this.focusTextColor = focusTextColor;
        }

        public void setFocusState() {
            isFocusState = true;
            if (focusBackgroudResourceId != 0) {
                setBackgroundResource(focusBackgroudResourceId);
            }
            if (focusTintColor != null)
                imageView.setTint(focusTintColor);

            if (imgWidth > 0 && imgHeight > 0 && focusImageResourceId != 0) {
                imageView.setImageResource(focusImageResourceId);
            } else {
                ViewGroup parent = (ViewGroup) imageView.getParent();
                if (parent != null)
                    parent.removeView(imageView);
            }

            if (focusText != null && focusTextSize > 0) {
                textView.setText(focusText);
                textView.setTextSize(focusTextSize);
                textView.setTextColor(focusTextColor != null ? focusTextColor : ColorStateList.valueOf(0xFFFFFFFF));
            } else {
                //无文本的话, 移除TextView
                ViewGroup parent = (ViewGroup) textView.getParent();
                if (parent != null)
                    parent.removeView(textView);
            }

            if (textFocusBackgroudResourceId != 0)
                textView.setBackgroundResource(textFocusBackgroudResourceId);

            textView.setPadding(textPaddingLeft, textPaddingTop, textPaddingRight, textPaddingBottom);
        }

        public void setNormalState() {
            isFocusState = false;
            if (normalBackgroudResourceId != 0) {
                setBackgroundResource(normalBackgroudResourceId);
            }
            if (imgWidth > 0 && imgHeight > 0 && normalImageResourceId != -1) {
                imageView.setImageResource(normalImageResourceId);
                imageView.setTint(normalTintColor);
            } else {
                ViewGroup parent = (ViewGroup) imageView.getParent();
                if (parent != null)
                    parent.removeView(imageView);
            }

            //Lerist.Log(getClass(), normalText + " " + normalTextColor + " " + normalTextSize);
            if (normalText != null && normalTextSize > 0) {
                textView.setText(normalText);
                textView.setTextSize(normalTextSize);
                textView.setTextColor(normalTextColor != null ? normalTextColor : ColorStateList.valueOf(0xFF000000));
            } else {
                //无文本的话, 移除TextView
                ViewGroup parent = (ViewGroup) textView.getParent();
                if (parent != null)
                    parent.removeView(textView);
            }

            if (textNormalBackgroudResourceId != 0)
                textView.setBackgroundResource(textNormalBackgroudResourceId);

            textView.setPadding(textPaddingLeft, textPaddingTop, textPaddingRight, textPaddingBottom);

        }

        public void setNormalBackgroudResourceId(int normalBackgroudResourceId) {
            this.normalBackgroudResourceId = normalBackgroudResourceId;
        }

        public void setFocusBackgroudResourceId(int focusBackgroudResourceId) {
            this.focusBackgroudResourceId = focusBackgroudResourceId;
        }

        public void setTextNormalBackgroudResourceId(int textNormalBackgroudResourceId) {
            this.textNormalBackgroudResourceId = textNormalBackgroudResourceId;
        }

        public void setTextFocusBackgroudResourceId(int textFocusBackgroudResourceId) {
            this.textFocusBackgroudResourceId = textFocusBackgroudResourceId;
        }

        public void setTextPadding(int left, int top, int right, int bottom) {
            this.textPaddingLeft = left;
            this.textPaddingRight = right;
            this.textPaddingTop = top;
            this.textPaddingBottom = bottom;
        }

        public void setRippleColor(ColorStateList color) {
            if (color == null) return;

            setRippleDrawable(Carbon.createRippleDrawable(color, RippleDrawable.Style.Over, this, true, -1));
        }

        public boolean isFocusState() {
            return isFocusState;
        }

        public void switchState() {
            if (isFocusState) {
                setNormalState();
            } else {
                setFocusState();
            }
        }
    }
}

package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.data.LocalData;
import com.xsl.lerist.llibrarys.activity.LActivity;
import com.xsl.lerist.llibrarys.adapter.LViewPagerAdapter;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.widget.LPageIndicator;
import com.xsl.lerist.llibrarys.widget.LViewPager;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

public class SplashActivity extends LActivity implements View.OnClickListener {

    private static final String SPLASH_IS_SHOW = "splash_is_show";
    private LViewPager viewPager;
    private LViewPagerAdapter pagerAdapter;
    private LocalData localData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        initData();
        initView();
    }

    private void initData() {
        localData = new LocalData(context);
        boolean isShowSplash = localData.getBoolean(SPLASH_IS_SHOW, true);
        if (!isShowSplash) {
            startActivity(StartActivity.class);
            finish();
            return;
        }
    }

    private void initView() {
        LPageIndicator pageIndicator = find(R.id.a_splash_lpi, LPageIndicator.class);
        viewPager = find(R.id.a_splash_lvp, LViewPager.class);

        pagerAdapter = new LViewPagerAdapter(getPagerItems());
        viewPager.setAdapter(pagerAdapter);

        final View btn_start = find(R.id.a_splash_app_intro_btn_setup);
        btn_start.setOnClickListener(this);


        pageIndicator.setFocusResourceIds(R.mipmap.dot_circle_accent);
        pageIndicator.setNormalResourceIds(R.mipmap.dot_circle_white);
        int dp6 = Lerist.dip2px(context, 12);
        int dp4 = Lerist.dip2px(context, 8);
        pageIndicator.setIconSize(dp6, dp6);
        pageIndicator.setInPaddingLeft(dp4);
        pageIndicator.setInPaddingRight(dp4);
        pageIndicator.setTextSize(0);
        pageIndicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == pagerAdapter.getCount() - 1 && btn_start.getVisibility() == View.GONE) {
                    //最末页显示进入按钮
                    btn_start.setAlpha(0);
                    btn_start.setVisibility(View.VISIBLE);
                    btn_start.animate().alpha(1).setDuration(100).start();
                }else{
                    btn_start.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private ArrayList<LViewPagerAdapter.PagerItem> getPagerItems() {
        ArrayList<LViewPagerAdapter.PagerItem> pagerItems = new ArrayList<>();

        int[] guideImgs = new int[]{
                R.mipmap.splash_1,
                R.mipmap.splash_2,
                R.mipmap.splash_3,
                R.mipmap.splash_4
        };

        for (int guideImg : guideImgs) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(guideImg);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            pagerItems.add(new LViewPagerAdapter.PagerItem(null, imageView));
        }
        return pagerItems;
    }

    @Override
    public void onClick(View v) {
        startActivity(MainActivity.class);
        localData.put(SPLASH_IS_SHOW, false);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}

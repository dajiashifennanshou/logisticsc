package com.xsl.distributor.ws.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xsl.distributor.R;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.lerist.llibrarys.adapter.LFragmentPagerAdapter;
import com.xsl.lerist.llibrarys.fragments.LFragment;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.widget.LPageIndicator;

import java.util.ArrayList;
import java.util.List;

import carbon.widget.ViewPager;

/**
 * 首页配送单
 */
public class LogisticsFragment extends LFragment {
    /**
     * 头部导航栏
     */
    LPageIndicator colorIndicator;
    /**
     * viewpager
     */
    ViewPager viewpager;
    private View mView;
    private ArrayList<LFragmentPagerAdapter.PageItem> list;
    private LFragmentPagerAdapter.PageItem pageItem;
    private String[] str;
    private List<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_logistics, null);
        viewpager = (ViewPager) mView.findViewById(R.id.viewpager);
        colorIndicator = (LPageIndicator) mView.findViewById(R.id.colorIndicator);
        colorIndicator.setTextSize(16);
        colorIndicator.setTextColor(getResources().getColor(R.color.font_color_focused), getResources().getColor(R.color.colorAccent2))
                .setTextNormalBackgroudResourceId(R.color.transparent)
                .setTextFocusBackgroudResourceId(R.drawable.bg_underline_color_accent_1dp)
                .setTextPadding(0, Lerist.dip2px(context, 12), 0, Lerist.dip2px(context, 10))
                .setRippleColorId(R.color.ripple);
        init();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle("配送单");

        initData();
    }

    private void initData() {
        LocalData localData = new LocalData(context);
        if (!localData.isLogined()) {
            return;
        }

    }

    private void init() {

        str = new String[]{"进行中", "已完成"};
        mFragments = new ArrayList<>();
        list = new ArrayList<>();
        DeliveryOrderFragment doingFragment = DeliveryOrderFragment.getNewInstance("0,1,2,3,4");
        DeliveryOrderFragment deliveryOrderFragment = DeliveryOrderFragment.getNewInstance("5");
        mFragments.add(doingFragment);
        mFragments.add(deliveryOrderFragment);
        for (int i = 0; i < str.length; i++) {
            pageItem = new LFragmentPagerAdapter.PageItem(str[i], mFragments.get(i));
            list.add(pageItem);
        }
        LFragmentPagerAdapter adapter = new LFragmentPagerAdapter(getFragmentManager(), list);
        viewpager.setAdapter(adapter);
        colorIndicator.setViewPager(viewpager);

    }

    @Override
    public void onRefreshFragment() {
        super.onRefreshFragment();
        if (viewpager == null || mFragments == null) return;

        try {
            ((DeliveryOrderFragment) mFragments.get(viewpager.getCurrentItem())).onRefreshFragment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.MyFragmentAdapter;
import com.wrt.xinsilu.lerist.ui.fragment.CollectionLineFragment;
import com.wrt.xinsilu.lerist.ui.fragment.CollectionLogisticsFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.TextView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 我的收藏
 */
public class MyCollectionActivity extends SwipeBackActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.title_enter)
    TextView titleEnter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.my_order_viewpager)
    ViewPager myOrderViewpager;
    /**
     * 存放tablayout的title
     */
    private List<String> title;
    private String[] str;
    /**
     * 保存的Fragment
     */
    private List<Fragment> list;
    private CollectionLogisticsFragment collectionLogisticsFragment;
    private CollectionLineFragment collectionLineFragment;

    protected void initView() {
        setTitle("我的收藏");
        titleEnter.setVisibility(View.VISIBLE);
        titleEnter.setText("管理");
        list = new ArrayList<>();
        title = new ArrayList<>();
    }

    protected void initValue() {
        str = new String[]{"物流商", "线路"};
        title = Arrays.asList(str);
        collectionLogisticsFragment = new CollectionLogisticsFragment();
        collectionLineFragment = new CollectionLineFragment();
        list.add(collectionLogisticsFragment);
        list.add(collectionLineFragment);
        for (int i = 0; i < title.size(); i++) {
            if (i == 0) {
                tabLayout.addTab(tabLayout.newTab().setText(title.get(i)), true);
            }
            tabLayout.addTab(tabLayout.newTab().setText(title.get(i)));
        }
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list, title);
        myOrderViewpager.setAdapter(adapter);
        myOrderViewpager.addOnPageChangeListener(this);
        //将TabLayout和ViewPager关联起来
        tabLayout.setupWithViewPager(myOrderViewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //给Tabs设置适配器
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        initView();
        initValue();
    }


    @OnClick(R.id.title_enter)
    public void onClick() {
        if (myOrderViewpager.getCurrentItem() == 0) {
            collectionLogisticsFragment.setEditable(!collectionLogisticsFragment.isEditable());
            titleEnter.setText(collectionLogisticsFragment.isEditable() ? "完成" : "管理");
        } else {
            collectionLineFragment.setEditable(!collectionLineFragment.isEditable());
            titleEnter.setText(collectionLineFragment.isEditable() ? "完成" : "管理");
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (myOrderViewpager.getCurrentItem() == 0) {
            titleEnter.setText(collectionLogisticsFragment.isEditable() ? "完成" : "管理");
        } else {
            titleEnter.setText(collectionLineFragment.isEditable() ? "完成" : "管理");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

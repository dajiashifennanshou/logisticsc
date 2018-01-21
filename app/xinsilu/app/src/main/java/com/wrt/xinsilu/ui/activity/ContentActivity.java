package com.wrt.xinsilu.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.MyFragmentAdapter;
import com.wrt.xinsilu.lerist.ui.fragment.CommonReceiverListFragment;
import com.wrt.xinsilu.lerist.ui.fragment.CommonSenderListFragment;
import com.wrt.xinsilu.ui.fragment.DriverFragment;
import com.xsl.lerist.llibrarys.utils.Lerist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ContentActivity extends SwipeBackActivity {

    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    /**
     * 常用联系人，常用收货人，常用司机
     */

    private List<Fragment> list;
    private MyFragmentAdapter adapter;
    private String[] str;
    private List<String> title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        initView();
        initValue();
    }

    protected void initView() {
        setTitle("常用联系人");
        titleEnter.setVisibility(View.VISIBLE);
        titleEnter.setText("");
        Drawable drawable = getResources().getDrawable(R.mipmap.common_add);
        drawable.setBounds(0, 0, Lerist.dip2px(context, 24), Lerist.dip2px(context, 24));
        titleEnter.setCompoundDrawables(drawable, null, null, null);
        str = new String[]{"常用发货人", "常用收货人", "常用司机"};
        title = Arrays.asList(str);
        list = new ArrayList<>();
    }

    protected void initValue() {
        list.add(new CommonSenderListFragment());
        list.add(new CommonReceiverListFragment());
        list.add(new DriverFragment());
        adapter = new MyFragmentAdapter(getSupportFragmentManager(), list, null);
        viewpager.setAdapter(adapter);
        for (int i = 0; i < title.size(); i++) {
            if (i == 0) {
                tabLayout.addTab(tabLayout.newTab().setText(title.get(i)), true);
            }
            tabLayout.addTab(tabLayout.newTab().setText(title.get(i)));
        }
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list, title);
        viewpager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        tabLayout.setupWithViewPager(viewpager);
        //给Tabs设置适配器
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    @OnClick({R.id.title_enter})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.title_enter:
                //根据pager获滑动到哪一个item进行判断进入到哪一个activity
                if (viewpager.getCurrentItem() == 0) {
                    startActivity(new Intent(this, AddSenderActivity.class));
                } else if (viewpager.getCurrentItem() == 1) {
                    startActivity(new Intent(this, AddReceiverActivity.class));
                } else if (viewpager.getCurrentItem() == 2) {
                    startActivity(new Intent(this, AddDriverActivity.class));
                }
                break;
        }
    }
}

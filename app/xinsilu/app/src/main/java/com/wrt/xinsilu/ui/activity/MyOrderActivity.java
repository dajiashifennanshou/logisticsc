package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.MyFragmentAdapter;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.ui.fragment.OrderFragment;
import com.xsl.lerist.llibrarys.widget.LToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 我的订单页面
 */
public class MyOrderActivity extends SwipeBackActivity {

    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.my_order_viewpager)
    ViewPager myOrderViewpager;
    private List<Fragment> list;
    private List<String> title;
    private String[] str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        initView();
        initValue();
    }

    protected void initView() {
        setTitle("我的订单");
        list = new ArrayList<>();
        title = new ArrayList<>();
    }

    protected void initValue() {
        LocalData localData = new LocalData(context);
        if (!localData.isLogined()) {
            startActivity(LoginActivity.class);
            LToast.show(context, "请先登录");
            finish();
            return;
        }

        str = new String[]{"全部", "预约", "已受理", "提货中", "议价", "货物入库", "运输中", "已到达", "送货中", "已签收", "已拒绝", "已作废", "已取消"};
        title = Arrays.asList(str);

        list.add(OrderFragment.newInstance(""));
        list.add(OrderFragment.newInstance("0"));
//        list.add(OrderFragment.newInstance("1"));
//        list.add(OrderFragment.newInstance("2"));
        list.add(OrderFragment.newInstance("3"));
//        list.add(OrderFragment.newInstance("4"));
        list.add(OrderFragment.newInstance("5"));
//        list.add(OrderFragment.newInstance("6"));
//        list.add(OrderFragment.newInstance("7"));
        list.add(OrderFragment.newInstance("8"));
//        list.add(OrderFragment.newInstance("9"));
//        list.add(OrderFragment.newInstance("10"));
//        list.add(OrderFragment.newInstance("11"));
        String[] str2 = new String[]{"全部", "预约", "议价", "运输中", "已签收"};

//        for (int i = 0; i < str2.length; i++) {
//            if (i == 0) {
//                tabLayout.addTab(tabLayout.newTab().setText(str2[i]), true);
//            }
//            tabLayout.addTab(tabLayout.newTab().setText(str2[i]));
//        }
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list, Arrays.asList(str2));
        myOrderViewpager.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来
        tabLayout.setupWithViewPager(myOrderViewpager);
        //给Tabs设置适配器
        tabLayout.setTabsFromPagerAdapter(adapter);

    }

}

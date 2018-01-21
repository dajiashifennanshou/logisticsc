package com.xsl.distributor.lerist.ui.activity;

import android.os.Bundle;

import com.xsl.distributor.R;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.dialog.JoinStateDialog;
import com.xsl.distributor.lerist.ui.fragment.user.GoodsInListFragment;
import com.xsl.distributor.lerist.ui.fragment.user.GoodsOutListFragment;
import com.xsl.lerist.llibrarys.adapter.LFragmentPagerAdapter;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.widget.LPageIndicator;
import com.xsl.lerist.llibrarys.widget.LToast;
import com.xsl.lerist.llibrarys.widget.LViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class MyGoodsActivity extends SwipeBackActivity {

    @BindView(R.id.a_my_goods_lpi)
    LPageIndicator mPageIndicator;
    @BindView(R.id.a_my_goods_lvp)
    LViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goods);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        LocalData localData = new LocalData(context);
        if (!localData.isLogined()) {
            LToast.show(context, "请先登录");
            finish();
            return;
        }

        UserInfo.Join join = localData.readUserInfo().getJoin();

        boolean isNoJoin = JoinStateDialog.show(context, join, true);
    }

    private void initView() {
        setTitle("货物流水");

        GoodsInListFragment goodsInListFragment = new GoodsInListFragment();
        GoodsOutListFragment goodsOutListFragment = new GoodsOutListFragment();
        mViewPager.setAdapter(new LFragmentPagerAdapter(getSupportFragmentManager())
                .addPage("入库", goodsInListFragment)
                .addPage("出库", goodsOutListFragment));

        mPageIndicator.setRippleColorId(R.color.ripple)
                .setTextColorId(R.color.font_color_focused, R.color.colorAccent2)
                .setTextNormalBackgroudResourceId(R.color.transparent)
                .setTextFocusBackgroudResourceId(R.drawable.bg_underline_color_accent_1dp)
                .setTextPadding(0, Lerist.dip2px(context, 12), 0, Lerist.dip2px(context, 10))
                .setTextSize(14)
                .setViewPager(mViewPager);
    }
}

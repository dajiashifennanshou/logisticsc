package com.xsl.distributor.lerist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.squareup.otto.Subscribe;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.GoodsInfo;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.dialog.JoinStateDialog;
import com.xsl.distributor.ws.ui.activity.LoginActivity;
import com.xsl.distributor.ws.ui.activity.MyDoGoodsActivity;
import com.xsl.lerist.llibrarys.utils.BusProvider;
import com.xsl.lerist.llibrarys.utils.ListUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class MyCloudRepositoryActivity extends SwipeBackActivity implements TextWatcher {

    @BindView(R.id.a_my_cloud_repository_tv_money)
    TextView tv_money;
    @BindView(R.id.a_my_cloud_repository_iv_car)
    ImageView iv_car;
    @BindView(R.id.a_my_cloud_repository_tv_count)
    carbon.widget.TextView tv_count;
    @BindView(R.id.a_my_cloud_repository_btn_ok)
    TextView btn_ok;
    @BindView(R.id.a_my_cloud_repository_overlay)
    View v_overlay;
    @BindView(R.id.a_my_cloud_repository_ll_car_detail)
    LinearLayout ll_car_detail;
    private ArrayList<GoodsInfo> mGoodsCar;
    private LocalData localData;
    private boolean isCarShowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cloud_repository);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        localData = new LocalData(this);
        if (!localData.isLogined()) {
            LToast.show(context, "请先登录");
            startActivity(LoginActivity.class);
            finish();
            return;
        }

        UserInfo.Join join = localData.readUserInfo().getJoin();

        boolean isNoJoin = JoinStateDialog.show(context, join, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        if (!localData.isLogined()) {
            LToast.show(context, "请先登录");
            startActivity(LoginActivity.class);
            finish();
        }

        hideCar();
        onOrderNumChanged(new ArrayList<GoodsInfo>());
    }

    private void initView() {
        setTitle("我的云仓");

        tv_count.addTextChangedListener(this);
    }

    private void activeCar() {
        tv_count.setVisibility(View.VISIBLE);
        tv_count.removeTextChangedListener(this);
        tv_count.addTextChangedListener(this);
//        tv_money.setTextColor(getResources().getColor(R.color.colorAccent3));
        iv_car.setImageResource(R.mipmap.my_warehouse_xiadan_cli);
        btn_ok.setBackgroundResource(R.color.colorAccent3);
        btn_ok.setEnabled(true);
    }

    private void inactiveCar() {
        tv_count.removeTextChangedListener(this);
        tv_count.setVisibility(View.GONE);
        tv_count.setText(0 + "");
        tv_money.setText("");
        tv_money.setTextColor(getResources().getColor(R.color.font_color_normal));
        iv_car.setImageResource(R.mipmap.my_warehouse_xiadan_nor);
        btn_ok.setBackgroundResource(R.color.font_color_hint);
        btn_ok.setEnabled(false);
    }

    @Subscribe
    public void onOrderNumChanged(HashMap<GoodsInfo, Integer> goodsCar) {
        if (goodsCar == null) {
            return;
        }

        int sum = 0;
        for (Integer integer : goodsCar.values()) {
            sum += integer;
        }
        tv_count.setText(sum + "");

    }

    @Subscribe
    public void onOrderNumChanged(ArrayList<GoodsInfo> goodsCar) {
        mGoodsCar = goodsCar;
        if (goodsCar == null) {
            return;
        }
        float installCost = 0;

        ll_car_detail.removeAllViews();
        for (GoodsInfo mGoodsInfo : mGoodsCar) {
            installCost += mGoodsInfo.getInstallCost();
            View item_goods = View.inflate(context, R.layout.item_goods_car, null);
            ((TextView) item_goods.findViewById(R.id.i_goods_car_tv_name)).setText(mGoodsInfo.getGoodsName());
            ((TextView) item_goods.findViewById(R.id.i_goods_car_tv_count)).setText("x " + mGoodsInfo.getGoodsNum());
            ll_car_detail.addView(item_goods);
        }
        tv_money.setText(installCost + "");

        tv_count.removeTextChangedListener(this);
        tv_count.addTextChangedListener(this);
        tv_count.setText(goodsCar.size() + "");
    }

    @OnClick({R.id.a_my_cloud_repository_overlay, R.id.a_my_cloud_repository_iv_car, R.id.a_my_cloud_repository_btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a_my_cloud_repository_overlay:
                hideCar();
                break;
            case R.id.a_my_cloud_repository_btn_ok:
                if (ListUtils.isNotEmpty(mGoodsCar)) {
                    Intent intent = new Intent(this, MyDoGoodsActivity.class);
                    intent.putParcelableArrayListExtra(GoodsInfo.class.getSimpleName(), mGoodsCar);
                    startActivity(intent);
                    hideCar();
                    onOrderNumChanged(new ArrayList<GoodsInfo>());
                }
                break;
            case R.id.a_my_cloud_repository_iv_car:
                if (isCarShowing) {
                    hideCar();
                } else {
                    showCar();
                }
                break;

        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s == null || s.length() == 0) {
            inactiveCar();
            return;
        }

        try {
            int num = Integer.parseInt(String.valueOf(s));

            if (num > 0) activeCar();
            else inactiveCar();
        } catch (NumberFormatException e) {

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 显示货物车
     */
    private void showCar() {
        if (ListUtils.isEmpty(mGoodsCar)) {
            return;
        }
        isCarShowing = true;
        ViewGroup carParent = (ViewGroup) iv_car.getParent();
        v_overlay.setVisibility(View.VISIBLE);
        ViewAnimator.animate(carParent)
                .translationY(0, -(carParent.getBottom() - ll_car_detail.getTop()))
                .duration(500)
                .andAnimate(v_overlay)
                .alpha(0, 1)
                .duration(500)
                .andAnimate(ll_car_detail)
                .alpha(0, 1)
                .duration(500)
                .start();
    }

    /**
     * 隐藏货物车
     */
    private void hideCar() {
        isCarShowing = false;
        ViewGroup carParent = (ViewGroup) iv_car.getParent();
        ViewAnimator.animate(carParent)
                .translationY(0)
                .duration(500)
                .andAnimate(v_overlay)
                .alpha(0)
                .duration(500)
                .andAnimate(ll_car_detail)
                .alpha(0)
                .duration(500)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        v_overlay.setVisibility(View.GONE);
                    }
                })
                .start();
    }

    @Override
    public void onBackPressed() {
        if (isCarShowing) {
            hideCar();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }
}

package com.wrt.xinsilu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.MyFragmentAdapter;
import com.wrt.xinsilu.bean.LogisticOrderDetailBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.OrderClient;
import com.wrt.xinsilu.lerist.interfaces.InputKeyValue;
import com.wrt.xinsilu.lerist.ui.activity.OrderPayActivity;
import com.wrt.xinsilu.ui.dialog.NoNetworkDialog;
import com.wrt.xinsilu.ui.fragment.GoodsFirstFragment;
import com.wrt.xinsilu.ui.fragment.GoodsFourthFragment;
import com.wrt.xinsilu.ui.fragment.GoodsSecongFragment;
import com.wrt.xinsilu.ui.fragment.GoodsThirdFragment;
import com.xsl.lerist.llibrarys.activity.LActivity;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LPageIndicator;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;
import com.xsl.lerist.llibrarys.widget.LViewPager;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.ImageView;

/**
 * 下单界面
 */
public class GoodsInformationActivity extends LActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.goods_ViewPager)
    LViewPager goodsViewPager;
    @BindView(R.id.pager_indicator)
    LPageIndicator pagerIndicator;
    @BindView(R.id.btn_back)
    ImageView backImg;
    @BindView(R.id.title_back)
    TextView lastTx;
    /**
     * 用于保存四个滑动的fragment
     */
    private List<Fragment> list;
    private LocalData localData;
    private OrderClient orderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_information);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        localData = new LocalData(context);
        orderClient = new OrderClient();
        if (localData.isLogined() == false) {
            LToast.show(context, "请先登录");
            startActivity(LoginActivity.class);
            finish();
            return;
        }
    }

    protected void initView() {
        setTitle("发 货");
        list = new ArrayList<>();
        /**以下为保存几个fragment*/
        list.add(new GoodsFirstFragment());
        list.add(new GoodsSecongFragment());
        GoodsThirdFragment goodsThirdFragment = new GoodsThirdFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sendNet", getIntent().getStringExtra("sendNet"));
        bundle.putString("ainpickNet", getIntent().getStringExtra("ainpickNet"));
        goodsThirdFragment.setArguments(bundle);
        list.add(goodsThirdFragment);
        GoodsFourthFragment goodsFourthFragment = new GoodsFourthFragment();
        goodsFourthFragment.setSubmitListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        list.add(goodsFourthFragment);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list, null);
        goodsViewPager.setAdapter(adapter);
        goodsViewPager.setOnPageChangeListener(this);
        pagerIndicator.setFocusResourceIds(R.drawable.background_shap_stroke_ovel)
                .setNormalResourceIds(R.drawable.background_shap_ovel)
                .setIconSize(Lerist.dip2px(context, 8), Lerist.dip2px(context, 8))
                .setInMargin(Lerist.dip2px(context, 10), 0, Lerist.dip2px(context, 10), 0)
                .setTextSize(0)
                .setViewPager(goodsViewPager);

        titleEnter.setVisibility(View.VISIBLE);
        titleEnter.setText("下一步");
        lastTx.setText("上一步");
        titleEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleEnter.getText().toString().equals("提交")) {
                    submit();
                } else {
                    goodsViewPager.nextPage();
                }
            }
        });
        lastTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsViewPager.previousPage();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        titleEnter.setText("下一步");
        lastTx.setText("上一步");
        switch (position) {
            case 0:
                setTitle("填写收发货人");
                lastTx.setVisibility(View.GONE);
                backImg.setVisibility(View.VISIBLE);
                break;
            case 1:
                setTitle("货物信息");
                lastTx.setVisibility(View.VISIBLE);
                backImg.setVisibility(View.GONE);
                break;
            case 2:
                setTitle("配送信息");
                lastTx.setVisibility(View.VISIBLE);
                backImg.setVisibility(View.GONE);
                break;
            case 3:
                setTitle("填写增值服务");
                lastTx.setVisibility(View.VISIBLE);
                backImg.setVisibility(View.GONE);
                titleEnter.setText("提交");
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 提交
     */
    private void submit() {
        HashMap<String, Object> hashMap = new HashMap<>();
        int tms_line_id = getIntent().getIntExtra("tms_line_id", 0);
        hashMap.put("tms_line_id", tms_line_id);
        for (Fragment fragment : list) {
            HashMap<String, Object> inputData = ((InputKeyValue) fragment).getInputData();
            if (inputData == null) {
                return;
            }
            hashMap.putAll(inputData);
        }
        UserInfo userInfo = localData.readUserInfo();
        if (userInfo == null) {
            return;
        }

        LProgressDialog.show(context, "");
        orderClient.add(userInfo.getUser().getId(), hashMap, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        LToast.show(context, "提交成功");
                        LogisticOrderDetailBean logisticOrderDetailBean = JSON.parseObject(resultInfo.getData(), LogisticOrderDetailBean.class);
                        if (logisticOrderDetailBean.getTake_cargo_cost() != 0) {
                            //预约费存在的时候跳转到支付
                            Intent intent = new Intent(context, OrderPayActivity.class);
                            intent.putExtra(LogisticOrderDetailBean.class.getSimpleName(), logisticOrderDetailBean);
                            startActivity(intent);
                        }
                        finish();
                        break;
                    case ResultCode.FAILURE:
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "提交失败" : resultInfo.getMsg());
                        break;
                    case ResultCode.NOPERMISSION:

                        break;

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                NoNetworkDialog.show(context);
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
                LProgressDialog.dismiss();
            }
        });
    }
}

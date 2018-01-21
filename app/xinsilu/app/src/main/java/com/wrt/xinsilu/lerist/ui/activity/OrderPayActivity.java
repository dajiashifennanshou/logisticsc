package com.wrt.xinsilu.lerist.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.LogisticOrderDetailBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.OrderClient;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class OrderPayActivity extends SwipeBackActivity {

    @BindView(R.id.a_orderpay_tv_ordernum)
    TextView tv_ordernum;
    @BindView(R.id.a_orderpay_tv_take_cost)
    TextView tv_take_cost;
    private LogisticOrderDetailBean myOrderBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pay);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        setTitle("支付预约费");
        //        getIntent().getDoubleExtra("")

        myOrderBean = (LogisticOrderDetailBean) getIntent().getSerializableExtra(LogisticOrderDetailBean.class.getSimpleName());
        if (myOrderBean == null) {
            LToast.show(context, "支付订单失败, 请稍后再试");
            finish();
            return;
        }

        tv_ordernum.setText(myOrderBean.getOrder_number());
        tv_take_cost.setText(String.format("%.2f元", myOrderBean.getTake_cargo_cost()));

    }


    @OnClick(R.id.a_orderpay_btn_do_pay)
    public void onClick() {
        pay();
    }

    private void pay() {
        UserInfo userInfo = new LocalData(context).readUserInfo();
        if (userInfo == null || myOrderBean == null) return;

        LProgressDialog.show(context, "");
        new OrderClient().orderPay(userInfo.getUser().getId(), 1,
                myOrderBean.getTake_cargo_cost(),
                myOrderBean.getOrder_number(),
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (result == null) {
                            return;
                        }
                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                String payUri = resultInfo.getData() + "";
                                if (payUri == null) {
                                } else {
                                    Intent intent = new Intent(context, PayActivity.class);
                                    intent.putExtra("uri", payUri);
                                    startActivity(intent);
                                }
                                break;
                            case ResultCode.FAILURE:
                                LToast.show(context, resultInfo.getMsg() == null ? "支付失败, 请稍后再试" : resultInfo.getMsg());
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ex.printStackTrace();
                        LToast.show(context, "支付失败, 请稍后再试");
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

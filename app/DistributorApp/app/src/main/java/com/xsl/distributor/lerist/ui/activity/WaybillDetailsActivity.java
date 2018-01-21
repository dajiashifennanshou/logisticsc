package com.xsl.distributor.lerist.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.DeliveryOrderClient;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.model.GoodsInfo;
import com.xsl.distributor.lerist.model.OrderInfo;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.dialog.LTextPreviewDialog;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LRecyclerView;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class WaybillDetailsActivity extends SwipeBackActivity {

    @BindView(R.id.a_waybill_details_order_tv_no)
    TextView order_tv_no;
    @BindView(R.id.a_waybill_details_order_tv_state)
    TextView order_tv_state;
    @BindView(R.id.a_waybill_details_consignee_tv_name)
    TextView consignee_tv_name;
    @BindView(R.id.a_waybill_details_consignee_tv_phone)
    TextView consignee_tv_phone;
    @BindView(R.id.a_waybill_details_consignee_tv_addr)
    TextView consignee_tv_addr;
    @BindView(R.id.a_waybill_details_goods_lrv)
    LRecyclerView lrv_goods;
    @BindView(R.id.a_waybill_details_money_tv_install)
    TextView money_tv_install;
    @BindView(R.id.a_waybill_details_money_tv_transmit)
    TextView money_tv_transmit;
    private LRecyclerViewAdapter<GoodsInfo> goodsListRecyclerViewAdapter;
    private DeliveryOrderClient deliveryOrderClient;
    private String deliveryNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waybill_details);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData() {
        deliveryOrderClient = new DeliveryOrderClient();
        deliveryNo = getIntent().getStringExtra("deliveryNo");
    }

    private void initView() {
        setTitle("运单详情");

        goodsListRecyclerViewAdapter = new LRecyclerViewAdapter<GoodsInfo>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_list_waybill_details_goods;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, GoodsInfo itemData) {
                viewHolder.setText(R.id.i_list_waybill_details_goods_tv_name, itemData.getGoodsName());
                viewHolder.setText(R.id.i_list_waybill_details_goods_tv_count, itemData.getGoodsNum() + " 件/套");
            }
        };
        lrv_goods.setLayoutManager(new LinearLayoutManager(context));
        lrv_goods.setAdapter(goodsListRecyclerViewAdapter);

        requestData();
    }

    private void requestData() {
        if (StringUtils.isEmpty(deliveryNo)) return;

        deliveryOrderClient.getDetail(deliveryNo, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        OrderInfo orderInfo = JSON.parseObject(resultInfo.getData(), OrderInfo.class);
                        bindingData(orderInfo);
                        break;
                    case ResultCode.FAILURE:

                        break;
                    case ResultCode.NOPERMISSION:
                        UserClient.login(context);
                        break; }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void bindingData(OrderInfo orderInfo) {
        if (orderInfo == null) return;

        List<GoodsInfo> goodsInfo = orderInfo.getGoodsInfo();
        goodsListRecyclerViewAdapter.removeDataAll();
        goodsListRecyclerViewAdapter.addDatas(goodsInfo);
        try {
            order_tv_no.setText(orderInfo.getDeliveryNo());
            String orderStatus = "";
            switch (orderInfo.getOrderStatus()) {
                case 0:
                    //待分配
                    orderStatus = "待分配";
                    break;
                case 1:
                    //已配载
                    orderStatus = "已配载";
                    break;
                case 2:
                    //在途
                    orderStatus = "在途";
                    break;
                case 3:
                    //已配送
                    orderStatus = "已配送";
                    break;
                case 4:
                    //代配送
                    orderStatus = "待支付";
                    break;
                case 5:
                    //已完成
                    orderStatus = "已完成";
                    break;
            }
            order_tv_state.setText(orderStatus);

            consignee_tv_name.setText(orderInfo.getConsigneeName());
            consignee_tv_phone.setText(orderInfo.getConsigneePhone());
            consignee_tv_addr.setText(orderInfo.getConsigneeAddr());

            money_tv_install.setText(orderInfo.getInstallCost() + " 元");
            money_tv_transmit.setText(orderInfo.getDeliveryCost() + " 元");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<GoodsInfo> getTestDatas() {
        ArrayList<GoodsInfo> goodsInInfos = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            goodsInInfos.add(new GoodsInfo());
        }
        return goodsInInfos;
    }

    @OnClick({R.id.a_waybill_details_order_tv_no, R.id.a_waybill_details_consignee_tv_addr})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a_waybill_details_order_tv_no:
            case R.id.a_waybill_details_consignee_tv_addr:
                LTextPreviewDialog.with(getSupportFragmentManager())
                        .setText(((TextView)view).getText().toString(),20)
                        .show();
                break;
        }
    }
}

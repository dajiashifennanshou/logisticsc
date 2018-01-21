package com.wrt.xinsilu.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.LogisticOrderDetailBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.OrderClient;
import com.wrt.xinsilu.lerist.client.UserClient;
import com.wrt.xinsilu.lerist.ui.activity.PayActivity;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LRecyclerView;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 物流商详情
 */
public class OrderDetailActivity extends SwipeBackActivity {

    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.order_name)
    TextView orderName;
    @BindView(R.id.order_status)
    TextView orderStatu;
    @BindView(R.id.sender_name)
    TextView senderName;
    @BindView(R.id.send_order_status)
    TextView sendorderStatue;
    @BindView(R.id.receive_order_status)
    TextView receiveorderStatue;
    @BindView(R.id.sender_address)
    TextView senderAddress;
    @BindView(R.id.sender_net)
    TextView senderNet;
    @BindView(R.id.receiver_name)
    TextView receiverName;
    @BindView(R.id.receiver_address)
    TextView receiverAddress;
    @BindView(R.id.receiver_net)
    TextView receiverNet;
    @BindView(R.id.drever_name)
    TextView dreverName;
    @BindView(R.id.licences)
    TextView licences;
    @BindView(R.id.goods_info_listview)
    RecyclerView goodsInfoListView;
    @BindView(R.id.goods_money_listiew)
    RecyclerView goodsMoneyListiew;
    @BindView(R.id.pay_order)
    TextView btn_payOrder;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.money_detial)
    TextView moneyDetial;
    @BindView(R.id.appoint_money)
    TextView appointMoney;
    @BindView(R.id.logistic_icon)
    android.widget.ImageView logisticIcon;
    @BindView(R.id.tv_Is_insurance)
    TextView tvIsInsurance;
    @BindView(R.id.tv_insurance_money)
    TextView tvInsuranceMoney;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    /**
     * 订单号
     */
    private String order_number;
    private LocalData data;
    private OrderClient orderClient;
    /**
     * 设置状态
     */
    private String status;
    private LogisticOrderDetailBean logisticOrderDetailBean;
    private int payType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic);
        ButterKnife.bind(this);
        setTitle("订单详情");
        order_number = getIntent().getStringExtra("order_number");
        orderName.setText(getIntent().getStringExtra("CompanyName"));
        data = new LocalData(context);
        goodsInfoListView.addItemDecoration(new LRecyclerView.RecycleViewDivider(context, LinearLayoutManager.VERTICAL, Lerist.dip2px(context, 20), Color.argb(0, 0, 0, 0)));
//        goodsMoneyListiew.addItemDecoration(new LRecyclerView.RecycleViewDivider(context, LinearLayoutManager.VERTICAL, Lerist.dip2px(context, 20), Color.argb(0, 0, 0, 0)));
        goodsInfoListView.setNestedScrollingEnabled(false);
        goodsMoneyListiew.setNestedScrollingEnabled(false);

        orderClient = new OrderClient();

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }

    private void requestData() {
        logisticOrderDetailBean = null;
        LProgressDialog.show(context, "");
        orderClient.getDetail(order_number, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        logisticOrderDetailBean = JSON.parseObject(resultInfo.getData(), LogisticOrderDetailBean.class);
                        readOrderDetail(logisticOrderDetailBean);
                        break;
                    case ResultCode.FAILURE:
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "" : resultInfo.getMsg());
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
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

    @OnClick({R.id.pay_order, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.pay_order:
                pay();
                break;
            case R.id.commit:
                switch (commit.getText().toString()) {
                    case "支付":
                        pay();
                        break;
                    case "评价":
                        startActivity(new Intent(context, AppraiseActivity.class).putExtra("orderId", logisticOrderDetailBean.getOrder_number()));
                        break;
                    case "取消订单":
                        new AlertDialog.Builder(context)
                                .setTitle("提示")
                                .setMessage("确定要取消此订单吗?")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        cancelOrder();
                                    }
                                })
                                .setNegativeButton("不", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .show();
                        break;
                }
                break;
        }
    }

    /**
     * 取消订单
     */
    private void cancelOrder() {
        if (logisticOrderDetailBean == null) return;

        LProgressDialog.show(context, "");
        orderClient.cancel(logisticOrderDetailBean.getOrder_number(), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        LToast.show(context, "订单已取消");
                        requestData();
                        break;
                    case ResultCode.FAILURE:
                        LToast.show(context, resultInfo.getMsg() == null ? "订单取消失败, 请稍后再试" : resultInfo.getMsg());
                        break;
                    case ResultCode.NOPERMISSION:
                        UserClient.login(context);
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
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

    private void pay() {
        UserInfo userInfo = data.readUserInfo();
        if (userInfo == null || logisticOrderDetailBean == null) {
            return;
        }
        double money = payType == 1 ? logisticOrderDetailBean.getTake_cargo_cost() : (logisticOrderDetailBean.getTotal_cost() - logisticOrderDetailBean.getTake_cargo_cost());
        if (money <= 0) return;

        LProgressDialog.show(context, "");
        orderClient.orderPay(userInfo.getUser().getId(),
                payType,
                money,
                logisticOrderDetailBean.getOrder_number(), new Callback.CommonCallback<String>() {
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

    private void readOrderDetail(final LogisticOrderDetailBean bean) {
        if (bean == null) return;

        setStatus(bean.getState());
        orderStatu.setText(status);
        receiverName.setText(bean.getConsignee_name());
        receiverAddress.setText(bean.getConsignee_province() + bean.getConsignee_city() + bean.getConsignee_county() + bean.getConsignee_address());
        sendorderStatue.setText(bean.getSend_cargo_type() == 0 ? "上门取货" : "送至网点");
        receiverNet.setText(bean.getEndLineAddr());
        senderName.setText(bean.getConsignor_name());
        receiveorderStatue.setText(bean.getReceive_cargo_type() == 0 ? "送货上门" : "客户自提");
        senderAddress.setText(bean.getConsignor_province() + bean.getConsignor_city() + bean.getConsignor_county() + bean.getConsignor_address());
        senderNet.setText(bean.getStartLineAddr());
        dreverName.setText(bean.getDriver_name());
        licences.setText(bean.getVehicle_number());
        tvIsInsurance.setText(bean.getIs_insurance() == 0 ? "是" : "否");
        tvInsuranceMoney.setText(String.format("%.2f元", bean.getInsurance_money()));
        tvPayType.setText(bean.getPay_type() == 0 ? "在线支付" : "线下支付");
        if(bean.getState() < 3){
            ((View) moneyDetial.getParent()).setVisibility(View.GONE);
            ((View) appointMoney.getParent()).setVisibility(View.VISIBLE);
        }else{
            ((View) moneyDetial.getParent()).setVisibility(View.VISIBLE);
            ((View) appointMoney.getParent()).setVisibility(View.GONE);
        }
        moneyDetial.setText(bean.getTotal_cost() - bean.getTake_cargo_cost() + "元");
        appointMoney.setText(bean.getTake_cargo_cost() + "元");
        getOrderDetail(bean.getCargoLst());
        getOrderDetailFooter(bean.getCargoLst());

        String[] orderStatus = new String[]{"全部", "预约", "已受理", "提货中", "议价", "货物入库", "运输中", "已到达", "送货中", "已签收", "已拒绝", "已作废", "已取消"};

        btn_payOrder.setVisibility(View.GONE);
        ((View) commit.getParent()).setVisibility(View.GONE);
        switch (bean.getState()) {
            case 8:
                if (bean.getIs_evaluation() == 1) {
                    //未评价
                    commit.setText("评价");
                    ((View) commit.getParent()).setVisibility(View.VISIBLE);
                }
                break;
            case 3:
                if (bean.getPay_type() == 0) { //0为在线支付
                    if(bean.getPayment().getState() == 1) { //当议价的时候 1 为未支付
                    //在线支付
                        payType = 0;
                        commit.setVisibility(View.VISIBLE);
                        commit.setText("支付");
                        ((View) commit.getParent()).setVisibility(View.VISIBLE);
                    }else{
                        //线下支付
                        ((View) commit.getParent()).setVisibility(View.GONE);
                    }
                }else{
                    //线下支付
                    commit.setVisibility(View.INVISIBLE);
                    ((View) commit.getParent()).setVisibility(View.GONE);
                }



/*

                if (bean.getIs_payment() == 1) { //未支付 1

                    if(bean.getPayment().getState() == 1){ //当议价的时候 1 为未支付
                        if (bean.getPay_type() == 0) { //0为在线支付
                            //在线支付
                            payType = 0;
                            btn_payOrder.setVisibility(View.VISIBLE);
//                        commit.setText("支付");
//                        ((View) commit.getParent()).setVisibility(View.VISIBLE);
                        } else {
                            //线下支付
                            commit.setText("支付");
                            ((View) commit.getParent()).setVisibility(View.VISIBLE);
                        }
                    }else{//已支付
                        ((View) commit.getParent()).setVisibility(View.INVISIBLE);
                    }

                } else {
                    //已支付
                }*/
                break;
            case 0://预约
                commit.setText("取消订单");
                ((View) commit.getParent()).setVisibility(View.VISIBLE);
                if (bean.getIs_payment() == 1) {
                    //未支付
                    if (bean.getPay_type() == 0) {
                        //在线支付
                        if (bean.getSend_cargo_type() == 1) {
                            //上门取货 支付预约费
                            if (bean.getTake_cargo_cost() > 0) {
                                payType = 1;
                                btn_payOrder.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }else{
                    ((View) commit.getParent()).setVisibility(View.GONE);
                }

                break;
        }
    }

    private void getOrderDetail(final List<LogisticOrderDetailBean.CargoLstBean> been) {
        LRecyclerViewAdapter<LogisticOrderDetailBean.CargoLstBean> adapter = new LRecyclerViewAdapter<LogisticOrderDetailBean.CargoLstBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.goods_info_layout;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, LogisticOrderDetailBean.CargoLstBean itemData) throws Exception {
                viewHolder.setText(R.id.name, itemData.getName());
                viewHolder.setText(R.id.pinpai, itemData.getBrand());
                viewHolder.setText(R.id.style, itemData.getModel());
                viewHolder.setText(R.id.pice, itemData.getNumber() + "");
                viewHolder.setText(R.id.set, itemData.getSet_number() + "");
                viewHolder.setText(R.id.weight, itemData.getSingle_weight() + "t");
                viewHolder.setText(R.id.volume, itemData.getSingle_volume() + "m³");
                viewHolder.setText(R.id.info, itemData.getPackage_name() == null ? "--" : itemData.getPackage_name());
            }
        };
        goodsInfoListView.setLayoutManager(new LRecyclerView.WrapContentLinearLayoutManager(context));
        goodsInfoListView.setAdapter(adapter);
        adapter.addDatas(been);
    }

    private void getOrderDetailFooter(final List<LogisticOrderDetailBean.CargoLstBean> been) {
        LRecyclerViewAdapter<LogisticOrderDetailBean.CargoLstBean> adapter = new LRecyclerViewAdapter<LogisticOrderDetailBean.CargoLstBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.order_detail_footer_layout;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, LogisticOrderDetailBean.CargoLstBean itemData) throws Exception {
                viewHolder.setText(R.id.order_detail_footer_name, itemData.getName() + " (" + (itemData.getPackage_name() == null ? "--" : itemData.getPackage_name()) + ")");
                viewHolder.setText(R.id.order_detail_footer_money, "运费：  " + itemData.getPrice() + "元");

            }
        };
        adapter.addDatas(been);
        goodsMoneyListiew.setLayoutManager(new LRecyclerView.WrapContentLinearLayoutManager(context));
        goodsMoneyListiew.setAdapter(adapter);
    }

    private void setStatus(int statu) {
        switch (statu) {
            case 0:
                status = "预约";
                break;
            case 1:
                status = "已受理";
                break;
            case 2:
                status = "提货中";
                break;
            case 3:
                status = "议价";
                break;
            case 4:
                status = "货物入库";
                break;
            case 5:
                status = "运输中";
                break;
            case 6:
                status = "已到达";
                break;
            case 7:
                status = "送货中";
                break;
            case 8:
                status = "已签收";
                break;
            case 9:
                status = "已拒绝";
                break;
            case 10:
                status = "已作废";
                break;
            case 11:
                status = "已取消";
                break;
        }
    }
}

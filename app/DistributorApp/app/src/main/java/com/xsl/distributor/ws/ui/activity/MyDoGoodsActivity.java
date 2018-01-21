package com.xsl.distributor.ws.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.DeliveryOrderClient;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.CustomerInfo;
import com.xsl.distributor.lerist.model.GoodsInfo;
import com.xsl.distributor.lerist.model.PayUriInfo;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.ui.activity.MyCloudRepositoryActivity;
import com.xsl.distributor.lerist.ui.activity.MyCustomerActivity;
import com.xsl.distributor.lerist.ui.activity.PayActivity;
import com.xsl.distributor.lerist.ui.dialog.NoNetworkDialog;
import com.xsl.distributor.ws.bean.KindOfPriceBean;
import com.xsl.distributor.ws.params.MyOrderInstallAndSendPriceParams;
import com.xsl.distributor.ws.ui.view.CustomPopupWindow;
import com.xsl.lerist.llibrarys.activity.LActivityManager;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.model.Contacts;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.LContactsChooser;
import com.xsl.lerist.llibrarys.utils.LResultActivity;
import com.xsl.lerist.llibrarys.utils.ListUtils;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LRecyclerView;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class MyDoGoodsActivity extends SwipeBackActivity implements View.OnClickListener {
    @BindView(R.id.layout)
    LinearLayout layout;
    /**
     * 发货人
     */
    @BindView(R.id.name)
    EditText name;
    /**
     * 点击进入导入联系人
     */
    @BindView(R.id.name_button)
    TextView nameButton;
    /**
     * 电话号码
     */
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    /**
     * 点击进入电话号码选择
     */
    @BindView(R.id.number_button)
    ImageView numberButton;
    /**
     * 地址显示
     */
    @BindView(R.id.sender_address)
    TextView senderAddress;
    /**
     * 点击选择地址
     */
    @BindView(R.id.sender_address_layout)
    LinearLayout senderAddressLayout;
    /**
     * 地址详情
     */
    @BindView(R.id.address_detail)
    EditText addressDetail;
    /**
     * 代收款项
     */
    @BindView(R.id.monney)
    EditText monney;
    /**
     * 我的货物listview
     */
    @BindView(R.id.my_goods)
    LRecyclerView myGoods;
    /**
     * 安装费
     */
    @BindView(R.id.anzhuang_money)
    TextView anzhuangMoney;
    /**
     * 配送费
     */

    @BindView(R.id.pay)
    carbon.widget.TextView pay;
    @BindView(R.id.a_my_goods_order_cb_collection)
    CheckBox cb_collection;
    @BindView(R.id.send_money)
    TextView sendMoney;

    private MyOrderInstallAndSendPriceParams params;

    private String type;
    private String branchNo;
    /**
     * 地址选择
     */
    private CustomPopupWindow popupWindow;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            senderAddress.setText(str);
        }
    };
    private ArrayList<GoodsInfo> mGoodsInfos;
    private LocalData localData;
    private DeliveryOrderClient deliveryOrderClient;
    private Callback.Cancelable cancelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goods_order);
        ButterKnife.bind(this);

        initData();
        initView();
        setOnCheckListener();
    }

    private void setOnCheckListener() {
        cb_collection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    monney.setVisibility(View.INVISIBLE);
                    monney.setText("");
                } else {
                    monney.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void initData() {
        localData = new LocalData(this);
        deliveryOrderClient = new DeliveryOrderClient();
        mGoodsInfos = getIntent().getParcelableArrayListExtra(GoodsInfo.class.getSimpleName());
        params = new MyOrderInstallAndSendPriceParams();
        branchNo = localData.readUserInfo().getJoin().getBranchNo();

        StringBuilder builder = new StringBuilder();
        for (GoodsInfo goodsinfo : mGoodsInfos) {
            builder.append(goodsinfo.getGoodsType() + ",");
            KLog.i(goodsinfo.getGoodsType());
        }
        KLog.i(builder.substring(0, builder.lastIndexOf(",")));
        KLog.i("-------------------------------------");
        requestKindsOfPrice(builder.substring(0, builder.lastIndexOf(",")), branchNo);
    }

    private void initView() {
        setTitle("确认订单");

        LRecyclerViewAdapter<GoodsInfo> recyclerViewAdapter = new LRecyclerViewAdapter<GoodsInfo>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_list_goods_order;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, GoodsInfo itemData) throws Exception {
                viewHolder.setText(R.id.i_list_goods_order_tv_name, itemData.getGoodsName());
                viewHolder.setText(R.id.i_list_goods_order_tv_count, itemData.getGoodsNum() + "");
            }
        };

        myGoods.setLayoutManager(new LinearLayoutManager(context));
        myGoods.setAdapter(recyclerViewAdapter);

        if (ListUtils.isNotEmpty(mGoodsInfos))
            recyclerViewAdapter.addDatas(mGoodsInfos);

        float installCost = 0;
        for (GoodsInfo mGoodsInfo : mGoodsInfos) {
            installCost += mGoodsInfo.getInstallCost();
        }
        anzhuangMoney.setText(installCost + "元");
        sendMoney.setText("0.0元");
    }

    private synchronized void pay() {
        if (cancelable != null) return;
        if (ListUtils.isEmpty(mGoodsInfos) || !localData.isLogined()) {
            return;
        }

        String consigneeName = name.getText().toString();
        String consigneePhone = phoneNumber.getText().toString();
        String addr = senderAddress.getText().toString();
        String addrDetail = addressDetail.getText().toString();

        if (StringUtils.isEmpty(consigneeName)) {
            LToast.show(context, "请输入收货人");
            return;
        }

        if (StringUtils.isEmpty(consigneePhone)) {
            LToast.show(context, "请输入收货人手机号");
            return;
        }

        if (StringUtils.isEmpty(addr) || StringUtils.isEmpty(addrDetail)) {
            LToast.show(context, "请完善收货地址");
            return;
        }

        final String consigneeAddr = addr + addrDetail;

        String goodIds = "";
        String goodsNames = "";
        float installCost = 0;
        float agentPaidCost = 0;
        try {
            agentPaidCost = cb_collection.isChecked() ? Float.parseFloat(monney.getText().toString()) : 0;
        } catch (NumberFormatException e) {
        }

        for (GoodsInfo mGoodsInfo : mGoodsInfos) {
            installCost += mGoodsInfo.getInstallCost();
            goodIds += mGoodsInfo.getId() + "_" + mGoodsInfo.getWayBillNo() + ((mGoodsInfos.indexOf(mGoodsInfo) == mGoodsInfos.size() - 1) ? "" : ",");
            goodsNames += mGoodsInfo.getGoodsName() + ((mGoodsInfos.indexOf(mGoodsInfo) == mGoodsInfos.size() - 1) ? "" : ",");
        }
        KLog.i(goodsNames);
        if (StringUtils.isEmpty(goodIds)) {
            return;
        }

        LProgressDialog.show(context, "");
        pay.setEnabled(false);
        cancelable = deliveryOrderClient.add(localData.readUserInfo().getUser().getId(),
                goodIds, goodsNames, localData.readUserInfo().getJoin().getBranchNo(), installCost, agentPaidCost,
                consigneeName, consigneePhone, consigneeAddr, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                PayUriInfo payUriInfo = JSON.parseObject(resultInfo.getData(), PayUriInfo.class);
                                if (payUriInfo == null) {
//                                    LToast.show(context, "订单提交失败, 请稍后再试");
                                    LToast.show(context, "订单提交成功");
                                    LActivityManager.getActivity(MyCloudRepositoryActivity.class).finish();
                                    finish();
                                } else {
                                    LToast.show(context, "下单成功, 正在跳转支付...");
                                    Intent intent = new Intent(context, PayActivity.class);
                                    intent.putExtra("uri", payUriInfo.toString());
                                    LActivityManager.getActivity(MyCloudRepositoryActivity.class).finish();
                                    startActivity(intent);
                                    finish();
                                }
                                break;
                            case ResultCode.FAILURE:
                                LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "订单提交失败, 请稍后再试" : resultInfo.getMsg());
                                break;
                            case ResultCode.NOPERMISSION:
                                UserClient.login(context);
                                break;  }
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
                        pay.setEnabled(true);
                        cancelable = null;
                    }
                });
    }

    @OnClick({R.id.name_button, R.id.number_button, R.id.sender_address_layout, R.id.pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.name_button:
                Intent intent = new Intent(context, MyCustomerActivity.class);
                intent.putExtra("select", true);
                LResultActivity.startActivityForResult(context, intent, new LResultActivity.Callback() {
                    @Override
                    public void onSuccess(Intent result) {
                        CustomerInfo customerInfo = (CustomerInfo) result.getSerializableExtra(CustomerInfo.class.getSimpleName());
                        if (customerInfo != null) {
                            name.setText(customerInfo.getClientName());
                            phoneNumber.setText(customerInfo.getPhone());
                            String address = customerInfo.getAddress();
                            if (StringUtils.isNotEmpty(address)) {
                                int indexOf = address.indexOf(address.contains("区") ? "区" : "县");
                                try {
                                    senderAddress.setText(address.substring(0, indexOf + 1));
                                    addressDetail.setText(address.substring(indexOf + 1));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                break;
            case R.id.number_button:
                LContactsChooser.openContacts(context, new LContactsChooser.Callbak() {
                    @Override
                    public void onSuccess(ArrayList<Contacts> selectedContactses) {
                        if (ListUtils.isNotEmpty(selectedContactses)) {
                            Contacts contacts = selectedContactses.get(0);
                            ArrayList<String> phones = contacts.getPhones();
                            String phone = phones.get(phones.size() - 1);
                            phoneNumber.setText(phone);
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                break;
            case R.id.sender_address_layout:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
                if (isOpen) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                }
                popupWindow = new CustomPopupWindow(this, handler);
                popupWindow.showPopupWindow(layout, true);
                break;
            case R.id.pay:
                pay();
                break;
        }
    }


    private void requestKindsOfPrice(String type, final String branchNo) {
        params.getKindsOfMoneyDetail(type, branchNo, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        KindOfPriceBean bean = JSON.parseObject(resultInfo.getData(), KindOfPriceBean.class);
                        anzhuangMoney.setText(String.valueOf(bean.getInstallCost()) + "元");
                        sendMoney.setText(String.valueOf(bean.getDeliveryCost()) + "元");

                }
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
}

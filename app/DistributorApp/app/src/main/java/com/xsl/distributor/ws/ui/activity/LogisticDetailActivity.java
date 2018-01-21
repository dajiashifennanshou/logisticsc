package com.xsl.distributor.ws.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.ws.adapter.GoodsDtailAdapter;
import com.xsl.distributor.ws.adapter.goodsPriceAdapter;
import com.xsl.distributor.ws.bean.LogisticDetailBean;
import com.xsl.distributor.ws.params.LogisticDtailParams;
import com.xsl.distributor.ws.ui.view.MyListView;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/***
 * 运单详情
 */
public class LogisticDetailActivity extends SwipeBackActivity {
    /**
     * 商家名
     */
    @BindView(R.id.order_name)
    TextView orderName;
    /**
     * 订单状态
     */
    @BindView(R.id.order_status)
    TextView orderStatus;
    /**
     * 公司图标
     */
    @BindView(R.id.company_icon)
    SimpleDraweeView companyIcon;
    /**
     * 发货人姓名
     */
    @BindView(R.id.sender_name)
    TextView senderName;
    /**
     * 发货状态，自送网店，客户自提
     */
    @BindView(R.id.sender_order_status)
    TextView sendOrderStatus;
    /**
     * 发货地址
     */
    @BindView(R.id.sender_address)
    TextView senderAddress;
    /**
     * 收货人姓名
     */
    @BindView(R.id.receiver_name)
    TextView receiverName;
    /**
     * 收货状态，客户自提等
     */
    @BindView(R.id.receive_order_status)
    TextView receiveOrderStatus;
    /**
     * 收货人地址
     */
    @BindView(R.id.receiver_address)
    TextView receiverAddress;
    /**
     * 配送司机
     */
    @BindView(R.id.drever_name)
    TextView dreverName;
    /**
     * 驾照
     */
    @BindView(R.id.licences)
    TextView licences;
    /**
     * 回单
     */
    @BindView(R.id.receipt)
    TextView receipt;
    /**
     * 等待发货
     */
    @BindView(R.id.is_going_send)
    TextView isGoingSend;
    /**
     * 代收货款
     */
    @BindView(R.id.daishou_money)
    TextView daishouMoney;
    /**
     * 装货
     */
    @BindView(R.id.is_shipments)
    TextView isShipments;
    /**
     * 卸货
     */
    @BindView(R.id.is_unloading)
    TextView isUnloading;
    /**
     * 综合服务信息
     */
    @BindView(R.id.info_service)
    TextView infoService;
    /**
     * 专线投保
     */
    @BindView(R.id.is_safest)
    TextView isSafest;
    /**
     * 投保金额
     */
    @BindView(R.id.safest_money)
    TextView safestMoney;
    /**
     * 发票信息
     */
    @BindView(R.id.is_need_invoice)
    TextView isNeedInvoice;
    /**
     * 支付方式
     */
    @BindView(R.id.pay_way)
    TextView payWay;
    /**
     * 货物信息
     */
    @BindView(R.id.goods_translate_money)
    MyListView goodsTranslateMoney;
    /**
     * 费用明细
     */
    @BindView(R.id.money_detail)
    TextView moneyDetail;
    /**
     * 预约费
     */
    @BindView(R.id.app_money)
    TextView appMoney;
    /**
     * 商品
     */
    @BindView(R.id.goods_money_listiew)
    MyListView goodsMoneyListiew;
    /**
     * 提交
     */
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.sender_number)
    TextView senderNumber;
    @BindView(R.id.receiver_number)
    TextView receiverNumber;
    @BindView(R.id.peisong_number)
    TextView peisongNumber;
    @BindView(R.id.yunsong_drever_name)
    TextView yunsongDreverName;
    @BindView(R.id.yunsong_licences)
    TextView yunsongLicences;
    @BindView(R.id.yunsong_number)
    TextView yunsongNumber;
    @BindView(R.id.sender_net_address)
    TextView sender_net_address;
    @BindView(R.id.receiver_net_address)
    TextView receiver_net_address;
    private List<LogisticDetailBean> list;
    private LogisticDetailBean bean;
    private GoodsDtailAdapter adapter;
    private goodsPriceAdapter goods_price;
    /**
     * 接收传过来的订单字段
     */
    private String order_number = "order_number";
    private LogisticDtailParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistic_detail);
        ButterKnife.bind(this);
        setTitle("运单详情");
        goodsTranslateMoney.setFocusable(false);
        goodsMoneyListiew.setFocusable(false);
        bean = new LogisticDetailBean();
        request();


    }

    /**
     * 添加数据
     */
    private void setData() throws Exception{
        if (bean == null) return;

        orderName.setText(bean.getCol().getCompany_name());
        String status = "";
        switch (bean.getCol().getStatus()) {
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
        orderStatus.setText(status);
        companyIcon.setImageURI(UriConstants.HOST + "/" + bean.getCol().getLogo());
        senderName.setText(bean.getOrder().getConsignor_name());
        senderAddress.setText(bean.getOrder().getConsignor_city() + bean.getOrder().getConsignor_county() + bean.getOrder().getConsignor_address());
        sender_net_address.setText(bean.getOrder().getConsignor_city() + bean.getOrder().getConsignor_county() + bean.getOrder().getConsignor_address());
        senderNumber.setText(bean.getOrder().getConsignor_phone_number());
        sendOrderStatus.setText(bean.getOrder().getSend_cargo_type() == 0 ? "自送网店" : "上门取货");//发货状态
        receiverName.setText(bean.getOrder().getConsignee_name());
        Log.i("---------", bean.getOrder().getConsignee_city() + bean.getOrder().getConsignee_county() + bean.getOrder().getConsignee_address());
        receiverAddress.setText(bean.getOrder().getConsignee_city() + bean.getOrder().getConsignee_county() + bean.getOrder().getConsignee_address());
        receiver_net_address.setText(bean.getOrder().getConsignee_city() + bean.getOrder().getConsignee_county() + bean.getOrder().getConsignee_address());
        receiverNumber.setText(bean.getOrder().getConsignee_phone_number());
        receiveOrderStatus.setText(bean.getOrder().getReceive_cargo_type() == 0 ? "客户自提" : "送货上门");//收货状态
        dreverName.setText(bean.getOrder().getDriver_name() + "");
        licences.setText(bean.getOrder().getVehicle_number() + "");
        receipt.setText(bean.getServer().getIs_receipt() + "");
        adapter = new GoodsDtailAdapter(context, bean.getCargo());
        goodsTranslateMoney.setAdapter(adapter);

        goods_price = new goodsPriceAdapter(context, bean.getCargo());
        goodsMoneyListiew.setAdapter(goods_price);
//        payWay.setText(bean.getOrder().getPay_type() == 0 ? "在线支付" : "货到付款");//支付方式
    }

    /**
     * 请求网络
     */
    private void request() {
        params = new LogisticDtailParams();
        params.join(getIntent().getStringExtra(order_number), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("----->", result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }
                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        bean = JSON.parseObject(resultInfo.getData(), LogisticDetailBean.class);
                        try {
                            setData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        LToast.show(context, "请求失败, 请稍后再试");
                        break;
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


    @OnClick(R.id.commit)
    public void onClick() {
        // TODO: 2016/7/12 0012 跳转到第三方支付
        LToast.show(context, "这里跳转第三方支付");
        finish();
    }


}

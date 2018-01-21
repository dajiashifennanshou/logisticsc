package com.wrt.xinsilu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.lerist.client.OrderClient;
import com.wrt.xinsilu.lerist.interfaces.InputKeyValue;
import com.wrt.xinsilu.lerist.model.OrderAddServer;
import com.wrt.xinsilu.lerist.model.OrderAddService;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.TextView;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class GoodsFourthFragment extends Fragment implements InputKeyValue {
    @BindView(R.id.goods_fourth_rl_addedvalue)
    LinearLayout rl_addedvalue;
    @BindView(R.id.goods_fourth_ctv_insure)
    CheckedTextView ctv_insure;
    @BindView(R.id.goods_fourth_et_insure_money)
    EditText et_insure_money;
    @BindView(R.id.goods_fourth_ll_payway)
    LinearLayout ll_payway;
    @BindView(R.id.goods_fourth_btn_submit)
    TextView btn_submit;
    @BindView(R.id.goods_fourth_ctv_shipment)
    CheckedTextView ctv_shipment;
    @BindView(R.id.goods_fourth_et_shipment_floor)
    EditText et_shipment_floor;
    @BindView(R.id.goods_fourth_et_shipment_isexist_lift)
    CheckBox cb_shipment_isexist_lift;
    @BindView(R.id.goods_fourth_ctv_unload)
    CheckedTextView ctv_unload;
    @BindView(R.id.goods_fourth_et_unload_floor)
    EditText et_unload_floor;
    @BindView(R.id.goods_fourth_et_unload_isexist_lift)
    CheckBox cb_unload_isexist_lift;
    @BindView(R.id.goods_fourth_ctv_agencyfund)
    CheckedTextView ctv_agencyfund;
    @BindView(R.id.goods_fourth_et_agencyfund_money)
    EditText et_agencyfund_money;
    @BindView(R.id.goods_fourth_rl_addedvalue_receipt)
    CheckedTextView ctv_receipt;
    @BindView(R.id.goods_fourth_rl_addedvalue_composite)
    CheckedTextView ctv_composite;
    @BindView(R.id.goods_fourth_rl_addedvalue_loan)
    CheckedTextView ctv_loan;
    private View mView;
    private View.OnClickListener onSubmitListener;
    private OrderClient orderClient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.goods_fourth_fragment, null);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        orderClient = new OrderClient();
        requestData();
    }

    private void requestData() {
        orderClient.getAddServer(getActivity().getIntent().getIntExtra("tms_line_id", 0), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        OrderAddService orderAddService = JSON.parseObject(resultInfo.getData(), OrderAddService.class);
                        bindData(orderAddService);
                        break;
                    default:
                        LToast.show(getContext(), resultInfo.getMsg() == null ? "获取增值服务失败" : resultInfo.getMsg());
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LToast.show(getContext(), "获取增值服务失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void bindData(OrderAddService orderAddService) {
        //是否提供装装卸
        boolean isLoadAndunload = orderAddService.getIsLoadAndUnload() == 1;
        //是否提供代收货款
        boolean isCollectionDelivery = orderAddService.getIsCollectionDelivery() == 1;
        //是否支持专线投保
        boolean isLineInsurance = orderAddService.getIsLineInsurance() == 1;
        //是否支持回单
        boolean isReceipt = orderAddService.getIsReceipt() == 1;
        //是否支持等待放款
        boolean isLoan = false;
        //是否支持综合信息服务
        boolean isComposite = false;

//        ((View) ctv_agencyfund.getParent()).setVisibility(isCollectionDelivery ? View.VISIBLE : View.GONE);
//        ((View) ctv_shipment.getParent()).setVisibility(isLoadAndunload ? View.VISIBLE : View.GONE);
//        ((View) ctv_unload.getParent()).setVisibility(isLoadAndunload ? View.VISIBLE : View.GONE);

        if (isReceipt == false) {
            ctv_receipt.setAlpha(0.6f);
            ctv_receipt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LToast.show(getContext(), "该线路暂不支持此增值服务");
                }
            });
        }

        if (isLineInsurance == false) {
            ctv_insure.setAlpha(0.6f);
            ctv_insure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LToast.show(getContext(), "该线路暂不支持此增值服务");
                }
            });
        }

        if (isCollectionDelivery == false) {
            ctv_agencyfund.setAlpha(0.6f);
            ctv_agencyfund.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LToast.show(getContext(), "该线路暂不支持此增值服务");
                }
            });
        }

        if (isComposite == false) {
            ctv_composite.setAlpha(0.6f);
            ctv_composite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LToast.show(getContext(), "该线路暂不支持此增值服务");
                }
            });
        }
        if (isLoan == false) {
            ctv_loan.setAlpha(0.6f);
            ctv_loan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LToast.show(getContext(), "该线路暂不支持此增值服务");
                }
            });
        }
        if (isLoadAndunload == false) {
            ctv_shipment.setAlpha(0.6f);
            ctv_shipment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LToast.show(getContext(), "该线路暂不支持此增值服务");
                }
            });
        }

        if (isLoadAndunload == false) {
            ctv_unload.setAlpha(0.6f);
            ctv_unload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LToast.show(getContext(), "该线路暂不支持此增值服务");
                }
            });
        }
    }

    private void initView() {
        btn_submit.setOnClickListener(onSubmitListener);

        ctv_insure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctv_insure.setChecked(!ctv_insure.isChecked());
                ctv_insure.setTextColor(ctv_insure.isChecked() ?
                        getResources().getColor(R.color.white) : getResources().getColor(R.color.font_color_hint));
                et_insure_money.setAlpha(ctv_insure.isChecked() ? 1 : 0.6f);
                et_insure_money.setEnabled(ctv_insure.isChecked());
                et_insure_money.setText("");
            }
        });
        for (int i = 0; i < rl_addedvalue.getChildCount(); i++) {
            if (!(rl_addedvalue.getChildAt(i) instanceof CheckedTextView)) {
                continue;
            }
            CheckedTextView childAt = (CheckedTextView) rl_addedvalue.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CheckedTextView) v).setChecked(!((CheckedTextView) v).isChecked());
                    ((CheckedTextView) v).setTextColor(((CheckedTextView) v).isChecked() ?
                            getResources().getColor(R.color.white) : getResources().getColor(R.color.font_color_hint));
                }
            });
        }

        for (int i = 0; i < ll_payway.getChildCount(); i++) {
            if (!(ll_payway.getChildAt(i) instanceof CheckedTextView)) {
                continue;
            }
            CheckedTextView childAt = (CheckedTextView) ll_payway.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //还原所有item状态
                    for (int i = 0; i < ll_payway.getChildCount(); i++) {
                        if (!(ll_payway.getChildAt(i) instanceof CheckedTextView)) {
                            continue;
                        }
                        CheckedTextView childAt = (CheckedTextView) ll_payway.getChildAt(i);
                        childAt.setTextColor(getResources().getColor(R.color.font_color_hint));
                        childAt.setChecked(false);
                    }
                    ((CheckedTextView) v).setChecked(true);
                    ((CheckedTextView) v).setTextColor(getResources().getColor(R.color.white));
                }
            });
        }
    }

    public void setSubmitListener(View.OnClickListener onClickListener) {
        this.onSubmitListener = onClickListener;
    }

    @Override
    public HashMap<String, Object> getInputData() {
        HashMap<String, Object> inputData = new HashMap<>();

        ArrayList<String> addedvalue = new ArrayList<>();
        for (int i = 0; i < rl_addedvalue.getChildCount(); i++) {
            if (!(rl_addedvalue.getChildAt(i) instanceof CheckedTextView)) {
                continue;
            }
            CheckedTextView childAt = (CheckedTextView) rl_addedvalue.getChildAt(i);
            if (childAt.isChecked()) {
                addedvalue.add(childAt.getText().toString());
            }
        }

        String payWay = "";
        for (int i = 0; i < ll_payway.getChildCount(); i++) {
            if (!(ll_payway.getChildAt(i) instanceof CheckedTextView)) {
                continue;
            }
            CheckedTextView childAt = (CheckedTextView) ll_payway.getChildAt(i);
            if (childAt.isChecked()) {
                payWay = childAt.getText().toString().equals("在线支付") ? "0" : "1";
                break;
            }
        }

        if (ctv_agencyfund.isChecked() && et_agencyfund_money.getText().toString().isEmpty()) {
            LToast.show(getActivity(), "请输入代收款金额");
            return null;
        }

        if (ctv_shipment.isChecked() && et_shipment_floor.getText().toString().isEmpty()) {
            LToast.show(getActivity(), "请输入装货楼层");
            return null;
        }

        if (ctv_unload.isChecked() && ctv_unload.getText().toString().isEmpty()) {
            LToast.show(getActivity(), "请输入卸货楼层");
            return null;
        }

        inputData.put("addedvalue", addedvalue);
        inputData.put("is_insurance", ctv_insure.isChecked() ? 1 : 0);
        if (ctv_insure.isChecked() && et_insure_money.getText().toString().isEmpty()) {
            LToast.show(getActivity(), "请输入投保金额");
            return null;
        }
        inputData.put("insurance_money", et_insure_money.getText().toString());
        inputData.put("pay_type", payWay);
        inputData.put("take_cargo_cost", 0);
        inputData.put("estimate_freight", 0);
        inputData.put("estimate_total", 0);
        OrderAddServer orderAddServer = new OrderAddServer();
        orderAddServer.setIs_load_cargo(ctv_shipment.isChecked() ? 1 : 0);
        if (ctv_shipment.isChecked()) {
            try {
                int load_cargo_floor = Integer.parseInt(et_shipment_floor.getText().toString());
                orderAddServer.setLoad_cargo_floor(load_cargo_floor);
            } catch (NumberFormatException e) {
                LToast.show(getActivity(), "装货楼层输入错误");
                return null;
            }
        }

        orderAddServer.setLoad_cargo_is_elevator(cb_shipment_isexist_lift.isChecked() ? 1 : 0);
        orderAddServer.setIs_unload_cargo(ctv_unload.isChecked() ? 1 : 0);
        if (ctv_unload.isChecked()) {
            try {
                int unload_cargo_floor = Integer.parseInt(et_unload_floor.getText().toString());
                orderAddServer.setUnload_cargo_floor(unload_cargo_floor);
            } catch (NumberFormatException e) {
                LToast.show(getActivity(), "卸货楼层输入错误");
                return null;
            }
        }
        orderAddServer.setUnload_cargo_is_elevator(cb_unload_isexist_lift.isChecked() ? 1 : 0);
        inputData.put("orderAddServer", orderAddServer);
        return inputData;
    }

    @OnClick({R.id.goods_fourth_ctv_agencyfund, R.id.goods_fourth_ctv_shipment, R.id.goods_fourth_ctv_unload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goods_fourth_ctv_agencyfund:
                ctv_agencyfund.setChecked(!ctv_agencyfund.isChecked());
                ctv_agencyfund.setTextColor(ctv_agencyfund.isChecked() ?
                        getResources().getColor(R.color.white) : getResources().getColor(R.color.font_color_hint));
                et_agencyfund_money.setText("");
                et_agencyfund_money.setAlpha(ctv_agencyfund.isChecked() ? 1 : 0.6f);
                et_agencyfund_money.setEnabled(ctv_agencyfund.isChecked());
                break;
            case R.id.goods_fourth_ctv_shipment:
                ctv_shipment.setChecked(!ctv_shipment.isChecked());
                ctv_shipment.setTextColor(ctv_shipment.isChecked() ?
                        getResources().getColor(R.color.white) : getResources().getColor(R.color.font_color_hint));
                et_shipment_floor.setText("");
                et_shipment_floor.setAlpha(ctv_shipment.isChecked() ? 1 : 0.6f);
                et_shipment_floor.setEnabled(ctv_shipment.isChecked());
                cb_shipment_isexist_lift.setAlpha(ctv_shipment.isChecked() ? 1 : 0.6f);
                cb_shipment_isexist_lift.setChecked(false);
                cb_shipment_isexist_lift.setEnabled(ctv_shipment.isChecked());
                break;
            case R.id.goods_fourth_ctv_unload:
                ctv_unload.setChecked(!ctv_unload.isChecked());
                ctv_unload.setTextColor(ctv_unload.isChecked() ?
                        getResources().getColor(R.color.white) : getResources().getColor(R.color.font_color_hint));
                et_unload_floor.setAlpha(ctv_unload.isChecked() ? 1 : 0.6f);
                et_unload_floor.setEnabled(ctv_unload.isChecked());
                et_unload_floor.setText("");
                cb_unload_isexist_lift.setAlpha(ctv_shipment.isChecked() ? 1 : 0.6f);
                cb_unload_isexist_lift.setChecked(false);
                cb_unload_isexist_lift.setEnabled(ctv_unload.isChecked());
                break;
        }
    }
}

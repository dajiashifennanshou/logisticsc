package com.wrt.xinsilu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.lerist.client.LineClient;
import com.wrt.xinsilu.lerist.interfaces.InputKeyValue;
import com.wrt.xinsilu.lerist.model.LogisticsNetAddr;
import com.wrt.xinsilu.lerist.ui.activity.CommonContactSelectActivity;
import com.xsl.lerist.llibrarys.model.Contacts;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.LContactsChooser;
import com.xsl.lerist.llibrarys.utils.LResultActivity;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class GoodsThirdFragment extends Fragment implements View.OnClickListener, InputKeyValue {
    @BindView(R.id.goods_third_ll_sendway)
    LinearLayout ll_sendway;
    @BindView(R.id.goods_third_ll_receiveway)
    LinearLayout ll_receiveway;
    @BindView(R.id.goods_third_ll_driver)
    LinearLayout ll_part_driver;
    @BindView(R.id.goods_third_tv_sendnet)
    TextView tv_sendnet;
    @BindView(R.id.goods_third_tv_ainpicknet)
    TextView tv_ainpicknet;
    private View mView;
    /**
     * 常用司机姓名
     */
    private EditText driver_name;
    /**
     * 点击选择常用司机按钮
     */
    private TextView driver_button;
    /**
     * 司机电话号码
     */
    private EditText driver_phone_number;
    /**
     * 手机号码按钮
     */
    private View number;
    private EditText driver_car_number;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.goods_third_fragment, null);
        ButterKnife.bind(this, mView);
        initViews();
        initValues();
        return mView;
    }

    /**
     * 初始化
     */
    private void initViews() {
        driver_name = (EditText) mView.findViewById(R.id.driver_name);
        driver_button = (TextView) mView.findViewById(R.id.driver_name_button);
        driver_phone_number = (EditText) mView.findViewById(R.id.driver_phone_number);
        driver_car_number = (EditText) mView.findViewById(R.id.driver_carnumber);
        number = mView.findViewById(R.id.number);
        for (int i = 0; i < ll_sendway.getChildCount(); i++) {
            if (!(ll_sendway.getChildAt(i) instanceof CheckedTextView)) {
                continue;
            }
            CheckedTextView childAt = (CheckedTextView) ll_sendway.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //还原所有item状态
                    for (int i = 0; i < ll_sendway.getChildCount(); i++) {
                        if (!(ll_sendway.getChildAt(i) instanceof CheckedTextView)) {
                            continue;
                        }
                        CheckedTextView childAt = (CheckedTextView) ll_sendway.getChildAt(i);
                        childAt.setTextColor(getResources().getColor(R.color.font_color_hint));
                        childAt.setChecked(false);
                    }
                    ((CheckedTextView) v).setChecked(true);
                    ((CheckedTextView) v).setTextColor(getResources().getColor(R.color.white));
                    if (((CheckedTextView) v).getText().toString().equals("自送网点")) {
                        ll_part_driver.setVisibility(View.VISIBLE);
                    } else {
                        ll_part_driver.setVisibility(View.GONE);
                    }
                }
            });
        }

        for (int i = 0; i < ll_receiveway.getChildCount(); i++) {
            if (!(ll_receiveway.getChildAt(i) instanceof CheckedTextView)) {
                continue;
            }
            CheckedTextView childAt = (CheckedTextView) ll_receiveway.getChildAt(i);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //还原所有item状态
                    for (int i = 0; i < ll_receiveway.getChildCount(); i++) {
                        if (!(ll_receiveway.getChildAt(i) instanceof CheckedTextView)) {
                            continue;
                        }
                        CheckedTextView childAt = (CheckedTextView) ll_receiveway.getChildAt(i);
                        childAt.setTextColor(getResources().getColor(R.color.font_color_hint));
                        childAt.setChecked(false);
                    }

                    ((CheckedTextView) v).setChecked(true);
                    ((CheckedTextView) v).setTextColor(getResources().getColor(R.color.white));
                }
            });
        }
    }

    private void initValues() {
        number.setOnClickListener(this);
        driver_button.setOnClickListener(this);

        //送货网点id
        String sendNetId = getArguments().getString("sendNet");
        //自提网点id
        String ainpickNetId = getArguments().getString("ainpickNet");

        new LineClient().getOutlesAddress(sendNetId, ainpickNetId, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        LogisticsNetAddr logisticsNetAddr = JSON.parseObject(resultInfo.getData(), LogisticsNetAddr.class);
                        if (logisticsNetAddr != null) {
                            tv_sendnet.setText("送货网点地址: " + logisticsNetAddr.getStartAdd());
                            tv_ainpicknet.setText("自提网点地址: " + logisticsNetAddr.getEndAdd());
                        }
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

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == driver_button) {
            CommonContactSelectActivity.startDriver(getActivity(), new LResultActivity.Callback() {
                @Override
                public void onSuccess(Intent result) {
                    String nameStr = result.getStringExtra("name");
                    String phoneStr = result.getStringExtra("phone");
                    String carNumStr = result.getStringExtra("carNum");
                    driver_name.setText(nameStr);
                    driver_phone_number.setText(phoneStr);
                    driver_car_number.setText(carNumStr);
                }

                @Override
                public void onFailure() {

                }
            });
        } else if (v == number) {
            LContactsChooser.openContacts(getContext(), new LContactsChooser.Callbak() {
                @Override
                public void onSuccess(ArrayList<Contacts> selectedContactses) {
                    try {
                        driver_phone_number.setText(selectedContactses.get(0).getPhones().get(0));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure() {

                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
        }
    }

    @Override
    public HashMap<String, Object> getInputData() {
        HashMap<String, Object> inputData = new HashMap<>();
        String driverName = driver_name.getText().toString();
        String driverPhone = driver_phone_number.getText().toString();
        String carNumber = driver_car_number.getText().toString();

        String sendWay = "";
        for (int i = 0; i < ll_sendway.getChildCount(); i++) {
            if (!(ll_sendway.getChildAt(i) instanceof CheckedTextView)) {
                continue;
            }
            CheckedTextView childAt = (CheckedTextView) ll_sendway.getChildAt(i);
            if (childAt.isChecked()) {
                sendWay = childAt.getText().toString().equals("上门取货") ? "1" : "0";
                break;
            }
        }
        String receiveWay = "";
        for (int i = 0; i < ll_receiveway.getChildCount(); i++) {
            if (!(ll_receiveway.getChildAt(i) instanceof CheckedTextView)) {
                continue;
            }
            CheckedTextView childAt = (CheckedTextView) ll_receiveway.getChildAt(i);
            if (childAt.isChecked()) {
                receiveWay = childAt.getText().toString().equals("送货上门") ? "1" : "0";
                break;
            }
        }
        inputData.put("send_cargo_type", sendWay);
        inputData.put("receive_cargo_type", receiveWay);
        if (sendWay.equals("0")) {
            //自送网点
            inputData.put("driver_name", driverName);
            inputData.put("driver_phone", driverPhone);
            inputData.put("vehicle_number", carNumber);
            inputData.put("vehicle_type", "");
            if (!StringUtils.isCarnumberNO(carNumber)) {
                LToast.show(getContext(), "车牌号有误");
                return null;
            }
        }

        for (String key : inputData.keySet()) {
            if (inputData.get(key) == null) {
                LToast.show(getContext(), "请完善配送信息");
                return null;
            }
        }
        return inputData;
    }
}

package com.wrt.xinsilu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.ProvinceBean;
import com.wrt.xinsilu.lerist.interfaces.InputKeyValue;
import com.wrt.xinsilu.lerist.ui.activity.CommonContactSelectActivity;
import com.wrt.xinsilu.ui.view.CustomPopupWindow;
import com.xsl.lerist.llibrarys.model.Contacts;
import com.xsl.lerist.llibrarys.utils.LContactsChooser;
import com.xsl.lerist.llibrarys.utils.LResultActivity;
import com.xsl.lerist.llibrarys.widget.LToast;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class GoodsFirstFragment extends Fragment implements View.OnClickListener, InputKeyValue {
    @BindView(R.id.name)
    EditText et_sender_name;
    @BindView(R.id.phone_number)
    EditText et_sender_phone;
    @BindView(R.id.sender_address)
    TextView tv_sender_address;
    @BindView(R.id.address_detail)
    EditText addressDetail;
    @BindView(R.id.reveive_name)
    EditText et_receive_name;
    @BindView(R.id.receive_phone_number)
    EditText et_receive_phone;
    @BindView(R.id.t8)
    TextView t8;
    @BindView(R.id.receive_address_detail)
    EditText et_receive_address_detail;
    /**
     * 联系人
     */
    private TextView name_button;
    /**
     * 收货人
     */
    private TextView receive_name_button;
    /**
     * 发货人姓名
     */
    private EditText name;
    /**
     * 收货人电话
     */
    private EditText receive_name;
    /**
     * 发货人地址
     */
    private TextView address;
    /**
     * 收货人地址
     */
    private TextView receiver_address;
    /**
     * 发货人具体地址
     */
    private EditText address_detail;
    /**
     * 收货人具体地址
     */
    private EditText receiver_address_detail;
    private View mView;
    private LinearLayout layout;
    private View sender_address_lauout;
    private View receiver_address_layout;
    /**
     * 这个是用来判断是哪一个按钮点击的，返回到显示id奥那一个textview上
     */
    private int type;
    private String address_content;

    private String provinceBeanName;
    private String cityBeanName;
    private String countyBeanName;
    private ProvinceBean startprovinceBean;
    private ProvinceBean.CityBean startcityBean;
    private ProvinceBean.CityBean.CountyBean startcountyBean;
    private ProvinceBean endprovinceBean;
    private ProvinceBean.CityBean endcityBean;
    private ProvinceBean.CityBean.CountyBean endcountyBean;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();

            switch (type) {
                case 0:
                    startprovinceBean = (ProvinceBean) data.getSerializable(ProvinceBean.class.getSimpleName());
                    startcityBean = (ProvinceBean.CityBean) data.getSerializable(ProvinceBean.CityBean.class.getSimpleName());
                    startcountyBean = (ProvinceBean.CityBean.CountyBean) data.getSerializable(ProvinceBean.CityBean.CountyBean.class.getSimpleName());
                    provinceBeanName = startprovinceBean == null ? "" : startprovinceBean.getName();
                    cityBeanName = startcityBean == null ? "" : startcityBean.getName();
                    countyBeanName = startcountyBean == null ? "" : startcountyBean.getName();
                    address.setText(provinceBeanName + " " + cityBeanName + " " + countyBeanName);
                    break;
                case 1:
                    endprovinceBean = (ProvinceBean) data.getSerializable(ProvinceBean.class.getSimpleName());
                    endcityBean = (ProvinceBean.CityBean) data.getSerializable(ProvinceBean.CityBean.class.getSimpleName());
                    endcountyBean = (ProvinceBean.CityBean.CountyBean) data.getSerializable(ProvinceBean.CityBean.CountyBean.class.getSimpleName());
                    provinceBeanName = endprovinceBean == null ? "" : endprovinceBean.getName();
                    cityBeanName = endcityBean == null ? "" : endcityBean.getName();
                    countyBeanName = endcountyBean == null ? "" : endcountyBean.getName();
                    receiver_address.setText(provinceBeanName + " " + cityBeanName + " " + countyBeanName);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.goods_first_fragment, null);
        ButterKnife.bind(this, mView);
        initViews();
        initValues();

        return mView;
    }

    private void initViews() {
        name_button = (TextView) mView.findViewById(R.id.name_button);
        receive_name_button = (TextView) mView.findViewById(R.id.receive_name_button);
        name = (EditText) mView.findViewById(R.id.name);
        receive_name = (EditText) mView.findViewById(R.id.reveive_name);
        address = (TextView) mView.findViewById(R.id.sender_address);
        receiver_address = (TextView) mView.findViewById(R.id.receive_address);
        address_detail = (EditText) mView.findViewById(R.id.address_detail);
        receiver_address_detail = (EditText) mView.findViewById(R.id.receive_address_detail);
        sender_address_lauout = mView.findViewById(R.id.sender_address_layout);
        receiver_address_layout = mView.findViewById(R.id.receiver_address_layout);
        layout = (LinearLayout) mView.findViewById(R.id.layout);

    }

    private void initValues() {
//        name_button.setOnClickListener(this);
//        receive_name_button.setOnClickListener(this);
//        sender_address_lauout.setOnClickListener(this);
//        receiver_address_layout.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {

        } else if (requestCode == 2) {

        }
    }

    @OnClick({R.id.name_button, R.id.number_button, R.id.sender_address_layout, R.id.receive_name_button, R.id.receive_number_button, R.id.receiver_address_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.name_button:
                CommonContactSelectActivity.startSender(getContext(), new LResultActivity.Callback() {
                    @Override
                    public void onSuccess(Intent result) {
                        String nameStr = result.getStringExtra("name");
                        String phoneStr = result.getStringExtra("phone");
                        et_sender_name.setText(nameStr);
                        et_sender_phone.setText(phoneStr);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                break;
            case R.id.number_button:
                LContactsChooser.openContacts(getContext(), new LContactsChooser.Callbak() {
                    @Override
                    public void onSuccess(ArrayList<Contacts> selectedContactses) {
                        try {
                            et_sender_phone.setText(selectedContactses.get(0).getPhones().get(0));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                break;
            case R.id.sender_address_layout:
                type = 0;
                new CustomPopupWindow(getActivity(), handler, type)
                        .showPopupWindow(view, true);
                break;
            case R.id.receive_name_button:
                CommonContactSelectActivity.startReceiver(getContext(), new LResultActivity.Callback() {
                    @Override
                    public void onSuccess(Intent result) {
                        String nameStr = result.getStringExtra("name");
                        String phoneStr = result.getStringExtra("phone");
                        et_receive_name.setText(nameStr);
                        et_receive_phone.setText(phoneStr);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                break;
            case R.id.receive_number_button:
                LContactsChooser.openContacts(getContext(), new LContactsChooser.Callbak() {
                    @Override
                    public void onSuccess(ArrayList<Contacts> selectedContactses) {
                        try {
                            et_receive_phone.setText(selectedContactses.get(0).getPhones().get(0));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                break;
            case R.id.receiver_address_layout:
                type = 1;
                new CustomPopupWindow(getActivity(), handler, type)
                        .showPopupWindow(view, true);
                break;
        }
    }

    @Override
    public HashMap<String, Object> getInputData() {
        HashMap<String, Object> inputData = new HashMap<>();
        inputData.put("consignor_name", et_sender_name.getText().toString());
        inputData.put("consignor_province", startprovinceBean == null ? null : startprovinceBean.getId());
        inputData.put("consignor_city", startcityBean == null ? null : startcityBean.getId());
        inputData.put("consignor_county", startcountyBean == null ? null : startcountyBean.getId());
        inputData.put("consignor_address", addressDetail.getText().toString());
        inputData.put("consignor_phone_number", et_sender_phone.getText().toString());
        inputData.put("consignor_telephone", "");
        inputData.put("consignee_name", et_receive_name.getText().toString());
        inputData.put("consignee_province", endprovinceBean == null ? null : endprovinceBean.getId());
        inputData.put("consignee_city", endcityBean == null ? null : endcityBean.getId());
        inputData.put("consignee_county", endcountyBean == null ? null : endcountyBean.getId());
        inputData.put("consignee_address", et_receive_address_detail.getText().toString());
        inputData.put("consignee_phone_number", et_receive_phone.getText().toString());
        inputData.put("consignee_telephone", "");

        for (String key : inputData.keySet()) {
            if (inputData.get(key) == null) {
                LToast.show(getContext(), "请完善发/收货人信息");
                return null;
            }
        }
        return inputData;
    }
}

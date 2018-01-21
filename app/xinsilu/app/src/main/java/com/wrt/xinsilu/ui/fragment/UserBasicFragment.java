package com.wrt.xinsilu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.ResultCode;
import com.wrt.xinsilu.bean.UserBean;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.UserClient;
import com.wrt.xinsilu.lerist.interfaces.InputKeyValue;
import com.wrt.xinsilu.lerist.interfaces.Selectable;
import com.xsl.lerist.llibrarys.fragments.LFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/17 0017.
 * 个人信息fragment
 */
public class UserBasicFragment extends LFragment implements Selectable, InputKeyValue {
    @BindView(R.id.my_account)
    TextView myAccount;
    @BindView(R.id.my_account_phonenumber)
    TextView myAccountPhonenumber;
    @BindView(R.id.my_account_name)
    EditText myAccountName;
    @BindView(R.id.my_account_address)
    EditText myAccountAddress;
    private View mView;
    private LocalData data;
    private UserClient client;
    private boolean isSelect;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.user_basic_layout_fragment, null);
        ButterKnife.bind(this, mView);
        initView();
        return mView;
    }

    private void initView() {
        data = new LocalData(context);
        client = new UserClient();
        comit();
    }

    /**
     * 当外部activity点击提交的时候，传进来的值
     */
    private void CheckInfo() {
        if (TextUtils.isEmpty(myAccountName.getText().toString())) {
            LToast.show(context, "请填写姓名");
        } else if (TextUtils.isEmpty(myAccountAddress.getText().toString())) {
            LToast.show(context, "请填写详细地址");
        } else {
            comit();
        }
    }

    private void comit() {
        UserInfo userInfo = data.readUserInfo();
        if (userInfo == null) return;

        LProgressDialog.show(context, "");
        client.getUserBasicInfo(
                data.readUserInfo().getUser().getLogin_name(),
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        KLog.i(result);
                        if (resultInfo == null) {
                            return;
                        }
                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                UserBean userBean = JSON.parseObject(resultInfo.getData(), UserBean.class);
                                myAccount.setText(userBean.getLogin_name() == null ? "" : userBean.getLogin_name());
                                myAccountPhonenumber.setText(userBean.getMobile() == null ? "" : userBean.getMobile());
                                myAccountName.setText(userBean.getTrue_name() == null ? "" : userBean.getTrue_name());
                                myAccountAddress.setText(userBean.getAddress() == null ? "" : userBean.getAddress());
                                break;
                            default:
                                LToast.show(context, resultInfo.getMsg() == null ? "加载基本信息失败, 请稍后再试" : resultInfo.getMsg());
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ex.printStackTrace();
                        LToast.show(context, "加载基本信息失败, 请稍后再试");
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

    @Override
    public HashMap<String, Object> getInputData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("true_name", myAccountName.getText().toString());
        map.put("address", myAccountAddress.getText().toString());
        map.put("type", "userBasic");
        return map;
    }

    @Override
    public boolean isSelectable() {
        return isSelect;
    }

    @Override
    public void setSelectable(boolean isSelectable) {
        isSelect = isSelectable;
        if (isSelect) {
            myAccountName.setEnabled(true);
            myAccountAddress.setEnabled(true);
        } else {
            myAccountName.setEnabled(false);
            myAccountAddress.setEnabled(false);
        }
    }
}

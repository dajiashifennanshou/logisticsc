package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.ResultCode;
import com.wrt.xinsilu.bean.UserBean;
import com.wrt.xinsilu.client.CompanyUserClient;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.UserClient;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class CompanyBasicInfoActivity extends SwipeBackActivity {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.my_account)
    TextView myAccount;
    @BindView(R.id.my_account_phonenumber)
    TextView myAccountPhonenumber;
    @BindView(R.id.my_account_name)
    EditText myAccountName;
    @BindView(R.id.my_account_address)
    EditText myAccountAddress;
    @BindView(R.id.title_enter)
    carbon.widget.TextView enter;
    private LocalData data;
    private UserClient client;
    private CompanyUserClient userClient;
    private int clickState = 1;//点击判断弄否修改
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_basic_info);
        ButterKnife.bind(this);
        tvTitle.setText("基本信息");
        data = new LocalData(context);
        client = new UserClient();
        userClient = new CompanyUserClient();
        initValue();
    }

    private void initValue() {
        myAccount.setText(data.readUserInfo().getUser().getLogin_name());
        myAccountPhonenumber.setText(data.readUserInfo().getUser().getMobile());
        enter.setVisibility(View.VISIBLE);
        enter.setText("修改");
        comit();
    }
    /**目前为获取基本信息*/
    private void comit() {
        client.getUserBasicInfo(
                data.readUserInfo().getUser().getLogin_name(),
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        KLog.i("----------------------" + result);
                        if (resultInfo == null) {
                            return;
                        }
                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                UserBean userBean = JSON.parseObject(resultInfo.getData(), UserBean.class);
                                myAccount.setText(userBean.getLogin_name() == null ? "" : userBean.getLogin_name());
                                myAccountPhonenumber.setText(userBean.getMobile() == null ? "" : userBean.getMobile());
                                myAccountName.setText(userBean.getTrue_name() == null ? "" : userBean.getTrue_name());
                                myAccountAddress.setText(userBean.getAddress()== null ? "" : userBean.getAddress());
                                break;
//                        finish();
                                default:
                                    LToast.show(context,"获取个人信息失败");
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        LToast.show(context,"获取个人信息失败");
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });



    }

    @OnClick({R.id.btn_back, R.id.title_enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.title_enter:
                clickState++;
                if(clickState % 2 == 0){
                    myAccountName.setEnabled(true);
                    myAccountAddress.setEnabled(true);
                    enter.setText("提交");
                }else{
                    enter.setText("修改");
                    myAccountName.setEnabled(false);
                    myAccountAddress.setEnabled(false);
                    if(TextUtils.isEmpty(myAccountName.getText().toString())){
                        LToast.show(context,"请填写姓名");
                    }else if(TextUtils.isEmpty(myAccountAddress.getText().toString())){
                        LToast.show(context,"请填写详细地址");
                    }
                    else {
                        LProgressDialog.show(context,"");
                        userClient.alterUserBasicInfo(
                                data.readUserInfo().getUser().getId(),
                                myAccountName.getText().toString(),
                                myAccountAddress.getText().toString()
                                , new Callback.CommonCallback<String>() {
                                    @Override
                                    public void onSuccess(String result) {
                                        KLog.i(result);
                                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                        KLog.i("----------------------" + result);
                                        if (resultInfo == null) {
                                            return;
                                        }
                                        switch (resultInfo.getCode()) {
                                            case ResultCode.SUCCEED:
                                                LToast.show(context,"修改成功");
                                                comit();
                                                break;
                                            default:
                                                LToast.show(context,"修改失败");
                                        }

                                    }

                                    @Override
                                    public void onError(Throwable ex, boolean isOnCallback) {
                                        LToast.show(context,"修改失败");
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
                break;
        }
    }
}

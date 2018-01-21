package com.wrt.xinsilu.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.client.CompanyUserClient;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.interfaces.InputKeyValue;
import com.wrt.xinsilu.lerist.interfaces.Selectable;
import com.wrt.xinsilu.ui.fragment.CompanyInfoFragment;
import com.wrt.xinsilu.ui.fragment.UserBasicFragment;
import com.xsl.lerist.llibrarys.fragments.LFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LFragmentContainer;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class MyCountAfterApplyComanyActivity extends SwipeBackActivity implements RadioGroup.OnCheckedChangeListener, LFragmentContainer.OnFragmentChangedListener {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.f_send_rb_send)
    RadioButton fSendRbSend;
    @BindView(R.id.f_send_rb_select)
    RadioButton fSendRbSelect;
    @BindView(R.id.f_send_rg)
    RadioGroup fSendRg;
    @BindView(R.id.fragment)
    LFragmentContainer lFragmentContainer;
    private CompanyInfoFragment companyInfoFragment;
    private UserBasicFragment userBasicFragment;
    private CompanyUserClient client;
    private LocalData data;
    private LocalData localData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_count_after_apply_comany);
        ButterKnife.bind(this);
        initData();
        initViews();
    }

    private void initData() {
        localData = new LocalData(context);
        if (!localData.isLogined()) {
            LToast.show(context, "请先登录");
            finish();
            return;
        }
    }

    /**
     * 初始化
     */
    private void initViews() {
        tvTitle.setText("我的账户");
        companyInfoFragment = new CompanyInfoFragment();
        userBasicFragment = new UserBasicFragment();
        titleEnter.setVisibility(View.VISIBLE);
        titleEnter.setText("修改");
        client = new CompanyUserClient();
        data = new LocalData(context);
        /*getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, userBasicFragment)
                .add(R.id.fragment, companyInfoFragment)
                .hide(companyInfoFragment)
                .show(userBasicFragment)
                .commit();*/
        lFragmentContainer.addFragment(userBasicFragment).addFragment(companyInfoFragment).setVisibleFragmentIndex(0);
        lFragmentContainer.addOnFragmentChangedListener(this);
        fSendRg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        boolean isSend = checkedId == R.id.f_send_rb_send;
        titleEnter.setVisibility(isSend ? View.VISIBLE : View.INVISIBLE);//当公司信息可以修改可以注释此方法
        lFragmentContainer.setVisibleFragmentIndex(isSend ? 0 : 1);

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((TextView) radioGroup.getChildAt(i)).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        ((TextView) radioGroup.findViewById(checkedId)).setTextColor(Color.WHITE);
    }

    @OnClick(R.id.title_enter)
    public void onClick() {
        Selectable selectable = (Selectable) lFragmentContainer.getCurrentVisibleFragment();
        if (titleEnter.getText().toString().equals("修改")) {//修改
            selectable.setSelectable(true);
            titleEnter.setText("提交");
        } else {//提交
            selectable.setSelectable(false);
            titleEnter.setText("修改");
            HashMap<String, Object> map = ((InputKeyValue) selectable).getInputData();//当前fragment数据，是一个hashMap
            // TODO: 2016/8/17 0017
            if (map.get("type").equals("userBasic")) {
                map.remove("type");
                client.alterUserBasicInfo(data.readUserInfo().getUser().getId(), map, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo==null) {
                            return;
                        }
                        switch (resultInfo.getCode()){
                            case ResultCode.SUCCEED:
                                LToast.show(context, "修改成功");
                                break;
                            default:
                                LToast.show(context, "修改失败");
                                break;
                        }
//                        finish();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        LToast.show(context, "修改失败");
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            } else if (map.get("type").equals("companyInfo")) {
                map.remove("type");
                /**************************以下公司信息如果可以修改的话只需开通此方法*******************************/
                /*client.alterCompanyInfo(data.readUserInfo().getUser().getId(), map, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        LToast.show(context,"修改成功");
                        finish();
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
                });*/
            }

        }
    }

    @Override
    public void onFragmentChangedBefore(Fragment currentVisiblefragment, int index) {
    }

    @Override
    public void onFragmentChanged(Fragment currentVisibleFragment, int index) {
        if (((Selectable) currentVisibleFragment).isSelectable()) {
            titleEnter.setText("提交");
        } else {
            titleEnter.setText("修改");
        }

        ((LFragment) currentVisibleFragment).onRefreshFragment();
    }
}

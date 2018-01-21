package com.xsl.distributor.lerist.ui.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xsl.distributor.R;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.activity.AboutActivity;
import com.xsl.distributor.lerist.ui.activity.MyCostActivity;
import com.xsl.distributor.lerist.ui.activity.MyCustomerActivity;
import com.xsl.distributor.lerist.ui.activity.MyGoodsActivity;
import com.xsl.distributor.ws.ui.activity.AddBankCardActivity;
import com.xsl.distributor.ws.ui.activity.HelpActivity;
import com.xsl.distributor.ws.ui.activity.LoginActivity;
import com.xsl.distributor.ws.ui.activity.MyAccountActivity;
import com.xsl.lerist.llibrarys.fragments.LFragment;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class UserFragment extends LFragment {

    @BindView(R.id.f_user_tv_name)
    TextView tv_name;
    @BindView(R.id.f_user_func_item_logout)
    carbon.widget.TextView tv_logout;
    @BindView(R.id.f_user_tv_cloudrepository_name)
    TextView tv_cloudrepository_name;
    @BindView(R.id.f_user_tv_btn_enable_onlinepay)
    carbon.widget.TextView btn_enable_onlinepay;
    @BindView(R.id.f_user_tv_btn_login)
    carbon.widget.TextView btn_login;
    private LocalData localData;
    private UserInfo userInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (localData.isLogined()) {
            userInfo = localData.readUserInfo();
        } else {
            userInfo = null;
        }

        bindingUserInfo(userInfo);
    }

    public UserFragment() {
        // Required empty public constructor
    }

    private void initData() {
        localData = new LocalData(context);
        userInfo = localData.readUserInfo();
    }

    private void initView() {
        setTitle("我的");

    }

    private void bindingUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            tv_cloudrepository_name.setText("");
            tv_name.setText("");
            tv_name.setVisibility(View.GONE);
            tv_logout.setVisibility(View.GONE);
            btn_enable_onlinepay.setVisibility(View.GONE);
            tv_cloudrepository_name.setVisibility(View.INVISIBLE);
            btn_login.setVisibility(View.VISIBLE);
            return;
        }
        tv_name.setVisibility(View.VISIBLE);
        btn_enable_onlinepay.setVisibility(View.VISIBLE);
        UserInfo.Join join = userInfo.getJoin();
        if (join != null && !StringUtils.isEmpty(join.getBranchName())) {
            tv_cloudrepository_name.setVisibility(View.VISIBLE);
            tv_cloudrepository_name.setText("(" + join.getBranchName() + ")");
        }
        tv_name.setText(userInfo.getUser().getLogin_name());
        tv_logout.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);
    }

    @OnClick({R.id.f_user_tv_btn_login, R.id.f_user_tv_btn_enable_onlinepay, R.id.f_user_func_item_logout, R.id.f_user_tv_name, R.id.f_user_func_item_my_account, R.id.f_user_func_item_my_customer, R.id.f_user_func_item_money_liushui, R.id.f_user_func_item_good_liushui, R.id.f_user_func_item_help, R.id.f_user_func_item_about_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.f_user_tv_name:
                if (localData.isLogined()) {
                    startActivity(MyAccountActivity.class);
                }
                break;
            case R.id.f_user_func_item_my_account:
                startActivity(MyAccountActivity.class);
                break;
            case R.id.f_user_func_item_my_customer:
                startActivity(MyCustomerActivity.class);
                break;
            case R.id.f_user_func_item_money_liushui:
                startActivity(MyCostActivity.class);
                break;
            case R.id.f_user_func_item_good_liushui:
                startActivity(MyGoodsActivity.class);
                break;
            case R.id.f_user_func_item_help:
                startActivity(HelpActivity.class);
                break;
            case R.id.f_user_func_item_about_me:
                startActivity(AboutActivity.class);
                break;
            case R.id.f_user_func_item_logout:
                new AlertDialog.Builder(context)
                        .setTitle("退出登录？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("退出登录", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logout();
                            }
                        })
                        .show();
                break;
            case R.id.f_user_tv_btn_enable_onlinepay:
                startActivity(AddBankCardActivity.class);
                break;
            case R.id.f_user_tv_btn_login:
                startActivity(LoginActivity.class);
                break;
        }
    }

    private void logout() {
        LProgressDialog.show(context, "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                localData.removeUserInfo();
                bindingUserInfo(null);
                LProgressDialog.dismiss();
            }
        }, 600);
    }

}

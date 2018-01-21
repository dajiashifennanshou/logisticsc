package com.wrt.xinsilu.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.otto.Subscribe;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.UserCenterAdappter;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.ui.activity.AboutActivity;
import com.wrt.xinsilu.ui.activity.CompanyBasicInfoActivity;
import com.wrt.xinsilu.ui.activity.ContentActivity;
import com.wrt.xinsilu.ui.activity.LoginActivity;
import com.wrt.xinsilu.ui.activity.MyCollectionActivity;
import com.wrt.xinsilu.ui.activity.MyCountAfterApplyComanyActivity;
import com.wrt.xinsilu.ui.activity.MyOrderActivity;
import com.wrt.xinsilu.ui.activity.UsuallyLogisticActivity;
import com.xsl.lerist.llibrarys.utils.BusProvider;
import com.xsl.lerist.llibrarys.widget.LToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心Fragment
 * Created by wangsong on 2016/6/23 0023.
 */
public class UserCenterFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    /**
     * 头像
     */
    private SimpleDraweeView imageView;
    /**
     * 登陆
     */
    private TextView login;
    /**
     * 我的订单
     */
    private TextView my_order;
    private View my_order_hint;
    /***/
    private TextView my_line;
    /**
     * 常用联系人
     */
    private TextView my_content;
    /**
     * 我的收藏
     */
    private TextView my_collction;
    /**
     * 公司信息等listView
     */
    private ListView listView;
    /**
     * 用于保存个人中心的list列表名称
     */
    private List<String> list = new ArrayList<String>();
    private UserInfo userBean;
    private View btn_logout;
    private LocalData localData;

    public UserCenterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_user_center, null);
        initViews(mView);
        BusProvider.getInstance().register(this);
        setOnClickListeners();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

    }

    private void initData() {
        localData = new LocalData(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        checkAccount();
    }

    private void checkAccount() {
        userBean = localData.readUserInfo();
        if (userBean != null) {
            login.setText(userBean.getUser().getLogin_name());
            login.setTextColor(getResources().getColor(R.color.font_color_normal2));
            login.setTextSize(16);
            login.setBackgroundResource(R.color.transparent);
            login.setEnabled(false);
            btn_logout.setVisibility(View.VISIBLE);
        } else {
            login.setEnabled(true);
            login.setText("立即登录");
            login.setTextSize(14);
            login.setTextColor(getResources().getColor(R.color.white));
            login.setBackgroundResource(R.color.colorPrimary);
            btn_logout.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化布局
     *
     * @param mView
     */
    private void initViews(View mView) {
        imageView = (SimpleDraweeView) mView.findViewById(R.id.circle_image_view);
        login = (TextView) mView.findViewById(R.id.login);
        btn_logout = mView.findViewById(R.id.f_user_center_logout);
        my_order = (TextView) mView.findViewById(R.id.my_order);
        my_line = (TextView) mView.findViewById(R.id.my_line);
        my_content = (TextView) mView.findViewById(R.id.my_content);
        my_collction = (TextView) mView.findViewById(R.id.my_collection);
        listView = (ListView) mView.findViewById(R.id.listView);
        my_order_hint = mView.findViewById(R.id.my_order_hint);
        list.add(getContext().getString(R.string.my_account));
        list.add("关于中工储运");
        UserCenterAdappter adapter = new UserCenterAdappter(getActivity(), list);
        listView.setAdapter(adapter);
    }

    /**
     * 初始化点击事件
     */
    private void setOnClickListeners() {
        imageView.setOnClickListener(this);
        login.setOnClickListener(this);
        ((View) my_order.getParent()).setOnClickListener(this);
        my_line.setOnClickListener(this);
        my_content.setOnClickListener(this);
        my_collction.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (imageView == v) {

        } else if (login == v) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        } else if (((View) my_order.getParent()) == v) {
            if (!localData.isLogined()) {
                LToast.show(getContext(), "请先登录");
                return;
            }
            startActivity(new Intent(getActivity(), MyOrderActivity.class));
            my_order_hint.setVisibility(View.INVISIBLE);
        } else if (my_line == v) {
            if (!localData.isLogined()) {
                LToast.show(getContext(), "请先登录");
                return;
            }
            startActivity(new Intent(getActivity(), UsuallyLogisticActivity.class));
        } else if (my_content == v) {
            if (!localData.isLogined()) {
                LToast.show(getContext(), "请先登录");
                return;
            }
            startActivity(new Intent(getActivity(), ContentActivity.class));
        } else if (my_collction == v) {
            if (!localData.isLogined()) {
                LToast.show(getContext(), "请先登录");
                return;
            }
            startActivity(new Intent(getActivity(), MyCollectionActivity.class));
        } else if (btn_logout == v) {
            new AlertDialog.Builder(getContext())
                    .setTitle("退出登录?")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            localData.removeUserInfo();
                            checkAccount();
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
//                if(localData.readUserInfo().getUser().getUser_type() == 2){
            if (!localData.isLogined()) {
                LToast.show(getContext(), "请先登录");
                return;
            }

            if (localData.readUserInfo().getUser().getUser_type() == 2) {
                startActivity(new Intent(getActivity(), MyCountAfterApplyComanyActivity.class));
            } else {
                startActivity(new Intent(getActivity(), CompanyBasicInfoActivity.class));
            }
        } else if (position == 1) {
            startActivity(new Intent(getActivity(), AboutActivity.class));
        }
    }
    @Subscribe
    public void updateOrder(BusProvider.BusEvent event){
        if(event.getFlag().equals("update_order")){
            my_order_hint.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }
}

package com.wrt.xinsilu.lerist.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.bean.UserMyOrderBean;
import com.wrt.xinsilu.lerist.client.OrderClient;
import com.wrt.xinsilu.ui.activity.OrderDetailActivity;
import com.wrt.xinsilu.ui.dialog.NoNetworkDialog;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LRecyclerViewFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.NetworkUtil;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.List;

/**
 * Created by Lerist on 2016/8/2, 0002.
 */

public class OrderFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {

    private LocalData localData;
    private OrderClient orderClient;
    private boolean isRefresh;
    private boolean isLoad;
    private int mCurrentPage;
    private LRecyclerViewAdapter<UserMyOrderBean.ObjectListBean> recyclerViewAdapter;
    private boolean isAllLoad;
    private int count;
    private String state;

    public static OrderFragment newInstance(String state) {
        OrderFragment orderFragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("state", state);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        state = getArguments().getString("state");
        localData = new LocalData(context);
        orderClient = new OrderClient();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callFresh();
            }
        }, 500);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<UserMyOrderBean.ObjectListBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.my_order_adapter;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, final UserMyOrderBean.ObjectListBean itemData) throws Exception {

                viewHolder.setText(R.id.order_name, itemData.getCom().getCompany_name());
                viewHolder.setText(R.id.order_status, itemData.getStateStr());
                viewHolder.setText(R.id.order_num, itemData.getOrder_number());
                viewHolder.setText(R.id.order_price, "¥" + String.format("%.2f", itemData.getTotal_cost()) + "元");
                viewHolder.setText(R.id.order_start, itemData.getConsignor_province() + itemData.getConsignor_city() + itemData.getConsignor_county());
                viewHolder.setText(R.id.order_end, itemData.getConsignee_province() + itemData.getConsignee_city() + itemData.getConsignee_county());
                viewHolder.setText(R.id.order_volume, itemData.getTotal_volume() + "m³");
                viewHolder.setText(R.id.order_weight, itemData.getTotal_weight() + "吨");
                viewHolder.setOnClickListener(R.id.orderDetail, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, OrderDetailActivity.class)
                                .putExtra("order_number", itemData.getOrder_number())
                                .putExtra("CompanyName", itemData.getCom().getCompany_name()));
                    }
                });

            }
        };
        recyclerViewAdapter.setOnLoadListener(this);
        return recyclerViewAdapter;
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        isRefresh = true;
        requestData(1);
    }

    @Override
    public void onLoad(int freeItemCount) {
        if (isAllLoad == false && !isRefreshing()) {
            setRefreshing(true);
            setLoading(true);
            isLoad = true;
            requestData(mCurrentPage + 1);
        } else {
            requestFinish();
        }
    }

    private void requestData(int page) {
        if (!NetworkUtil.isConnected(context)) {
            NoNetworkDialog.show(context);
            if (recyclerViewAdapter.getItemCount() == 0) {
                //出现异常并且当前无数据, 显示提示
                showRequestError();
            }
            requestFinish();
            return;
        }

        if (!localData.isLogined()) {
            recyclerViewAdapter.removeDataAll();
//            LToast.show(context, "请先登录");
//            startActivity(LoginActivity.class);
            requestFinish();
            showRequestError();
            return;
        }

        hideRequestError();
        count = 10;
        UserInfo userInfo = localData.readUserInfo();
        mCurrentPage = page;
        orderClient.getList(userInfo.getUser().getId(), state, page, count, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        UserMyOrderBean myOrderBeanWraper = JSON.parseObject(resultInfo.getData(), UserMyOrderBean.class);
                        List<UserMyOrderBean.ObjectListBean> myOrderBeanList = myOrderBeanWraper.getObjectList();
                        if (myOrderBeanList == null) {
                            return;
                        }
                        if (isRefresh) {
                            recyclerViewAdapter.removeDataAll();
                        }
                        recyclerViewAdapter.addDatas(myOrderBeanList);
                        if (recyclerViewAdapter.getDatas().isEmpty()) {
                            showNoDataHint();
                        }
                        if (myOrderBeanList.size() < count) {
                            isAllLoad = true;
                        } else {
                            isAllLoad = false;
                        }
                        break;
                    case ResultCode.FAILURE:
                        showRequestError();
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "" : resultInfo.getMsg());
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                NoNetworkDialog.show(context);
                if (recyclerViewAdapter.getItemCount() == 0) {
                    //出现异常并且当前无数据, 显示提示
                    showRequestError();
                }
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (recyclerViewAdapter.getItemCount() == 0) {
                    //出现异常并且当前无数据, 显示提示
                    showRequestError();
                }
                requestFinish();
            }
        });
    }


    private void requestFinish() {
        isRefresh = false;
        isLoad = false;
        setRefreshing(false);
        setLoading(false);
    }

    /**
     * 显示加载失败提示
     */
    private void showRequestError() {
        View hintErrorView = LayoutInflater.from(context).inflate(R.layout.layout_hint_page_load_failure, null);
        hintErrorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        getBackgroudContainer().removeAllViews();
        getBackgroudContainer().addView(hintErrorView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * 隐藏加载失败失败
     */
    public void hideRequestError() {
        getBackgroudContainer().removeAllViews();
    }
}

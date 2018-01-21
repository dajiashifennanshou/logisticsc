package com.xsl.distributor.lerist.ui.fragment.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.CustomerClient;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.CustomerInfo;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.activity.WaybillDetailsActivity;
import com.xsl.distributor.lerist.ui.dialog.NoNetworkDialog;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LRecyclerViewFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.NetworkUtil;
import com.xsl.lerist.llibrarys.widget.LRecyclerView;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lerist on 2016/7/9, 0009.
 */
public class CustomerListFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {

    private LRecyclerViewAdapter<CustomerInfo> recyclerViewAdapter;
    private LocalData localData;
    private CustomerClient customerClient;

    private int count;
    private int mCurrentPage = 1;
    private boolean isLoad;
    private boolean isRefresh;
    private boolean isAllLoad;
    private boolean select;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initView();
    }

    private void initData() {
        localData = new LocalData(context);
        customerClient = new CustomerClient();
        select = getActivity().getIntent().getBooleanExtra("select", false);
        onRefresh();
    }

    private void initView() {
        if (select) {
            //选择在客户
            recyclerViewAdapter.setOnItemClickListener(new LRecyclerViewAdapter.LOnItemClickListener() {
                @Override
                public void OnClickListener(View view, int position) {
                    CustomerInfo customerInfo = recyclerViewAdapter.getItemData(position);
                    Intent intent = new Intent();
                    intent.putExtra(CustomerInfo.class.getSimpleName(), customerInfo);
                    getActivity().setResult(Activity.RESULT_OK, intent);
                    getActivity().finish();
                }

                @Override
                public void OnLongClickListener(View view, int position) {

                }
            });
        }
    }

    private List<CustomerInfo> getTestDatas() {
        ArrayList<CustomerInfo> customerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            customerInfos.add(new CustomerInfo());
        }
        return customerInfos;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<CustomerInfo>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_list_customer;
            }

            @Override
            public void onBindViewHolder(final LRecyclerViewHolder viewHolder, int position, CustomerInfo itemData) {
                viewHolder.setText(R.id.i_list_customer_tv_name, itemData.getClientName());
                viewHolder.setText(R.id.i_list_customer_tv_phone, itemData.getPhone());
                viewHolder.setText(R.id.i_list_customer_tv_addr, itemData.getAddress());
                LRecyclerView view = viewHolder.getView(R.id.customer_listview, LRecyclerView.class);
                view.setLayoutManager(new LinearLayoutManager(context));
                final LRecyclerViewAdapter<CustomerInfo.ListBean> adapter = new LRecyclerViewAdapter<CustomerInfo.ListBean>(context) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
                        return R.layout.my_order_item;
                    }

                    @Override
                    public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, CustomerInfo.ListBean itemData)
                            throws Exception {
                        viewHolder.setText(R.id.i_list_customer_tv_order, itemData.getDeliveryNo());
                    }
                };
                view.setDisableClick(select);
                view.setAdapter(adapter);
                adapter.addDatas(itemData.getList());
                adapter.setOnItemClickListener(new LOnItemClickListener() {
                    @Override
                    public void OnClickListener(View view, int position) {
                        Intent intent = new Intent(context, WaybillDetailsActivity.class);
                        intent.putExtra("deliveryNo", adapter.getItemData(position).getDeliveryNo());
                        startActivity(intent);
                    }

                    @Override
                    public void OnLongClickListener(View view, int position) {

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
        if (!isAllLoad && !isRefreshing()) {
            setRefreshing(true);
            isLoad = true;
            requestData(mCurrentPage++);
        }else {
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
            requestFinish();
            return;
        }
        hideRequestError();
        count = 20;
        UserInfo userInfo = localData.readUserInfo();
        mCurrentPage = page;
        customerClient.getList(userInfo.getUser().getId(), page, count, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        List<CustomerInfo> deliveryOrders = JSON.parseArray(String.valueOf(resultInfo.getData()), CustomerInfo.class);
                        if (deliveryOrders == null) {
                            return;
                        }
                        if (isRefresh) {
                            recyclerViewAdapter.removeDataAll();
                        }
                        recyclerViewAdapter.addDatas(deliveryOrders);
                        if (recyclerViewAdapter.getDatas().isEmpty()){
                            showNoDataHint();
                        }
                        if (deliveryOrders.size() < count) {
                            isAllLoad = true;
                        } else {
                            isAllLoad = false;
                        }
                        break;
                    case ResultCode.FAILURE:

                        break;
                    case ResultCode.NOPERMISSION:
                        UserClient.login(context);
                        break;  }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                NoNetworkDialog.show(context);
                if (recyclerViewAdapter.getItemCount() == 0) {
                    //出现异常并且当前无数据, 显示提示
                    showRequestError();
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                requestFinish();
            }
        });
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

    private void requestFinish() {
        isRefresh = false;
        isLoad = false;
        setRefreshing(false);
        setLoading(false);
    }
}

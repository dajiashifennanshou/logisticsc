package com.xsl.distributor.lerist.ui.fragment.user;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.CostClient;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.CostInfo;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.activity.WaybillDetailsActivity;
import com.xsl.distributor.lerist.ui.dialog.NoNetworkDialog;
import com.xsl.distributor.ws.bean.InstallOrderConstListBean;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LRecyclerViewFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.NetworkUtil;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lerist on 2016/7/9, 0009.
 */
public class InstallCostListFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {

    @BindView(R.id.l_cost_time_filter_tv_time_start)
    TextView tv_time_start;
    @BindView(R.id.l_cost_time_filter_tv_time_end)
    TextView tv_time_end;

    private LRecyclerViewAdapter<InstallOrderConstListBean> recyclerViewAdapter;

    /**
     * 请求的页面
     */
    int page = 0;
    private CostClient costClient;
    private boolean isRefresh;
    private boolean isLoad;
    private LocalData localData;
    private int mCurrentPage = 1;
    private int count;
    private boolean isAllLoad;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        costClient = new CostClient();
        localData = new LocalData(context);

        onRefresh();
    }

    private void initView() {

        View timeFilter = View.inflate(context, R.layout.layout_cost_time_filter, null);
        ButterKnife.bind(this, timeFilter);

        getHeadContainer().removeAllViews();
        getHeadContainer().addView(timeFilter);
        tv_time_end.setText(Calendar.getInstance().get(Calendar.YEAR) + "-" +
                (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" +
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

//        recyclerViewAdapter.addDatas(getTestDatas());
    }

    private List<CostInfo> getTestDatas() {
        ArrayList<CostInfo> costInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            costInfos.add(new CostInfo());
        }
        return costInfos;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<InstallOrderConstListBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_list_cost_install;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, InstallOrderConstListBean itemData) throws Exception {
                viewHolder.setText(R.id.i_list_cost_tv_no, itemData.getDeliveryNo());
                if (itemData.getAgentPaidCost() == 0.0) {
                    viewHolder.getView(R.id.i_list_cost_tv_money_goods).setVisibility(View.INVISIBLE);
                    viewHolder.getView(R.id.daishou).setVisibility(View.INVISIBLE);
                } else {
                    viewHolder.getView(R.id.i_list_cost_tv_money_goods).setVisibility(View.VISIBLE);
                    viewHolder.getView(R.id.daishou).setVisibility(View.VISIBLE);
                    viewHolder.setText(R.id.i_list_cost_tv_money_goods, itemData.getAgentPaidCost() + " 元");
                }
                viewHolder.setText(R.id.i_list_cost_tv_money_transmit, itemData.getDeliveryCost() + " 元");
                viewHolder.setText(R.id.i_list_cost_tv_time, StringUtils.toTimeStr(Long.parseLong(itemData.getCreateTime()), "yyyy年MM月dd日"));
            }
        };

        recyclerViewAdapter.setOnItemClickListener(new LRecyclerViewAdapter.LOnItemClickListener() {
            @Override
            public void OnClickListener(View view, int position) {
                Intent intent = new Intent(context, WaybillDetailsActivity.class);
                intent.putExtra("deliveryNo", recyclerViewAdapter.getItemData(position).getDeliveryNo());
                startActivity(intent);
            }

            @Override
            public void OnLongClickListener(View view, int position) {

            }
        });
        recyclerViewAdapter.setOnLoadListener(this);
        return recyclerViewAdapter;
    }


    @OnClick({R.id.l_cost_time_filter_tv_time_start, R.id.l_cost_time_filter_tv_time_end, R.id.l_cost_time_filter_btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.l_cost_time_filter_tv_time_start:
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv_time_start.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        long selectStartTimeMilliseconds = StringUtils.toTimeMilliseconds(tv_time_start.getText().toString(), "yyyy-MM-dd");
                        //控制开始时间在今天及之前
                        if (selectStartTimeMilliseconds > System.currentTimeMillis()) {
                            tv_time_start.setText("");
                            LToast.show(context, "选择日期超过今日, 请重新选择");
                            return;
                        }
                        //控制开始时间在结束时间之前
                        long selectEndTimeMilliseconds = StringUtils.toTimeMilliseconds(tv_time_end.getText().toString(), "yyyy-MM-dd");
                        if (selectEndTimeMilliseconds != 0 && selectStartTimeMilliseconds > selectEndTimeMilliseconds) {
                            tv_time_start.setText("");
                            LToast.show(context, "选择日期范围错误, 请重新选择");
                            return;
                        }
                    }
                }, Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.l_cost_time_filter_tv_time_end:
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv_time_end.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        long selectStartTimeMilliseconds = StringUtils.toTimeMilliseconds(tv_time_start.getText().toString(), "yyyy-MM-dd");
                        long selectEndTimeMilliseconds = StringUtils.toTimeMilliseconds(tv_time_end.getText().toString(), "yyyy-MM-dd");
                        //控制结束时间在开始时间之后
                        if (selectStartTimeMilliseconds > selectEndTimeMilliseconds) {
                            tv_time_end.setText("");
                            LToast.show(context, "选择日期范围错误, 请重新选择");
                            return;
                        }
                    }
                }, Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.l_cost_time_filter_btn_ok:
                onRefresh();
                break;
        }
    }


    @Override
    public void onRefresh() {
        super.onRefresh();
        if (isCreated()) {
            isRefresh = true;
            requestData(1);
        }
    }

    @Override
    public void onLoad(int freeItemCount) {
        if (!isAllLoad && !isRefreshing()) {
            setRefreshing(true);
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
            requestFinish();
            return;
        }
        hideRequestError();
        count = 20;
        UserInfo userInfo = localData.readUserInfo();
        mCurrentPage = page;
        costClient.getInstallList(userInfo.getUser().getId(), page, count,
                StringUtils.toTimeMilliseconds(tv_time_start.getText().toString(), "yyyy-MM-dd"),
                StringUtils.toTimeMilliseconds(tv_time_end.getText().toString(), "yyyy-MM-dd"), new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                List<InstallOrderConstListBean> deliveryOrders = JSON.parseArray(String.valueOf(resultInfo.getData()), InstallOrderConstListBean.class);
                                if (deliveryOrders == null) {
                                    return;
                                }
                                if (isRefresh) {
                                    recyclerViewAdapter.removeDataAll();
                                }
                                recyclerViewAdapter.addDatas(deliveryOrders);
                                if (recyclerViewAdapter.getDatas().isEmpty()) {
                                    showNoDataHint();
                                }
                                if (deliveryOrders.size() < count) {
                                    isAllLoad = true;
                                } else {
                                    isAllLoad = false;
                                }
                                break;
                            case ResultCode.FAILURE:
                                showRequestError();
                                LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "" : resultInfo.getMsg());
                                break;
                            case ResultCode.NOPERMISSION:
                                UserClient.login(context);
                                break;
                        }
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

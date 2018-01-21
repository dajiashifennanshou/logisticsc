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
import com.xsl.distributor.lerist.model.SpecialLineCostInfo;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.dialog.NoNetworkDialog;
import com.xsl.distributor.ws.ui.activity.LogisticDetailActivity;
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
 * 专线费用流水
 */
public class SpecialLineCostListFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {

    @BindView(R.id.l_cost_time_filter_tv_time_start)
    TextView tv_time_start;
    @BindView(R.id.l_cost_time_filter_tv_time_end)
    TextView tv_time_end;

    private LRecyclerViewAdapter<SpecialLineCostInfo> recyclerViewAdapter;


    /**
     * 请求的页面
     */
    int page = 0;
    private boolean isRefresh;
    private boolean isLoad;
    private int count;
    private LocalData localData;
    private CostClient costClient;
    private int mCurrentPage = 1;
    private boolean isAllLoad;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        localData = new LocalData(context);
        costClient = new CostClient();
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
        recyclerViewAdapter = new LRecyclerViewAdapter<SpecialLineCostInfo>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_list_cost_specialline;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, SpecialLineCostInfo itemData) {
                String way_bill_number = itemData.getWay_bill_number() == null ? "" : itemData.getWay_bill_number();
                viewHolder.setText(R.id.i_list_cost_tv_no, itemData.getOrder_number() == null ? way_bill_number : itemData.getOrder_number());
//                viewHolder.setText(R.id.i_list_cost_tv_no, itemData.getWay_bill_number() == null ? "" : itemData.getWay_bill_number());
                viewHolder.setText(R.id.i_list_cost_tv_money_goods, itemData.getIs_payment() == 1 ? "应付费用:  " + itemData.getPaid_price() + " 元"
                        : "专线费用:  " + itemData.getFinal_price() + " 元");
//                viewHolder.getView(R.id.i_list_cost_btn_pay).setVisibility(itemData.getIs_payment() == 1 ? View.VISIBLE : View.GONE);
                viewHolder.setText(R.id.i_list_cost_tv_time, StringUtils.toTimeStr(Long.parseLong(itemData.getOrder_time()), "yyyy年MM日dd日"));

            }
        };

        recyclerViewAdapter.setOnItemClickListener(new LRecyclerViewAdapter.LOnItemClickListener() {
            @Override
            public void OnClickListener(View view, int position) {
                // 专线运单详情
                Intent intent = new Intent(context, LogisticDetailActivity.class);
                intent.putExtra("order_number", recyclerViewAdapter.getItemData(position).getOrder_number());
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
        isRefresh = true;
        requestData(1);
    }

    @Override
    public void onLoad(int freeItemCount) {
        if (!isAllLoad && !isRefreshing()) {
            isLoad = true;
            setRefreshing(true);
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
        costClient.getSpecialLineList(userInfo.getId(),
                page,
                count,
                StringUtils.toTimeMilliseconds(tv_time_start.getText().toString(), "yyyy-MM-dd"),
                StringUtils.toTimeMilliseconds(tv_time_end.getText().toString(), "yyyy-MM-dd"),
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                List<SpecialLineCostInfo> specialLineCostInfos = JSON.parseArray(String.valueOf(resultInfo.getData()), SpecialLineCostInfo.class);
                                if (specialLineCostInfos == null) {
                                    return;
                                }
                                if (isRefresh) {
                                    recyclerViewAdapter.removeDataAll();
                                }
                                recyclerViewAdapter.addDatas(specialLineCostInfos);
                                if (recyclerViewAdapter.getDatas().isEmpty()) {
                                    showNoDataHint();
                                }
                                if (specialLineCostInfos.size() < count) {
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
                                break;   }
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

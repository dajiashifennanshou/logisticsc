package com.wrt.xinsilu.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.LogisticBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.client.GetLineInfoClient;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.LogisticsClient;
import com.wrt.xinsilu.ui.activity.CommentDetailActivity;
import com.wrt.xinsilu.ui.activity.GoodsInformationActivity;
import com.wrt.xinsilu.ui.dialog.NoNetworkDialog;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LRecyclerViewFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.NetworkUtil;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class LineSearchFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {
    private ListView lv;
    private View mView;
    /**
     * 保存的后台传过来的数据
     */
    private List<LogisticBean> list;
    /**
     * 存放物流商信息
     */
    private LogisticBean bean;

    private LocalData localData;
    private GetLineInfoClient client;
    private boolean isRefresh;
    private boolean isLoad;
    private int mCurrentPage;
    private LRecyclerViewAdapter<LogisticBean.ObjectListBean> recyclerViewAdapter;
    private boolean isAllLoad;
    private int count;
    String startProvincePid;
    String startCityPid;
    String endProvincePid;
    String endCityPid;
    double Latitude;
    double longitude;

    public static LineSearchFragment newInstance(String startProvincePid,
                                                 String startCityPid,
                                                 String endProvincePid,
                                                 String endCityPid,
                                                 double Latitude,
                                                 double longitude) {
        LineSearchFragment orderFragment = new LineSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("startProvincePid", startProvincePid);
        bundle.putString("startCityPid", startCityPid);
        bundle.putString("endProvincePid", endProvincePid);
        bundle.putString("endCityPid", endCityPid);
        bundle.putDouble("Latitude", Latitude);
        bundle.putDouble("longitude", longitude);
        orderFragment.setArguments(bundle);
        return orderFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        startProvincePid = getArguments().getString("startProvincePid");
        startCityPid = getArguments().getString("startCityPid");
        endProvincePid = getArguments().getString("endProvincePid");
        endCityPid = getArguments().getString("endCityPid");
        Latitude = getArguments().getDouble("Latitude");
        longitude = getArguments().getDouble("longitude");
        KLog.i("\n" + startProvincePid + "\n" + startCityPid + "\n" + endProvincePid + "\n" + endCityPid + "\n" + Latitude + "\n" + longitude);
        localData = new LocalData(context);
        client = new GetLineInfoClient();

        LProgressDialog.show(context, "");
        onRefresh();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<LogisticBean.ObjectListBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.line_search_adapter;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, final int position, final LogisticBean.ObjectListBean itemData) throws Exception {
                viewHolder.setText(R.id.company_name, itemData.getCompanyName());
                viewHolder.setText(R.id.line_time, itemData.getTime_long() + "小时");
                viewHolder.setText(R.id.weight, "￥" + itemData.getHeavy_cargo_price_low() + "/吨");
                viewHolder.setText(R.id.service_type, itemData.getServer_type());
                viewHolder.setText(R.id.foam, "￥" + itemData.getBulky_cargo_price_low() + "/立方米");
                viewHolder.setText(R.id.transport_way, itemData.getTransport_mode());
                viewHolder.setText(R.id.lower_money, "￥" + itemData.getLowest_price() + "/票");
                viewHolder.setText(R.id.tranport_num, "已承运" + itemData.getCountOrder() + "票");
                viewHolder.getView(R.id.position).setVisibility(View.VISIBLE);
                viewHolder.setText(R.id.position, String.format("%.2f", itemData.getDistance() / 1000f) + "km");
                viewHolder.setText(R.id.comment_detail, "已有" + itemData.getCountOrderEvaluation() + "条评论");
                /**打电话*/
                viewHolder.setOnClickListener(R.id.dail_number, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + itemData.getContactsMobile());
                        intent.setData(data);
                        context.startActivity(intent);
                    }
                });
                /**评论详情*/
                viewHolder.setOnClickListener(R.id.comment_detail, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, CommentDetailActivity.class)
                                .putExtra("CommentNumber", itemData.getCountOrderEvaluation())
                                .putExtra("lineId", itemData.getId()));
                    }
                });
                /**为0表示已收藏*/
                if (itemData.getIsCollect() == 0) {
                    viewHolder.setImageResource(R.id.collect, R.mipmap.common_collection_nor);
                } else {
                    viewHolder.setImageResource(R.id.collect, R.mipmap.common_collection_shixiao);
                }
                viewHolder.setOnClickListener(R.id.collect, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (itemData.getIsCollect() == 0) {
                            uncollect(view, itemData);
                        } else {
                            collect(view, itemData);
                        }
                    }
                });
                // TODO: 2016/8/8 0008 这里判断下单
                if (itemData.getIsAddServer() == 1) {
                    viewHolder.setImageResource(R.id.commit,R.mipmap.my_collection_xiadan_shixiao);
                    viewHolder.getView(R.id.commit).setEnabled(false);
                } else {
                    viewHolder.getView(R.id.commit).setEnabled(true);
                    viewHolder.setOnClickListener(R.id.commit, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, GoodsInformationActivity.class);
                            intent.putExtra("tms_line_id", itemData.getId());
                            intent.putExtra("sendNet", itemData.getStart_outlets());
                            intent.putExtra("ainpickNet", itemData.getEnd_outlets());
                            startActivity(intent);
                        }
                    });
                }
            }
        };
        return recyclerViewAdapter;
    }

    private void collect(final View view, final LogisticBean.ObjectListBean itemData) {
        UserInfo userInfo = localData.readUserInfo();
        if (userInfo == null) return;

        new LogisticsClient().addCollectCompany(userInfo.getUser().getId(),
                itemData.getCompanyId(),
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                LToast.show(context, "已收藏");
                                itemData.setIsCollect(0);
                                ((android.widget.ImageView) view).setImageResource(R.mipmap.common_collection_nor);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ex.printStackTrace();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    private void uncollect(final View view, final LogisticBean.ObjectListBean itemData) {
        UserInfo userInfo = localData.readUserInfo();
        if (userInfo == null) return;

        new LogisticsClient().deleteLogistic(
                localData.readUserInfo().getUser().getId(),
                itemData.getCompanyId(),
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                itemData.setIsCollect(1);
                                ((android.widget.ImageView) view).setImageResource(R.mipmap.common_collection_shixiao);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ex.printStackTrace();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
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
            LToast.show(context, "请先登录");
//            startActivity(LoginActivity.class);
            requestFinish();
            showRequestError();
            return;
        }

        hideRequestError();
        count = 10;
        UserInfo userInfo = localData.readUserInfo();
        mCurrentPage = page;
        client.getLineInfo(Common.LINE_INFO,
                startProvincePid,
                startCityPid,
                endProvincePid,
                endCityPid,
                longitude,
                Latitude,
                localData.readUserInfo().getUser().getId(),
                page, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                LogisticBean myOrderBeanList = JSON.parseObject(String.valueOf(resultInfo.getData()), LogisticBean.class);
                                if (myOrderBeanList == null) {
                                    return;
                                }
                                if (isRefresh) {
                                    recyclerViewAdapter.removeDataAll();
                                }
                                recyclerViewAdapter.addDatas(myOrderBeanList.getObjectList());
                                if (recyclerViewAdapter.getDatas().isEmpty()) {
                                    showNoDataHint();
                                }
                                if (myOrderBeanList.getObjectList().size() < count) {
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
        LProgressDialog.dismiss();
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

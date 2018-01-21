package com.wrt.xinsilu.lerist.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.daimajia.swipe.SwipeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.CollctionLogisticAdapter;
import com.wrt.xinsilu.bean.CollectionLogisticBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.LogisticsClient;
import com.wrt.xinsilu.ui.activity.LogisticLineDetailActivity;
import com.wrt.xinsilu.ui.dialog.NoNetworkDialog;
import com.wrt.xinsilu.ui.view.MyListView;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LRecyclerViewFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.NetworkUtil;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.List;

/**
 * Created by Lerist on 2016/8/4, 0004.
 */

public class CollectionLogisticsFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {

    private LRecyclerViewAdapter<CollectionLogisticBean> recyclerViewAdapter;
    private boolean isRefresh;
    private boolean isAllLoad;
    private boolean isLoad;
    private int mCurrentPage;
    private LocalData localData;
    private LogisticsClient logisticsClient;
    private int count;
    private boolean isEditable;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        localData = new LocalData(context);
        logisticsClient = new LogisticsClient();
        callFresh();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<CollectionLogisticBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_collection_logistics;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, final int position, final CollectionLogisticBean itemData) throws Exception {
                SwipeLayout swipeLayout = viewHolder.getView(R.id.i_colection_logistics_sl, SwipeLayout.class);
                if (isEditable) {
                    swipeLayout.setSwipeEnabled(false);
                    swipeLayout.open(true);
                } else {
                    swipeLayout.setSwipeEnabled(false);
                    swipeLayout.close(true);
                }
                viewHolder.setText(R.id.collection_name, itemData.getCom().getCompany_name());
                ((SimpleDraweeView) viewHolder.getView(R.id.logistic_icon)).setImageURI(Common.GET_COMPANY_IMG + itemData.getCom().getLogo());
                ((MyListView) viewHolder.getView(R.id.logistics_listview)).setAdapter(new CollctionLogisticAdapter(context, itemData.getCom().getList()));
//                viewHolder.setText(R.id.destance, itemData.getCom().);
                viewHolder.setOnClickListener(R.id.logistics_detail, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, LogisticLineDetailActivity.class);
                        intent.putExtra("CompanyId", itemData.getComId());
                        intent.putExtra("CompanyName", itemData.getCom().getCompany_name());

                        context.startActivity(intent);
                    }
                });
                viewHolder.setOnClickListener(R.id.i_colection_logistics_btn_del, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete(itemData);
                    }
                });

            }
        };
        recyclerViewAdapter.setOnLoadListener(this);
        return recyclerViewAdapter;
    }

    private void delete(final CollectionLogisticBean bean) {
        new AlertDialog.Builder(context).setTitle("提示")
                .setMessage("确认删除该条收藏?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        logisticsClient.deleteLogistic(bean.getId(), new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                KLog.i(result);
                                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                if (resultInfo == null) return;

                                switch (resultInfo.getCode()) {
                                    case ResultCode.SUCCEED:
                                        LToast.show(context, "已删除");
                                        recyclerViewAdapter.removeData(bean);
                                        break;
                                    default:
                                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "删除失败, 请稍后再试" : resultInfo.getMsg());
                                        break;
                                }
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                LToast.show(context, "删除失败, 请稍后再试");

                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                    }
                }).show();
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
        logisticsClient.getList(userInfo.getUser().getId(), page, count, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        List<CollectionLogisticBean> logisticsCompanyInfos = JSON.parseArray(String.valueOf(resultInfo.getData()), CollectionLogisticBean.class);
                        if (logisticsCompanyInfos == null) {
                            return;
                        }
                        if (isRefresh) {
                            recyclerViewAdapter.removeDataAll();
                        }
                        recyclerViewAdapter.addDatas(logisticsCompanyInfos);
                        if (recyclerViewAdapter.getDatas().isEmpty()) {
                            showNoDataHint();
                        }
                        if (logisticsCompanyInfos.size() < count) {
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

    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
        recyclerViewAdapter.notifyDataSetChanged();
    }

    public boolean isEditable() {
        return isEditable;
    }
}

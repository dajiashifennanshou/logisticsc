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
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.CollectionLineBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.LineClient;
import com.wrt.xinsilu.ui.activity.CommentDetailActivity;
import com.wrt.xinsilu.ui.activity.GoodsInformationActivity;
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
 * Created by Lerist on 2016/8/4, 0004.
 */

public class CollectionLineFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {

    private LRecyclerViewAdapter<CollectionLineBean> recyclerViewAdapter;
    private boolean isRefresh;
    private boolean isAllLoad;
    private boolean isLoad;
    private int mCurrentPage;
    private LocalData localData;
    private int count;
    private boolean isEditable;
    private LineClient lineClient;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        localData = new LocalData(context);
        lineClient = new LineClient();
        callFresh();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<CollectionLineBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_collection_line;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, final CollectionLineBean itemData) throws Exception {
                SwipeLayout swipeLayout = viewHolder.getView(R.id.i_colection_line_sl, SwipeLayout.class);
                if (isEditable) {
                    swipeLayout.setSwipeEnabled(false);
                    swipeLayout.open(true);
                } else {
                    swipeLayout.setSwipeEnabled(false);
                    swipeLayout.close(true);
                }
                viewHolder.setText(R.id.start, itemData.getLine().getStart_outlets_name());
                viewHolder.setText(R.id.end, itemData.getLine().getEnd_outlets_name());
                viewHolder.setText(R.id.line_time, itemData.getLine().getTime_long() + " 小时");
                viewHolder.setText(R.id.weight, "￥" + itemData.getLine().getHeavy_cargo_price_low() + "元");
                viewHolder.setText(R.id.service_type, itemData.getLine().getServer_type() + "元");
                viewHolder.setText(R.id.foam, "￥" + itemData.getLine().getBulky_cargo_price_low() + "元");
                viewHolder.setText(R.id.transport_way, itemData.getLine().getTransport_mode());
                viewHolder.setText(R.id.lower_money, "￥" + itemData.getLine().getLowest_price() + "元");
                viewHolder.setText(R.id.tranport_num, "已承运" + itemData.getCountOrder() + "票");
                viewHolder.setText(R.id.comment_detail, "已有" + itemData.getCountOrderEvaluation() + "条评价");
                viewHolder.setText(R.id.start, itemData.getLine().getStart_outlets_name());
                /**模拟0表示不能下单，1表示可以下单*/
                /**评论详情*/
                viewHolder.setOnClickListener(R.id.comment_detail, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, CommentDetailActivity.class)
                                .putExtra("CommentNumber", itemData.getCountOrderEvaluation())
                                .putExtra("lineId", itemData.getId()));
                    }
                });

                viewHolder.setOnClickListener(R.id.i_colection_line_btn_del, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete(itemData);
                    }
                });
                if (itemData.getLine().getIsAddServer() == 1) {
                    // TODO: 不能点击，要换一个图标
//                    viewHolder.getView(R.id.xianshi).setVisibility(View.VISIBLE);
                    viewHolder.setImageResource(R.id.commit, R.mipmap.my_collection_xiadan_shixiao);
                    viewHolder.getView(R.id.commit).setEnabled(false);
                } else{
                    viewHolder.getView(R.id.commit).setEnabled(true);
                    viewHolder.setImageResource(R.id.commit, R.mipmap.my_collection_xiadan_nor);
//                    viewHolder.getView(R.id.xianshi).setVisibility(View.INVISIBLE);
                    viewHolder.setOnClickListener(R.id.commit, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, GoodsInformationActivity.class);
                            intent.putExtra("tms_line_id", itemData.getLine().getId());
                            intent.putExtra("sendNet", itemData.getLine().getStart_outlets());
                            intent.putExtra("ainpickNet", itemData.getLine().getEnd_outlets());
                            startActivity(intent);
                        }
                    });
                }
            }
        };
        return recyclerViewAdapter;
    }

    private void delete(final CollectionLineBean lineBean) {
        new AlertDialog.Builder(context).setTitle("提示")
                .setMessage("确认删除该条线路?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lineClient.deleteCollectLine(lineBean.getId(), new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                KLog.i(result);
                                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                if (resultInfo == null) return;

                                switch (resultInfo.getCode()) {
                                    case ResultCode.SUCCEED:
                                        LToast.show(context, "已删除");
                                        recyclerViewAdapter.removeData(lineBean);
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
        lineClient.getCollectList(userInfo.getUser().getId(), page, count, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        List<CollectionLineBean> logisticsCompanyInfos = JSON.parseArray(String.valueOf(resultInfo.getData()), CollectionLineBean.class);
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

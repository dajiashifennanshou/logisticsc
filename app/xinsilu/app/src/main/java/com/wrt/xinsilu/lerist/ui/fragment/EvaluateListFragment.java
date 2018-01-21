package com.wrt.xinsilu.lerist.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.CommentDetailBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.client.CommentClient;
import com.wrt.xinsilu.data.LocalData;
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
 * Created by Lerist on 2016/8/19, 0019.
 */

public class EvaluateListFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {

    private LRecyclerViewAdapter<CommentDetailBean> recyclerViewAdapter;
    private LocalData localData;
    private CommentClient commentClient;
    private int count;
    private int mCurrentPage;
    private boolean isRefresh;
    private boolean isAllLoad;
    private boolean isLoad;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callFresh();
            }
        }, 500);
    }

    private void initData() {
        localData = new LocalData(context);
        commentClient = new CommentClient();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<CommentDetailBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.comment_detail_adapter;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, CommentDetailBean itemData) throws Exception {

                viewHolder.setText(R.id.company_name, itemData.getUser().getTrue_name().equals("")
                        ? itemData.getUser().getLogin_name()
                        : itemData.getUser().getTrue_name());
                try {
                    viewHolder.setText(R.id.comment_date, StringUtils.toTimeStr(Long.parseLong(itemData.getEvaluate_time()), "yyyy-MM-dd"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                viewHolder.setText(R.id.comment_detail, itemData.getEvaluate_content());
                ((RatingBar) viewHolder.getView(R.id.RatingBar)).setRating(itemData.getEvaluate_level());
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
        commentClient.getComment(getActivity().getIntent().getIntExtra("lineId", 0),
                userInfo.getUser().getId(), page, count,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                CommentDetailBean.Wrapper wrapper = JSON.parseObject(resultInfo.getData(), CommentDetailBean.Wrapper.class);
                                List<CommentDetailBean> logisticsCompanyInfos = wrapper.getObjectList();
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

}

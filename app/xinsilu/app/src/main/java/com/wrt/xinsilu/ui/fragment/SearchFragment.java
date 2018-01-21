package com.wrt.xinsilu.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.daimajia.swipe.SwipeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.RecomendWayAdapter;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.client.GetLogisticClient;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.model.LogisticsCompanyInfo;
import com.wrt.xinsilu.ui.activity.LogisticLineDetailActivity;
import com.wrt.xinsilu.ui.dialog.NoNetworkDialog;
import com.wrt.xinsilu.ui.view.MyListView;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LRecyclerViewFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.ListUtils;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.TextView;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class SearchFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener, TextView.OnEditorActionListener {


    @BindView(R.id.input_company)
    EditText inputCompany;

    private LRecyclerViewAdapter<LogisticsCompanyInfo.ObjectListBean> adapter;
    private GetLogisticClient client;
    private int count = 20;
    private boolean isAllLoad;
    private String keyword;
    private int mCurrentPage = 1;
    String province;
    String city;
    double Latitude;
    double longitude;
    private String company_name;
    private LocalData localData;

    public static SearchFragment newInstance(String startProvincePid,
                                             String startCityPid,
                                             String company_name,
                                             double Latitude,
                                             double longitude) {
        SearchFragment orderFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("province", startProvincePid);
        bundle.putString("city", startCityPid);
        bundle.putString("company_name", company_name);
        bundle.putDouble("Latitude", Latitude);
        bundle.putDouble("longitude", longitude);

        orderFragment.setArguments(bundle);
        return orderFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {
        province = getArguments().getString("province");
        city = getArguments().getString("city");
        keyword = getArguments().getString("company_name");
        Latitude = getArguments().getDouble("Latitude");
        longitude = getArguments().getDouble("longitude");
        KLog.i("\n" + province + "\n" + city + "\n" + Latitude + "\n" + longitude);
        localData = new LocalData(context);
        client = new GetLogisticClient();
        inputCompany.setText(keyword);
        inputCompany.setOnEditorActionListener(this);
        onRefresh();

    }

    private void initView() {
        setRefreshEnabled(true);
        getHeadContainer().addView(View.inflate(context, R.layout.search_layout_top, null));
        ButterKnife.bind(this, getHeadContainer());

    }

    @Override
    public boolean onEditorAction(android.widget.TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            InputMethodManager imm = (InputMethodManager) v
                    .getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(
                        v.getApplicationWindowToken(), 0);
            }
            /****************这里是网络请求**********************/
            keyword = inputCompany.getText().toString();
            if (StringUtils.isEmpty(keyword)) {
                return false;
            }
            super.onRefresh();
            int page = 1;
            requestData(inputCompany.getText().toString(), page);

        }
        return false;
    }

    private void requestData(String keyword, int page) {
        mCurrentPage = page;
        LProgressDialog.show(context, "");
        adapter.removeDataAll();
        hideRequestError();
        client.getLogistic(Common.GET_COMPANY_INFO,
                keyword,
                province,
                city,
                longitude,
                Latitude,
                page,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.json(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                LogisticsCompanyInfo logisticsCompanyInfos = JSON.parseObject(String.valueOf(resultInfo.getData()), LogisticsCompanyInfo.class);
                                if (ListUtils.isEmpty(logisticsCompanyInfos.getObjectList())) {
                                    showNoResultHint();
                                    return;
                                }
                                adapter.addDatas(logisticsCompanyInfos.getObjectList());
                                if (adapter.getDatas().isEmpty()) {
                                    showNoDataHint();
                                }
                                if (logisticsCompanyInfos.getObjectList().size() < count) {
                                    isAllLoad = true;
                                } else {
                                    isAllLoad = false;
                                }
                                break;
                            case ResultCode.FAILURE:
                                showRequestError();
                                LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "查询错误" : resultInfo.getMsg());
                                break;
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        NoNetworkDialog.show(context);
                        if (adapter.getItemCount() == 0) {
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
                        setRefreshing(false);
                        setLoading(false);
                        LProgressDialog.dismiss();
                        if (adapter.getItemCount() == 0) {
//                    showNoResultHint();
                        }
                    }
                });
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        adapter = new LRecyclerViewAdapter<LogisticsCompanyInfo.ObjectListBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_collection_logistics;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, final LogisticsCompanyInfo.ObjectListBean itemData) throws Exception {
                SwipeLayout swipeLayout = viewHolder.getView(R.id.i_colection_logistics_sl, SwipeLayout.class);
                swipeLayout.setSwipeEnabled(false);
                swipeLayout.close(true);
                viewHolder.setText(R.id.collection_name, itemData.getCompany_name());
                ((SimpleDraweeView) viewHolder.getView(R.id.logistic_icon)).setImageURI(Common.GET_COMPANY_IMG + itemData.getLogo());
                ((MyListView) viewHolder.getView(R.id.logistics_listview)).setAdapter(new RecomendWayAdapter(context, itemData.getList()));
//                viewHolder.setText(R.id.destance, itemData.getDistance());
                viewHolder.setOnClickListener(R.id.logistics_detail, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, LogisticLineDetailActivity.class);
                        intent.putExtra("CompanyId", itemData.getId());
                        intent.putExtra("CompanyName", itemData.getCompany_name());
                        context.startActivity(intent);
                    }
                });
            }
        };
        adapter.setOnLoadListener(this);
        return adapter;
    }


    private void showNoResultHint() {
        getBackgroudContainer().removeAllViews();
        getBackgroudContainer().addView(View.inflate(context, R.layout.layout_hint_page_load_no_info, null),
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * 显示加载失败提示
     */
    private void showRequestError() {
        View hintErrorView = LayoutInflater.from(context)
                .inflate(R.layout.layout_hint_page_load_failure, null);
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

    @Override
    public void onLoad(int freeItemCount) {
        if (!isRefreshing() && !isAllLoad) {
            if (StringUtils.isEmpty(keyword)) {
                return;
            }
            setRefreshing(true);
            requestData(keyword, mCurrentPage++);
        } else {
            setLoading(false);
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mCurrentPage = 1;
        requestData(keyword, mCurrentPage);
    }
}

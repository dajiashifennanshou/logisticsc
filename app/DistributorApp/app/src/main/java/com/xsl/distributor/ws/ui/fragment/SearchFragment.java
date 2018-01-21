package com.xsl.distributor.ws.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.OtherClient;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.ui.dialog.NoNetworkDialog;
import com.xsl.distributor.ws.bean.SearchResultBean;
import com.xsl.distributor.ws.params.LogisticDtailParams;
import com.xsl.distributor.ws.ui.activity.LogisticDetailActivity;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LRecyclerViewFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;

import org.xutils.common.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.TextView;

/**
 * Created by Administrator on 2016/7/15 0015.
 */
public class SearchFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {
    /**
     * 搜索内容
     */
    @BindView(R.id.search_content)
    EditText searchContent;
    /**
     * 取消/查找
     */
    @BindView(R.id.cancle)
    TextView cancle;

    private LRecyclerViewAdapter<SearchResultBean> adapter;
    private OtherClient otherClient;
    private int count = 20;
    private boolean isAllLoad;
    private String keyword;
    private int mCurrentPage = 1;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initView();

    }

    private void initData() {
        otherClient = new OtherClient();
    }

    private void initView() {
        setRefreshEnabled(false);

        getHeadContainer().addView(View.inflate(context, R.layout.search_layout_top, null));
        ButterKnife.bind(this, getHeadContainer());
        searchContent.setOnEditorActionListener(new android.widget.TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(android.widget.TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        search();
                        break;
                }
                return true;
            }
        });
    }

    private void search() {
        keyword = searchContent.getText().toString();
        if (StringUtils.isEmpty(keyword)) {
            return;
        }

        super.onRefresh();
        int page = 1;
        requestData(searchContent.getText().toString(), page);
    }

    private void requestData(String keyword, int page) {
        mCurrentPage = page;
        LProgressDialog.show(context, "");
        adapter.removeDataAll();
        hideRequestError();
        new LogisticDtailParams().join(keyword, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        SearchResultBean bean = JSON.parseObject(String.valueOf(resultInfo.getData()), SearchResultBean.class);
                        if (bean == null) {
                            return;
                        }
                        adapter.removeDataAll();
                        adapter.addData(bean);
//
//                        if (bean.size() < count) {
//                            isAllLoad = true;
//                        } else {
//                            isAllLoad = false;
//                        }
                        break;
                    case ResultCode.FAILURE:
//                        LToast.show(context,resultInfo.getData() == null ? "查询异常" : resultInfo.getData());
                        showNoResultHint();
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
                    showNoResultHint();
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
        adapter = new LRecyclerViewAdapter<SearchResultBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.search_result_item;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, final SearchResultBean itemData) throws Exception {
                viewHolder.setText(R.id.search_order_num, itemData.getOrder().getOrder_number() + "");
                viewHolder.setText(R.id.search_start_line, itemData.getCol().getStart_outlets_name());
                viewHolder.setText(R.id.search_end_line, itemData.getCol().getEnd_outlets_name());
                viewHolder.setText(R.id.search_final_palce, itemData.getOrder().getConsignor_address());
                viewHolder.setText(R.id.send_number, Html.fromHtml("<u>" + itemData.getOrder().getConsignor_phone_number() + "</u>").toString());
                viewHolder.setText(R.id.receive_number, Html.fromHtml("<u>" + itemData.getOrder().getConsignee_phone_number() + "</u>").toString());
                viewHolder.setOnClickListener(R.id.detail, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, LogisticDetailActivity.class).putExtra("order_number", itemData.getOrder().getOrder_number()));
                    }
                });
            }
        };
        adapter.setOnLoadListener(this);
        return adapter;
    }

    @OnClick(R.id.cancle)
    public void onClick() {
        getActivity().finish();
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
                .inflate(R.layout.layout_hint_page_load_failure_search, null);
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
}

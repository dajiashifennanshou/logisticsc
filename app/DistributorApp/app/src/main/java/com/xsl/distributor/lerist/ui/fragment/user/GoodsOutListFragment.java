package com.xsl.distributor.lerist.ui.fragment.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.GoodsClient;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.GoodsOutInfo;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.activity.WaybillDetailsActivity;
import com.xsl.distributor.lerist.ui.dialog.NoNetworkDialog;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LRecyclerViewFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.ListUtils;
import com.xsl.lerist.llibrarys.utils.NetworkUtil;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lerist on 2016/7/9, 0009.
 */
public class GoodsOutListFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {

    public static final String GOODS_TYPE = "GoodsType";
    public static final int GOODSTYPE_IN = 100;
    public static final int GOODSTYPE_OUT = 101;

    private LRecyclerViewAdapter<GoodsOutInfo> recyclerViewAdapter;
    private int mGoodsType;
    private LocalData localData;
    private GoodsClient goodsClient;
    private boolean isRefresh;
    private boolean isLoad;
    private int mCurrentPage = 1;
    private int count;
    private boolean isAllLoad;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initView();
    }

    private void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mGoodsType = (int) arguments.get(GOODS_TYPE);
        }

        goodsClient = new GoodsClient();
        localData = new LocalData(context);
        onRefresh();
    }

    private void initView() {
//        recyclerViewAdapter.addDatas(getTestDatas());
    }

    private List<GoodsOutInfo> getTestDatas() {
        ArrayList<GoodsOutInfo> customerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            customerInfos.add(new GoodsOutInfo());
        }
        return customerInfos;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {

        recyclerViewAdapter =
                new LRecyclerViewAdapter<GoodsOutInfo>(context) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
                        return R.layout.item_list_goods_out;
                    }

                    @Override
                    public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, GoodsOutInfo itemData) throws Exception {
                        viewHolder.setText(R.id.i_list_goods_tv_type, "配送单号:  " + itemData.getDeliveryNo());

                        LinearLayout ll_goods_container = viewHolder.getView(R.id.i_list_goods_ll_goods_container, LinearLayout.class);
                        ll_goods_container.removeAllViews();

                        if (ListUtils.isNotEmpty(itemData.getGoods())) {
                            for (GoodsOutInfo.GoodsBean goodsBean : itemData.getGoods()) {
                                View goods_item = inflater.inflate(R.layout.item_list_goods_item, null);
                                ((TextView) goods_item.findViewById(R.id.i_list_goods_tv_name)).setText(goodsBean.getGoodsName());
                                ((TextView) goods_item.findViewById(R.id.i_list_goods_tv_brand)).setText(goodsBean.getGoodsBrand());
                                ((TextView) goods_item.findViewById(R.id.i_list_goods_tv_count)).setText(goodsBean.getGoodsNum() + "  件");
                                ll_goods_container.addView(goods_item);
                            }
                        }

                        viewHolder.setText(R.id.i_list_goods_tv_time, StringUtils.toTime(Long.parseLong(itemData.getCreateTime()), "yyyy年MM月dd日"));

                    }
                };
        recyclerViewAdapter.setOnLoadListener(this);
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
        goodsClient.getOutList(userInfo.getUser().getId(), page, count, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        List<GoodsOutInfo> deliveryOrders = JSON.parseArray(String.valueOf(resultInfo.getData()), GoodsOutInfo.class);
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
                        break;  }
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

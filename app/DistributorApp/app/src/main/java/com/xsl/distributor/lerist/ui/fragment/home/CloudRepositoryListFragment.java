package com.xsl.distributor.lerist.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.GoodsClient;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.CloudRepositoryInfo;
import com.xsl.distributor.lerist.model.GoodsInfo;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.dialog.NoNetworkDialog;
import com.xsl.distributor.lerist.widget.NumRegulatorView;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LRecyclerViewFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.BusProvider;
import com.xsl.lerist.llibrarys.utils.NetworkUtil;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lerist on 2016/7/9, 0009.
 */
public class CloudRepositoryListFragment extends LRecyclerViewFragment implements NumRegulatorView.OnNumChangedListener, LRecyclerViewAdapter.OnLoadListener, CompoundButton.OnCheckedChangeListener {

    private LRecyclerViewAdapter<GoodsInfo> recyclerViewAdapter;
    private boolean isRefresh;
    private boolean isLoad;
    private int mCurrentPage;
    private LocalData localData;
    private GoodsClient goodsClient;
    private int count;
    private ArrayList<GoodsInfo> goodsCar;
    private boolean isAllLoad;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    private void initData() {
        localData = new LocalData(context);
        goodsClient = new GoodsClient();
        goodsCar = new ArrayList<>();
    }

    private void initView() {
        showNoDataHint();
    }

    private List<CloudRepositoryInfo> getTestDatas() {
        ArrayList<CloudRepositoryInfo> cloudRepositoryInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cloudRepositoryInfos.add(new CloudRepositoryInfo());
        }
        return cloudRepositoryInfos;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<GoodsInfo>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_list_cloud_repository;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, final GoodsInfo itemData) {
                final CheckBox checkBox = viewHolder.getView(R.id.i_list_cloud_repository_nrv, CheckBox.class);
                checkBox.setChecked(false);
                checkBox.setOnCheckedChangeListener(CloudRepositoryListFragment.this);
                checkBox.setTag(itemData);
                viewHolder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkBox.performClick();
                    }
                });

                viewHolder.setText(R.id.i_list_cloud_repository_tv_name, itemData.getGoodsName());
                viewHolder.setText(R.id.i_list_cloud_repository_tv_count, itemData.getGoodsNum() + " 件");
                viewHolder.setText(R.id.i_list_cloud_repository_tv_type, "专线入库");

                viewHolder.setText(R.id.i_list_cloud_repository_tv_time, StringUtils.toTimeStr(Long.parseLong(itemData.getCreateTime()), "yyyy年MM月dd日"));
            }
        };
        recyclerViewAdapter.setOnLoadListener(this);
        return recyclerViewAdapter;
    }

    @Override
    public void onNumAdd(Object host, int addValue) {
        if (!goodsCar.contains(host)) {
            goodsCar.add((GoodsInfo) host);
        }
//        Integer integer = goodsCar.get(host);
//        goodsCar.remove(host);
//        goodsCar.put((GoodsInfo) host, integer.intValue() + addValue);

        BusProvider.getInstance().post(goodsCar);
    }

    @Override
    public void onNumCut(Object host, int cutValue) {
        if (goodsCar.contains(host)) {
            goodsCar.remove((GoodsInfo) host);
        }
//        Integer integer = goodsCar.get(host);
//        goodsCar.remove(host);
//        int newValue = integer.intValue() - cutValue;
//        if (newValue > 0) {
//            goodsCar.put((GoodsInfo) host, newValue);
//        }

        BusProvider.getInstance().post(goodsCar);
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
        }else {
            requestFinish();
        }
    }

    private void requestData(int page) {
        if (context == null) {
            return;
        }

        if (!NetworkUtil.isConnected(context)) {
            NoNetworkDialog.show(context);
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
        goodsClient.getGoodsList(userInfo.getUser().getId(), page, count, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        List<GoodsInfo> goodsInfos = JSON.parseArray(String.valueOf(resultInfo.getData()), GoodsInfo.class);
                        if (goodsInfos == null) {
                            return;
                        }
                        if (isRefresh) {
                            recyclerViewAdapter.removeDataAll();
                        }
                        recyclerViewAdapter.addDatas(goodsInfos);

                        if (recyclerViewAdapter.getDatas().isEmpty()) {
                            showNoDataHint();
                        }

                        if (goodsInfos.size() < count) {
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            onNumAdd(buttonView.getTag(), 1);
        } else {
            onNumCut(buttonView.getTag(), 1);
        }
    }
}

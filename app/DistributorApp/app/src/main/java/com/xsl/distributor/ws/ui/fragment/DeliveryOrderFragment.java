package com.xsl.distributor.ws.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.DeliveryOrderClient;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.DeliveryOrder;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.activity.WaybillDetailsActivity;
import com.xsl.distributor.lerist.ui.dialog.JoinStateDialog;
import com.xsl.distributor.lerist.ui.dialog.NoNetworkDialog;
import com.xsl.distributor.ws.ui.activity.CommentDtailActivity;
import com.xsl.distributor.ws.ui.activity.LoginActivity;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LRecyclerViewFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.NetworkUtil;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LRecyclerView;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.List;

/**
 * 已经完成的F配送单fragment
 * Created by Administrator on 2016/7/11 0011.
 */
public class DeliveryOrderFragment extends LRecyclerViewFragment implements LRecyclerViewAdapter.OnLoadListener {

    private LRecyclerViewAdapter<DeliveryOrder> recyclerViewAdapter;
    private String mOrderStatus;
    private DeliveryOrderClient deliveryOrderClient;
    private int mCurrentPage = 1;
    private LocalData localData;
    private boolean isRefresh;
    private boolean isLoad;
    private int count;
    private boolean isAllLoad;
    public List<DeliveryOrder> deliveryOrders;

    public static DeliveryOrderFragment getNewInstance(String orderStatus) {
        DeliveryOrderFragment deliveryOrderFragment = new DeliveryOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("orderStatus", orderStatus);
        deliveryOrderFragment.setArguments(bundle);
        return deliveryOrderFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        isRefresh = true;
        requestData(1);
    }

    private void initData() {
        localData = new LocalData(context);
        deliveryOrderClient = new DeliveryOrderClient();

        Bundle arguments = getArguments();
        if (arguments != null) {
            mOrderStatus = arguments.getString("orderStatus");
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
        deliveryOrderClient.getList(userInfo.getId(), userInfo.getJoin().getBranchNo(), mOrderStatus, page, count, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        deliveryOrders = JSON.parseArray(String.valueOf(resultInfo.getData()), DeliveryOrder.class);
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

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<DeliveryOrder>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.logistics_layout;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, final int position, final DeliveryOrder itemData) {
                viewHolder.setText(R.id.logistic_num, "配送单号: " + itemData.getDeliveryNo());
                viewHolder.getView(R.id.layout_footer).setVisibility(View.GONE);
                int orderStatusImgId = R.mipmap.peisongdan_chuku;

                final boolean isException = itemData.getIsExcepiton() == 1;
                if (isException) {
                    orderStatusImgId = R.mipmap.peisongdan_yichang;
                    viewHolder.getView(R.id.layout_footer).setVisibility(View.VISIBLE);
                    viewHolder.setText(R.id.money_status, "异常原因:  专线费用未付清");
                    viewHolder.setTextColor(R.id.money_status, Color.RED);
                    Drawable drawable = getResources().getDrawable(R.mipmap.peisongdan_prompt2);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    viewHolder.getView(R.id.money_status, TextView.class).setCompoundDrawables(drawable, null, null, null);
                    viewHolder.setText(R.id.detail, "查看");
                    viewHolder.setOnClickListener(R.id.detail, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, WaybillDetailsActivity.class);
                            intent.putExtra("deliveryNo", itemData.getDeliveryNo());
                            startActivity(intent);
                        }
                    });
                } else {
                    viewHolder.getView(R.id.layout_footer).setVisibility(View.GONE);
                    switch (itemData.getOrderStatus()) {
                        case 0:
                            //待分配
                            orderStatusImgId = R.mipmap.peisongdan_daifenpei;
                            break;
                        case 1:
                            //已配载
                            orderStatusImgId = R.mipmap.peisongdan_yipeizai;
                            break;
                        case 2:
                            //在途
                            orderStatusImgId = R.mipmap.peisongdan_zaitu;
                            break;
                        case 3:
                            //已配送
                            orderStatusImgId = R.mipmap.peisongdan_yipeisong;
                            break;
                        case 4:
                            //代配送
                            orderStatusImgId = R.mipmap.peisongdan_chuku;
                            break;
                        case 5:
                            //已完成
                            orderStatusImgId = R.mipmap.peisongdan_yiwanchegn;
                            viewHolder.getView(R.id.layout_footer).setVisibility(View.VISIBLE);
                            viewHolder.setText(R.id.money_status, "此款项已提至您的银行卡中");
                            viewHolder.setTextColor(R.id.money_status, getResources().getColor(R.color.font_color_focused));
                            Drawable drawable = getResources().getDrawable(R.mipmap.peisongdan_prompt1);
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            viewHolder.getView(R.id.money_status, TextView.class).setCompoundDrawables(drawable, null, null, null);
                            carbon.widget.TextView evaluate = viewHolder.getView(R.id.detail);
                            evaluate.setBackgroundResource(itemData.getEvaluateStatus() == 1 ? R.color.colorAccent2 : R.color.font_color_hint_light);
                            evaluate.setTextColor(getResources().getColor(itemData.getEvaluateStatus() == 1 ? R.color.white : R.color.font_color_normal));
                            viewHolder.setText(R.id.detail, itemData.getEvaluateStatus() == 1 ? "查看评价" : "暂无评价");
                            evaluate.setEnabled(itemData.getEvaluateStatus() == 1);
                            viewHolder.setOnClickListener(R.id.detail, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    context.startActivity(new Intent(context, CommentDtailActivity.class)
                                            //评论
                                            .putExtra("comment", deliveryOrders.get(position).getCustomerSug()));
                                }
                            });
                            break;
                    }
                }
                viewHolder.setImageResource(R.id.status, orderStatusImgId);
                viewHolder.setText(R.id.name, itemData.getConsigneeName());
                viewHolder.setText(R.id.number, itemData.getConsigneePhone());
                viewHolder.setText(R.id.address, itemData.getConsigneeAddr());
                LRecyclerView gridView = viewHolder.getView(R.id.goods_gridVivew, LRecyclerView.class);
                gridView.setLayoutManager(new LRecyclerView.WrapContentLinearLayoutManager(context));
                LRecyclerViewAdapter<DeliveryOrder.LstGoods> adapter = new LRecyclerViewAdapter<DeliveryOrder.LstGoods>(context) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
//                        return R.layout.item_list_goods_name;
                        return R.layout.content_detail_layout;
                    }

                    @Override
                    public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, DeliveryOrder.LstGoods itemData) throws Exception {
//                        ((TextView) viewHolder.getItemView()).setText(itemData.getGoodsName());
                        viewHolder.setText(R.id.pinpai, itemData.getGoodsBrand());
                        Log.i("11111111111", itemData.getGoodsBrand());
                        viewHolder.setText(R.id.pinpai_text, itemData.getGoodsName());
                        Log.i("22222222222", itemData.getGoodsName());
                    }
                };
                gridView.setAdapter(adapter);
                adapter.addDatas(itemData.getLstGoods());

                viewHolder.setText(R.id.daishou_money, "代收：" + itemData.getAgentPaidCost());
                viewHolder.setText(R.id.logistic_money, "配送费：" + itemData.getDeliveryCost());

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
        if (!localData.isLogined()) {
            recyclerViewAdapter.removeDataAll();
            LToast.show(context, "请先登录");
            startActivity(LoginActivity.class);
            requestFinish();
            showRequestError();
            return;
        }

        UserInfo.Join join = localData.readUserInfo().getJoin();
        boolean isNoJoin = JoinStateDialog.show(context, join, false);
        if (isNoJoin) {
            recyclerViewAdapter.removeDataAll();
            requestFinish();
            showRequestError();
            return;
        }
        isRefresh = true;
        requestData(1);
    }

    private void requestFinish() {
        isRefresh = false;
        isLoad = false;
        setRefreshing(false);
        setLoading(false);
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

    @Override
    public void onRefreshFragment() {
        super.onRefreshFragment();
        if (localData == null || context == null) return;

        if (!localData.isLogined()) {
            LToast.show(context, "请先登录");
            startActivity(LoginActivity.class);
            requestFinish();
            showRequestError();
            return;
        }
    }
}

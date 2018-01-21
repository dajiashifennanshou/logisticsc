package com.wrt.xinsilu.lerist.ui.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.SenderBean;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.CommonContactClient;
import com.wrt.xinsilu.lerist.interfaces.Selectable;
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
 * Created by Administrator on 2016/6/29 0029.
 * 常用发货人
 */
public class CommonSenderListFragment extends LRecyclerViewFragment implements Selectable, LRecyclerViewAdapter.OnLoadListener, LRecyclerViewAdapter.LOnItemClickListener {

    private LRecyclerViewAdapter<SenderBean> recyclerViewAdapter;
    private LocalData localData;
    private CommonContactClient commonContactClient;
    private boolean isRefresh;
    private boolean isAllLoad;
    private boolean isLoad;
    private int mCurrentPage;
    private int count;
    private boolean isSelectable;

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
        commonContactClient = new CommonContactClient();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<SenderBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_common_list_sender;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, final SenderBean itemData) throws Exception {
                SwipeLayout swipeLayout = viewHolder.getView(R.id.i_common_list_sender_sl, SwipeLayout.class);
                swipeLayout.setSwipeEnabled(!isSelectable);

                viewHolder.setText(R.id.i_common_list_sender_tv_hint_addr, "发货地址:  ");
                viewHolder.setText(R.id.i_common_list_sender_tv_name, itemData.getContacts_name());
                viewHolder.setText(R.id.i_common_list_sender_tv_phone, itemData.getPhone_number());
                viewHolder.setText(R.id.i_common_list_sender_tv_addr, itemData.getProvince()+" "+itemData.getCity()+" "+itemData.getCounty()+" "+itemData.getAddress());
                viewHolder.setText(R.id.i_common_list_sender_tv_status, itemData.getState() == 1 ? "自送网点" : "客户自提");
                viewHolder.setOnClickListener(R.id.i_common_list_sender_btn_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete(itemData);
                    }
                });
            }
        };
        recyclerViewAdapter.setOnLoadListener(this);
        recyclerViewAdapter.setOnItemClickListener(this);
        return recyclerViewAdapter;
    }

    private void delete(final SenderBean itemData) {
        new AlertDialog.Builder(context).setTitle("提示")
                .setMessage("确认删除该条联系人?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        commonContactClient.delete(itemData.getId(), new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                KLog.i(result);
                                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                if (resultInfo == null) return;

                                switch (resultInfo.getCode()) {
                                    case ResultCode.SUCCEED:
                                        LToast.show(context, "已删除");
                                        recyclerViewAdapter.removeData(itemData);
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
        commonContactClient.getSenderList(userInfo.getUser().getId(), page, count, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        List<SenderBean> senderBeanList = JSON.parseArray(String.valueOf(resultInfo.getData()), SenderBean.class);
                        if (senderBeanList == null) {
                            return;
                        }
                        if (isRefresh) {
                            recyclerViewAdapter.removeDataAll();
                        }
                        recyclerViewAdapter.addDatas(senderBeanList);
                        if (recyclerViewAdapter.getDatas().isEmpty()) {
                            showNoDataHint();
                        }
                        if (senderBeanList.size() < count) {
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


    @Override
    public boolean isSelectable() {
        return isSelectable;
    }

    @Override
    public void setSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
    }

    @Override
    public void OnClickListener(View view, int position) {
        if (isSelectable) {
            SenderBean senderBean = recyclerViewAdapter.getItemData(position);
            Intent intent = new Intent();
            intent.putExtra("name", senderBean.getContacts_name());
            intent.putExtra("phone", senderBean.getPhone_number());
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        }
    }

    @Override
    public void OnLongClickListener(View view, int position) {

    }
}

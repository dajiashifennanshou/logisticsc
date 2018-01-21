package com.wrt.xinsilu.ui.fragment;

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
import com.wrt.xinsilu.basic.ResultCode;
import com.wrt.xinsilu.bean.DriverBean;
import com.wrt.xinsilu.client.AddCommonDriverClient;
import com.wrt.xinsilu.common.Common;
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
 */
public class DriverFragment extends LRecyclerViewFragment implements Selectable, LRecyclerViewAdapter.OnLoadListener, LRecyclerViewAdapter.LOnItemClickListener {
    private LocalData data;
    private AddCommonDriverClient client;
    private int i = 1;
    private LRecyclerViewAdapter<DriverBean> recyclerViewAdapter;
    private int count = 20;
    private boolean isAllLoad;
    private boolean isRefresh;
    private boolean isLoad;
    private int mCurrentPage;
    private boolean isSelectable;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
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

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Override
    public RecyclerView.Adapter getRecyclerViewAdapter() {
        recyclerViewAdapter = new LRecyclerViewAdapter<DriverBean>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.driver_adapter;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, final DriverBean itemData) throws Exception {
                SwipeLayout swipeLayout = viewHolder.getView(R.id.i_common_list_driver_sl, SwipeLayout.class);
                swipeLayout.setSwipeEnabled(!isSelectable);

                viewHolder.setText(R.id.driver_name, itemData.getDriver_name());
                viewHolder.setText(R.id.driver_num, itemData.getPhone_number());
                viewHolder.setText(R.id.driver_license, itemData.getLicense_number());
                viewHolder.getView(R.id.choice).setVisibility(View.GONE);
                viewHolder.getView(R.id.content_footer_layout).setVisibility(View.GONE);
                viewHolder.setOnClickListener(R.id.i_common_list_driver_btn_delete, new View.OnClickListener() {
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

    private void delete(final DriverBean itemData) {
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
                        new CommonContactClient().deleteDriver(itemData.getId(), new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                KLog.i(result);
                                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                if (resultInfo == null) return;

                                switch (resultInfo.getCode()) {
                                    case com.wrt.xinsilu.bean.ResultCode.SUCCEED:
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


    private void initValues() {
        data = new LocalData(getActivity());
        client = new AddCommonDriverClient();
    }

    /**
     * 获取常用司机
     *
     * @param i
     */
    private void getDriver(int i) {
        if (!NetworkUtil.isConnected(context)) {
            NoNetworkDialog.show(context);
            if (recyclerViewAdapter.getItemCount() == 0) {
                //出现异常并且当前无数据, 显示提示
                showRequestError();
            }
            requestFinish();
            return;
        }

        if (!data.isLogined()) {
            recyclerViewAdapter.removeDataAll();
//            LToast.show(context, "请先登录");
//            startActivity(LoginActivity.class);
            requestFinish();
            showRequestError();
            return;
        }

        hideRequestError();
        mCurrentPage = i;
        client.getDriver(Common.COMMON_DRIVER,
                data.readUserInfo().getUser().getId(),
                i, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        KLog.i(result);
                        if (resultInfo == null) {
                            return;
                        }
                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                List<DriverBean> driverBeanList = JSON.parseArray(resultInfo.getData(), DriverBean.class);
                                if (driverBeanList == null) {
                                    return;
                                }
                                if (isRefresh) {
                                    recyclerViewAdapter.removeDataAll();
                                }
                                recyclerViewAdapter.addDatas(driverBeanList);
                                if (recyclerViewAdapter.getDatas().isEmpty()) {
                                    showNoDataHint();
                                }
                                if (driverBeanList.size() < count) {
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

    @Override
    public void onRefresh() {
        super.onRefresh();
        isRefresh = true;
        getDriver(1);
    }

    @Override
    public void onLoad(int freeItemCount) {
        if (isAllLoad == false && !isRefreshing()) {
            setRefreshing(true);
            setLoading(true);
            isLoad = true;
            getDriver(mCurrentPage + 1);
        } else {
            requestFinish();
        }
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
            DriverBean driverBean = recyclerViewAdapter.getItemData(position);
            Intent intent = new Intent();
            intent.putExtra("name", driverBean.getDriver_name());
            intent.putExtra("phone", driverBean.getPhone_number());
            intent.putExtra("carNum", driverBean.getLicense_number());
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
        }
    }

    @Override
    public void OnLongClickListener(View view, int position) {

    }
}

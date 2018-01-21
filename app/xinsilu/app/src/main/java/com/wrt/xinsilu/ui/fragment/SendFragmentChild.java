package com.wrt.xinsilu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.socks.library.KLog;
import com.squareup.otto.Subscribe;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.LogisticAdapter;
import com.wrt.xinsilu.basic.ResultCode;
import com.wrt.xinsilu.bean.CollectionLineBean;
import com.wrt.xinsilu.bean.LogisticBean;
import com.wrt.xinsilu.bean.NearByLineBean;
import com.wrt.xinsilu.bean.ProvinceBean;
import com.wrt.xinsilu.client.GetAddressClient;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.ui.activity.SearchLineActivity;
import com.wrt.xinsilu.ui.view.CustomPopupWindow;
import com.wrt.xinsilu.ui.view.MyListView;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.fragments.LFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.BusProvider;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class SendFragmentChild extends LFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, LRecyclerViewAdapter.OnLoadListener {
    private MyListView listView;
    private NestedScrollView view;
    private List<LogisticBean> way;
    /**
     * 搜索
     */
    private TextView search;
    /**
     * 起点点击
     */
    private LinearLayout start_layout;
    /**
     * 终点点击
     */
    private LinearLayout end_layout;
    /**
     * 起点显示
     */
    private TextView start;
    /**
     * 终点显示
     */
    private TextView end;
    private LinearLayout linear_layout;
    private LogisticBean bean;
    /**
     * 物流商的线路信息
     */
    private CollectionLineBean linebean;
    /**
     * 物流商的线路信息集合
     */
    private List<CollectionLineBean> line_way;
    /**
     * 刷新控件
     */
    private SpringView refreshLayout;
    private View mView;
    private CustomPopupWindow popupWindow;
    private int type;
    private GetAddressClient client;
    private boolean isRefresh;
    /**
     * 这个是每次下拉的时候添加返回的数据
     */
    private List<NearByLineBean.ObjectListBean> list;
    /**
     * 获取附近信息的省
     */
    private String nearProvince;
    /**
     * 获取附近信息的市
     */
    private String nearCity;
    /**
     * 获取附近信息的县
     */
    private String nearDistrict;
    /**
     * 经度
     */
    private double lontitude;
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 选择的省
     */
    private String province;
    /**
     * 选择的市
     */
    private String city;
    /**
     * 选择的县
     */
    private String district;
    private LocalData data;
    /**
     * 请求页
     */
    int i = 1;
    private String provinceBeanName;
    private String cityBeanName;
    private String countyBeanName;
    private ProvinceBean startprovinceBean;
    private ProvinceBean.CityBean startcityBean;
    private ProvinceBean.CityBean.CountyBean startcountyBean;
    private ProvinceBean endprovinceBean;
    private ProvinceBean.CityBean endcityBean;
    private ProvinceBean.CityBean.CountyBean endcountyBean;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle data = msg.getData();

            switch (type) {
                case 0:
                    startprovinceBean = (ProvinceBean) data.getSerializable(ProvinceBean.class.getSimpleName());
                    startcityBean = (ProvinceBean.CityBean) data.getSerializable(ProvinceBean.CityBean.class.getSimpleName());
                    startcountyBean = (ProvinceBean.CityBean.CountyBean) data.getSerializable(ProvinceBean.CityBean.CountyBean.class.getSimpleName());
                    provinceBeanName = startprovinceBean == null ? "" : startprovinceBean.getName();
                    cityBeanName = startcityBean == null ? "" : startcityBean.getName();
                    countyBeanName = startcountyBean == null ? "" : startcountyBean.getName();
                    start.setText(provinceBeanName + " " + cityBeanName + " " + countyBeanName);
                    break;
                case 1:
                    endprovinceBean = (ProvinceBean) data.getSerializable(ProvinceBean.class.getSimpleName());
                    endcityBean = (ProvinceBean.CityBean) data.getSerializable(ProvinceBean.CityBean.class.getSimpleName());
                    endcountyBean = (ProvinceBean.CityBean.CountyBean) data.getSerializable(ProvinceBean.CityBean.CountyBean.class.getSimpleName());
                    provinceBeanName = endprovinceBean == null ? "" : endprovinceBean.getName();
                    cityBeanName = endcityBean == null ? "" : endcityBean.getName();
                    countyBeanName = endcountyBean == null ? "" : endcountyBean.getName();
                    end.setText(provinceBeanName + " " + cityBeanName + " " + countyBeanName);

            }
        }
    };
    private ProvinceBean provinceBean;
    private ProvinceBean.CityBean cityBean;
    private ProvinceBean.CityBean.CountyBean countyBean;

    private LogisticAdapter adapter;
    private Callback.Cancelable clientNearAddress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_send_child, null);
        initView();
        BusProvider.getInstance().register(this);
        way = new ArrayList<>();
        return mView;
    }


    private void initView() {
        list = new ArrayList<>();

        view = (NestedScrollView) mView.findViewById(R.id.scoreView);
        view.setNestedScrollingEnabled(false);
        refreshLayout = (SpringView) mView.findViewById(R.id.refresh_layout);
        refreshLayout.setHeader(new DefaultHeader(context));
        refreshLayout.setFooter(new DefaultFooter(context));
        refreshLayout.setType(SpringView.Type.FOLLOW);
        refreshLayout.setGive(SpringView.Give.BOTH);
        refreshLayout.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                SendFragmentChild.this.onRefresh();
            }

            @Override
            public void onLoadmore() {
                SendFragmentChild.this.onLoad(i);
            }
        });
        listView = (MyListView) mView.findViewById(R.id.send_child_listView);
        search = (TextView) mView.findViewById(R.id.search);
        start_layout = (LinearLayout) mView.findViewById(R.id.begin_to_layout);
        end_layout = (LinearLayout) mView.findViewById(R.id.end_to_layout);
        linear_layout = (LinearLayout) mView.findViewById(R.id.linear_layout);
        start = (TextView) mView.findViewById(R.id.start);
        end = (TextView) mView.findViewById(R.id.end);
        listView.setFocusable(false);
        search.setOnClickListener(this);
        start_layout.setOnClickListener(this);
        end_layout.setOnClickListener(this);
        client = new GetAddressClient();

        data = new LocalData(getActivity());
//        if (!FileUtils.isFileExist(Environment.getExternalStorageDirectory() + "/city.json")) {
//            getAddress();
//        }

    }

    /**
     * 获取附近物流商
     *
     * @param page
     */
    private synchronized void getNearByLine(final int page) {
        KLog.i(Common.NEAR_LOGISTICS + "\n" + latitude + "\n" + lontitude + "\n" + nearProvince + "\n" + nearCity + "\n" + nearDistrict + "\n" + page);
        if (lontitude == 0) {
            refreshLayout.onFinishFreshAndLoad();
            return;
        }

        if (clientNearAddress != null) clientNearAddress.cancel();
        clientNearAddress = client.getNearAddress(Common.NEAR_LOGISTICS,
                lontitude,
                latitude,
                nearProvince,
                nearCity,
                nearDistrict,
                page,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        KLog.i(result);
                        if (resultInfo == null) {
                            return;
                        }
                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                NearByLineBean nearByLineBeen = JSON.parseObject(resultInfo.getData(), NearByLineBean.class);
                                if (isRefresh) {
                                    list.clear();
                                }
                                list.clear();
                                list.addAll(nearByLineBeen.objectList);
                                adapter = new LogisticAdapter(getActivity(), list);
                                listView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ex.printStackTrace();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {
                        clientNearAddress = null;
                        isRefresh = false;
                        refreshLayout.onFinishFreshAndLoad();
                    }
                });
    }

    private void getAddress() {
//        LProgressDialog.show(context, "正在加载省市列表...");
//        client.getAddress(Common.GET_XZQH, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
//                KLog.i(result);
//                if (resultInfo == null) {
//                    return;
//                }
//                switch (resultInfo.getCode()) {
//                    case ResultCode.SUCCEED:
//                        FileUtils.writeFile(Environment.getExternalStorageDirectory() + "/city.json", resultInfo.getData());
//                }
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                ex.printStackTrace();
//                LToast.show(context, "加载省市列表失败, 请稍后再试");
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//                LProgressDialog.dismiss();
//                refreshLayout.onFinishFreshAndLoad();
//            }
//        });
    }


    @Override
    public void onClick(View v) {
        if (v == search) {
            if (startprovinceBean == null
                    || startcityBean == null
                    || endprovinceBean == null
                    || endcityBean == null
                    ) {
                LToast.show(context, "请选择起始点");
                return;
            }
            Intent intent = new Intent(getActivity(), SearchLineActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ProvinceBean.class.getSimpleName() + "start", startprovinceBean);
            bundle.putSerializable(ProvinceBean.CityBean.class.getSimpleName() + "start", startcityBean);
            bundle.putSerializable(ProvinceBean.class.getSimpleName() + "end", endprovinceBean);
            bundle.putSerializable(ProvinceBean.CityBean.class.getSimpleName() + "end", endcityBean);
//            bundle.putSerializable(ProvinceBean.CityBean.CountyBean.class.getSimpleName(), countyBean);
            bundle.putDouble("latitude", latitude);
            bundle.putDouble("lontitude", lontitude);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (v == start_layout) {
//            if (!FileUtils.isFileExist(Environment.getExternalStorageDirectory() + "/city.json")) {
//                getAddress();
//                return;
//            }
            type = 0;
            popupWindow = new CustomPopupWindow(getActivity(), handler, type);
            popupWindow.showPopupWindow(linear_layout, true);
        } else if (v == end_layout) {
//            if (!FileUtils.isFileExist(Environment.getExternalStorageDirectory() + "/city.json")) {
//                getAddress();
//                return;
//            }
            type = 1;
            popupWindow = new CustomPopupWindow(getActivity(), handler, type);
            popupWindow.showPopupWindow(linear_layout, true);
        }
    }

    @Override
    public void onLoad(int freeItemCount) {
//        getAddress();
        // TODO: 2016/8/3 0003 这里是加载的其他的
        getNearByLine(++i);
    }

    @Override
    public void onRefresh() {
        i = 1;
        isRefresh = true;
        getNearByLine(i);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);


    }

    @Subscribe
    public void onReceiveLocation(BDLocation location) {
        lontitude = location.getLongitude();
        latitude = location.getLatitude();
        nearProvince = location.getProvince();
        nearCity = location.getCity();
        nearDistrict = location.getDistrict();
        if (list.size() == 0) {
            getNearByLine(1);
        }
    }

}

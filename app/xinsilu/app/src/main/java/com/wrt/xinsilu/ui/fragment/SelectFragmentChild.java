package com.wrt.xinsilu.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.socks.library.KLog;
import com.squareup.otto.Subscribe;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.HistorySelectAdapter;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.ui.activity.LogisticSearchActivity;
import com.wrt.xinsilu.ui.view.MyListView;
import com.xsl.lerist.llibrarys.utils.BusProvider;

import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class SelectFragmentChild extends Fragment implements TextView.OnEditorActionListener, AdapterView.OnItemClickListener, View.OnClickListener {
    /**
     * 手动输入物流商
     */
    private EditText select;
    /**
     * 历史纪录
     */
    private MyListView history;
    /**
     * 历史纪录adapter
     */
    private HistorySelectAdapter adapter;
    /**
     * 测试
     */
    private List<String> list;
    /**
     * 用于点击清空list里面的数据
     */
    private TextView delete;
    private TextView text;
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
    private LocalData data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_select_child, null);
        select = (EditText) mView.findViewById(R.id.input_company);
        select.setOnEditorActionListener(this);
        history = (MyListView) mView.findViewById(R.id.history_listView);
        delete = (TextView) mView.findViewById(R.id.delete_all);
        text = (TextView) mView.findViewById(R.id.text);
        data = new LocalData(getActivity());
        text.setVisibility(View.VISIBLE);
        BusProvider.getInstance().register(this);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        //加载搜索历史
        list = data.getHistory();
        Collections.reverse(list);

        if (list != null && list.size() > 0) {
            delete.setVisibility(View.VISIBLE);
        }
        adapter = new HistorySelectAdapter(getActivity(), list);
        history.setAdapter(adapter);
        history.setOnItemClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_GO) {
            InputMethodManager imm = (InputMethodManager) v
                    .getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(
                        v.getApplicationWindowToken(), 0);
            }
            KLog.i("\n" + nearProvince + "\n" + nearCity + "\n" + lontitude + "\n" + latitude + "\n" + select.getText().toString());
            startActivity(new Intent(getActivity(), LogisticSearchActivity.class)
                    .putExtra("nearProvince", nearProvince)
                    .putExtra("nearCity", nearCity)
                    .putExtra("lontitude", lontitude)
                    .putExtra("latitude", latitude)
                    .putExtra("logistic", select.getText().toString()));
            data.putHistory(select.getText().toString());
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        KLog.i("\n" + nearProvince + "\n" + nearCity + "\n" + lontitude + "\n" + latitude + "\n" + list.get(position));
        startActivity(new Intent(getActivity(), LogisticSearchActivity.class)
                .putExtra("logistic", list.get(position))
                .putExtra("nearProvince", nearProvince)
                .putExtra("nearCity", nearCity)
                .putExtra("lontitude", lontitude)
                .putExtra("latitude", latitude));
    }

    @Override
    public void onClick(View v) {
        if (v == delete) {
            data.deleteAllHistory();
            list.clear();
            adapter.synList(list);
            if (list == null || list.isEmpty()) {
                delete.setVisibility(View.GONE);
            }
        }
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
    }
}

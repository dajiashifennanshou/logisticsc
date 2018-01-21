package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.bean.LogisticOrderDetailBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class LogisticDetailOrderAdapter extends BasicAdapter<LogisticOrderDetailBean.CargoLstBean> {
    public LogisticDetailOrderAdapter(Context context, List<LogisticOrderDetailBean.CargoLstBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }
}

package com.wrt.xinsilu.basic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Constandine on 2016/3/30.
 */
public abstract class BasicAdapter<T> extends BaseAdapter {
    /**
     * 需要的list数据集合
     */
    public List<T> list;
    /**
     * context
     */
    public Context context;

    public BasicAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 同步list数据
     *
     * @param list
     */
    public void synList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list == null || list.size() == 0 ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 用于inflate加载的布局方法
     *
     * @param id 自定义的界面布局
     * @return 返回的view
     */
    public View getConvertView(int id) {
        return LayoutInflater.from(context).inflate(id, null);

    }
}

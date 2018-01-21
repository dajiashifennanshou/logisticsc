package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.lerist.model.LogisticsCompanyInfo;
import com.wrt.xinsilu.ui.activity.LogisticLineDetailActivity;
import com.wrt.xinsilu.ui.view.MyListView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28 0028.
 * 推荐的物流商
 */
public class LogisticCollectionAdapter extends BasicAdapter<LogisticsCompanyInfo.ObjectListBean.ListBean>{
    private RecomendWayAdapter adapter;
    private int type;
    public LogisticCollectionAdapter(Context context, List<LogisticsCompanyInfo.ObjectListBean.ListBean> list, int type) {
        super(context, list);
        this.type = type;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.logistics_layout);
            holder.logistic_name = (TextView) convertView.findViewById(R.id.collection_name);
            holder.logistic_icon = (ImageView) convertView.findViewById(R.id.logistic_icon);
            holder.gridView = (MyListView) convertView.findViewById(R.id.logistics_listview);
            holder.detail = (TextView) convertView.findViewById(R.id.logistics_detail);
            holder.ditence = (TextView) convertView.findViewById(R.id.destance);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(type == 0){
            holder.ditence.setVisibility(View.VISIBLE);
//            holder.ditence.setText(list.get(position).get);
        }
//        holder.logistic_name.setText(list.get(position).getLogistic_name());
//        loader.displayImage(list.get(position).getLogistic_icon(),holder.logistic_icon, Options.getListOptions());
        if(list.get(position)!= null){
            adapter = new RecomendWayAdapter(context,list);
            holder.gridView.setAdapter(adapter);
        }
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 进入物流商详情的
                context.startActivity(new Intent(context, LogisticLineDetailActivity.class));
            }
        });
        return convertView;
    }
    class ViewHolder{
        ImageView logistic_icon;
        TextView logistic_name;
        MyListView gridView;
        TextView detail;
        TextView ditence;
    }
}

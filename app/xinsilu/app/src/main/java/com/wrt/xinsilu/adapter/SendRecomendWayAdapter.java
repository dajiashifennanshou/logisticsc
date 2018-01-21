package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.bean.NearByLineBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28 0028.
 * sendFragmentChild推荐路线里面的路线adapter
 */
public class SendRecomendWayAdapter extends BasicAdapter<NearByLineBean.ObjectListBean.ListBean> {
    public SendRecomendWayAdapter(Context context, List<NearByLineBean.ObjectListBean.ListBean> list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : (list.size() > 2 ? 2 : list.size());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.grid_way_activity);
            holder.from_way = (TextView) convertView.findViewById(R.id.from_way);
            holder.to_way = (TextView) convertView.findViewById(R.id.to_way);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.from_way.setText(list.get(position).getStart_outlets_name());
        holder.to_way.setText(list.get(position).getEnd_outlets_name());
        return convertView;
    }

    class ViewHolder {
        TextView from_way;
        TextView to_way;
    }
}

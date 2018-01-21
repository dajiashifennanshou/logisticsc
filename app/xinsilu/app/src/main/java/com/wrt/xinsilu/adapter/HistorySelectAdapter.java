package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.data.LocalData;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class HistorySelectAdapter extends BasicAdapter<String> {
    public HistorySelectAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.history_select_layout);
            holder.history = (TextView) convertView.findViewById(R.id.company_name);
            holder.delete = (ImageView) convertView.findViewById(R.id.delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //获取历史信息
        holder.history.setText(list.get(position));
        //删除事件
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LocalData(context).deleteHistory(list.get(position));
                list.remove(position);//移除该位置
                notifyDataSetChanged();//通知移除
            }
        });
        return convertView;
    }

    class ViewHolder {
        /**
         * 历史记录
         */
        TextView history;
        /**
         * 删除
         */
        ImageView delete;
    }
}

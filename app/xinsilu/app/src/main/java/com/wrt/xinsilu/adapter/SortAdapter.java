package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class SortAdapter extends BasicAdapter<String> {
    private int type;
    public SortAdapter(Context context, List<String> list,int type) {
        super(context, list);
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.sort_popupwindow);
            holder.img = (ImageView) convertView.findViewById(R.id.choice_img);
            holder.tv = (TextView) convertView.findViewById(R.id.sort_all);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position));
        if(type == position){
            holder.tv.setTextColor(Color.parseColor("#26B463"));
            holder.img.setVisibility(View.VISIBLE);
        }else{
            holder.img.setVisibility(View.GONE);
            holder.tv.setTextColor(Color.parseColor("#aaaaaa"));
        }
        return convertView;
    }
    class ViewHolder{
        /**排序文字显示*/
        TextView tv;
        /**显示选中状态*/
        ImageView img;
    }
}

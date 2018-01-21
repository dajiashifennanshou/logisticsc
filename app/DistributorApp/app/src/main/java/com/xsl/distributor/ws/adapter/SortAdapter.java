package com.xsl.distributor.ws.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.xsl.distributor.R;
import com.xsl.distributor.ws.bean.CloudStoreBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008.
 *
 */
public class SortAdapter extends BasicAdapter<CloudStoreBean> {

    public SortAdapter(Context context, List<CloudStoreBean> list) {
        super(context, list);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.sort_popupwindow);
            holder.tv = (TextView) convertView.findViewById(R.id.sort_all);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position).getBranchName());

        return convertView;
    }
    class ViewHolder{
        /**排序文字显示*/
        TextView tv;
    }
}

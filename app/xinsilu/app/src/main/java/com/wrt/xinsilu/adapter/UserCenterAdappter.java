package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class UserCenterAdappter extends BasicAdapter<String> {
    public UserCenterAdappter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.user_center_adapter);
            holder.tv = (TextView) convertView.findViewById(R.id.tv1);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView tv;
    }
}

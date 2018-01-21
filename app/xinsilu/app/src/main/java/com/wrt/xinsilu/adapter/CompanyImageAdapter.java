package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class CompanyImageAdapter extends BasicAdapter<String> {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    public CompanyImageAdapter(Context context, List<String> list, Handler handler) {
        super(context, list);
        this.handler = handler;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.company_add_image_adapter);
            holder.img = (ImageView) convertView.findViewById(R.id.add_image);
            holder.info = (TextView) convertView.findViewById(R.id.regest_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.info.setText(list.get(position));
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 这里点击进入相册，自定义相册，下周做
//                PopDialog dialog = new PopDialog(context,handler,position);
//                dialog.show();
            }
        });
        return convertView;
    }
    class ViewHolder{
        /**保存的照片*/
        ImageView img;
        /**显示的公司信息*/
        TextView info;
    }
}

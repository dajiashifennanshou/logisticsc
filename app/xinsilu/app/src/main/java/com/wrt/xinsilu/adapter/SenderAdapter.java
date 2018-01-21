package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.bean.SenderBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29 0029.
 * 常用发货人adapter和接收人
 */
public class SenderAdapter extends BasicAdapter<SenderBean> {
    /** type
     * 0：表示从常用发货人进来的
     * 1：常用收货人
     */
    private int type;
    /**判断是否显示设置默认收货人，默认发货人*/
    private boolean choice;
    public SenderAdapter(Context context, List<SenderBean> list,int type,boolean choice) {
        super(context, list);
        this.type = type;
        this.choice = choice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.sender_adapter);
            holder.sender_name = (TextView) convertView.findViewById(R.id.sender_name);
            holder.sender_num = (TextView) convertView.findViewById(R.id.sender_num);
            holder.status = (TextView) convertView.findViewById(R.id.statu);
            holder.sender_adress = (TextView) convertView.findViewById(R.id.sender_address);
            holder.send_text_view = (TextView) convertView.findViewById(R.id.send_textview);
            holder.content_footer_layout = (LinearLayout) convertView.findViewById(R.id.content_footer_layout);
            holder.setting = (CheckBox) convertView.findViewById(R.id.choice);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(type == 1){
            holder.send_text_view.setText("常用发货人");
            holder.setting.setText("发货地址");
        }else if(type == 2){
            holder.setting.setText("设置为收货人");
            holder.send_text_view.setText("收货地址");
        }
        if(choice){
            holder.content_footer_layout.setVisibility(View.VISIBLE);
        }else{
            holder.content_footer_layout.setVisibility(View.GONE);
        }
//        holder.sender_name.setText(list.get(position).getSender_name());
//        holder.sender_num.setText(list.get(position).getSender_num());
//        holder.status.setText(list.get(position).getStatus());
//        holder.sender_adress.setText(list.get(position).getSender_address());
        return convertView;
    }
    class ViewHolder{
        /**发货人，收货人姓名*/
        TextView sender_name;
        /**发货人，收货人电话号码*/
        TextView sender_num;
        /**发货人，收货人地址*/
        TextView sender_adress;
        TextView status;
        /**显示发货还是收货*/
        TextView send_text_view;
        /**设置为默认常用发货人，常用收货人*/
        CheckBox setting;
        LinearLayout content_footer_layout;
    }
}

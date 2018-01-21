package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.bean.MyOrderBean;
import com.wrt.xinsilu.ui.activity.OrderDetailActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class MyOrderAdapter extends BasicAdapter<MyOrderBean> {

    public MyOrderAdapter(Context context, List<MyOrderBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.my_order_adapter);
            holder.company_name = (TextView) convertView.findViewById(R.id.order_name);
            holder.status = (TextView) convertView.findViewById(R.id.order_status);
            holder.order_num = (TextView) convertView.findViewById(R.id.order_num);
            holder.price = (TextView) convertView.findViewById(R.id.order_price);
            holder.weight = (TextView) convertView.findViewById(R.id.order_weight);
            holder.volume = (TextView) convertView.findViewById(R.id.order_volume);
            holder.start_positoin = (TextView) convertView.findViewById(R.id.order_start);
            holder.end_position = (TextView) convertView.findViewById(R.id.order_end);
            holder.order_detail = (TextView) convertView.findViewById(R.id.orderDetail);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.company_name.setText(list.get(position).getCompany_name());
//        holder.status.setText(list.get(position).getStatus());
//        holder.order_num.setText(list.get(position).getOrder_num());
//        holder.price.setText(list.get(position).getPrice());
//        holder.weight.setText(list.get(position).getWeight());
//        holder.volume.setText(list.get(position).getVolume());
//        holder.start_positoin.setText(list.get(position).getStart_positoin());
//        holder.end_position.setText(list.get(position).getEnd_position());
        holder.order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 进入下一个页面，详情
                context.startActivity(new Intent(context, OrderDetailActivity.class));
            }
        });
        return convertView;
    }
    class ViewHolder{
        /**公司名字*/
        TextView company_name;
        /**状态 预约，未处理，已处理等*/
        TextView status;
        /**订单数量*/
        TextView order_num;
        /**订单重量*/
        TextView weight;
        /**订单体积*/
        TextView volume;
        /**订单价格*/
        TextView price;
        /**订单起始位置*/
        TextView start_positoin;
        /**底单终点位置*/
        TextView end_position;
        /**查看详情*/
        TextView order_detail;
    }
}

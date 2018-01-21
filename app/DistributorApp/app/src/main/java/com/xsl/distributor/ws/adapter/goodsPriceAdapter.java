package com.xsl.distributor.ws.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xsl.distributor.R;
import com.xsl.distributor.ws.bean.LogisticDetailBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/12 0012.
 * 运单详情下面货物的价格adapter
 */
public class goodsPriceAdapter extends BasicAdapter<LogisticDetailBean.CargoBean>  {
    /**
     * 初始化ImageLoader
     *
     * @param context
     * @param list
     */
    public goodsPriceAdapter(Context context, List<LogisticDetailBean.CargoBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.logistic_detail_foot_adapter);
            holder.goods_name = (TextView) convertView.findViewById(R.id.logistic_detail_name);
            holder.goods_price = (TextView) convertView.findViewById(R.id.logistic_detail_money);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.goods_name.setText(list.get(position).getName());
        holder.goods_price.setText(list.get(position).getCount_cost_mode() + "元");
        return convertView;
    }
    class ViewHolder{
        /**
         *商品名字
         */
        TextView goods_name;
        /**
         *商品价格
         */
        TextView goods_price;
    }
}

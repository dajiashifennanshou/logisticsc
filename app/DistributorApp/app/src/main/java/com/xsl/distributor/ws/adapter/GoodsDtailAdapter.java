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
 * 货物信息详情adapter
 */
public class GoodsDtailAdapter extends BasicAdapter<LogisticDetailBean.CargoBean> {
    /**
     *
     * @param context
     * @param list
     */
    public GoodsDtailAdapter(Context context, List<LogisticDetailBean.CargoBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(null == convertView){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.goods_info_layout);
            holder.name = (TextView) convertView.findViewById(R.id.goods_name);
            holder.brands = (TextView) convertView.findViewById(R.id.goods_brands);
            holder.modle = (TextView) convertView.findViewById(R.id.goods_model);
            holder.num = (TextView) convertView.findViewById(R.id.goods_num);
            holder.set = (TextView) convertView.findViewById(R.id.goods_set);
            holder.weight = (TextView) convertView.findViewById(R.id.goods_weight);
            holder.volume = (TextView) convertView.findViewById(R.id.goods_volume);
            holder.info = (TextView) convertView.findViewById(R.id.goods_info);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        holder.brands.setText(list.get(position).getBrand());
        holder.modle.setText(list.get(position).getModel());
        holder.num.setText(list.get(position).getNumber()+"");
        holder.set.setText(list.get(position).getSet_number()+"");
        holder.weight.setText(list.get(position).getSingle_weight()+"");
        holder.volume.setText(list.get(position).getSingle_volume()+"");
        holder.info.setText(list.get(position).getPackage_type()+"");

        return convertView;
    }
    class ViewHolder{
        /**货物名称*/
        TextView name;
        /**货物品牌*/
        TextView brands;
        /**货物型号*/
        TextView modle;
        /**货物件数*/
        TextView num;
        /**套数*/
        TextView set;
        /**货物重量*/
        TextView weight;
        /**体积*/
        TextView volume;
        /**包装信息*/
        TextView info;
    }
}

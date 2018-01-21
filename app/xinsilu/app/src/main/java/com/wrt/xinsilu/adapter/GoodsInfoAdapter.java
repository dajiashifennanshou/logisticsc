package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.bean.Cargo;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class GoodsInfoAdapter extends BasicAdapter<Cargo> {
    public GoodsInfoAdapter(Context context, List<Cargo> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.goods_layout);
            holder.name = (EditText) convertView.findViewById(R.id.goods_name);
            holder.brand = (EditText) convertView.findViewById(R.id.goods_brand);
            holder.size = (EditText) convertView.findViewById(R.id.goods_size);
            holder.set_num = (EditText) convertView.findViewById(R.id.goods_set_num);
            holder.num = (EditText) convertView.findViewById(R.id.goods_num);
            holder.weight = (EditText) convertView.findViewById(R.id.goods_weight);
            holder.pager_box = (TextView) convertView.findViewById(R.id.pager_box);
            holder.wood_box = (TextView) convertView.findViewById(R.id.mood_box);
            holder.foam = (TextView) convertView.findViewById(R.id.foam);
            holder.pallets = (TextView) convertView.findViewById(R.id.pallets);
            holder.rl_goodspack_types = (RelativeLayout) convertView.findViewById(R.id.goods_layout_rl_goodspack_types);
        }

        for (int i = 0; i < holder.rl_goodspack_types.getChildCount(); i++) {
            CheckedTextView childAt = (CheckedTextView) holder.rl_goodspack_types.getChildAt(i);
            final ViewHolder finalHolder = holder;
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //还原所有item状态
                    for (int i = 0; i < finalHolder.rl_goodspack_types.getChildCount(); i++) {
                        CheckedTextView childAt = (CheckedTextView) finalHolder.rl_goodspack_types.getChildAt(i);
                        childAt.setTextColor(context.getResources().getColor(R.color.font_color_hint));
                        childAt.setChecked(false);
                    }
                    ((CheckedTextView) v).setChecked(true);
                    ((CheckedTextView) v).setTextColor(context.getResources().getColor(R.color.white));
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        /**
         * 货物名称
         */
        private EditText name;
        /**
         * 品牌名称
         */
        private EditText brand;
        /**
         * 货物型号
         */
        private EditText size;
        /**
         * 货物件数
         */
        private EditText num;
        /**
         * 货物套数
         */
        private EditText set_num;
        /**
         * 货物重量
         */
        private EditText weight;
        /**
         * 货物体积
         */
        private EditText volume;
        /**
         * 纸箱
         */
        private TextView pager_box;
        /**
         * 木箱
         */
        private TextView wood_box;
        /**
         * 裸货
         */
        private TextView basic_goods;
        /**
         * 托盘
         */
        private TextView pallets;
        /**
         * 泡沫
         */
        private TextView foam;
        private RelativeLayout rl_goodspack_types;
    }
}

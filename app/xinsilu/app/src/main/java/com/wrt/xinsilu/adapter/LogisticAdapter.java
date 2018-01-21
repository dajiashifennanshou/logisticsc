package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.bean.NearByLineBean;
import com.wrt.xinsilu.ui.activity.LogisticLineDetailActivity;
import com.wrt.xinsilu.ui.view.MyListView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28 0028.
 * 推荐的物流商
 */
public class LogisticAdapter extends BasicAdapter<NearByLineBean.ObjectListBean> {
    private SendRecomendWayAdapter adapter;

    public LogisticAdapter(Context context, List<NearByLineBean.ObjectListBean> list) {
        super(context, list);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        List<NearByLineBean.ObjectListBean.ListBean> lineList = this.list.get(position).getList();
        adapter = new SendRecomendWayAdapter(context, lineList);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.recomand_way_activity);
            holder.overlay = convertView.findViewById(R.id.overlay);
            holder.logistic_name = (TextView) convertView.findViewById(R.id.logistic_name);
            holder.logistic_icon = (ImageView) convertView.findViewById(R.id.logistic_icon);
            holder.gps_guide = (TextView) convertView.findViewById(R.id.gps_guide);
            holder.gridView = (MyListView) convertView.findViewById(R.id.grid_view_way);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.logistic_name.setText(this.list.get(position).getCompanyName() != null ? this.list.get(position).getCompanyName() : "");
        holder.gps_guide.setText(String.format("%.2f", this.list.get(position).getDistance() / 1000f) + "km");
        holder.gridView.setAdapter(adapter);
        holder.gridView.setFocusable(false);
        holder.gridView.setEnabled(false);
        holder.overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LogisticLineDetailActivity.class);
                intent.putExtra("CompanyId", LogisticAdapter.this.list.get(position).getCId());
                intent.putExtra("CompanyName", LogisticAdapter.this.list.get(position).getCompanyName());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView logistic_icon;
        TextView logistic_name;
        TextView gps_guide;
        MyListView gridView;
        View overlay;
    }
}

package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.bean.CommentDetailBean;
import com.wrt.xinsilu.ui.view.CircleImageView;
import com.wrt.xinsilu.util.DateUtils;
import com.xsl.lerist.llibrarys.utils.StringUtils;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2016/7/2 0002.
 * 评论adapter
 */
public class CommentAdapter extends BasicAdapter<CommentDetailBean> {
    public CommentAdapter(Context context, List<CommentDetailBean> list) {
        super(context, list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.comment_detail_adapter);
            holder.company_name = (TextView) convertView.findViewById(R.id.company_name);
            holder.comment_date = (TextView) convertView.findViewById(R.id.comment_date);
            holder.comment_detail = (TextView) convertView.findViewById(R.id.comment_detail);
            holder.bar = (RatingBar) convertView.findViewById(R.id.RatingBar);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.company_name.setText(list.get(position).getUser().getTrue_name().equals("")
                ? list.get(position).getUser().getLogin_name()
                : list.get(position).getUser().getTrue_name());
        try {
            holder.comment_date.setText(StringUtils.toTimeStr(Long.parseLong(list.get(position).getEvaluate_time()),"yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.comment_detail.setText(list.get(position).getEvaluate_content());
        holder.bar.setRating(list.get(position).getEvaluate_level());
        return convertView;
    }
    class ViewHolder{
        TextView company_name;
        TextView comment_date;
        TextView comment_detail;
        RatingBar bar;
    }
}

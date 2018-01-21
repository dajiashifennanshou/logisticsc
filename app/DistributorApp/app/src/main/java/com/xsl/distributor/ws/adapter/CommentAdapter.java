package com.xsl.distributor.ws.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xsl.distributor.R;
import com.xsl.distributor.ws.bean.CommentBean;
import com.xsl.distributor.ws.ui.view.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11 0011.
 * 评论详情
 */
public class CommentAdapter extends BasicAdapter<CommentBean> {
    /**
     * @param context
     * @param list
     */
    public CommentAdapter(Context context, List<CommentBean> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(null == convertView){
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.comment_detail_adapter);
            holder.comment_date = (TextView) convertView.findViewById(R.id.comment_date);
            holder.comment_detail = (TextView) convertView.findViewById(R.id.comment_detail);
            holder.company_icon = (CircleImageView) convertView.findViewById(R.id.company_icon);
            holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
            holder.ratingBar = (AppCompatRatingBar) convertView.findViewById(R.id.rating_bar);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.user_name.setText(list.get(position).getUser_name());
        holder.comment_detail.setText(list.get(position).getComment_detail());
        return convertView;
    }
        class ViewHolder{
            /**用户头像*/
            CircleImageView company_icon;
            /**用户名*/
            TextView user_name;
            /**评论时间*/
            TextView comment_date;
            /**评论详情*/
            TextView comment_detail;
            /**星星*/
            AppCompatRatingBar ratingBar;
    }
}

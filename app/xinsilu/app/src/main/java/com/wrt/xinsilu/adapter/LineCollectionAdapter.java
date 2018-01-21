package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.bean.LogisticDetailBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.LineClient;
import com.wrt.xinsilu.ui.activity.CommentDetailActivity;
import com.wrt.xinsilu.ui.activity.GoodsInformationActivity;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1 0001.
 * 我的收藏里面的线路收藏
 */
public class LineCollectionAdapter extends BasicAdapter<LogisticDetailBean.PdglistBean> {
    public LineCollectionAdapter(Context context, List<LogisticDetailBean.PdglistBean> list) {
        super(context, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.line_collection_adapter);
            holder.start = (TextView) convertView.findViewById(R.id.start);
            holder.end = (TextView) convertView.findViewById(R.id.end);
            holder.time = (TextView) convertView.findViewById(R.id.line_time);
            holder.weight = (TextView) convertView.findViewById(R.id.weight);
            holder.service_type = (TextView) convertView.findViewById(R.id.service_type);
            holder.foam = (TextView) convertView.findViewById(R.id.foam);
            holder.way = (TextView) convertView.findViewById(R.id.transport_way);
            holder.lower_money = (TextView) convertView.findViewById(R.id.lower_money);
            holder.commit = (ImageView) convertView.findViewById(R.id.commit);
            holder.transport_num = (TextView) convertView.findViewById(R.id.tranport_num);
            holder.comment_num = (TextView) convertView.findViewById(R.id.comment_detail);
            holder.comment_num.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            holder.comment_num.getPaint().setAntiAlias(true);
            holder.off_sale = (ImageView) convertView.findViewById(R.id.xianshi);
            holder.iv_collect = (ImageView) convertView.findViewById(R.id.i_line_collection_iv_collection);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.start.setText(list.get(position).getStart_outlets_name());
        holder.end.setText(list.get(position).getEnd_outlets_name());
        holder.time.setText(list.get(position).getTime_long() + "小时");
        holder.weight.setText("￥" + list.get(position).getHeavy_cargo_price_mid() + "/吨");
        holder.service_type.setText(list.get(position).getServer_type());
        holder.foam.setText("￥" + list.get(position).getBulky_cargo_price_low() + "/m³");
        holder.way.setText(list.get(position).getTransport_mode());
        holder.lower_money.setText("￥" + list.get(position).getLowest_price() + "/票");
        holder.transport_num.setText("已承运" + list.get(position).getCountOrder() + "票");
        holder.comment_num.setText("已有" + list.get(position).getCountOrderEvaluation() + "条评论");

        /**模拟0表示能下单，1表示不可以下单*/
        if (list.get(position).getIsAddServer() == 1) {
            // TODO: 不能点击，要换一个图标
//            holder.off_sale.setVisibility(View.VISIBLE);
            holder.commit.setImageResource(R.mipmap.my_collection_xiadan_shixiao);
            holder.commit.setEnabled(false);
        } else {
            holder.commit.setEnabled(true);
            holder.commit.setImageResource(R.mipmap.my_collection_xiadan_nor);
//            holder.off_sale.setVisibility(View.INVISIBLE);
            holder.commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO:这里需求以后会请求网络，或者将改路线传递到发货界面
                    Intent intent = new Intent(context, GoodsInformationActivity.class);
                    intent.putExtra("tms_line_id", list.get(position).getId());
                    intent.putExtra("sendNet", list.get(position).getStart_outlets());
                    intent.putExtra("ainpickNet", list.get(position).getEnd_outlets());
                    context.startActivity(intent);
                }
            });
        }
        if (list.get(position).getIsCollect() == 0) {
            holder.iv_collect.setImageResource(R.mipmap.common_collection_nor);
        } else {
            holder.iv_collect.setImageResource(R.mipmap.common_collection_shixiao);
        }
        final ViewHolder finalHolder = holder;
        holder.iv_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getIsCollect() == 0) {
                    uncollect(finalHolder.iv_collect, list.get(position));
                } else {
                    collect(finalHolder.iv_collect, list.get(position));
                }
            }
        });

        holder.comment_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).getCountOrderEvaluation() != 0){
                    context.startActivity(new Intent(context, CommentDetailActivity.class)
                            .putExtra("lineId",list.get(position).getId())
                            .putExtra("CommentNumber",list.get(position).getCountOrderEvaluation()));
                }
            }
        });

        return convertView;
    }

    private void collect(final View view, final LogisticDetailBean.PdglistBean itemData) {
        UserInfo userInfo = new LocalData(context).readUserInfo();
        if (userInfo == null) return;

        new LineClient().addCollectLine(userInfo.getUser().getId(),
                itemData.getId(),
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                LToast.show(context, "已收藏");
                                itemData.setIsCollect(0);
                                ((android.widget.ImageView) view).setImageResource(R.mipmap.common_collection_nor);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    private void uncollect(final View view, final LogisticDetailBean.PdglistBean itemData) {
        UserInfo userInfo = new LocalData(context).readUserInfo();
        if (userInfo == null) return;

        new LineClient().deleteCollectLine(userInfo.getUser().getId(), itemData.getId(),
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                LToast.show(context, "已取消收藏");
                                itemData.setIsCollect(1);
                                ((android.widget.ImageView) view).setImageResource(R.mipmap.common_collection_shixiao);
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    class ViewHolder {
        /**
         * 起点
         */
        TextView start;
        /**
         * 终点
         */
        TextView end;
        /**
         * 时效
         */
        TextView time;
        /**
         * 重货
         */
        TextView weight;
        /**
         * 服务类型
         */
        TextView service_type;
        /**
         * 泡货
         */
        TextView foam;
        /**
         * 运输方式
         */
        TextView way;
        /**
         * 最低价
         */
        TextView lower_money;
        /**
         * 下单
         */
        ImageView commit;
        /**
         * 下架
         */
        ImageView is_sale;
        /**
         * 承运数量
         */
        TextView transport_num;
        /**
         * 评论数量
         */
        TextView comment_num;
        /**
         * 下架图标
         */
        ImageView off_sale;

        ImageView iv_collect;
    }
}

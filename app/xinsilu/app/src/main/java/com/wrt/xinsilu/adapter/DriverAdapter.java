package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicAdapter;
import com.wrt.xinsilu.bean.DriverBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.lerist.client.CommonContactClient;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.List;

//┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃ 　
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//┃　　　┃  神兽保佑　　　　　　　　
//┃　　　┃  代码无BUG！
//┃　　　┗━━━┓
//┃　　　　　　　┣┓
//┃　　　　　　　┏┛
//┗┓┓┏━┳┓┏┛
// ┃┫┫　┃┫┫
// ┗┻┛　┗┻┛
public class DriverAdapter extends BasicAdapter<DriverBean> {
    /**
     * 表示从那个界面进来的
     */
    private boolean type;

    public DriverAdapter(Context context, List<DriverBean> list, boolean type) {
        super(context, list);
        this.type = type;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getConvertView(R.layout.driver_adapter);
            holder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.i_common_list_driver_sl);
            holder.btn_del = (TextView) convertView.findViewById(R.id.i_common_list_driver_btn_delete);
            holder.driver_name = (TextView) convertView.findViewById(R.id.driver_name);
            holder.driver_num = (TextView) convertView.findViewById(R.id.driver_num);
            holder.driver_liscense = (TextView) convertView.findViewById(R.id.driver_license);
            holder.layout = (LinearLayout) convertView.findViewById(R.id.content_footer_layout);
            holder.button = (CheckBox) convertView.findViewById(R.id.choice);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.layout.setVisibility(type ? View.VISIBLE : View.GONE);
        holder.swipeLayout.setSwipeEnabled(!type);
        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
            }
        });
        holder.driver_name.setText(list.get(position).getDriver_name() != null ? list.get(position).getDriver_name() : "");
        holder.driver_num.setText(list.get(position).getPhone_number() != null ? list.get(position).getPhone_number() : "");
        holder.driver_liscense.setText(list.get(position).getLicense_number() != null ? list.get(position).getLicense_number() : "");
        holder.button.setText("设置为司机");
        return convertView;
    }

    private void delete(final int position) {
        new AlertDialog.Builder(context).setTitle("提示")
                .setMessage("确认删除该条联系人?")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new CommonContactClient().deleteDriver(list.get(position).getId(), new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                KLog.i(result);
                                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                if (resultInfo == null) return;

                                switch (resultInfo.getCode()) {
                                    case ResultCode.SUCCEED:
                                        LToast.show(context, "已删除");
                                        list.remove(position);
                                        notifyDataSetChanged();
                                        break;
                                    default:
                                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "删除失败, 请稍后再试" : resultInfo.getMsg());
                                        break;
                                }
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                LToast.show(context, "删除失败, 请稍后再试");
                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                    }
                }).show();
    }

    class ViewHolder {
        /**
         * 司机名字
         */
        TextView driver_name;
        /**
         * 司机电话号码
         */
        TextView driver_num;
        /**
         * 司机驾照
         */
        TextView driver_liscense;
        LinearLayout layout;
        CheckBox button;
        SwipeLayout swipeLayout;
        TextView btn_del;
    }
}

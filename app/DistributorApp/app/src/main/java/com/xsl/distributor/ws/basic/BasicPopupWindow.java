package com.xsl.distributor.ws.basic;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.xsl.distributor.R;
import com.xsl.distributor.ws.adapter.SortAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class BasicPopupWindow<T> extends PopupWindow implements View.OnClickListener{

    public View conentView;
    /**综合排序*/
    public ListView sort;
    public Handler handler;
    public Activity context;
    public List<T> list;
    public SortAdapter adapter;
    public FrameLayout layout_popup;
    private int h;
    private int w;

    public BasicPopupWindow(final Activity context, Handler handler, List<T>list) {
        this.context = context;
        this.handler = handler;
        this.list = list;
        setUpViews();
    }

    private void setUpViews() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popupwindow_list, null);
        layout_popup = (FrameLayout) conentView.findViewById(R.id.layout_popup);
        layout_popup.setOnClickListener(this);
        sort = (ListView) conentView.findViewById(R.id.sort_listview);
        h = context.getWindowManager().getDefaultDisplay().getWidth();
        w = context.getWindowManager().getDefaultDisplay().getHeight();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(h);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        setAnimationStyle(R.style.PopupAnimation);
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        backgroundAlpha(0.5f);
    }

//    private void setadapter() {
//        adapter = new SortAdapter(context,list);
//        sort.setAdapter(adapter);
//        sort.setOnItemClickListener(this);
//    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent,boolean alignBottom) {
        if(alignBottom == false){
            if (!this.isShowing()) {
                // 以下拉方式显示popupwindow
                showAsDropDown(parent, parent.getMeasuredWidth(), 0);

            } else {
                dismiss();
            }

        }else{
            showAtLocation(parent , Gravity.BOTTOM, parent.getMeasuredWidth() , 0 );

        }

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Message message = new Message();
//        message.obj = list.get(position);
//        handler.sendMessage(message);
//        LToast.show(context,list.get(position).getBranchName());
//        LToast.show(context,list.get(position).getBranchNo());
//        backgroundAlpha(1f);
        dismiss();
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}


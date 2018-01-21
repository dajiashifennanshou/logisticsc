package com.wrt.xinsilu.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.SortAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class SortPopupWindow extends PopupWindow implements AdapterView.OnItemClickListener {

    public static final int FLAG_OPEN = 1000;
    public static final int FLAG_CLOSE = 2000;
    private View conentView;
    /**
     * 综合排序
     */
    private ListView sort;
    private Handler handler;
    private Activity context;
    private String[] str;
    private int type;
    private SortAdapter adapter;
    private List<String> list;

    public SortPopupWindow(final Activity context, Handler handler, int type) {
        this.context = context;
        this.handler = handler;
        this.type = type;
        setUpViews();
        setUpDada();
        setadapter();
        handler.sendEmptyMessage(FLAG_OPEN);
    }

    private void setUpViews() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popupwindow_list, null);
        conentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        sort = (ListView) conentView.findViewById(R.id.sort_listview);
        int h = context.getWindowManager().getDefaultDisplay().getWidth();
        int w = context.getWindowManager().getDefaultDisplay().getHeight();
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
//        setAnimationStyle(R.style.PopupAnimation);
        // 设置SelectPicPopupWindow弹出窗体动画效果
    }

    private void setadapter() {
        adapter = new SortAdapter(context, list, type);
        sort.setAdapter(adapter);
        sort.setOnItemClickListener(this);
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent, boolean alignBottom) {
        if (alignBottom == false) {
            if (!this.isShowing()) {
                // 以下拉方式显示popupwindow
                showAsDropDown(parent, parent.getLayoutParams().width / 2, 0);
            } else {
                dismiss();
            }

        } else {
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        handler.sendEmptyMessage(position);
        dismiss();
    }

    private void setUpDada() {
        list = new ArrayList<>();
        str = new String[]{"综合排序", "离我最近", "重货价从低到高", "泡货价从低到高"};
        list = Arrays.asList(str);
    }

    @Override
    public void dismiss() {
        handler.sendEmptyMessage(FLAG_CLOSE);
        super.dismiss();
    }
}


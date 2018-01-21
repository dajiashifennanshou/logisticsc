package com.wrt.xinsilu.basic;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.citywhell.wheel.widget.WheelView;
import com.wrt.xinsilu.citywhell.wheel.widget.cascade.activity.BaseActivity;
import com.wrt.xinsilu.data.LocalData;

/**
 * Created by Administrator on 2016/8/5 0005.
 *
 */
public class BasicPopupWindow extends PopupWindow {
    public Activity context;
    public View conentView;
    public Handler handler;
    public BasicPopupWindow(Activity context, Handler handler, int id) {
        this.context = context;
        this.handler = handler;
        setUpViews(id);


    }
    private void setUpViews(int id) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(id, null);

//        int h = context.getWindowManager().getDefaultDisplay().getHeight();
//        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
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
        setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果

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
                showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
            } else {
                dismiss();
            }

        } else {
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        }

    }
}

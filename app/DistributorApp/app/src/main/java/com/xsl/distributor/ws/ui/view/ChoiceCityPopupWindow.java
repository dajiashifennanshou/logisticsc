package com.xsl.distributor.ws.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xsl.distributor.R;
import com.xsl.distributor.ws.ui.widget.OnWheelChangedListener;
import com.xsl.distributor.ws.ui.widget.WheelView;
import com.xsl.distributor.ws.ui.widget.adapters.ArrayWheelAdapter;
import com.xsl.distributor.ws.ui.widget.cascade.activity.BaseActivity;

import java.util.List;

/**
 * 使用的时候在加载布局的时候自己吧布局放进去
 * 初始化并设置响应事件
 * 显示的时候根据情况自己调整
 * 第一个参数是父容器，第二个是否依附控件，true依附，false，底部弹出
 */
public class ChoiceCityPopupWindow extends PopupWindow implements View.OnClickListener, OnWheelChangedListener {

    private View conentView;
    /**
     * 取消
     */
    private TextView delete;
    /**
     * 确认
     */
    private TextView ok;
    /**
     * 省
     */
    private WheelView view1;

    private Activity context;
    private BaseActivity base;
    private Handler handler;

    public ChoiceCityPopupWindow(final Activity context, Handler handler, List<String> list) {
        this.context = context;
        this.handler = handler;
        setUpViews();
        setUpListener();
        setUpData(list);
    }

    private void setUpViews() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        conentView = inflater.inflate(R.layout.add_popup_dialog, null);
        conentView = inflater.inflate(R.layout.city_layout_single, null);
        delete = (TextView) conentView.findViewById(R.id.btn_delete);
        ok = (TextView) conentView.findViewById(R.id.btn_confirm);
        view1 = (WheelView) conentView.findViewById(R.id.id_province);
        view1.setWheelBackground(R.color.white);
        base = new BaseActivity();
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w);
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

    }

    private void setUpData(List<String> list) {
//        base.initProvinceDatas();
        base.initCitydata(list);
        view1.setViewAdapter(new ArrayWheelAdapter<String>(context, base.mProvinceDatas));
        // 设置可见条目数量
        view1.setVisibleItems(7);
        updateCities();
    }

    private void setUpListener() {
        delete.setOnClickListener(this);
        ok.setOnClickListener(this);
        view1.addChangingListener(this);

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


    @Override
    public void onClick(View v) {
        if (v == delete) {
            dismiss();
        } else if (v == ok) {
//            showSelectedResult();
            Log.i("name", "" + base.mCurrentProviceName);
            Message message = new Message();
            message.obj = base.mCurrentProviceName;
            message.what = view1.getCurrentItem();
            handler.sendMessage(message);
            dismiss();
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        updateCities();
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = view1.getCurrentItem();
        if (base.mProvinceDatas.length > 0 && pCurrent < base.mProvinceDatas.length) {
            base.mCurrentProviceName = base.mProvinceDatas[pCurrent];
        }
    }
}
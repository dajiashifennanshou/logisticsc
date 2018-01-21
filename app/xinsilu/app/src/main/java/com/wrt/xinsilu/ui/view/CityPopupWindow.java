package com.wrt.xinsilu.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.citywhell.wheel.widget.OnWheelChangedListener;
import com.wrt.xinsilu.citywhell.wheel.widget.WheelView;
import com.wrt.xinsilu.citywhell.wheel.widget.adapters.ArrayWheelAdapter;
import com.wrt.xinsilu.citywhell.wheel.widget.cascade.activity.BaseActivity;


/**
 * Created by songgege on 2016/4/1.
 * 使用的时候在加载布局的时候自己吧布局放进去
 * 初始化并设置响应事件
 * 显示的时候根据情况自己调整
 * 第一个参数是父容器，第二个是否依附控件，true依附，false，底部弹出
 */
public class CityPopupWindow extends PopupWindow implements View.OnClickListener, OnWheelChangedListener {

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
    /**
     * 市
     */
    private WheelView view2;
    /**
     * 区县
     */
    private WheelView view3;
    private Activity context;
    private BaseActivity base;
    private Handler handler;

    public CityPopupWindow(final Activity context, Handler handler) {
        this.context = context;
        this.handler = handler;
        setUpViews();
        setUpListener();
        setUpData();


    }

    private void setUpViews() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        conentView = inflater.inflate(R.layout.add_popup_dialog, null);
        conentView = inflater.inflate(R.layout.city_layout, null);
        delete = (TextView) conentView.findViewById(R.id.btn_delete);
        ok = (TextView) conentView.findViewById(R.id.btn_confirm);
        view1 = (WheelView) conentView.findViewById(R.id.id_province);
        view2 = (WheelView) conentView.findViewById(R.id.id_city);
        view3 = (WheelView) conentView.findViewById(R.id.id_district);
        view1.setWheelBackground(R.color.white);
        view2.setWheelBackground(R.color.white);
        view3.setWheelBackground(R.color.white);
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
        ColorDrawable dw = new ColorDrawable(55000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        setAnimationStyle(R.style.PopupAnimation);
        // 设置SelectPicPopupWindow弹出窗体动画效果

    }

    private void setUpData() {
        base.initProvinceDatas();
        view1.setViewAdapter(new ArrayWheelAdapter<String>(context, base.mProvinceDatas));
        // 设置可见条目数量
        view1.setVisibleItems(7);
        view2.setVisibleItems(7);
        view3.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    private void setUpListener() {
        delete.setOnClickListener(this);
        ok.setOnClickListener(this);
        view1.addChangingListener(this);
        view2.addChangingListener(this);
        view3.addChangingListener(this);

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
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("Province", base.mCurrentProviceName);
            bundle.putString("City", base.mCurrentCityName);
            bundle.putString("District", base.mCurrentDistrictName);
            message.setData(bundle);
            message.obj = base.mCurrentProviceName + base.mCurrentCityName + base.mCurrentDistrictName;
            handler.sendMessage(message);
            dismiss();
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == view1) {
            updateCities();
        } else if (wheel == view2) {
            updateAreas();
        } else if (wheel == view3) {
            base.mCurrentDistrictName = base.mDistrictDatasMap.get(base.mCurrentCityName)[newValue];
            base.mCurrentZipCode = base.mZipcodeDatasMap.get(base.mCurrentDistrictName);
        }

    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = view2.getCurrentItem();
        base.mCurrentCityName = base.mCitisDatasMap.get(base.mCurrentProviceName)[pCurrent];
        base.mCurrentDistrictName = base.mDistrictDatasMap.get(base.mCurrentCityName)[0];
        String[] areas = base.mDistrictDatasMap.get(base.mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        view3.setViewAdapter(new ArrayWheelAdapter<String>(context, areas));
        view3.setCurrentItem(0);
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = view1.getCurrentItem();
        base.mCurrentProviceName = base.mProvinceDatas[pCurrent];
        String[] cities = base.mCitisDatasMap.get(base.mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        view2.setViewAdapter(new ArrayWheelAdapter<String>(context, cities));
        view2.setCurrentItem(0);
        updateAreas();
    }

    private void showSelectedResult() {
        Toast.makeText(context, "当前选中:" + base.mCurrentProviceName + "," + base.mCurrentCityName + ","
                + base.mCurrentDistrictName + "," + base.mCurrentZipCode, Toast.LENGTH_SHORT).show();
    }
}
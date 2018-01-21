package com.wrt.xinsilu.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.ProvinceBean;
import com.wrt.xinsilu.citywhell.wheel.widget.OnWheelChangedListener;
import com.wrt.xinsilu.citywhell.wheel.widget.WheelView;
import com.wrt.xinsilu.citywhell.wheel.widget.adapters.ArrayWheelAdapter;
import com.wrt.xinsilu.citywhell.wheel.widget.cascade.activity.BaseActivity;
import com.wrt.xinsilu.data.LocalData;
import com.xsl.lerist.llibrarys.utils.FileUtils;

import java.util.List;

/**
 * Created by songgege on 2016/4/1.
 * 使用的时候在加载布局的时候自己吧布局放进去
 * 初始化并设置响应事件
 * 显示的时候根据情况自己调整
 * 第一个参数是父容器，第二个是否依附控件，true依附，false，底部弹出
 */
public class CustomPopupWindow extends PopupWindow implements View.OnClickListener, OnWheelChangedListener {

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
    private int type;
    private LocalData data;
    private List<ProvinceBean> provinceList;
    private ArrayWheelAdapter<ProvinceBean> provinceArrayWheelAdapter;
    private ArrayWheelAdapter<ProvinceBean.CityBean> cityBeanArrayWheelAdapter;
    private ArrayWheelAdapter<ProvinceBean.CityBean.CountyBean> countyBeanArrayWheelAdapter;

    public CustomPopupWindow(final Activity context, Handler handler, int type) {
        this.context = context;
        this.handler = handler;
        this.type = type;
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
        base = new BaseActivity();
        data = new LocalData(context);
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
        setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果

    }

    private void setUpData() {

        // 设置可见条目数量
        view1.setVisibleItems(7);
        view2.setVisibleItems(7);
        view3.setVisibleItems(7);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {

//        String aa = FileUtils.readFileContent(Environment.getExternalStorageDirectory() + "/city.json");
        String aa = FileUtils.getFromAssets(context, "city.json");
        provinceList = JSON.parseArray(aa, ProvinceBean.class);
        if (provinceList == null) return;
        provinceArrayWheelAdapter = new ArrayWheelAdapter<>(context, provinceList.toArray(new ProvinceBean[]{}));
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                view1.setViewAdapter(provinceArrayWheelAdapter);
                updateCities();
                updateAreas();
            }
        });
//            }
//        }).start();

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
            showSelectedResult();
            Message message = new Message();
            ProvinceBean province = provinceArrayWheelAdapter.getItemData(view1.getCurrentItem());
            ProvinceBean.CityBean cityBean = cityBeanArrayWheelAdapter.getItemData(view2.getCurrentItem());
            ProvinceBean.CityBean.CountyBean countyBean = countyBeanArrayWheelAdapter.getItemData(view3.getCurrentItem());
            Bundle bundle = new Bundle();
            bundle.putSerializable(ProvinceBean.class.getSimpleName(), province);
            bundle.putSerializable(ProvinceBean.CityBean.class.getSimpleName(), cityBean);
            bundle.putSerializable(ProvinceBean.CityBean.CountyBean.class.getSimpleName(), countyBean);
            message.setData(bundle);
            handler.sendMessage(message);
            dismiss();
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == view1) {
            updateCities();
            updateAreas();
        } else if (wheel == view2) {
            updateAreas();
        } else if (wheel == view3) {
        }

    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = view2.getCurrentItem();
        if (cityBeanArrayWheelAdapter.getItemsCount() > pCurrent) {
            countyBeanArrayWheelAdapter = new ArrayWheelAdapter<>(context, cityBeanArrayWheelAdapter.getItemData(pCurrent).getList().toArray(new ProvinceBean.CityBean.CountyBean[]{}));
        } else {
            countyBeanArrayWheelAdapter = new ArrayWheelAdapter<>(context, new ProvinceBean.CityBean.CountyBean[]{});
        }
        view3.setViewAdapter(countyBeanArrayWheelAdapter);
        view3.setCurrentItem(0);
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = view1.getCurrentItem();
        if (provinceArrayWheelAdapter.getItemsCount() > pCurrent) {
            cityBeanArrayWheelAdapter = new ArrayWheelAdapter<>(context, provinceArrayWheelAdapter.getItemData(pCurrent).getList().toArray(new ProvinceBean.CityBean[]{}));
        } else {
            cityBeanArrayWheelAdapter = new ArrayWheelAdapter<>(context, new ProvinceBean.CityBean[]{});
        }
        view2.setViewAdapter(cityBeanArrayWheelAdapter);
        view2.setCurrentItem(0);
    }

    private void showSelectedResult() {
//        Toast.makeText(context, "当前选中:" + base.mCurrentProviceName + "," + base.mCurrentCityName + ","
//                + base.mCurrentDistrictName + "," + base.mCurrentZipCode, Toast.LENGTH_SHORT).show();
    }
}
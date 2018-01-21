package com.wrt.xinsilu.lerist.ui.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.citywhell.wheel.widget.OnWheelChangedListener;
import com.wrt.xinsilu.citywhell.wheel.widget.WheelView;
import com.wrt.xinsilu.citywhell.wheel.widget.adapters.ArrayWheelAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lerist on 2016/8/16, 0016.
 */

public class WheelPopupWindow<T extends Object> extends PopupWindow{
    private final Context mContext;
    private final T[] mDataArray;
    private final OnSelectListener<T> mOnSelectListener;
    @BindView(R.id.p_wheel_selector_wv_list)
    WheelView wheelView;
    private ArrayWheelAdapter<T> wheelAdapter;

    public interface OnSelectListener<T extends Object> {
        void onCancel();

        void onOk(T selectedData);
    }

    public WheelPopupWindow(Context context, T[] dataArray, OnSelectListener<T> onSelectListener) {
        super(context);
        this.mContext = context;
        this.mDataArray = dataArray;
        this.mOnSelectListener = onSelectListener;

        initView();
    }

    private void initView() {
        View rootView = View.inflate(mContext, R.layout.popupwindow_wheel_selector, null);
        ButterKnife.bind(this, rootView);
        setContentView(rootView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        ColorDrawable dw = new ColorDrawable(55000000);
        this.setBackgroundDrawable(dw);
        setInputMethodMode(INPUT_METHOD_FROM_FOCUSABLE);

        setAnimationStyle(R.style.PopupAnimation);


        wheelAdapter = new ArrayWheelAdapter<>(mContext, mDataArray);
        wheelView.setViewAdapter(wheelAdapter);
        wheelView.setVisibleItems(7);
        wheelView.setWheelBackground(R.color.white);
        wheelView.setWheelForeground(R.color.font_color_focused);
        wheelView.setDrawShadows(true);
        wheelView.setCurrentItem(0);
        wheelView.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {

            }
        });

        update();
    }

    @OnClick({R.id.p_wheel_selector_btn_cancel, R.id.p_wheel_selector_btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.p_wheel_selector_btn_cancel:
                mOnSelectListener.onCancel();
                dismiss();
                break;
            case R.id.p_wheel_selector_btn_ok:
                mOnSelectListener.onOk(wheelAdapter.getItemData(wheelView.getCurrentItem()));
                dismiss();
                break;
        }
    }
}

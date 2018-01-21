package com.xsl.distributor.lerist.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.xsl.distributor.R;

import carbon.widget.LinearLayout;

/**
 * Created by Lerist on 2016/7/9, 0009.
 */

public class NumRegulatorView extends LinearLayout implements View.OnClickListener {

    private View rootView;
    private TextView tv_num;
    private View btn_cut;
    private View btn_add;
    private int maxValue;
    private int minValue;
    private Object host;
    private OnNumChangedListener onNumChangedListener;

    public interface OnNumChangedListener {
        void onNumAdd(Object host, int addValue);

        void onNumCut(Object host, int cutValue);
    }

    public NumRegulatorView(Context context) {
        this(context, null);
    }

    public NumRegulatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumRegulatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public NumRegulatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    public <E extends View> E find(int resid) {
        return (E) find(resid, View.class);
    }

    public <E extends View> E find(int resid, Class<E> e) {
        View view = findViewById(resid);
        return (E) view;
    }

    private void init() {
        rootView = View.inflate(getContext(), R.layout.view_num_regulator, this);

        btn_cut = find(R.id.v_num_reglator_btn_cut);
        tv_num = find(R.id.v_num_reglator_tv_num, TextView.class);
        btn_add = find(R.id.v_num_reglator_btn_add);

        btn_cut.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        tv_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tv_num.getText().toString().equals("0") && tv_num.getVisibility() == GONE)
                    return;

                if (s == null || s.length() == 0) {
                    hideCut();
                    return;
                }

                try {
                    int num = Integer.parseInt(String.valueOf(s));

                    if (num > 0) showCut();
                    else hideCut();
                } catch (NumberFormatException e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public Object getHost() {
        return host;
    }

    public void setHost(Object host) {
        this.host = host;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.v_num_reglator_btn_add:
                try {
                    int num = Integer.parseInt(tv_num.getText().toString());
                    int newNum = num + 1;
                    if (newNum > maxValue) {
                        newNum = maxValue;
                    } else {
                        if (onNumChangedListener != null) onNumChangedListener.onNumAdd(host, 1);
                    }
                    tv_num.setText(newNum + "");
                } catch (NumberFormatException e) {
                }

                break;
            case R.id.v_num_reglator_btn_cut:
                try {
                    int num = Integer.parseInt(tv_num.getText().toString());
                    int newNum = num - 1;
                    if (newNum < minValue) {
                        newNum = minValue;
                    } else {
                        if (onNumChangedListener != null) onNumChangedListener.onNumCut(host, 1);
                    }
                    tv_num.setText(newNum + "");
                } catch (NumberFormatException e) {
                }
                break;
        }
    }

    private void hideCut() {
        tv_num.setVisibility(GONE);
        tv_num.setText(0 + "");
        btn_cut.setVisibility(GONE);
    }

    private void showCut() {
        tv_num.setVisibility(VISIBLE);
        btn_cut.setVisibility(VISIBLE);
    }

    public void setOnNumChangedListener(OnNumChangedListener onNumChangedListener) {
        this.onNumChangedListener = onNumChangedListener;
    }
}

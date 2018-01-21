package com.xsl.lerist.llibrarys.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.xsl.lerist.llibrarys.R;

import java.util.Calendar;


/**
 * Created by Lerist on 2015/4/22, 0022.
 */
public class LDateDialog implements DatePicker.OnDateChangedListener {


    private final Context context;
    private final LayoutInflater inflater;
    private DatePicker datePicker;
    private AlertDialog.Builder builder;
    private String dateStr;
    private DatePicker.OnDateChangedListener onDateChangedListener;

    public static LDateDialog with(Context context) {
        return new LDateDialog(context);
    }

    public static LDateDialog with(Context context, int year, int month, int day) {
        return new LDateDialog(context, year, month, day);
    }

    public LDateDialog(Context context) {
        this(context, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    public LDateDialog(Context context, int year, int month, int day) {
        this.context = context;
        inflater = LayoutInflater.from(context);

        initView(year, month, day);
    }

    private void initView(int year, int month, int day) {
        View view = inflater.inflate(R.layout.ldialog_datepicker, null);
        if (view.getParent() != null)
            ((ViewGroup) view.getParent()).removeView(view);

        datePicker = (DatePicker) view.findViewById(R.id.ldialog_dp);
        datePicker.init(year, month, day, this);
        String monthStr = (month + 1) + "";
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }
        dateStr = year + "-" + monthStr + "-" + day;
        builder = new AlertDialog.Builder(context);
        builder.setView(view);
    }

    public LDateDialog setOnDateChangedListener(DatePicker.OnDateChangedListener onDateChangedListener) {
        this.onDateChangedListener = onDateChangedListener;
        return this;
    }

    public LDateDialog setNegativeButtonOnClickListener(DialogInterface.OnClickListener onClickListner) {
        builder.setNegativeButton("取消", onClickListner);
        return this;
    }

    public LDateDialog setPositiveButtonOnClickListener(DialogInterface.OnClickListener onClickListner) {
        builder.setPositiveButton("确定", onClickListner);
        return this;
    }

    public void show() {
        builder.create().show();
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String monthStr = (monthOfYear + 1) + "";
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }
        builder.setTitle(year + "-" + monthStr + "-" + dayOfMonth);
        dateStr = year + "-" + monthStr + "-" + dayOfMonth;

        if (onDateChangedListener != null) {
            onDateChangedListener.onDateChanged(view, year, monthOfYear, dayOfMonth);
        }
    }
}

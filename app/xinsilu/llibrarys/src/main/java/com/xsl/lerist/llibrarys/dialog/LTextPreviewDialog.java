package com.xsl.lerist.llibrarys.dialog;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xsl.lerist.llibrarys.R;


/**
 * Created by Lerist on 2016/5/3, 0003.
 */
public class LTextPreviewDialog extends DialogFragment {

    private LinearLayout rootView;
    private FragmentManager manager;
    private CharSequence text = "";
    private int textSize = 20;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.TranslucentAlertDialog);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = new LinearLayout(getContext());
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setGravity(Gravity.CENTER);
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(textSize);
        rootView.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return rootView;
    }

    public void setFragmentManager(FragmentManager manager) {
        this.manager = manager;
    }

    public LTextPreviewDialog setText(CharSequence text, int textSize) {
        this.text = text;
        this.textSize = textSize;
        return this;
    }

    public void show() {
        if (manager == null) return;

        super.show(manager, this.getClass().getSimpleName());
    }

    public static LTextPreviewDialog with(FragmentManager manager) {
        LTextPreviewDialog previewDialog = new LTextPreviewDialog();
        previewDialog.setFragmentManager(manager);
        return previewDialog;
    }
}

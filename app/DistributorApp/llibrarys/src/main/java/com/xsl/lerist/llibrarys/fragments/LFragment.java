package com.xsl.lerist.llibrarys.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.xsl.lerist.llibrarys.R;

import org.xutils.x;

/**
 * Created by Lerist on 2015/9/4, 0004.
 */
public class LFragment extends Fragment {

    public Context context;
    private int flag;
    private int height = -1;
    private int width = -1;
    private View rootView;
    private boolean isCreated;

    public LFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = x.view().inject(this, inflater, container);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resize();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

        isCreated = true;
        //初始化默认视图
        initDefaultView();

    }


    /**
     * 调整高度和宽度
     */
    private void resize() {
        if (getView() == null) return;
        if (height == -1 && width == -1) return;

        getView().setAlpha(0);
        getView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    getView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                ViewGroup.LayoutParams layoutParams = getView().getLayoutParams();
                if (height != -1)
                    layoutParams.height = height;
                if (width != -1)
                    layoutParams.width = width;
                getView().setLayoutParams(layoutParams);
                getView().setAlpha(1);
            }
        });
    }

    /**
     * 初始化默认视图
     */
    private void initDefaultView() {
        View btn_back = findViewById(R.id.btn_back);
        if (btn_back != null)
            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
    }

    public void setHeight(final int height) {
        this.height = height;
        resize();
    }

    public void setWidth(final int width) {
        this.width = width;
        resize();
    }

    public void setText(String text, int resId) {
        TextView textView = find(resId, TextView.class);
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void setTitle(String title) {
        setText(title, R.id.tv_title);
    }

    public void onRefreshFragment() {
    }

    public <E extends View> E find(int resid) {
        return (E) find(resid, View.class);
    }

    public <E extends View> E find(int resid, Class<E> e) {
        View view = findViewById(resid);
        return (E) view;
    }

    public View findViewById(int resid) {
        return getView() == null ? null : getView().findViewById(resid);
    }

    public void startActivity(Class c) {
        startActivity(new Intent(context, c));
    }

    public void startActivity(Class c, Bundle options) {
        getActivity().startActivity(new Intent(context, c), options);
    }


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void runOnUiThread(Runnable runnable) {
        if (context instanceof Activity)
            ((Activity) context).runOnUiThread(runnable);
        else throw new IllegalArgumentException("context instanceof Activity is false.");
    }

    public boolean isCreated() {
        return isCreated;
    }

    /**
     * @return 拦截(消耗)此次返回动作
     */
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isCreated = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

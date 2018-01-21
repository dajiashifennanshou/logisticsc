package com.wrt.xinsilu.ui.view;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.BasicPopupWindow;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class LogisticCollectPopupWindow extends BasicPopupWindow implements View.OnClickListener{
    private TextView collction;
    private TextView dail;
    private int type;
    /**
     *
     * @param context
     * @param handler
     * @param id xml布局文件
     * @param type 判断收藏类型
     */
    public LogisticCollectPopupWindow(Activity context, Handler handler, int id,int type) {
        super(context, handler, id);
        this.type = type;
        initView();
        setOnClickListener();
    }

    public void initView(){
        collction = (TextView) conentView.findViewById(R.id.collection);
        dail = (TextView) conentView.findViewById(R.id.dail);
        if(type == 0){
            collction.setText("取消收藏");
            Drawable drawable = context.getResources().getDrawable(R.mipmap.common_collection_nor);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            collction.setCompoundDrawables(drawable,null,null,null);
        }else if(type == 1){
            collction.setText("收藏物流商");
            Drawable drawable = context.getResources().getDrawable(R.mipmap.common_collection_shixiao);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            collction.setCompoundDrawables(drawable,null,null,null);
        }

    }
    public void setOnClickListener(){
        collction.setOnClickListener(this);
        dail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (collction == view){
            handler.sendEmptyMessage(0);//表示点击的收藏
            dismiss();
        }else if(dail == view){
            handler.sendEmptyMessage(1);//表示点击的打电话
            dismiss();
        }
    }
}

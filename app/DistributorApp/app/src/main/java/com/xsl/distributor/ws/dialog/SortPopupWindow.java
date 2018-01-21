package com.xsl.distributor.ws.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.xsl.distributor.R;
import com.xsl.distributor.ws.adapter.SortAdapter;
import com.xsl.distributor.ws.bean.CloudStoreBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class SortPopupWindow extends PopupWindow implements AdapterView.OnItemClickListener,View.OnClickListener{

    private View conentView;
    /**综合排序*/
    private ListView sort;
    private Handler handler;
    private Activity context;
    private List<CloudStoreBean>list;
    private SortAdapter adapter;
    private FrameLayout layout_popup;
    public SortPopupWindow(final Activity context, Handler handler, List<CloudStoreBean>list) {
        this.context = context;
        this.handler = handler;
        this.list = list;
        setUpViews();
        setadapter();
    }

    private void setUpViews() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.popupwindow_list, null);
        layout_popup = (FrameLayout) conentView.findViewById(R.id.layout_popup);
        layout_popup.setOnClickListener(this);
        sort = (ListView) conentView.findViewById(R.id.sort_listview);
        int h = context.getWindowManager().getDefaultDisplay().getWidth();
        int w = context.getWindowManager().getDefaultDisplay().getHeight();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(h);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
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
//        backgroundAlpha(0.5f);
    }

    private void setadapter() {
        adapter = new SortAdapter(context,list);
        sort.setAdapter(adapter);
        sort.setOnItemClickListener(this);
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent,boolean alignBottom) {
        if(alignBottom == false){
            if (!this.isShowing()) {
                // 以下拉方式显示popupwindow
                showAsDropDown(parent, parent.getLayoutParams().width / 2, 0);

            } else {
                dismiss();
            }

        }else{
            showAtLocation(parent , Gravity.BOTTOM, 0 , 0 );

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Message message = new Message();
        message.obj = list.get(position);
        handler.sendMessage(message);
//        LToast.show(context,list.get(position).getBranchName());
//        LToast.show(context,list.get(position).getBranchNo());
//        backgroundAlpha(1f);
        dismiss();
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
//        context.getsetAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}


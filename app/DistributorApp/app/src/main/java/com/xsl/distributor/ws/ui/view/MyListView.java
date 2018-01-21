package com.xsl.distributor.ws.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 解决listView嵌套的显示不完全与按键冲突
 * @author songgege
 * @since 2015-12-18
 */
public class MyListView extends ListView {  
    public MyListView(Context context) {  
            super(context);  
    }  
    public MyListView(Context context, AttributeSet attrs) {  
            super(context, attrs);  
    }  
    public MyListView(Context context, AttributeSet attrs, int defStyle) {  
            super(context, attrs, defStyle);  
    }  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
                            MeasureSpec.AT_MOST);  
            super.onMeasure(widthMeasureSpec, expandSpec);  
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
    	return super.onInterceptTouchEvent(ev);
    }
}  
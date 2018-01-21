package com.xsl.distributor.ws.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 解决listView嵌套gridView冲突
 * @author songgege
 * @since 2015-12-18
 */
public class MyGridView extends GridView {     
    private boolean haveScrollbar = true;     
    public MyGridView(Context context) {     
        super(context);     
    }     
    public MyGridView(Context context, AttributeSet attrs) {     
        super(context, attrs);     
    }     
    public MyGridView(Context context, AttributeSet attrs, int defStyle) {     
        super(context, attrs, defStyle);     
    }     
    /**    
     * 设置是否有ScrollBar，当要在ScollView中显示时，应当设置为false。 默认为 true    
     *     
     * @param haveScrollbar
     */     
    public void setHaveScrollbar(boolean haveScrollbar) {     
        this.haveScrollbar = haveScrollbar;     
    }     
    @Override     
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {     
        if (haveScrollbar == false) {     
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);    
            super.onMeasure(widthMeasureSpec, expandSpec);     
        } else {     
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);     
        }     
    }     
}  

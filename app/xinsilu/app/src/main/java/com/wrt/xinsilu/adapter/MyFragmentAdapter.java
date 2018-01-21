package com.wrt.xinsilu.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import com.wrt.xinsilu.R;

import java.util.List;

/**
 * Created by wangsong on 2016/3/30.
 */
public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;
    private List<String>title;
    public MyFragmentAdapter(FragmentManager fm,List<Fragment> list,List<String>title){
        super(fm);
        this.list = list;
        this.title = title;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    /**显示文字*/
    @Override
    public CharSequence getPageTitle(int position) {
        if (title != null && title.size() >= 0){
            return title.get(position % title.size());
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}

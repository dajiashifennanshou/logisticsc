package com.xsl.lerist.llibrarys.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Lerist on 2015/9/5, 0005.
 */
public class LViewPagerAdapter extends PagerAdapter {

    private ArrayList<PagerItem> pagerItems;

    public LViewPagerAdapter() {
        super();
        this.pagerItems = new ArrayList<>();
    }

    public LViewPagerAdapter(ArrayList<PagerItem> pagerItems) {
        super();
        this.pagerItems = pagerItems;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerItems.get(position).getTitle();
    }

    public ArrayList<PagerItem> getPagerItems() {
        return pagerItems;
    }

    public PagerItem getPagerItem(int position) {
        return pagerItems.get(position);
    }


    @Override
    public int getCount() {
        return pagerItems.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = getPagerItem(position).getView();
        if (itemView.getParent() != null) {
            ((ViewGroup) itemView.getParent()).removeView(itemView);
        }
        container.addView(itemView);
        return itemView;
    }

    public LViewPagerAdapter addPage(String title, View view) {
        return addPage(new PagerItem(title, view));
    }

    public LViewPagerAdapter addPage(PagerItem pageItem) {
        if (pagerItems == null) pagerItems = new ArrayList<>();
        pagerItems.add(pageItem);
        notifyDataSetChanged();
        return this;
    }

    public void remove(int index) {
        if (pagerItems == null) return;

        pagerItems.remove(index);
        notifyDataSetChanged();
    }

    public void remove(PagerItem pageItem) {
        if (pagerItems == null) return;
        remove(pagerItems.indexOf(pageItem));
    }

    public void removeAll() {
        if (pagerItems == null) return;
        pagerItems.clear();
        notifyDataSetChanged();
    }

    public static class PagerItem {
        private String title;
        private View view;

        public PagerItem(String title, View view) {
            this.title = title;
            this.view = view;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public View getView() {
            return view;
        }

        public void settViewt(View view) {
            this.view = view;
        }
    }
}

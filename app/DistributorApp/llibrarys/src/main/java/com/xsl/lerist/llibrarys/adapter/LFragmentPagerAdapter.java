package com.xsl.lerist.llibrarys.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Lerist on 2015/11/13, 0013.
 */
public class LFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<PageItem> pagerItems;

    public LFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.pagerItems = new ArrayList<>();
    }

    public LFragmentPagerAdapter(FragmentManager fm, ArrayList<PageItem> pagerItems) {
        super(fm);
        this.pagerItems = pagerItems;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerItems.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return pagerItems.get(position).getFragment();
    }

    public ArrayList<PageItem> getPagerItems() {
        return pagerItems;
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
//        super.destroyItem(container, position, object);
    }

    public LFragmentPagerAdapter addPage(String title, Fragment fragment) {
        return addPage(new PageItem(title, fragment));
    }

    public LFragmentPagerAdapter addPage(PageItem pagerItem) {
        if (pagerItems == null) pagerItems = new ArrayList<>();
        pagerItems.add(pagerItem);
        notifyDataSetChanged();
        return this;
    }

    public void removePage(String title) {
        for (int i = 0; i < pagerItems.size(); i++) {
            PageItem pagerItem = pagerItems.get(i);
            String pageItemTitle = pagerItem.getTitle();
            if (pageItemTitle == null) continue;

            if (pagerItem.getTitle().equals(title)) {
                pagerItems.remove(pagerItem);
                i--;
            }
        }
    }

    public void removePage(Fragment fragment) {
        for (int i = 0; i < pagerItems.size(); i++) {
            PageItem pagerItem = pagerItems.get(i);
            if (pagerItem.getFragment() == fragment) {
                pagerItems.remove(pagerItem);
                i--;
            }
        }
    }

    public void removePage(PageItem pagerItem) {
        pagerItems.remove(pagerItem);
    }

    public void removePage(int index) {
        removePage(pagerItems.get(index));
    }

    public void removePageAll() {
        pagerItems.clear();
        notifyDataSetChanged();
    }

    public static class PageItem {
        private String title;
        private Fragment fragment;

        public PageItem(String title, Fragment fragment) {
            this.title = title;
            this.fragment = fragment;
        }

        public static PageItem Instance(String title, Fragment fragment) {
            return new PageItem(title, fragment);
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Fragment getFragment() {
            return fragment;
        }

        public void setFragment(Fragment fragment) {
            this.fragment = fragment;
        }
    }
}

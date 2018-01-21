package com.wrt.xinsilu.viewpagerindicator;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);

    // From PagerAdapter
    int getCount();
}

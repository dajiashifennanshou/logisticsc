package com.xsl.lerist.llibrarys.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lerist on 2015/8/8, 0008.
 * <p/>
 * ITEM itemData
 */
public abstract class LRecyclerViewAdapter<ITEM extends Object> extends RecyclerView.
        Adapter<RecyclerView.ViewHolder> {
    public final int ITEM_TYPE_HEADER = -1;
    public final int ITEM_TYPE_FOOTER = -2;
    public final int ITEM_TYPE_NORMAL = 0;
    public final Context context;
    public final LayoutInflater inflater;
    private RecyclerView recyclerView;
    private List<ITEM> datas;
    private OnLoadListener onLoadListener;
    private int offset = 0;
    private int agoPaddingTop = -1;
    private int spanCount = 1;
    private boolean isEnableHeaderView;
    private boolean isEnableFooterView;

    public interface OnLoadListener {
        void onLoad(int freeItemCount);
    }

    public LRecyclerViewAdapter(Context context) {
        this(context, null);
    }

    public LRecyclerViewAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        inflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
        //给Header占位
        if (isEnableHeaderView()) datas.add(null);
    }

    //点击事件接口
    public interface LOnItemClickListener {
        void OnClickListener(View view, int position);

        void OnLongClickListener(View view, int position);
    }

    private LOnItemClickListener lOnItemClickListener;

    /**
     * 设置item的点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(LOnItemClickListener onItemClickListener) {
        this.lOnItemClickListener = onItemClickListener;
    }

    public LOnItemClickListener getOnItemClickListener() {
        return lOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_TYPE_NORMAL) {
            return new LRecyclerViewHolder(getItemLayoutView(viewGroup, viewType));
        }

        if (viewType == ITEM_TYPE_HEADER) {
            return new LRecyclerViewHolder(getHeaderLayoutView(viewGroup));
        }

        if (viewType == ITEM_TYPE_FOOTER) {
            return new LRecyclerViewHolder(getFooterLayoutView(viewGroup));
        }
        return new LRecyclerViewHolder(getItemLayoutView(viewGroup, viewType));
    }

    /**
     * Header
     *
     * @return
     */
    public int getHeaderLayoutId() {
        return 0;
    }

    public View getHeaderLayoutView(ViewGroup viewGroup) {
        return inflater.inflate(getHeaderLayoutId(), viewGroup, false);
    }

    public int getFooterLayoutId() {
        return 0;
    }

    public View getFooterLayoutView(ViewGroup viewGroup) {
        return inflater.inflate(getFooterLayoutId(), viewGroup, false);
    }

    protected abstract int getItemLayoutId(int viewType);

    public View getItemLayoutView(ViewGroup viewGroup, int viewType) {
        return inflater.inflate(getItemLayoutId(viewType), viewGroup, false);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        try {
            onBindViewHolder((LRecyclerViewHolder) viewHolder, position, getItemData(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
        onClickListener(viewHolder);
        switch (getItemViewType(position)) {
            case ITEM_TYPE_NORMAL:

                break;
            case ITEM_TYPE_HEADER:
                onBindHeaderViewHolder((LRecyclerViewHolder) viewHolder, position, getItemData(position));
                //set PaddingTop
                if (position == 0) {
                    if (agoPaddingTop == -1) {
                        agoPaddingTop = viewHolder.itemView.getPaddingTop();
                    }
                    viewHolder.itemView.setPadding(
                            viewHolder.itemView.getPaddingLeft(),
                            agoPaddingTop + offset,
                            viewHolder.itemView.getPaddingRight(),
                            viewHolder.itemView.getPaddingBottom());
                }
                break;
        }


        //load if
        int freeCount = 5;
        if (datas.size() > freeCount && viewHolder.getLayoutPosition() >= datas.size() - freeCount) {
            if (onLoadListener != null) {
//                onLoadListener.onLoad(datas.size() - 1 - viewHolder.getLayoutPosition());//剩余item数
            }
        }

    }

    public abstract void onBindViewHolder(final LRecyclerViewHolder viewHolder, int position, ITEM itemData) throws Exception;

    public void onBindHeaderViewHolder(final LRecyclerViewHolder viewHolder, int position, ITEM itemData) {
    }

    public void setEnableHeaderView(boolean enableHeaderView) {
        isEnableHeaderView = enableHeaderView;
    }

    public void setEnableFooterView(boolean enableFooterView) {
        isEnableFooterView = enableFooterView;
    }

    public boolean isEnableHeaderView() {
        return isEnableHeaderView;
    }

    public boolean isEnableFooterView() {
        return isEnableFooterView;
    }

    public List<ITEM> getDatas() {
        return datas;
    }

    public ArrayList<ITEM> getArrayListDatas() {
        ArrayList<ITEM> arrayList = new ArrayList<>();
        for (ITEM e : datas) {
            arrayList.add(e);
        }
        return arrayList;
    }

    public void setDatas(List<ITEM> datas) {
        this.datas = datas;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    /**
     * 设置内PaddingTop, 需要EnableHeaderView
     *
     * @param offset
     * @param spanCount
     */
    public void setTopOffset(int offset, int spanCount) {
        this.offset = offset;
        this.spanCount = spanCount;
    }

    public void setTopOffset(int offset) {
        setTopOffset(offset, 1);
    }

    /**
     * 设置item点击事件
     *
     * @param viewHolder
     */
    private void onClickListener(final RecyclerView.ViewHolder viewHolder) {
        if (lOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = viewHolder.getLayoutPosition();
                    lOnItemClickListener.OnClickListener(v, layoutPosition);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = viewHolder.getLayoutPosition();
                    lOnItemClickListener.OnLongClickListener(v, layoutPosition);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return getDatas().size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
//        if (isEnableHeaderView() && position == 0) {
//            return ITEM_TYPE_HEADER;
//        }
//
//        if (isEnableFooterView() && position == getItemCount() - 1) {
//            return ITEM_TYPE_FOOTER;
//        }
        return ITEM_TYPE_NORMAL;
    }

    public ITEM getItemData(int position) {
        if (position < 0 || position >= datas.size()) {
            return null;
        }
        return datas.get(position);
    }

    public void setData(int location, ITEM obj) {
        datas.set(location, obj);
        notifyItemChanged(location);
    }

    public LRecyclerViewAdapter addData(int position, ITEM obj) {
        datas.add(position, obj);
        notifyItemInserted(position);
        return this;
    }

    public LRecyclerViewAdapter addData(ITEM obj) {
        return addData(datas.size(), obj);
    }

    public LRecyclerViewAdapter addDatas(int position, List<ITEM> objs) {
        datas.addAll(position, objs);
        notifyItemRangeInserted(position, objs.size());
        return this;
    }

    public LRecyclerViewAdapter addDatas(List<ITEM> objs) {
        return addDatas(datas.size(), objs);
    }

    public void removeData(int position) {
        if (isEnableHeaderView() && position == 0) {
            removeData(1);
            return;
        }

        datas.remove(position);
        notifyItemRemoved(position);
    }

    public void removeData(ITEM obj) {
        removeData(datas.indexOf(obj));
    }

    public void removeDatas(List<ITEM> objs) {
        datas.removeAll(objs);
        notifyItemRangeRemoved(datas.size() - objs.size(), objs.size());
    }

    public void removeDataAll() {
        int size = datas.size();
        datas.clear();
        //给Header占位
        if (isEnableHeaderView()) datas.add(null);
        notifyDataSetChanged();
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    public OnLoadListener getOnLoadListener() {
        return onLoadListener;
    }

    /**
     * ViewHodler
     * RecyclerView中强制使用ViewHodler模式
     */
    public static class LNullViewHolder extends RecyclerView.ViewHolder {

        public LNullViewHolder(View itemView) {
            super(itemView);
        }

        public LNullViewHolder(Context context) {
            this(new ImageView(context));
        }
    }

    public static class LRecyclerViewHolder extends RecyclerView.ViewHolder {

        public LRecyclerViewHolder(View itemView) {
            super(itemView);
        }

        public View getItemView() {
            return itemView;
        }

        /**
         * 通过控件的Id获取对于的控件，如果没有则加入views
         *
         * @param viewId
         * @return
         */
        public <T extends View> T getView(int viewId) {
            View view = itemView.findViewById(viewId);
            return (T) view;
        }

        public <T extends View> T getView(int viewId, Class<T> viewClass) {
            View view = itemView.findViewById(viewId);
            return (T) view;
        }

        /**
         * 为TextView设置字符串
         *
         * @param viewId
         * @param text
         * @return
         */
        public LRecyclerViewHolder setText(int viewId, String text) {
            TextView view = getView(viewId);
            if (view != null && text != null) view.setText(text);
            return this;
        }

        /**
         * 为TextView设置字体颜色
         *
         * @param viewId
         * @param color
         * @return
         */
        public LRecyclerViewHolder setTextColor(int viewId, int color) {
            TextView view = getView(viewId);
            view.setTextColor(color);
            return this;
        }

        /**
         * 为TextView设置字体大小
         *
         * @param viewId
         * @param size
         * @return
         */
        public LRecyclerViewHolder setTextSize(int viewId, float size) {
            TextView view = getView(viewId);
            view.setTextSize(size);
            return this;
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param drawableId
         * @return
         */
        public LRecyclerViewHolder setImageResource(int viewId, int drawableId) {
            ImageView view = getView(viewId);
            view.setImageResource(drawableId);
            return this;
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param bm
         * @return
         */
        public LRecyclerViewHolder setImageBitmap(int viewId, Bitmap bm) {
            ImageView view = getView(viewId);
            view.setImageBitmap(bm);
            return this;
        }

        public LRecyclerViewHolder setImageUri(int viewId, Context context, String uri) {
            ImageView view = getView(viewId);
            Glide.with(context).load(uri).centerCrop().crossFade().into(view);
            return this;
        }

        public LRecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener onClickListener) {
            getView(viewId).setOnClickListener(onClickListener);
            return this;
        }
    }

}

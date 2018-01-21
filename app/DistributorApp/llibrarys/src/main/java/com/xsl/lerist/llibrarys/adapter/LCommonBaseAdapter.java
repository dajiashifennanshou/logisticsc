package com.xsl.lerist.llibrarys.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lerist on 2015/11/21, 0021.
 */
public abstract class LCommonBaseAdapter<T> extends BaseAdapter {
    private View itemView;
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected int mItemLayoutId;

    public LCommonBaseAdapter(Context context, int itemLayoutId) {
        this(context, new ArrayList<T>(), itemLayoutId);
    }

    public LCommonBaseAdapter(Context context, View itemView) {
        this(context, new ArrayList<T>(), itemView);
    }

    public LCommonBaseAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public LCommonBaseAdapter(Context context, List<T> mDatas, View itemView) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.itemView = itemView;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CommonViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void convert(CommonViewHolder helper, T item);

    private CommonViewHolder getViewHolder(int position, View convertView,
                                           ViewGroup parent) {
        if (itemView != null && mItemLayoutId == 0) {
            return CommonViewHolder.get(mContext, convertView, parent, itemView,
                    position);
        }
        return CommonViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

    public T getItemData(int position) {
        return mDatas.get(position);
    }

    public LCommonBaseAdapter<T> addData(int position, T obj) {
        mDatas.add(position, obj);
        notifyDataSetChanged();
        return this;
    }

    public LCommonBaseAdapter<T> addData(T obj) {
        return addData(mDatas.size(), obj);
    }

    public LCommonBaseAdapter<T> addDatas(int position, List<T> objs) {
        mDatas.addAll(position, objs);
        notifyDataSetChanged();
        return this;
    }

    public LCommonBaseAdapter<T> addDatas(List<T> objs) {
        return addDatas(mDatas.size(), objs);
    }

    public void removeData(int position) {
        if (position == 0) return;
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    public void removeData(T obj) {
        removeData(mDatas.indexOf(obj));
    }

    public void removeDatas(List<T> objs) {
        mDatas.removeAll(objs);
        notifyDataSetChanged();
    }

    public void removeDataAll() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public static class CommonViewHolder {
        private final SparseArray<View> mViews;
        private final Context context;
        private int mPosition;
        private View mConvertView;

        private CommonViewHolder(Context context, ViewGroup parent, int layoutId,
                                 int position) {
            this.context = context;
            this.mPosition = position;
            this.mViews = new SparseArray<View>();
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                    false);
            // setFlag
            mConvertView.setTag(this);
        }

        private CommonViewHolder(Context context, ViewGroup parent, View itemView,
                                 int position) {
            this.context = context;
            this.mPosition = position;
            this.mViews = new SparseArray<View>();
            mConvertView = itemView;
            // setFlag
            mConvertView.setTag(this);
        }

        /**
         * 拿到一个ViewHolder对象
         *
         * @param context
         * @param convertView
         * @param parent
         * @param layoutId
         * @param position
         * @return
         */
        public static CommonViewHolder get(Context context, View convertView,
                                           ViewGroup parent, int layoutId, int position) {
            if (convertView == null) {
                return new CommonViewHolder(context, parent, layoutId, position);
            }
            return (CommonViewHolder) convertView.getTag();
        }

        public static CommonViewHolder get(Context context, View convertView,
                                           ViewGroup parent, View itemView, int position) {
            if (convertView == null) {
                return new CommonViewHolder(context, parent, itemView, position);
            }
            return (CommonViewHolder) convertView.getTag();
        }

        public View getConvertView() {
            return mConvertView;
        }

        /**
         * 通过控件的Id获取对于的控件，如果没有则加入views
         *
         * @param viewId
         * @return
         */
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * 为TextView设置字符串
         *
         * @param viewId
         * @param text
         * @return
         */
        public CommonViewHolder setText(int viewId, String text) {
            TextView view = getView(viewId);
            view.setText(text);
            return this;
        }

        /**
         * 为ImageView设置图片
         *
         * @param viewId
         * @param drawableId
         * @return
         */
        public CommonViewHolder setImageResource(int viewId, int drawableId) {
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
        public CommonViewHolder setImageBitmap(int viewId, Bitmap bm) {
            ImageView view = getView(viewId);
            view.setImageBitmap(bm);
            return this;
        }

        public int getPosition() {
            return mPosition;
        }

    }
}

package com.wrt.xinsilu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.SenderAdapter;
import com.wrt.xinsilu.bean.SenderBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29 0029.
 * 常用收货人
 */
public class ReceiverFragment extends Fragment {
    private View mView;
    private ListView listView;
    private SenderAdapter adapter;
    /*********************************/
    private List<SenderBean> list;
    private SenderBean sender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.sender_layout,null);
        listView = (ListView) mView.findViewById(R.id.sender_listView);
        initValues();
        adapter = new SenderAdapter(getActivity(),list,2,false);
        listView.setAdapter(adapter);
        return mView;
    }
    private void initValues() {

    }
}

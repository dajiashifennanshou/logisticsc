package com.xsl.distributor.lerist.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xsl.distributor.R;
import com.xsl.lerist.llibrarys.fragments.LFragment;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class OrderFragment extends LFragment {

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        setTitle("配送单");

    }
}

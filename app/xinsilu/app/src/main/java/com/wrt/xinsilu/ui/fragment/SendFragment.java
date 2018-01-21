package com.wrt.xinsilu.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.xsl.lerist.llibrarys.fragments.LFragment;

/**
 * Created by wangsong on 2016/6/24 0024.
 */
public class SendFragment extends LFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    private SelectFragmentChild selectFragmentChild;
    private SendFragmentChild sendFragmentChild;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        sendFragmentChild = new SendFragmentChild();
        selectFragmentChild = new SelectFragmentChild();
        getFragmentManager().beginTransaction()
                .add(R.id.fragment, sendFragmentChild)
                .add(R.id.fragment, selectFragmentChild)
                .hide(selectFragmentChild)
                .show(sendFragmentChild)
                .commit();

        RadioGroup radioGroup = find(R.id.f_send_rg, RadioGroup.class);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        boolean isSend = checkedId == R.id.f_send_rb_send;
        getFragmentManager().beginTransaction()
                .hide(!isSend ? sendFragmentChild : selectFragmentChild)
                .show(isSend ? sendFragmentChild : selectFragmentChild)
                .commit();

        for (int i = 0; i < group.getChildCount(); i++) {
            ((TextView) group.getChildAt(i)).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        ((TextView) group.findViewById(checkedId)).setTextColor(Color.WHITE);
    }
}

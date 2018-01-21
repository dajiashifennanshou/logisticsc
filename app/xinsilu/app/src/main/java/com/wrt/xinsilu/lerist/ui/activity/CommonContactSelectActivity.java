package com.wrt.xinsilu.lerist.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.lerist.interfaces.Selectable;
import com.wrt.xinsilu.lerist.ui.fragment.CommonReceiverListFragment;
import com.wrt.xinsilu.lerist.ui.fragment.CommonSenderListFragment;
import com.wrt.xinsilu.ui.fragment.DriverFragment;
import com.xsl.lerist.llibrarys.utils.LResultActivity;
import com.xsl.lerist.llibrarys.widget.LFragmentContainer;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class CommonContactSelectActivity extends SwipeBackActivity {
    private static final int FLAG_RECEIVER = 100;
    private static final int FLAG_SENDER = 200;
    private static final int FLAG_DRIVER = 300;

    public static void startReceiver(Context context, LResultActivity.Callback callback) {
        Intent intent = new Intent(context, CommonContactSelectActivity.class);
        intent.putExtra("Flag", FLAG_RECEIVER);
        LResultActivity.startActivityForResult(context, intent, callback);
    }

    public static void startSender(Context context, LResultActivity.Callback callback) {
        Intent intent = new Intent(context, CommonContactSelectActivity.class);
        intent.putExtra("Flag", FLAG_SENDER);
        LResultActivity.startActivityForResult(context, intent, callback);
    }

    public static void startDriver(Context context, LResultActivity.Callback callback) {
        Intent intent = new Intent(context, CommonContactSelectActivity.class);
        intent.putExtra("Flag", FLAG_DRIVER);
        LResultActivity.startActivityForResult(context, intent, callback);
    }

    @BindView(R.id.a_common_contact_select_lfc)
    LFragmentContainer commonContactSelectLfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_contact_select);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        int flag = getIntent().getIntExtra("Flag", FLAG_RECEIVER);
        Selectable selectable = null;
        switch (flag) {
            case FLAG_RECEIVER:
                setTitle("常用收货人");
                selectable = new CommonReceiverListFragment();
                break;
            case FLAG_SENDER:
                setTitle("常用发货人");
                selectable = new CommonSenderListFragment();
                break;
            case FLAG_DRIVER:
                setTitle("常用司机");
                selectable = new DriverFragment();
                break;
        }
        if (selectable == null) return;

        selectable.setSelectable(true);
        commonContactSelectLfc.addFragment((Fragment) selectable).setVisibleFragmentIndex(0);
    }
}

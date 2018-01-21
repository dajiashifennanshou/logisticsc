package com.xsl.distributor.lerist.ui.activity;

import android.os.Bundle;

import com.xsl.distributor.R;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.dialog.JoinStateDialog;
import com.xsl.distributor.lerist.ui.fragment.user.CustomerListFragment;
import com.xsl.lerist.llibrarys.widget.LFragmentContainer;
import com.xsl.lerist.llibrarys.widget.LToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class MyCustomerActivity extends SwipeBackActivity {

    @BindView(R.id.a_my_customer_lfc)
    LFragmentContainer mFragmentContainer;
    private LocalData localData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_customer);
        ButterKnife.bind(this);

        setTitle("我的客户");

        initData();
        CustomerListFragment customerListFragment = new CustomerListFragment();
        mFragmentContainer.addFragment(customerListFragment)
                .setVisibleFragmentIndex(0);
    }

    private void initData() {
        localData = new LocalData(context);

        if (!localData.isLogined()) {
            LToast.show(context, "请先登录");
            finish();
            return;
        }

        UserInfo.Join join = localData.readUserInfo().getJoin();

        boolean isNoJoin = JoinStateDialog.show(context, join,true);
    }

}

package com.xsl.distributor.ws.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.xsl.distributor.R;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.dialog.JoinStateDialog;
import com.xsl.distributor.ws.bean.UserInfoBean;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class MyAccountActivity extends SwipeBackActivity implements SwipeRefreshLayout.OnRefreshListener {
    /**
     * 用户名
     */
    @BindView(R.id.company_name)
    TextView companyName;
    /**
     * 仓库地址
     */
    @BindView(R.id.address)
    TextView address;
    /**
     * 有效期
     */
    @BindView(R.id.time)
    TextView time;
    /**
     * 绑定电话号码
     */
    @BindView(R.id.bind_phone_number)
    TextView bindPhoneNumber;
    private UserInfoBean bean;
    private LocalData localData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        localData = new LocalData(this);
        setTitle("我的账户");
        initData();
    }

    /**
     * 模拟数据
     */
    private void initData() {
        if (!localData.isLogined()) {
            LToast.show(context, "请先登录");
            finish();
            return;
        }

        UserInfo.Join join = localData.readUserInfo().getJoin();
        UserInfo.User user = localData.readUserInfo().getUser();

        boolean isNoJoin = JoinStateDialog.show(context, join, true);

//        bean = new UserInfoBean();
//        bean.setCompany_name(localData.readUserInfo().getCompany_id()+"");
//        bean.setAddress("暂无地址");
//        bean.setTime(localData.readUserInfo().get);
//        bean.setBind_phone_number(localData.readUserInfo().getPhone());
//
        if (join != null && user != null) {
            companyName.setText(join.getJoinName() == null ? "----" : join.getJoinName());
            address.setText(join.getBranchName() == null ? "----" : join.getBranchName());
            bindPhoneNumber.setText("绑定手机号码: " + (user.getMobile() == null ? "----" : user.getMobile()));
            time.setText("有效期至: " + (join.getEndUseTime() == null ? "----" : StringUtils.toTimeStr(StringUtils.toTimeMilliseconds(join.getEndUseTime().substring(0, 10), "yyyy-MM-dd"), "yyyy年MM月dd日")));
        }
    }

    @Override
    public void onRefresh() {

    }
}

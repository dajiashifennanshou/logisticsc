package com.xsl.distributor.lerist.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.dialog.JoinStateDialog;
import com.xsl.distributor.lerist.ui.fragment.HomeFragment;
import com.xsl.distributor.lerist.ui.fragment.UserFragment;
import com.xsl.distributor.ws.ui.activity.LoginActivity;
import com.xsl.distributor.ws.ui.fragment.LogisticsFragment;
import com.xsl.lerist.llibrarys.activity.LActivity;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.Authorization;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.widget.LFragmentContainer;
import com.xsl.lerist.llibrarys.widget.LPageIndicator;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends LActivity {

    @BindView(R.id.a_main_lfc)
    LFragmentContainer mFragmentContainer;
    @BindView(R.id.a_main_lpi)
    LPageIndicator mPageIndicator;
    private UserClient userClient;
    private LocalData localData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setEnableFinishHint(true);

        initData();
        initView();
    }

    private void initData() {
        userClient = new UserClient();
        localData = new LocalData(this);
        if (localData.isLogined()) {
            login(localData.readUserInfo().getUser().getLogin_name(), localData.readUserInfo().getUser().getPassword());
        }
    }

    /**
     * 登陆
     *
     * @param name 账号
     * @param pwd  密码
     */
    private void login(String name, String pwd) {
        userClient.login(name, pwd, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    LToast.show(context, "身份已过期, 请重新登录");
                    startActivity(LoginActivity.class);
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        //登录成功保存用户信息
                        UserInfo userInfo = JSON.parseObject(String.valueOf(resultInfo.getData()), UserInfo.class);
                        if (userInfo == null) {
                            LToast.show(context, "登录失败, 请稍后再试");
                            return;
                        }
                        localData.saveUserInfo(userInfo);
                        KLog.i(JPushInterface.getRegistrationID(context));
                        userClient.registJPush(userInfo.getUser().getId(), JPushInterface.getRegistrationID(context), new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                KLog.i(result);
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {

                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                        break;
                    default:
                        LToast.show(context, "身份已过期, 请重新登录");
                        startActivity(LoginActivity.class);
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void initView() {
        HomeFragment homeFragment = new HomeFragment();
        LogisticsFragment logisticsFragment = new LogisticsFragment();
        UserFragment userFragment = new UserFragment();
        mFragmentContainer.addFragment(homeFragment)
                .addFragment(logisticsFragment)
                .addFragment(userFragment)
                .addOnFragmentChangedListener(new LFragmentContainer.OnFragmentChangedListener() {
                    @Override
                    public void onFragmentChangedBefore(Fragment currentVisiblefragment, int index) {

                    }

                    @Override
                    public void onFragmentChanged(Fragment currentVisibleFragment, int index) {
                        if (currentVisibleFragment instanceof LogisticsFragment) {
                            if (localData.isLogined()) {
                                UserInfo.Join join = localData.readUserInfo().getJoin();
                                boolean isNoJoin = JoinStateDialog.show(context, join, false);
                                if (isNoJoin == false) {
                                    ((LogisticsFragment) currentVisibleFragment).onRefreshFragment();
                                } else {
                                    mFragmentContainer.setVisibleFragmentIndex(0);
                                }
                            } else {
                                LToast.show(context, "请先登录");
                                startActivity(LoginActivity.class);
                                mFragmentContainer.setVisibleFragmentIndex(0);
                                return;
                            }

                        }
                    }
                });

        String[] pageTitles = {
                "首页",
                "配送单",
                "我的"
        };
        int[] pageIconNors = {
                R.mipmap.home_shouye_nor,
                R.mipmap.home_peisongdan_nor,
                R.mipmap.home_mine_nor
        };
        int[] pageIconFocs = {
                R.mipmap.home_shouye_cli,
                R.mipmap.home_peisongdan_cli,
                R.mipmap.home_mine_cli
        };

        int dp20 = Lerist.dip2px(context, 20);
        int dp8 = Lerist.dip2px(context, 8);
        int dp4 = Lerist.dip2px(context, 4);
        mPageIndicator.setTexts(pageTitles)
                .setFocusResourceIds(pageIconFocs)
                .setNormalResourceIds(pageIconNors)
                .setTextColor(getResources().getColor(R.color.font_color_hint), getResources().getColor(R.color.colorAccent))
                .setTextSize(10)
                .setIconSize(dp20, dp20)
                .setInPaddingTop(dp8)
                .setInPaddingBottom(dp4)
                .setIcMarginBottom(dp4)
                .setFragmentContainer(mFragmentContainer);

        mFragmentContainer.setVisibleFragmentIndex(0);

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
        MobclickAgent.onPageStart(MainActivity.class.getSimpleName());
        MobclickAgent.onResume(this);
        Authorization.init(context);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
        MobclickAgent.onPageEnd(MainActivity.class.getSimpleName());
        MobclickAgent.onPause(this);
    }
}

package com.wrt.xinsilu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.Service.LocationService;
import com.wrt.xinsilu.adapter.MyFragmentAdapter;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.UserClient;
import com.wrt.xinsilu.ui.fragment.SendFragment;
import com.wrt.xinsilu.ui.fragment.UserCenterFragment;
import com.wrt.xinsilu.ui.view.CustomViewPager;
import com.xsl.lerist.llibrarys.activity.LActivity;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.Authorization;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.widget.LPageIndicator;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * @since 2016-6-28
 * 使用的一个ViewPager对两个个人中心和发货连个Fragment进行滑动处理
 * 个人中心
 */
public class MainActivity extends LActivity implements View.OnClickListener {
    private CustomViewPager pager;
    private TextView send;
    private TextView user_center;
    private List<Fragment> list;
    private LPageIndicator pageIndicator;
    private String[] str;
    private List<String> title;
    private TextView company_center;
    public static final String TAG = "MainActivity";
    private Bundle mSavedInstanceState;
    private LocalData localData;

    public <E extends View> E find(int resid) {
        return (E) find(resid, View.class);
    }

    public <E extends View> E find(int resid, Class<E> e) {
        View view = findViewById(resid);
        return (E) view;
    }

    public void setText(String text, int resId) {
        TextView textView = find(resId, TextView.class);
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void setTitle(String title) {
        super.setTitle(title);
        setText(title, R.id.tv_title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationService.start(this);
        setTitle("中工储运-货主");
        initData();
        setEnableFinishHint(true);
        mSavedInstanceState = savedInstanceState;
//        if (savedInstanceState == null)
        initViews();
    }

    private void initData() {
        localData = new LocalData(context);
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
        new UserClient().login(name, pwd, new Callback.CommonCallback<String>() {
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
                        //更新极光推送id
                        UserClient.updateJPushId(context);
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

    /**
     * 初始化view
     */
    private void initViews() {

        title = new ArrayList<>();
        str = new String[]{"发货", "个人中心"};
        title = Arrays.asList(str);
        company_center = (TextView) findViewById(R.id.company_center);
        pager = (CustomViewPager) findViewById(R.id.center_viewPager);
        list = new ArrayList<Fragment>();
        list.add(new SendFragment());
        list.add(new UserCenterFragment());
        company_center.setOnClickListener(this);
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list, title);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setTitle(position == 0 ? "中工储运-货主" : "个人中心");
            }

            @Override
            public void onPageSelected(int position) {

                if (localData.readUserInfo() == null
                        || localData.readUserInfo().getUser().getUser_type() == 2
                        || localData.readUserInfo().getUser().getTemporary_company_id() != 0//正在审核
                        ) {
                    company_center.setVisibility(View.GONE);
                    return;
                }
                if (position == 1) {
                    company_center.setVisibility(View.VISIBLE);
                } else {
                    company_center.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pageIndicator = (LPageIndicator) findViewById(R.id.tabLayout);
        pageIndicator.setNormalResourceIds(R.mipmap.common_delivery_nor, R.mipmap.common_prolife_nor);
        pageIndicator.setFocusResourceIds(R.mipmap.common_delivery_sel, R.mipmap.common_prolife_sel);
        pageIndicator.setIconSize(Lerist.dip2px(this, 20), Lerist.dip2px(this, 20));
        pageIndicator.setInPadding(Lerist.dip2px(this, 6));
        pageIndicator.setTexts(str);
        pageIndicator.setTextSize(10);
        pageIndicator.setRippleColor(getResources().getColor(R.color.basic_color));
        pageIndicator.setTextColor(getResources().getColor(R.color.font_color_normal), getResources().getColor(R.color.colorPrimary));
        pageIndicator.setViewPager(pager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Authorization.init(this);
        startService(new Intent(this, LocationService.class));
        JPushInterface.onResume(this);
        MobclickAgent.onPageStart(MainActivity.class.getSimpleName());
        MobclickAgent.onResume(this);
        KLog.i(JPushInterface.getRegistrationID(context) + "-------------onResume-----------");
        if (localData.readUserInfo() == null
                || localData.readUserInfo().getUser().getUser_type() == 2
                || localData.readUserInfo().getUser().getTemporary_company_id() != 0//正在审核
                ) {
            try {
                company_center.setVisibility(View.GONE);
            } catch (Exception e) {
            }
            return;
        }

        try {
            if (pager.getCurrentItem() == 1) {
                company_center.setVisibility(View.VISIBLE);
            } else {
                company_center.setVisibility(View.GONE);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
        context.stopService(new Intent(context, LocationService.class));
        MobclickAgent.onPageEnd(MainActivity.class.getSimpleName());
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        if (view == company_center) {
            startActivity(new Intent(this, CompanyUserActivity.class));
        }
    }
}

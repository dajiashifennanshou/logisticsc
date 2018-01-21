package com.xsl.distributor.ws.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.ui.MainActivity;
import com.xsl.distributor.lerist.utils.Matchs;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/***
 * 登陆
 */
public class LoginActivity extends SwipeBackActivity implements TextWatcher {


    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login_login)
    TextView loginLogin;
    @BindView(R.id.login_regest)
    carbon.widget.TextView loginRegest;
    @BindView(R.id.login_forget)
    carbon.widget.TextView loginForget;
    private LocalData localData;
    private UserClient userClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        setTitle("登录");

        initData();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (localData.isLogined()) finish();
    }

    private void initData() {
        localData = new LocalData(context);
        userClient = new UserClient();
        localData.removeUserInfo();
    }

    private void initView() {
        loginName.addTextChangedListener(this);
        loginPwd.addTextChangedListener(this);
    }


    /**
     * 登陆
     *
     * @param name 账号
     * @param pwd  密码
     */
    private void login(String name, String pwd) {
        LProgressDialog.show(context, "");
        userClient.login(name, pwd, true, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                KLog.i(result);
                if (resultInfo == null) {
                    LToast.show(context, "登录失败, 请稍后再试");
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        //登录成功保存用户信息
                        UserInfo userInfo = JSON.parseObject(String.valueOf(resultInfo.getData()), UserInfo.class);
                        KLog.i(userInfo);
                        if (userInfo == null) {
                            LToast.show(context, "登录失败, 请稍后再试");
                            return;
                        }
                        localData.saveUserInfo(userInfo);
                        if (localData.isLogined()) {
                            LToast.show(context, "登录成功");
                            userClient.registJPush(userInfo.getUser().getId(), JPushInterface.getRegistrationID(context), new CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {

                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {

                                }

                                @Override
                                public void onCancelled(CancelledException cex) {

                                }

                                @Override
                                public void onFinished() {
                                    finish();
                                }
                            });

                        } else {
                            LToast.show(context, "登录失败, 请稍后再试");
                        }
                        break;
                    default:
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "登录失败, 请稍后再试" : resultInfo.getMsg());
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                if (ex instanceof HttpException) {
                    LToast.show(context, "网络异常!", Gravity.CENTER);
                } else
                    LToast.show(context, "登录失败, 请稍后再试");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                LProgressDialog.dismiss();
            }
        });
    }

    @OnClick({R.id.login_regest, R.id.login_forget, R.id.login_login,R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                if (TextUtils.isEmpty(loginName.getText().toString())) {
                    LToast.show(context, "请输入账号");
                } else if (!Matchs.getPhoneNumber(loginName.getText().toString())) {
                    LToast.show(context, "手机号码格式不正确");
                } else if (TextUtils.isEmpty(loginPwd.getText().toString())) {
                    LToast.show(context, "请输入密码");
                } else {
                    login(loginName.getText().toString(), loginPwd.getText().toString());
                }
                break;
            case R.id.login_forget:
                startActivity(ForgetPwdActivity.class);
                break;
            case R.id.login_regest:
                startActivity(RegestActivity.class);
                break;
            case R.id.btn_back:
                startActivity(MainActivity.class);
                finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
//        if (StringUtils.isExistEmpty(loginName.getText().toString(),
//                loginPwd.getText().toString())) {
//            loginLogin.setBackgroundResource(R.color.font_color_hint_light);
//            loginLogin.setTextColor(getResources().getColor(R.color.font_color_hint));
//        } else {
//            loginLogin.setBackgroundResource(R.color.colorAccent2);
//            loginLogin.setTextColor(getResources().getColor(R.color.white));
//        }
    }
}

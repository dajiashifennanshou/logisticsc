package com.wrt.xinsilu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.UserClient;
import com.wrt.xinsilu.util.ToastUtil;
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


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends SwipeBackActivity implements TextWatcher {
    /**
     * 返回状态，这里要对注册成功之后进行自动登陆
     */
    private static final int result = 1;
    public static final String TAG = "loginActivity";

    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login_button)
    carbon.widget.TextView loginButton;
    @BindView(R.id.regest_fast)
    TextView regestFast;
    @BindView(R.id.forget_password)
    TextView forgetPassword;
    private LocalData localData;
    private UserClient userClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusConfig();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setTitle("登录");
        initData();
    }

    private void initData() {
        localData = new LocalData(context);
        userClient = new UserClient();
        userName.addTextChangedListener(this);
        password.addTextChangedListener(this);
        localData.removeUserInfo();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (localData.isLogined()) {
            finish();
        }
    }


    @OnClick({R.id.login_button, R.id.regest_fast, R.id.forget_password})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.login_button:
                if (TextUtils.isEmpty(userName.getText().toString())) {
                    ToastUtil.show(context, getString(R.string.user_name_is_null));
                    return;
                } else if (TextUtils.isEmpty(password.getText().toString())) {
                    ToastUtil.show(this, getString(R.string.pws_is_null));
                    return;
                } else {
                    try {
                        login(userName.getText().toString(),password.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        ToastUtil.show(this, getString(R.string.hint_login_error_msg));
                    }
                }
                break;
            case R.id.regest_fast:
                startActivityForResult(new Intent(context, RegestActivity.class), result);
                break;
            case R.id.forget_password:

                startActivity(new Intent(context, ForgetPwdActivity.class));
                break;
        }
    }

    private void login(String name, String pwd) {
        LProgressDialog.show(context, "");
        userClient.login(name, pwd, true, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("result", result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                KLog.i(result);
                if (resultInfo == null) {
                    LToast.show(context, "登录失败, 账号或密码错误");
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        //登录成功保存用户信息
                        UserInfo userInfo = JSON.parseObject(String.valueOf(resultInfo.getData()), UserInfo.class);
                        KLog.i(userInfo);
                        if (userInfo == null) {
                            LToast.show(context, "登录失败, 账号或密码错误");
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
                            LToast.show(context, "登录失败, 账号或密码错误");
                        }
                        break;
                    default:
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "登录失败, 账号或密码错误" : resultInfo.getMsg());
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

    @Override
    public int getStateBarColor() {
        return R.color.white;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(StringUtils.isExistEmpty(
                userName.getText().toString(),
                password.getText().toString())){
            loginButton.setBackgroundColor(getResources().getColor(R.color.font_color_hint_light));
            loginButton.setTextColor(getResources().getColor(R.color.font_color_normal));
        }else{
            loginButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            loginButton.setTextColor(getResources().getColor(R.color.white));
        }
    }
}


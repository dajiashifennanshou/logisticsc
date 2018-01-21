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
import com.xsl.distributor.lerist.utils.Matchs;
import com.xsl.distributor.lerist.utils.TimerCount;
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
 * 注册页面
 */
public class RegestActivity extends SwipeBackActivity implements View.OnClickListener, TextWatcher {
    /**
     * 用户名
     */
    @BindView(R.id.user_name)
    EditText userName;
    /**
     * 密码
     */
    @BindView(R.id.password)
    EditText password;
    /**
     * 再次输入密码
     */
    @BindView(R.id.password_again)
    EditText passwordAgain;
    /**
     * 验证码
     */
    @BindView(R.id.verification_code)
    EditText verificationCode;
    /**
     * 点击获取获取验证码
     */
    @BindView(R.id.yanzhengma)
    TextView yanzhengma;
    @BindView(R.id.login_button)
    TextView loginButton;
    private UserClient userClient;
    private String codeStr;
    private LocalData localData;
    private TimerCount timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regest);
        ButterKnife.bind(this);
        setTitle("注册");
        yanzhengma.setOnClickListener(this);
        userName.addTextChangedListener(this);
        password.addTextChangedListener(this);
        verificationCode.addTextChangedListener(this);
        passwordAgain.addTextChangedListener(this);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private void initData() {
        localData = new LocalData(this);
        userClient = new UserClient();
        timer = new TimerCount(60000, 1000, yanzhengma);
    }

    @Override
    public void onClick(View v) {
        final String phoneOrEmail = userName.getText().toString();
        if (TextUtils.isEmpty(phoneOrEmail)) {
            LToast.show(context, "请输入 手机号 或 邮箱");
            return;
        } else if (v == yanzhengma) {
            if(Matchs.getPhoneNumber(phoneOrEmail) == false){
                LToast.show(context, "手机号码格式错误");
            }else {
                timer.start();
                sendCode(phoneOrEmail);
            }
        }
    }

    private void sendCode(String phoneOrEmail) {
        LToast.show(context, "获取验证码...");
        codeStr = null;
        userClient.sendCode(phoneOrEmail, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        codeStr = String.valueOf(resultInfo.getData());
//                        LToast.show(context, codeStr);
                        break;
                    case ResultCode.FAILURE:
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "获取验证码失败, 请稍后再试" : resultInfo.getMsg());
                        timer.cancel();
                        timer.onFinish();
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                timer.cancel();
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void register(final String phoneOrEmail, final String password) {
        if (TextUtils.isEmpty(password)) {
            LToast.show(context, "密码不能为空");
            return;
        } else if (TextUtils.isEmpty(passwordAgain.getText().toString())) {
            LToast.show(context, "请确认密码");
            return;
        } else if (!this.passwordAgain.getText().toString().equals(password)) {
            LToast.show(context, "两次密码不一致");
            return;
        } else if (TextUtils.isEmpty(verificationCode.getText().toString())) {
            LToast.show(context, "请输入验证码");
            return;
        } else if (!verificationCode.getText().toString().equals(codeStr)) {
//            LToast.show(context, "验证码错误");
//            return;
        }
        LProgressDialog.show(context, "");
        userClient.regist(phoneOrEmail, password, verificationCode.getText().toString(), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        LToast.show(context, "注册成功");
                        //注册成功后立即登录
                        login(phoneOrEmail, password);
                        break;
                    default:
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "注册失败, 请稍后再试" : resultInfo.getMsg());
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                if (ex instanceof HttpException) {
                    LToast.show(context, "网络异常!", Gravity.CENTER);
                } else
                    LToast.show(context, "注册失败, 请稍后再试");

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

    private void login(String phoneOrEmail, String password) {
        if (StringUtils.isExistEmpty(phoneOrEmail, password)) {
            LToast.show(context, "请检查登录名和密码");
            return;
        }

        LProgressDialog.show(context, "");
        userClient.login(phoneOrEmail, password, true, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    LToast.show(context, "登录失败, 请稍后再试");
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
//                        LToast.show(context, "已为您自动登录");
                        //注册成功加盟云仓
                        startActivity(CloudStoreActivity.class);
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
                        break;
                    default:
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "登录失败, 请稍后再试" : resultInfo.getMsg());
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
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

    @OnClick(R.id.login_button)
    public void onClick() {
        register(userName.getText().toString(), password.getText().toString());
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
                password.getText().toString(),
                verificationCode.getText().toString(),
                passwordAgain.getText().toString())){
            loginButton.setBackgroundColor(getResources().getColor(R.color.font_color_hint_light));
            loginButton.setTextColor(getResources().getColor(R.color.font_color_normal));
//            loginButton.setEnabled(false);
        }else{
            loginButton.setBackgroundColor(getResources().getColor(R.color.colorAccent2));
            loginButton.setTextColor(getResources().getColor(R.color.white));
//            loginButton.setEnabled(true);
        }
    }
}

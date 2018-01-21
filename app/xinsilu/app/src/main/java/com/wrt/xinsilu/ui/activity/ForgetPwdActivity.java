package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.UserClient;
import com.wrt.xinsilu.util.TimerCount;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import carbon.widget.TextView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 忘记密码
 */
public class ForgetPwdActivity extends SwipeBackActivity implements TextWatcher {


    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.verification_code)
    EditText verificationCode;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.password_again)
    EditText passwordAgain;
    @BindView(R.id.commit_button)
    TextView commitButton;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    android.widget.TextView tvTitle;
    @BindView(R.id.yanzhengma)
    android.widget.TextView yanzhengma;
    private LocalData localData;
    private UserClient userClient;
    private TimerCount timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusConfig();
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
        tvTitle.setText("忘记密码");
        localData = new LocalData(context);
        userClient = new UserClient();
        userName.addTextChangedListener(this);
        password.addTextChangedListener(this);
        passwordAgain.addTextChangedListener(this);
        verificationCode.addTextChangedListener(this);
        timer = new TimerCount(60000, 1000, yanzhengma);
    }

    @OnClick({R.id.commit_button,R.id.yanzhengma,R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.commit_button:
                regest();
                break;
            case R.id.yanzhengma:
                String usernameInput = userName.getText().toString();
                if (StringUtils.isEmpty(usernameInput)) {
                    LToast.show(context, "请输入手机号");
                }else if (!StringUtils.isMobileNO(usernameInput)) {
                    LToast.show(context, "请输入正确的手机号");
                }else{
                    sendCode(userName.getText().toString());
                }
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void regest(){
        String usernameInput = userName.getText().toString();
        String passwordInput = password.getText().toString();
        String passwordAgainInput = passwordAgain.getText().toString();
        String codeInput = verificationCode.getText().toString();
        if (StringUtils.isExistEmpty(usernameInput, passwordInput, passwordAgainInput)) {
            LToast.show(context, "请完善注册信息");
        }else if (passwordInput.equals(passwordAgainInput) == false) {
            LToast.show(context, "两次密码不匹配, 请重新输入");
        }else if (StringUtils.isEmpty(codeInput)) {
            LToast.show(context, "请输入验证码");
        }else {
            userClient.updatePwd(userName.getText().toString(), password.getText().toString(), verificationCode.getText().toString(), new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                    KLog.i(result);
                    if (resultInfo == null) {
                        LToast.show(context, "修改密码失败, 请稍后再试");
                        return;
                    }
                    switch (resultInfo.getCode()) {
                        case ResultCode.SUCCEED:
                            LToast.show(context, "修改成功");
                            login(userName.getText().toString(),password.getText().toString());
                            //注册成功后立即登录
                            break;
                        default:
                            LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "注册失败, 请稍后再试" : resultInfo.getMsg());
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
    }
    private void sendCode(String phoneOrEmail) {
        LToast.show(context, "获取验证码...");
        timer.start();
        userClient.sendCode(Common.GET_SMS,phoneOrEmail, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
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
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
//
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
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
                password.getText().toString(),
                passwordAgain.getText().toString(),
                verificationCode.getText().toString())){
            commitButton.setBackgroundColor(getResources().getColor(R.color.font_color_hint_light));
            commitButton.setTextColor(getResources().getColor(R.color.font_color_normal));
        }else{
            commitButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            commitButton.setTextColor(getResources().getColor(R.color.white));
        }
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
                    LToast.show(context, "登录失败, 账号或密码错误");
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        //登录成功保存用户信息
                        UserInfo userInfo = JSON.parseObject(String.valueOf(resultInfo.getData()), UserInfo.class);
                        if (userInfo == null) {
                            LToast.show(context, "登录失败, 账号或密码错误");
                            return;
                        }
                        localData.saveUserInfo(userInfo);
                        LToast.show(context, "已为您自动登录");
                        UserClient.updateJPushId(context);
                        startActivity(MainActivity.class);
                        finish();
                        break;
                    default:
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "登录失败, 账号或密码错误" : resultInfo.getMsg());
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (ex instanceof HttpException) {
                    LToast.show(context, "网络异常!", Gravity.CENTER);
                } else
                    LToast.show(context, "登录失败, 账号或密码错误");
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
}

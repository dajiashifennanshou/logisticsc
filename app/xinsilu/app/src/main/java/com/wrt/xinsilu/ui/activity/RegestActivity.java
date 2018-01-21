package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
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
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import static com.wrt.xinsilu.R.id.verification_code;

/**
 * 注册
 */
public class RegestActivity extends SwipeBackActivity implements TextWatcher{

    public static final String TAG = "regest--->";

    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.password_again)
    EditText passwordAgain;
    @BindView(verification_code)
    EditText verificationCode;
    @BindView(R.id.yanzhengma)
    TextView yanzhengma;
    @BindView(R.id.regest_guide)
    TextView regestGuide;
    @BindView(R.id.login_button)
    carbon.widget.TextView loginButton;
    @BindView(R.id.a_regist_cb_protocol)
    CheckBox cb_protocol;
    private LocalData localData;
    private UserClient userClient;
    private TimerCount timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusConfig();
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);

        setTitle("注册");
        initData();
    }

    private void initData() {
        localData = new LocalData(context);
        userClient = new UserClient();
        timer = new TimerCount(60000, 1000, yanzhengma);
        userName.addTextChangedListener(this);
        password.addTextChangedListener(this);
        passwordAgain.addTextChangedListener(this);
        verificationCode.addTextChangedListener(this);
    }

    @OnClick({R.id.yanzhengma, R.id.login_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yanzhengma:
                final String usernameInput = userName.getText().toString();
                if (StringUtils.isEmpty(usernameInput)) {
                    LToast.show(context, "请输入手机号");
                    return;
                }
                if (!StringUtils.isMobileNO(userName.getText().toString())) {
                    LToast.show(context, "请输入正确的手机号");
                    return;
                }
                sendCode(userName.getText().toString());

                break;
            case R.id.login_button:
                regist();
                break;
        }
    }

    private void sendCode(String phoneOrEmail) {
        LToast.show(context, "获取验证码...");
        timer.start();
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

    private void regist() {
        final String usernameInput = userName.getText().toString();
        final String passwordInput = password.getText().toString();
        String passwordAgainInput = passwordAgain.getText().toString();
        String codeInput = verificationCode.getText().toString();
        if (StringUtils.isExistEmpty(usernameInput, passwordInput, passwordAgainInput)) {
            LToast.show(context, "请完善注册信息");
        }else if (passwordInput.equals(passwordAgainInput) == false) {
            LToast.show(context, "两次密码不匹配, 请重新输入");
        }else if (StringUtils.isEmpty(codeInput)) {
            LToast.show(context, "请输入验证码");
        }else  if (cb_protocol.isChecked() == false) {
            LToast.show(context, "请阅读《电商注册手册》");
        }else {
            LProgressDialog.show(context, "");

            userClient.regist(userName.getText().toString(), password.getText().toString(), verificationCode.getText().toString(), new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                    KLog.i(result);
                    if (resultInfo == null) {
                        LToast.show(context, "注册失败, 请稍后再试");
                        return;
                    }

                    switch (resultInfo.getCode()) {
                        case ResultCode.SUCCEED:
                            LToast.show(context, "注册成功");
                            //注册成功后立即登录
                            login(usernameInput, passwordInput);
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
            loginButton.setBackgroundColor(getResources().getColor(R.color.font_color_hint_light));
            loginButton.setTextColor(getResources().getColor(R.color.font_color_normal));
        }else{
            loginButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            loginButton.setTextColor(getResources().getColor(R.color.white));
        }
    }
}

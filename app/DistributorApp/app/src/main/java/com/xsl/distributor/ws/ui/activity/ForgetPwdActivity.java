package com.xsl.distributor.ws.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.utils.Matchs;
import com.xsl.distributor.lerist.utils.TimerCount;
import com.xsl.distributor.ws.params.ForgetPwdParams;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import carbon.widget.TextView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/***
 * 注册
 */
public class ForgetPwdActivity extends SwipeBackActivity implements View.OnClickListener, TextWatcher {


    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.verification_code)
    EditText verificationCode;
    @BindView(R.id.yanzhengma)
    android.widget.TextView yanzhengma;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.password_again)
    EditText passwordAgain;
    @BindView(R.id.login_button)
    TextView loginButton;
    private LocalData localData;
    private UserClient userClient;
    private TimerCount timer;
    private ForgetPwdParams forgetPwdParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regest);
        ButterKnife.bind(this);
        setTitle("忘记密码");
        userName.addTextChangedListener(this);
        password.addTextChangedListener(this);
        verificationCode.addTextChangedListener(this);
        passwordAgain.addTextChangedListener(this);
        initData();
        userClient = new UserClient();
        loginButton.setOnClickListener(this);
        yanzhengma.setOnClickListener(this);
        timer = new TimerCount(60000, 1000, yanzhengma);
    }

    private void initData() {
        userClient = new UserClient();
        forgetPwdParams = new ForgetPwdParams();
    }

    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(userName.getText().toString())
                && TextUtils.isEmpty(password.getText().toString())
                && TextUtils.isEmpty(verificationCode.getText().toString())
                && TextUtils.isEmpty(passwordAgain.getText().toString())) {
            return;
        }
        if (v == loginButton) {
            if (!password.getText().toString().equals(passwordAgain.getText().toString())) {
                LToast.show(context, "两次密码不一致");
            }else {
                forgetPwd();
            }
        } else if (v == yanzhengma) {
            if (TextUtils.isEmpty(userName.getText().toString())) {
                LToast.show(context, "手机号不能为空");
            } else if (Matchs.getPhoneNumber(userName.getText().toString()) == false) {
                LToast.show(context, "手机号码格式不正确");
            } else {
                sendCode(userName.getText().toString().trim());
            }
        }
    }

    private void forgetPwd() {
        LProgressDialog.show(context, "");
        forgetPwdParams.update(UriConstants.UPDATEPWD,
                userName.getText().toString(),
                password.getText().toString(),
                verificationCode.getText().toString(),
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;

                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                LToast.show(context, "密码已重置");
                                finish();
                                break;
                            case ResultCode.FAILURE:
                                LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "重置密码失败, 请稍后再试" : resultInfo.getMsg());
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        LToast.show(context, "网络错误");
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

    /**
     * 获取验证码
     *
     * @param phoneOrEmail 手机号码
     */
    private void sendCode(String phoneOrEmail) {
        LToast.show(context, "获取验证码...");
        timer.start();
        forgetPwdParams.sendCode(phoneOrEmail, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
//                        LToast.show(context, String.valueOf(resultInfo.getData()));
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
                LToast.show(context, "网络错误");
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
            loginButton.setEnabled(false);
        }else{
            loginButton.setBackgroundColor(getResources().getColor(R.color.colorAccent2));
            loginButton.setTextColor(getResources().getColor(R.color.white));
            loginButton.setEnabled(true);
        }
    }
}

package com.xsl.distributor.ws.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.Authorization;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.ws.bean.CloudStoreBean;
import com.xsl.distributor.ws.dialog.SortPopupWindow;
import com.xsl.distributor.ws.params.CloudStoreParams;
import com.xsl.distributor.ws.ui.view.ChoiceCityPopupWindow;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/***
 * 加盟云仓
 */
public class CloudStoreActivity extends SwipeBackActivity implements TextWatcher {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.layout_line)
    LinearLayout layoutLine;
    @BindView(R.id.add_name)
    EditText addName;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.commit)
    carbon.widget.TextView commit;
    @BindView(R.id.choice_net)
    LinearLayout choice;
    @BindView(R.id.cloud_store)
    LinearLayout cloud_store;
    @BindView(R.id.cloud_store_choice)
    TextView cloud_store_choice;
    @BindView(R.id.apply_day)
    EditText apply_day;
    private LocalData localData;
    /**
     * 点击网点返回的网点信息
     */
    public CloudStoreBean bean;
    /**
     * 请求类
     */
    private CloudStoreParams params;
    /**
     * 弹框
     */
    private SortPopupWindow popup_window;
    private int position;
    private Handler hand = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            cloud_store_choice.setText((String) msg.obj);
            position = msg.what;
        }
    };
    private List<String> station;
    private List<CloudStoreBean> list;
    private ChoiceCityPopupWindow cityPopupWindow;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_store);
        ButterKnife.bind(this);
        params = new CloudStoreParams();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setTitle("加盟云仓");
        initData();
        initView();
    }

    private void initView() {
        addName.addTextChangedListener(this);
        content.addTextChangedListener(this);
        phoneNumber.addTextChangedListener(this);
        cloud_store_choice.addTextChangedListener(this);
        apply_day.addTextChangedListener(this);
    }

    private void initData() {
        localData = new LocalData(context);
        if (!localData.isLogined()) {
            LToast.show(context, "请先登录");
            finish();
            return;
        }

        String login_name = localData.readUserInfo().getUser().getLogin_name();
        String mobile = localData.readUserInfo().getUser().getMobile();
        station = new ArrayList<>();
//        content.setText(login_name == null ? "" : login_name);
        phoneNumber.setText(mobile == null ? "" : mobile);
        getCloudStore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.choice_net, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choice_net:
                if (list != null) {
                    popup_window = new SortPopupWindow(context, hand, list);
//                    popup_window.showPopupWindow(cloud_store, true);
                    if (content != null) {
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                        }
                    }
                    cityPopupWindow = new ChoiceCityPopupWindow(context, hand, station);
                    cityPopupWindow.showPopupWindow(cloud_store, true);
                } else {
                    getCloudStore();
                }
                break;
            case R.id.commit:
                if (TextUtils.isEmpty(addName.getText().toString())) {
                    LToast.show(context, "请输入加盟商名字");
                } else if (TextUtils.isEmpty(apply_day.getText().toString())) {
                    LToast.show(context, "请输入加盟时长");
                } else {
                    join();
                }
                break;
        }
    }

    /**
     * 申请加盟
     */
    private void join() {
        if (list == null) {
            return;
        }
        commit.setEnabled(false);
        LProgressDialog.show(context, "");
        params.join(UriConstants.ADD_JION,
                addName.getText().toString(),
                localData.readUserInfo().getUser().getId(),
                list.get(position).getBranchNo(),
                Integer.parseInt(apply_day.getText().toString()),
                Authorization.SIGN,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("----------", result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);

                        if (resultInfo == null) {
                            LToast.show(context, "加盟失败");
                            return;
                        }
                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                LToast.show(context, "加盟申请已提交, 正在审核...");
                                login();
                                finish();
                                break;
                            case ResultCode.NOPERMISSION:
                                UserClient.login(context);
                                break;    }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        if (ex instanceof HttpException) {
                            LToast.show(context, "网络异常!", Gravity.CENTER);
                        }
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {
                        LProgressDialog.dismiss();
                        commit.setEnabled(true);
                    }
                });
    }

    /**
     * 获取云仓地址
     */
    private void getCloudStore() {
        params.getStorageBranch(UriConstants.GET_STORAGE_BRANCH, Authorization.SIGN, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("----------", result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    LToast.show(context, "登录失败, 请稍后再试");
                    return;
                }
                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        list = JSON.parseArray(String.valueOf(resultInfo.getData()), CloudStoreBean.class);
                        station.clear();
                        for (int i = 0; i < list.size(); i++) {
                            station.add(list.get(i).getBranchName());
                        }
                        break;
                    case ResultCode.NOPERMISSION:
                        UserClient.login(context);
                        break; }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (ex instanceof HttpException) {
                    LToast.show(context, "网络异常!", Gravity.CENTER);
                }
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void login() {
        UserInfo.User user = localData.readUserInfo().getUser();

        LProgressDialog.show(context, "");
        new UserClient().login(user.getLogin_name(), user.getPassword(), new Callback.CommonCallback<String>() {
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
                        finish();
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (StringUtils.isExistEmpty(
                addName.getText().toString(),
                content.getText().toString(),
                phoneNumber.getText().toString(),
                cloud_store_choice.getText().toString())) {
            commit.setBackgroundColor(getResources().getColor(R.color.font_color_hint_light));
            commit.setTextColor(getResources().getColor(R.color.font_color_normal));
        } else {
            commit.setBackgroundColor(getResources().getColor(R.color.colorAccent2));
            commit.setTextColor(getResources().getColor(R.color.white));
        }
    }
}

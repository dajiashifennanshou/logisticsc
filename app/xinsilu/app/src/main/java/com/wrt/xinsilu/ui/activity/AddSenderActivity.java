package com.wrt.xinsilu.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.ResultCode;
import com.wrt.xinsilu.client.AddCommonContactClient;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.ui.view.CityPopupWindow;
import com.xsl.lerist.llibrarys.model.Contacts;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.LContactsChooser;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 新增发货人
 */
public class AddSenderActivity extends SwipeBackActivity {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.add_sender_name)
    EditText addSenderName;
    @BindView(R.id.add_sender_number)
    EditText addSenderNumber;
    @BindView(R.id.add_sender_number_btn)
    android.widget.ImageView addSenderNumberBtn;
    @BindView(R.id.add_sender_address)
    TextView addSenderAddress;
    @BindView(R.id.add_address_detail)
    EditText addAddressDetail;
    @BindView(R.id.add_commit)
    Button addCommit;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.a_add_receiver_tv_hint)
    TextView tv_hint;
    private AddCommonContactClient client;
    private LocalData data;
    private InputMethodManager imm;
    /**
     * 城市选择器
     */
    private CityPopupWindow popupWindow;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String address_content = (String) msg.obj;
            addSenderAddress.setText(address_content);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receiver);
        ButterKnife.bind(this);
        setTitle("添加发货人");
        tv_hint.setText("发货人信息");
        client = new AddCommonContactClient();
        data = new LocalData(context);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!data.isLogined()) {
            LToast.show(context, "请先登录");
            finish();
        }
    }

    @OnClick({R.id.add_sender_address, R.id.add_commit, R.id.add_sender_number_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_sender_address:
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                }
                popupWindow = new CityPopupWindow(this, handler);
                popupWindow.showPopupWindow(layout, true);
                break;
            case R.id.add_sender_number_btn:
                LContactsChooser.openContacts(context, new LContactsChooser.Callbak() {
                    @Override
                    public void onSuccess(ArrayList<Contacts> selectedContactses) {
                        addSenderNumber.setText(selectedContactses.get(0).getPhones().get(0));
                    }

                    @Override
                    public void onFailure() {
                        addSenderNumber.setText("");
                    }
                });
                break;
            case R.id.add_commit:
                // // TODO: 2016/7/29 0029 网络请求
                if (TextUtils.isEmpty(addSenderName.getText().toString())) {
                    LToast.show(context, "收货人姓名不能为空");
                } else if (TextUtils.isEmpty(addSenderNumber.getText().toString())) {
                    LToast.show(context, "手机号不能为空");
                } else if (TextUtils.isEmpty(addSenderAddress.getText().toString())) {
                    LToast.show(context, "地址不能为空");
                } else if (TextUtils.isEmpty(addAddressDetail.getText().toString())) {
                    LToast.show(context, "请输入详细地址");
                } else if(!StringUtils.isMobileNO(addSenderNumber.getText().toString())){
                    LToast.show(context, "请输入正确的手机号码");
                }else {
                    LProgressDialog.show(context, "");
                    client.addCommonContact(Common.ADD_COMMON_CONTACTS,
                            data.readUserInfo().getUser().getId(),
                            addSenderName.getText().toString(),
                            addSenderNumber.getText().toString(),
                            addSenderAddress.getText().toString() + " " + addAddressDetail.getText().toString(),
                            "0", new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                    KLog.i(result);
                                    if (resultInfo == null) {
                                        return;
                                    }
                                    switch (resultInfo.getCode()) {
                                        case ResultCode.SUCCEED:
                                            LToast.show(context, "添加成功");
                                            finish();
                                    }
                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {
                                    LToast.show(context, "网络开小差了~");
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
                break;
        }
    }
}

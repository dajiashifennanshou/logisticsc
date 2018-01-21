package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.ResultCode;
import com.wrt.xinsilu.client.AddCommonDriverClient;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
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
/**添加司机*/
public class AddDriverActivity extends SwipeBackActivity {


    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.driver_name)
    EditText driverName;
    @BindView(R.id.driver_number)
    EditText driverNumber;
    @BindView(R.id.add_driver_number)
    android.widget.ImageView licences;
    @BindView(R.id.add_address_detail)
    EditText addAddressDetail;
    @BindView(R.id.add_commit)
    carbon.widget.TextView addCommit;
    private AddCommonDriverClient client;
    private LocalData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
        ButterKnife.bind(this);
        setTitle("添加司机");
        client = new AddCommonDriverClient();
        data = new LocalData(context);
        if (!data.isLogined()) {
            LToast.show(context,"请先登录");
            finish();
        }
    }

    @OnClick({R.id.add_commit,R.id.add_driver_number})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_driver_number:
                LContactsChooser.openContacts(context, new LContactsChooser.Callbak() {
                    @Override
                    public void onSuccess(ArrayList<Contacts> selectedContactses) {
                        driverNumber.setText(selectedContactses.get(0).getPhones().get(0));
                    }

                    @Override
                    public void onFailure() {
                        driverNumber.setText("");
                    }
                });
                break;
            case R.id.add_commit:
                //TODO 请求网络
                if(TextUtils.isEmpty(driverName.getText().toString())){
                    LToast.show(context,"姓名不能为空");
                }else if(TextUtils.isEmpty(driverNumber.getText().toString())){
                    LToast.show(context,"手机号码不能为空");
                }else if(TextUtils.isEmpty(addAddressDetail.getText().toString())){
                    LToast.show(context,"车牌号码不能为空");
                }else if(!StringUtils.isCarnumberNO(addAddressDetail.getText().toString())){
                    LToast.show(context,"请输入正确的车牌号");
                }else if(!StringUtils.isMobileNO(driverNumber.getText().toString())){
                    LToast.show(context,"请输入正确的手机号");
                }else{
                    LProgressDialog.show(context,"");
                    client.addDriver(Common.ADD_COMMON_DRIVER,
                            data.readUserInfo().getUser().getId(),
                            driverName.getText().toString(),
                            driverNumber.getText().toString(),
                            addAddressDetail.getText().toString(), new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                    KLog.i(result);
                                    if (resultInfo == null) {
                                        return;
                                    }
                                    switch (resultInfo.getCode()){
                                        case ResultCode.SUCCEED:
                                            LToast.show(context,"添加成功");
                                            finish();
                                    }
                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {
                                    LToast.show(context,"网络开小差了~");
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

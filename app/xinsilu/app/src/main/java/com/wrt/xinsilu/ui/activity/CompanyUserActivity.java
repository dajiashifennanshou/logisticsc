package com.wrt.xinsilu.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.basic.ResultCode;
import com.wrt.xinsilu.client.CompanyUserClient;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.util.ImageUtil;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 企业注册
 */
public class CompanyUserActivity extends SwipeBackActivity {
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.company_name)
    EditText companyName;
    @BindView(R.id.company_address)
    EditText companyAddress;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_number)
    EditText userNumber;
    @BindView(R.id.content_name)
    EditText contentName;
    @BindView(R.id.content_number)
    EditText contentNumber;
    @BindView(R.id.company_org)
    EditText companyOrg;
    @BindView(R.id.update_licences)
    ImageView updateLicences;
    @BindView(R.id.delete1)
    android.widget.ImageView delete1;
    @BindView(R.id.update_company_pic)
    ImageView updateCompanyPic;
    @BindView(R.id.delete2)
    android.widget.ImageView delete2;
    @BindView(R.id.update_id_card)
    ImageView updateIdCard;
    @BindView(R.id.delete3)
    android.widget.ImageView delete3;
    @BindView(R.id.update_id_pic)
    ImageView updateIdPic;
    @BindView(R.id.delete4)
    android.widget.ImageView delete4;
    @BindView(R.id.update_company_logo)
    ImageView updateCompanyLogo;
    @BindView(R.id.delete5)
    android.widget.ImageView delete5;
    private ImageUtil imageUtil;
    private CompanyUserClient client;
    private LocalData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_company_user);
        ButterKnife.bind(this);
        initView();
        initValue();
    }

    protected void initView() {
        imageUtil = new ImageUtil(context);
        tvTitle.setText("申请企业货主");
        titleEnter.setVisibility(View.VISIBLE);
        titleEnter.setText("提交");
        client = new CompanyUserClient();
        data = new LocalData(context);
    }

    protected void initValue() {

    }

    @OnClick({R.id.btn_back, R.id.title_enter, R.id.update_licences, R.id.delete1, R.id.update_company_pic, R.id.delete2, R.id.update_id_card, R.id.delete3, R.id.update_id_pic, R.id.delete4, R.id.update_company_logo, R.id.delete5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.title_enter:
                checkContentIsOk();
                break;
            case R.id.update_licences:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
                imageUtil.ChoiceImg("fileName0.jpg", updateLicences, delete1);
                break;
            case R.id.delete1:
                imageUtil.deleteImage(updateLicences, delete1);
                break;
            case R.id.update_company_pic:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
                imageUtil.ChoiceImg("fileName1.jpg", updateCompanyPic, delete2);
                break;
            case R.id.delete2:
                imageUtil.deleteImage(updateCompanyPic, delete2);
                break;
            case R.id.update_id_card:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
                imageUtil.ChoiceImg("fileName2.jpg", updateIdCard, delete3);
                break;
            case R.id.delete3:
                imageUtil.deleteImage(updateIdCard, delete3);
                break;
            case R.id.update_id_pic:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
                imageUtil.ChoiceImg("fileName3.jpg", updateIdPic, delete4);
                break;
            case R.id.delete4:
                imageUtil.deleteImage(updateIdPic, delete5);
                break;
            case R.id.update_company_logo:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
                imageUtil.ChoiceImg("fileName4.jpg", updateCompanyLogo, delete5);
                break;
            case R.id.delete5:
                imageUtil.deleteImage(updateCompanyLogo, delete5);
                break;
        }
    }

    /**
     * 检查填写内容是否填写完成
     */
    private void checkContentIsOk() {
        final String ic_logo_path = (String) updateCompanyLogo.getTag();
        final String ic_pic_path = (String) updateCompanyPic.getTag();
        final String ic_idcard_path = (String) updateIdCard.getTag();
        final String ic_idpic_path = (String) updateIdPic.getTag();
        final String ic_licences_path = (String) updateLicences.getTag();

        if (TextUtils.isEmpty(companyName.getText().toString())) {
            LToast.show(context, "请填写公司名称");
        } else if (TextUtils.isEmpty(companyAddress.getText().toString())) {
            LToast.show(context, "请填写公司地址");
        } else if (TextUtils.isEmpty(userName.getText().toString())) {
            LToast.show(context, "请填写法人姓名");
        } else if (TextUtils.isEmpty(userNumber.getText().toString())) {
            LToast.show(context, "请填写法人电话");
        } else if (TextUtils.isEmpty(contentName.getText().toString())) {
            LToast.show(context, "请填写联系人姓名");
        } else if (TextUtils.isEmpty(contentNumber.getText().toString())) {
            LToast.show(context, "请填写联系人电话");
        } else if (TextUtils.isEmpty(companyOrg.getText().toString())) {
            LToast.show(context, "请填写组织代码");
        } else if (TextUtils.isEmpty(ic_licences_path)) {
            LToast.show(context, "请选择营业执照");
        } else if (TextUtils.isEmpty(ic_pic_path)) {
            LToast.show(context, "请选择公司样片");
        } else if (TextUtils.isEmpty(ic_idcard_path)) {
            LToast.show(context, "请选择法人身份证");
        } else if (TextUtils.isEmpty(ic_idpic_path)) {
            LToast.show(context, "请选择法人名片");
        } else if (TextUtils.isEmpty(ic_logo_path)) {
            LToast.show(context, "请选择公司logo");
        } else {
            new AlertDialog.Builder(context)
                    .setTitle("提示")
                    .setMessage("申请提交之后将不能更改, 请认真审核后提交")
                    .setPositiveButton("确认提交", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            submit(ic_logo_path, ic_pic_path, ic_idcard_path, ic_idpic_path, ic_licences_path);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
    }

    private void submit(String ic_logo_path, String ic_pic_path, String ic_idcard_path, String ic_idpic_path, String ic_licences_path) {
        LProgressDialog.show(context, "");
        client.commit(
                Common.APPLY_ENTER_PRISE,
                data.readUserInfo().getUser().getId(),
                companyName.getText().toString(),
                companyAddress.getText().toString(),
                userName.getText().toString(),
                userNumber.getText().toString(),
                contentName.getText().toString(),
                contentNumber.getText().toString(),
                companyOrg.getText().toString(),
                ic_licences_path,
                ic_pic_path,
                ic_idcard_path,
                ic_idpic_path,
                ic_logo_path,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) {
                            return;
                        }
                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                LToast.show(context, "已提交");
                                finish();
                                break;
                            default:
                                LToast.show(context, resultInfo.getMsg() == null ? "提交失败, 请稍后再试" : resultInfo.getMsg());
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        LToast.show(context, "提交失败, 请稍后再试");
                        ex.printStackTrace();
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

package com.wrt.xinsilu.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.CompanyInfoAdapter;
import com.wrt.xinsilu.bean.CompanyInfoBean;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.dialog.PopDialog;
import com.wrt.xinsilu.ui.view.MyListView;
import com.wrt.xinsilu.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 公司信息
 */
public class CompanyInfoActivity extends SwipeBackActivity {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.company_info_listview)
    MyListView companyInfoListview;
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
    @BindView(R.id.login_button)
    carbon.widget.TextView loginButton;
    private CompanyInfoAdapter adapter;
    private List<CompanyInfoBean> infoBeanList;
    private CompanyInfoBean bean;
    private Map<String, String> map;
    private PopDialog dialog;
    private ImageUtil imageUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);
        ButterKnife.bind(this);
        tvTitle.setText("公司信息");
        initValues();
    }

    private void initValues() {
        imageUtil = new ImageUtil(context);
        infoBeanList = new ArrayList<>();
        for (int i = 0; i < Common.company_info.length; i++) {
            bean = new CompanyInfoBean();
            bean.setText_first(Common.company_info[i]);
            bean.setHint(Common.company_hint[i]);
            infoBeanList.add(bean);
        }
        adapter = new CompanyInfoAdapter(context, infoBeanList);
        companyInfoListview.setAdapter(adapter);
    }


    @OnClick({R.id.btn_back, R.id.login_button, R.id.update_licences, R.id.delete1, R.id.update_company_pic, R.id.delete2, R.id.update_id_card, R.id.delete3, R.id.update_id_pic, R.id.delete4, R.id.update_company_logo, R.id.delete5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.login_button:
                for (CompanyInfoBean bean : infoBeanList) {
                    KLog.i(bean.getCompany_info());
                }
            case R.id.update_licences:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
                imageUtil.ChoiceImg("licences.jpg", updateLicences, delete1);

                break;
            case R.id.delete1:
                imageUtil.deleteImage(updateLicences, delete1);
                break;
            case R.id.update_company_pic:
                imageUtil.ChoiceImg("company_pic.jpg", updateCompanyPic, delete2);
                break;
            case R.id.delete2:
                imageUtil.deleteImage(updateCompanyPic, delete2);

                break;
            case R.id.update_id_card:
                imageUtil.ChoiceImg("ic_cad.jpg", updateIdCard, delete3);
                break;
            case R.id.delete3:
                imageUtil.deleteImage(updateIdCard, delete3);
                break;
            case R.id.update_id_pic:
                imageUtil.ChoiceImg("id_pic.jpg", updateIdPic, delete4);

                break;
            case R.id.delete4:
                imageUtil.deleteImage(updateIdPic, delete4);
                break;
            case R.id.update_company_logo:
                imageUtil.ChoiceImg("company_logo.jpg", updateCompanyLogo, delete5);
                break;
            case R.id.delete5:
                imageUtil.deleteImage(updateCompanyLogo, delete5);
                break;
        }
    }


    /*private void uploadImg(String imgPath, final View view) {
        if (StringUtils.isEmpty(imgPath)) {
            return;
        }

        UserInfo userInfo = localData.readUserInfo();
        if (userInfo == null) return;

        LProgressDialog.show(context, "");
        view.setTag(null);
        bankCardClient.uploadImg(userInfo.getId(), imgPath, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) return;

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        view.setTag(resultInfo.getData());
                        break;
                    case ResultCode.FAILURE:
                        view.setTag(null);
                        bingImg((ImageView) view, "");
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "照片上传失败, 请重新选择" : resultInfo.getMsg());
                        break;
                    case ResultCode.NOPERMISSION:
                        view.setTag(null);
                        bingImg((ImageView) view, "");
                        LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "照片上传失败, 请重新选择" : resultInfo.getMsg());
                        UserClient.login(context);
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
                LProgressDialog.dismiss();
            }
        });
    }*/
}

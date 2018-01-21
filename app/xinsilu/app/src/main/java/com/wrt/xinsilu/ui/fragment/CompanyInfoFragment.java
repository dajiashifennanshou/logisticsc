package com.wrt.xinsilu.ui.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.CompanyInfoAdapter;
import com.wrt.xinsilu.bean.CompanyInfoBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.UserClient;
import com.wrt.xinsilu.lerist.interfaces.InputKeyValue;
import com.wrt.xinsilu.lerist.interfaces.Selectable;
import com.wrt.xinsilu.lerist.model.MyCompanyInfo;
import com.wrt.xinsilu.ui.view.MyListView;
import com.wrt.xinsilu.util.ImageUtil;
import com.xsl.lerist.llibrarys.fragments.LFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static butterknife.ButterKnife.bind;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class CompanyInfoFragment extends LFragment implements Selectable, InputKeyValue {
    @BindView(R.id.company_info_listview)
    MyListView companyInfoListview;
    @BindView(R.id.update_licences)
    SimpleDraweeView updateLicences;
    @BindView(R.id.delete1)
    android.widget.ImageView delete1;
    @BindView(R.id.update_company_pic)
    SimpleDraweeView updateCompanyPic;
    @BindView(R.id.delete2)
    android.widget.ImageView delete2;
    @BindView(R.id.update_id_card)
    SimpleDraweeView updateIdCard;
    @BindView(R.id.delete3)
    android.widget.ImageView delete3;
    @BindView(R.id.update_id_pic)
    SimpleDraweeView updateIdPic;
    @BindView(R.id.delete4)
    android.widget.ImageView delete4;
    @BindView(R.id.update_company_logo)
    SimpleDraweeView updateCompanyLogo;
    @BindView(R.id.delete5)
    android.widget.ImageView delete5;
    @BindView(R.id.view)
    View v;
    private CompanyInfoAdapter adapter;
    private List<CompanyInfoBean> infoBeanList;
    private CompanyInfoBean bean;
    private ImageUtil imageUtil;
    private View mView;
    private boolean isSelect;
    private String[] str = new String[]{"company_name", "company_address", "legal_person", "legal_mobile", "contacts",
            "contacts_mobile", "qq", "post_code", "company_phone", "company_fax",
            "company_tax_no", "finance_email", "company_info", "fileName0", "fileName1",
            "fileName2", "fileName3", "fileName4", "company_code"};
    private UserClient userClient;
    private LocalData localData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_company_info, null);
        bind(this, mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValues();
    }

    private void initValues() {
        localData = new LocalData(context);
        userClient = new UserClient();

        imageUtil = new ImageUtil(context);
        infoBeanList = new ArrayList<>();
        v.setVisibility(View.VISIBLE);
        for (int i = 0; i < Common.company_info.length; i++) {
            bean = new CompanyInfoBean();
            bean.setText_first(Common.company_info[i]);
            bean.setHint(Common.company_hint[i]);
            infoBeanList.add(bean);
        }
        adapter = new CompanyInfoAdapter(context, infoBeanList);
        companyInfoListview.setAdapter(adapter);
        companyInfoListview.setFocusable(false);
    }

    @OnClick({R.id.update_licences, R.id.delete1, R.id.update_company_pic, R.id.delete2, R.id.update_id_card, R.id.delete3, R.id.update_id_pic, R.id.delete4, R.id.update_company_logo, R.id.delete5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_licences:
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
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

    @Override
    public boolean isSelectable() {
        return isSelect;
    }

    @Override
    public void setSelectable(boolean isSelectable) {
        isSelect = isSelectable;
        adapter.setSelectable(isSelect);
        adapter.notifyDataSetChanged();
//        v.setVisibility(isSelect ? View.GONE : View.VISIBLE);
    }

    @Override
    public HashMap<String, Object> getInputData() {
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < infoBeanList.size(); i++) {
            map.put(str[i], infoBeanList.get(i).getCompany_info());
        }
        map.put("type", "companyInfo");
        return map;
    }

    @Override
    public void onRefreshFragment() {
        super.onRefreshFragment();
        reuqestData();
    }

    private void reuqestData() {
        UserInfo userInfo = localData.readUserInfo();
        ((ViewGroup) mView).getChildAt(0).setVisibility(View.GONE);
        if (userInfo == null) {
            return;
        }

        if (userInfo.getUser().getTemporary_company_id() != 0
                && userInfo.getUser().getCompany_id() == 0) {
            LToast.show(context, "企业信息正在审核中, 请耐心等待");
            return;
        }

        if (userInfo.getUser().getCompany_id() == 0) {
            return;
        }

        LProgressDialog.show(context, "");
        userClient.getUserCompany(userInfo.getUser().getCompany_id(), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        MyCompanyInfo myCompanyInfo = JSON.parseObject(resultInfo.getData(), MyCompanyInfo.class);
                        bindData(myCompanyInfo);
                        break;
                    default:
                        LToast.show(context, resultInfo.getMsg() == null ? "加载公司信息失败, 请稍后再试" : resultInfo.getMsg());
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                LToast.show(context, "加载公司信息失败, 请稍后再试");
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

    private void bindData(MyCompanyInfo myCompanyInfo) {
        if (myCompanyInfo == null) return;

        ((ViewGroup) mView).getChildAt(0).setVisibility(View.VISIBLE);
        String[] contents = new String[]{
                myCompanyInfo.getCompany_name(),
                myCompanyInfo.getCompany_address(),
                myCompanyInfo.getLegal_person(),
                myCompanyInfo.getLegal_mobile(),
                myCompanyInfo.getContacts(),
                myCompanyInfo.getContacts_mobile(),
                myCompanyInfo.getCompany_code(),
                myCompanyInfo.getPost_code(),
                myCompanyInfo.getCompany_phone(),
                myCompanyInfo.getCompany_fax(),
                myCompanyInfo.getCompany_tax_no(),
                myCompanyInfo.getFinance_email(),
                myCompanyInfo.getQq(),
                myCompanyInfo.getCompany_info()
        };

        infoBeanList = new ArrayList<>();
        for (int i = 0; i < Common.company_info.length; i++) {
            bean = new CompanyInfoBean();
            bean.setText_first(Common.company_info[i]);
            bean.setCompany_info(contents[i]);
            infoBeanList.add(bean);
        }
        adapter = new CompanyInfoAdapter(context, infoBeanList);
        companyInfoListview.setAdapter(adapter);

        updateLicences.setImageURI(Common.GET_COMPANY_IMG + myCompanyInfo.getBusiness_license());
        updateCompanyPic.setImageURI(Common.GET_COMPANY_IMG + myCompanyInfo.getCompany_phone());
        updateIdCard.setImageURI(Common.GET_COMPANY_IMG + myCompanyInfo.getLegal_photo());
        updateIdPic.setImageURI(Common.GET_COMPANY_IMG + myCompanyInfo.getCard_photo());
        updateCompanyLogo.setImageURI(Common.GET_COMPANY_IMG + myCompanyInfo.getLogo());
    }
}

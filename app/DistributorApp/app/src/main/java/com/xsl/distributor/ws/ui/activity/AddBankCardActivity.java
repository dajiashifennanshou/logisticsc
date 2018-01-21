package com.xsl.distributor.ws.ui.activity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.AppApplication;
import com.xsl.distributor.lerist.client.Authorization;
import com.xsl.distributor.lerist.client.BankCardClient;
import com.xsl.distributor.lerist.client.UserClient;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.utils.Matchs;
import com.xsl.distributor.ws.basic.CityBean;
import com.xsl.distributor.ws.basic.ProvinceBean;
import com.xsl.distributor.ws.bean.BankBean;
import com.xsl.distributor.ws.bean.BankOfBranch;
import com.xsl.distributor.ws.dialog.AddBankPopopWindow;
import com.xsl.distributor.ws.dialog.PopDialog;
import com.xsl.distributor.ws.params.AddBankCardParams;
import com.xsl.distributor.ws.ui.view.ChoiceCityPopupWindow;
import com.xsl.distributor.ws.ui.widget.cascade.activity.BaseActivity;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.FileUtils;
import com.xsl.lerist.llibrarys.utils.NetworkUtil;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class AddBankCardActivity extends SwipeBackActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.layout_top)
    LinearLayout layout_top;
    /**
     * 返回
     */
    @BindView(R.id.btn_back)
    ImageView btnBack;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.layout_line)
    LinearLayout layoutLine;
    /**
     * 绑定的手机号码，
     */
    @BindView(R.id.add_card_get_number)
    TextView addCardGetNumber;
    /**
     * 个人
     */
    @BindView(R.id.add_card_person)
    RadioButton addCardPerson;
    /**
     * 企业
     */
    @BindView(R.id.add_card_company)
    RadioButton addCardCompany;
    /**
     * 注册类型
     */
    @BindView(R.id.add_card_radiogroup)
    RadioGroup addCardRadiogroup;
    /**
     * 联系人姓名
     */
    @BindView(R.id.add_card_name)
    EditText addCardName;
    /**
     * 银行卡号
     */
    @BindView(R.id.add_card_bank_card_number)
    EditText addCardBankCardNumber;
    /**
     * 显示选择的城市
     */
    @BindView(R.id.add_card_city_choice)
    TextView addCardCityChoice;
    /**
     * 点击选择城市
     */
    @BindView(R.id.add_card_city_layout)
    LinearLayout addCardCityLayout;
    /**
     * 显示选择的银行
     */
    @BindView(R.id.add_card_bank_choice)
    TextView addCardBankChoice;
    /**
     * 点击选择银行
     */
    @BindView(R.id.add_card_bank_layout)
    LinearLayout addCardBankLayout;
    /**
     * 显示那个城市的银行的支行
     */
    @BindView(R.id.add_card_city_bank_choice)
    TextView addCardCityBankChoice;
    /**
     * 点击选择那个城市的银行的支行
     */
    @BindView(R.id.add_card_city_bank_layout)
    LinearLayout addCardCityBankLayout;
    /**
     * 添加开户名
     */
    @BindView(R.id.add_card_account)
    EditText addCardAccount;
    /**
     * 对私
     */
    @BindView(R.id.add_card_type_person)
    RadioButton addCardTypePerson;
    /**
     * 对公
     */
    @BindView(R.id.add_card_type_company)
    RadioButton addCardTypeCompany;
    /**
     * 选择银行类别
     */
    @BindView(R.id.add_card_bank_typeradiogroup)
    RadioGroup addCardBankTyperadiogroup;
    /**
     * 选择省市
     */
   /* @BindView(R.id.add_card_city_choice2)
    EditText addCardCityChoice2;*/
    /**
     * 添加身份证
     */
    @BindView(R.id.add_card_id_card)
    EditText addCardIdCard;
    /**
     * 提交
     */
    @BindView(R.id.commit)
    carbon.widget.TextView commit;
    /**
     * 用于popupwindow
     */
    @BindView(R.id.layout)
    RelativeLayout layout;
    @BindView(R.id.add_card_province_choice)
    TextView addCardProvinceChoice;
    @BindView(R.id.add_card_province_layout)
    LinearLayout addCardProvinceLayout;
    @BindView(R.id.update_id_pic)
    ImageView updateIdPic;
    @BindView(R.id.delete1)
    android.widget.ImageView delete1;
    @BindView(R.id.update_id_pic2)
    ImageView updateIdPic2;
    @BindView(R.id.delete2)
    android.widget.ImageView delete2;
    @BindView(R.id.update_id_pic_hand)
    ImageView updateIdPicHand;
    @BindView(R.id.delete3)
    android.widget.ImageView delete3;
    @BindView(R.id.update_id_pic2_hand)
    ImageView updateIdPic2Hand;
    @BindView(R.id.delete4)
    android.widget.ImageView delete4;
    private LocalData localData;
    private AddBankCardParams params;
    AddBankPopopWindow window;
    private PopDialog dialog;
    private BaseActivity base;
    private ChoiceCityPopupWindow cityPopupWindow;
    /**
     * 保存的省
     */
    private List<ProvinceBean> list;
    /**
     * 保存的市
     */
    List<CityBean> city;
    /**
     * 保存的银行
     */
    private List<BankBean> bank;
    /**
     * 保存的银行的支行
     */
    private List<BankOfBranch> bankOfBranch;

    /**
     * 报存的省市县的值
     */
    private List<String> content;


    /**
     * 保存的市
     */
    List<String> city_content;
    /**
     * 保存的银行
     */
    private List<String> bankcity_content;
    /**
     * 保存的银行的支行
     */
    private List<String> bankOfBranchcity_content;
    /**
     * 判断是哪一个点击的
     */
    int i = 0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!NetworkUtil.isConnected(context)) {
                LToast.show(context, "请检查网络连接");
                return;
            }
            switch (i) {
                case 0:
                    LProgressDialog.show(context, "");
                    Log.i("NAME------", addCardProvinceChoice.getText().toString());
                    params.getProvince(UriConstants.GET_PROVINCE,
                            Authorization.SIGN,
                            new org.xutils.common.Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    Log.i("----------", result);
                                    ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                    if (resultInfo == null) {
                                        return;
                                    }
                                    switch (resultInfo.getCode()) {
                                        case ResultCode.SUCCEED:
                                            list = JSON.parseArray(resultInfo.getData(), ProvinceBean.class);
                                            content = new ArrayList<String>();
                                            for (int i = 0; i < list.size(); i++) {
                                                content.add(list.get(i).getProvinceName());
                                            }
                                            break;
                                        case ResultCode.NOPERMISSION:
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
                    break;
                case 1:
                    addCardProvinceChoice.setText((String) msg.obj);
                    addCardCityChoice.setText("");
                    addCardBankChoice.setText("");
                    addCardCityBankChoice.setText("");

                    LProgressDialog.show(context, "");
                    Log.i("NAME------", addCardCityChoice.getText().toString());
                    params.getCity(UriConstants.GET_CITY,
                            addCardProvinceChoice.getText().toString(), Authorization.SIGN,
                            new org.xutils.common.Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    Log.i("----------", result);
                                    ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                    if (resultInfo == null) {
                                        return;
                                    }
                                    switch (resultInfo.getCode()) {
                                        case ResultCode.SUCCEED:
                                            city = JSON.parseArray(resultInfo.getData(), CityBean.class);
                                            city_content = new ArrayList<String>();
                                            for (int i = 0; i < city.size(); i++) {
                                                city_content.add(city.get(i).getCityName());
                                            }
                                            break;
                                        case ResultCode.NOPERMISSION:
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
                    break;
                case 2:
                    addCardCityChoice.setText((String) msg.obj);
                    addCardBankChoice.setText("");
                    addCardCityBankChoice.setText("");
                    LProgressDialog.show(context, "");
                    Log.i("NAME------", addCardCityBankChoice.getText().toString());
                    params.getBank(UriConstants.GET_BANK,
                            addCardProvinceChoice.getText().toString(),
                            addCardCityChoice.getText().toString(),
                            Authorization.SIGN,
                            new org.xutils.common.Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    Log.i("----------", result);
                                    ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                    if (resultInfo == null) {
                                        return;
                                    }
                                    switch (resultInfo.getCode()) {
                                        case ResultCode.SUCCEED:
                                            bank = JSON.parseArray(resultInfo.getData(), BankBean.class);
                                            bankcity_content = new ArrayList<String>();
                                            for (int i = 0; i < bank.size(); i++) {
                                                bankcity_content.add(bank.get(i).getHeadName());
                                            }
                                            break;
                                        case ResultCode.NOPERMISSION:
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

                    break;
                case 3:
                    addCardBankChoice.setText((String) msg.obj);
                    addCardCityBankChoice.setText("");
                    LProgressDialog.show(context, "");
                    Log.i("NAME-------", addCardCityBankChoice.getText().toString());
                    params.getBankOfBranch(UriConstants.GET_BANK_BRANCH,
                            addCardProvinceChoice.getText().toString(),
                            addCardCityChoice.getText().toString(),
                            addCardBankChoice.getText().toString(),
                            Authorization.SIGN,
                            new org.xutils.common.Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    Log.i("----------", result);
                                    ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                    if (resultInfo == null) {
                                        return;
                                    }
                                    switch (resultInfo.getCode()) {
                                        case ResultCode.SUCCEED:
                                            bankOfBranch = JSON.parseArray(resultInfo.getData(), BankOfBranch.class);
                                            bankOfBranchcity_content = new ArrayList<String>();
                                            for (int i = 0; i < bankOfBranch.size(); i++) {
                                                bankOfBranchcity_content.add(bankOfBranch.get(i).getBranchName());
                                            }
                                            break;
                                        case ResultCode.NOPERMISSION:
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
                    break;
                case 4:
                    addCardCityBankChoice.setText((String) msg.obj);
                    break;
            }
        }
    };
    private BankCardClient bankCardClient;
    private String photoPath1;
    private String photoPath2;
    private String photoPath3;
    private String photoPath4;
    private InputMethodManager imm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        ButterKnife.bind(this);
        initData();
        initView();
        base = new BaseActivity();
        setTitle("添加银行卡");
        initData();
        addCardRadiogroup.setOnCheckedChangeListener(this);
        addCardBankTyperadiogroup.setOnCheckedChangeListener(this);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void initView() {
        updateIdPic2.setTag(null);
        updateIdPic.setTag(null);
        updateIdPicHand.setTag(null);
        updateIdPic2Hand.setTag(null);
    }

    private void initData() {
        bankCardClient = new BankCardClient();
        params = new AddBankCardParams();
        localData = new LocalData(context);
        if (!localData.isLogined()) {
            LToast.show(context, "请先登录");
            finish();
            return;
        }
        addCardGetNumber.setText(localData.readUserInfo().getUser().getMobile());
        //进入页面请求省
        i = 0;
        handler.sendEmptyMessage(0);
    }

    @OnClick({R.id.add_card_city_layout,
            R.id.add_card_bank_layout,
            R.id.add_card_city_bank_layout,
            R.id.commit,
            R.id.add_card_province_layout,
            R.id.update_id_pic,
            R.id.delete1,
            R.id.update_id_pic2,
            R.id.delete2,
            R.id.update_id_pic_hand,
            R.id.delete3,
            R.id.update_id_pic2_hand,
            R.id.delete4
    })
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.add_card_province_layout://点击获得省
                i = 1;
                if (content != null) {
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                    }
                    cityPopupWindow = new ChoiceCityPopupWindow(context, handler, content);
                    cityPopupWindow.showPopupWindow(addCardProvinceLayout, true);

                } else {
                    //无数据, 重新获取
                    i = 0;
                    handler.sendEmptyMessage(0);
                }
                break;
            case R.id.add_card_city_layout://点击获得城市
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                }
                if (TextUtils.isEmpty(addCardProvinceChoice.getText().toString())) {
                    LToast.show(context, "请选择省");
                } else {
                    i = 2;
                    if (city != null && !city.isEmpty()) {
                        cityPopupWindow = new ChoiceCityPopupWindow(context, handler, city_content);
                        cityPopupWindow.showPopupWindow(addCardProvinceLayout, true);
                    } else {
                        //无数据, 重新获取
                        i = 1;
                        handler.sendEmptyMessage(0);
                    }

                }
                break;

            case R.id.add_card_bank_layout: //点击获得银行
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                }
                if (TextUtils.isEmpty(addCardProvinceChoice.getText().toString())) {
                    LToast.show(context, "请选择省");
                } else if (TextUtils.isEmpty(addCardCityChoice.getText().toString())) {
                    LToast.show(context, "请选择市");
                } else {
                    i = 3;
                    if (bank != null) {
                        cityPopupWindow = new ChoiceCityPopupWindow(context, handler, bankcity_content);
                        cityPopupWindow.showPopupWindow(addCardProvinceLayout, true);

                    } else {
                        //无数据, 重新获取
                        i = 2;
                        handler.sendEmptyMessage(0);
                    }

                }
                break;
            case R.id.add_card_city_bank_layout://点击获得支行
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                }
                if (TextUtils.isEmpty(addCardProvinceChoice.getText().toString())) {
                    LToast.show(context, "请选择省");
                } else if (TextUtils.isEmpty(addCardCityChoice.getText().toString())) {
                    LToast.show(context, "请选择市");
                } else if (TextUtils.isEmpty(addCardBankChoice.getText().toString())) {
                    LToast.show(context, "请选择银行");
                } else {
                    i = 4;
                    if (bankOfBranch != null) {
                        cityPopupWindow = new ChoiceCityPopupWindow(context, handler, bankOfBranchcity_content);
                        cityPopupWindow.showPopupWindow(addCardProvinceLayout, true);
                    } else {
                        //无数据, 重新获取
                        i = 3;
                        handler.sendEmptyMessage(0);
                    }

                }
                break;
            case R.id.commit://提交+-
                submit();
                break;
            case R.id.update_id_pic:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
                dialog = new PopDialog(context, new PopDialog.Callback() {
                    @Override
                    public void onSuccess(final String photoPath) {
                        photoPath1 = AppApplication.PATH_TEMP + "/id_card_front.jpg";
                        FileUtils.renameFile(photoPath, photoPath1);
                        bingImg(updateIdPic, "file://" + photoPath1);
                        delete1.setVisibility(View.VISIBLE);
                        uploadImg(photoPath1, updateIdPic);
                    }

                    @Override
                    public void onFailure() {
                        photoPath1 = null;
                        bingImg(updateIdPic, "");
                        delete1.setVisibility(View.GONE);
                        updateIdPic.setTag(null);
                    }
                });
                dialog.show();
                break;
            case R.id.delete1:
                photoPath1 = null;
                delete1.setVisibility(View.GONE);
                updateIdPic.setTag(null);
                bingImg(updateIdPic, "");
                break;
            case R.id.update_id_pic2:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
                dialog = new PopDialog(context, new PopDialog.Callback() {
                    @Override
                    public void onSuccess(String photoPath) {
                        photoPath2 = AppApplication.PATH_TEMP + "/id_card_back.jpg";
                        FileUtils.renameFile(photoPath, photoPath2);
                        bingImg(updateIdPic2, photoPath2);
                        delete2.setVisibility(View.VISIBLE);
                        uploadImg(photoPath2, updateIdPic2);
                    }

                    @Override
                    public void onFailure() {
                        photoPath2 = null;
                        bingImg(updateIdPic2, "");
                        delete2.setVisibility(View.GONE);
                        updateIdPic2.setTag(null);
                    }
                });
                dialog.show();
                break;
            case R.id.delete2:
                photoPath2 = null;
                delete2.setVisibility(View.GONE);
                bingImg(updateIdPic2, "");

                updateIdPic2.setTag(null);
                break;
            case R.id.update_id_pic_hand:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
                dialog = new PopDialog(context, new PopDialog.Callback() {
                    @Override
                    public void onSuccess(String photoPath) {
                        photoPath3 = AppApplication.PATH_TEMP + "/persou_photo.jpg";
                        FileUtils.renameFile(photoPath, photoPath3);
                        bingImg(updateIdPicHand, "file://" + photoPath3);

                        delete3.setVisibility(View.VISIBLE);
                        uploadImg(photoPath3, updateIdPicHand);
                    }

                    @Override
                    public void onFailure() {
                        photoPath3 = null;
                        bingImg(updateIdPicHand, "");

                        delete3.setVisibility(View.GONE);
                        updateIdPicHand.setTag(null);
                    }
                });
                dialog.show();
                break;
            case R.id.delete3:
                photoPath3 = null;
                delete3.setVisibility(View.GONE);
                bingImg(updateIdPicHand, "");

                updateIdPicHand.setTag(null);
                break;
            case R.id.update_id_pic2_hand:

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        100);
                dialog = new PopDialog(context, new PopDialog.Callback() {
                    @Override
                    public void onSuccess(String photoPath) {
                        photoPath4 = AppApplication.PATH_TEMP + "/bank_card_front.jpg";
                        FileUtils.renameFile(photoPath, photoPath4);
                        bingImg(updateIdPic2Hand, "file://" + photoPath4);
                        delete4.setVisibility(View.VISIBLE);
                        uploadImg(photoPath4, updateIdPic2Hand);
                    }

                    @Override
                    public void onFailure() {
                        photoPath4 = null;
                        bingImg(updateIdPic2Hand, "");
                        delete4.setVisibility(View.GONE);
                        updateIdPic2Hand.setTag(null);
                    }
                });
                dialog.show();
                break;
            case R.id.delete4:
                photoPath4 = null;
                delete4.setVisibility(View.GONE);
                bingImg(updateIdPic2Hand, "");
                updateIdPic2Hand.setTag(null);
                break;
        }
    }

    /**
     * 提交
     */
    private void submit() {
        UserInfo userInfo = localData.readUserInfo();
        if (userInfo == null) return;

        if (TextUtils.isEmpty(addCardName.getText().toString())) {
            LToast.show(context, "请输入联系人姓名");
        } else if (TextUtils.isEmpty(addCardBankCardNumber.getText().toString())) {
            LToast.show(context, "请输入银行卡号");
        } else if (TextUtils.isEmpty(addCardBankChoice.getText().toString())) {
            LToast.show(context, "请选择开户行城市");
        } else if (TextUtils.isEmpty(addCardBankChoice.getText().toString())) {
            LToast.show(context, "请输入开户银行");
        } else if (TextUtils.isEmpty(addCardCityBankChoice.getText().toString())) {
            LToast.show(context, "请输入开户银行行支行");
        } else if (TextUtils.isEmpty(addCardAccount.getText().toString())) {
            LToast.show(context, "请输入开户名");
        } else if (TextUtils.isEmpty(addCardIdCard.getText().toString())) {
            LToast.show(context, "请输入身份证号码");
        } else if (!Matchs.getIDCard(addCardIdCard.getText().toString())) {
            LToast.show(context, "身份证格式错误");
        } else {
            long id = userInfo.getId();

            String bindmobile = addCardGetNumber.getText().toString();
            String customertype = addCardRadiogroup.getCheckedRadioButtonId() == R.id.add_card_person ? "个人" : "企业";
            String linkman = addCardName.getText().toString();
            String idcard = addCardIdCard.getText().toString();
            String provincename = addCardProvinceChoice.getText().toString();
            final String cityname = addCardCityChoice.getText().toString();
            String bankheadname = addCardBankChoice.getText().toString();
            String bankname = addCardCityBankChoice.getText().toString();
            String accountname = addCardAccount.getText().toString();
            String bankaccounttype = addCardBankTyperadiogroup.getCheckedRadioButtonId() == R.id.add_card_type_person ? "个人" : "企业";
            String bankprovince = provincename;
            String bankcity = cityname;
            String bankaccountnumber = addCardBankCardNumber.getText().toString();
            String id_card_front = (String) updateIdPic.getTag();
            String id_ccard_back = (String) updateIdPic2.getTag();
            String persou_photo = (String) updateIdPicHand.getTag();
            String bank_card_front = (String) updateIdPic2Hand.getTag();
            if (StringUtils.isExistEmpty(id_card_front, id_ccard_back, persou_photo, bank_card_front)) {
                LToast.show(context, "请添加身份证及银行卡照片");
                return;
            }
            LProgressDialog.show(context, "");
            bankCardClient.bind(id, bindmobile, customertype, linkman, idcard, provincename, cityname, bankheadname, bankname, accountname, bankaccounttype, bankprovince, bankcity, bankaccountnumber, id_card_front, id_ccard_back, bank_card_front, persou_photo
                    , new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            KLog.i(result);
                            ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                            if (resultInfo == null) return;

                            switch (resultInfo.getCode()) {
                                case ResultCode.SUCCEED:
                                    LToast.show(context, "提交成功");
                                    finish();
                                    break;
                                case ResultCode.FAILURE:
                                    LToast.show(context, StringUtils.isEmpty(resultInfo.getMsg()) ? "提交失败, 请稍后再试" : resultInfo.getMsg());
                                    break;
                                case ResultCode.NOPERMISSION:
                                    UserClient.login(context);
                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            LToast.show(context, "提交失败, 请稍后再试");
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

    private void uploadImg(String imgPath, final View view) {
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
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getId()) {
            case R.id.add_card_radiogroup:
                if (i == R.id.add_card_person) {
                } else if (i == R.id.add_card_company) {
                }
                break;
            case R.id.add_card_bank_typeradiogroup:
                if (i == R.id.add_card_type_person) {
                } else if (i == R.id.add_card_type_company) {
                }
        }
    }

    private void bingImg(ImageView imageView, String filePath) {
        imageView.setTag(null);
        Glide.with(this).load(filePath).centerCrop().crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

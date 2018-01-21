package com.wrt.xinsilu.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.LineCollectionAdapter;
import com.wrt.xinsilu.bean.CollectionLineBean;
import com.wrt.xinsilu.bean.LogisticBean;
import com.wrt.xinsilu.bean.LogisticDetailBean;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.lerist.client.LogisticsClient;
import com.wrt.xinsilu.ui.view.LogisticCollectPopupWindow;
import com.wrt.xinsilu.ui.view.MyListView;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.TextView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class LogisticLineDetailActivity extends SwipeBackActivity {

    @BindView(R.id.title_enter)
    TextView titleEnter;
    @BindView(R.id.business_icon)
    SimpleDraweeView businessIcon;
    @BindView(R.id.RatingBar1)
    RatingBar RatingBar1;
    @BindView(R.id.RatingBar2)
    RatingBar RatingBar2;
    @BindView(R.id.RatingBar3)
    RatingBar RatingBar3;
    @BindView(R.id.RatingBar4)
    RatingBar RatingBar4;
    @BindView(R.id.logistic_detail)
    MyListView logisticDetail;
    @BindView(R.id.btn_back)
    carbon.widget.ImageView btnBack;
    @BindView(R.id.tv_title)
    android.widget.TextView tvTitle;
    private LogisticCollectPopupWindow popupWindow;
    /**
     * 保存的路线详情
     */
    private CollectionLineBean bean;
    /**
     * 路线详情集合
     */
    private List<LogisticBean.ObjectListBean> list;
    private LinearLayout.LayoutParams params;
    private LogisticDetailBean logisticBean;
    private LocalData localData;
    /**
     * 物流商id
     */
    private int id;
    /**
     * 表示收藏或者取消收藏 0，收藏，1，取消收藏
     */
    private int isCollction;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (logisticBean == null) {
                return;
            }
            switch (msg.what) {
                case 0://表示收藏
                    if (isCollction == 1) {
                        collect(logisticBean);
                    } else {
                        unCollect(logisticBean);
                    }

                    break;
                case 1://表示拨打电话
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + logisticBean.getContacts_mobile());
                    intent.setData(data);
                    context.startActivity(intent);
                    break;
            }
        }
    };

    protected void initView() {
        localData = new LocalData(context);
        logisticDetail.setFocusable(false);
        list = new ArrayList<>();
    }

    protected void initValue() {
        Drawable drawable = getResources().getDrawable(R.mipmap.common_gengduo);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        titleEnter.setVisibility(View.VISIBLE);
        titleEnter.setCompoundDrawables(drawable, null, null, null);
        tvTitle.setText(getIntent().getStringExtra("CompanyName"));
        initdata();

    }

    private void initdata() {
        UserInfo userInfo = localData.readUserInfo();
        if (userInfo == null) {
            LToast.show(context, "请先登录");
            startActivity(LoginActivity.class);
            finish();
            return;
        }

        id = getIntent().getIntExtra("CompanyId", -1);
        LProgressDialog.show(context, "");
        new LogisticsClient().getDetails(localData.readUserInfo().getUser().getId(), id, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        logisticBean = JSON.parseObject(resultInfo.getData(), LogisticDetailBean.class);
                        bindData();
                        break;
                    default:
                        LToast.show(context, resultInfo.getMsg() == null ? "查询失败" : resultInfo.getMsg());
                        logisticDetail.setAdapter(new LineCollectionAdapter(context, new ArrayList<LogisticDetailBean.PdglistBean>()));
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
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

    private void bindData() {
        if (logisticBean == null) return;

        businessIcon.setImageURI(Common.GET_COMPANY_IMG + logisticBean.getLogo());
        isCollction = logisticBean.getIsCollect();
        LineCollectionAdapter adapter = new LineCollectionAdapter(context, logisticBean.getPdglist());
        logisticDetail.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logistic_line_detail);
        ButterKnife.bind(this);
        initView();
        initValue();
    }

    @OnClick({R.id.btn_back, R.id.title_enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.title_enter:
                popupWindow = new LogisticCollectPopupWindow(this, handler, R.layout.logistic_collct_adapter, isCollction);
                popupWindow.showAsDropDown(titleEnter);
//                PopupWindow window = new PopupWindow(View.inflate(this,R.layout.logistic_collct_adapter,null),
//                        ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
//                window.showAsDropDown(titleEnter);
//                window.getContentView().findViewById()
                break;
        }
    }

    private void collect(
//            final View view,
            final LogisticDetailBean itemData) {
        if (localData.readUserInfo().getUser() == null) return;
        new LogisticsClient().addCollectCompany(localData.readUserInfo().getUser().getId(),
                id,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;
                        switch (resultInfo.getCode()) {
                            case ResultCode.SUCCEED:
                                LToast.show(context, "已收藏");
                                isCollction = 0;
                                break;
                            default:
                                LToast.show(context, resultInfo.getMsg() + "");
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

                    }
                });
    }

    /**
     * 取消物流商收藏
     */
    private void unCollect(
//            final View view,
            final LogisticDetailBean itemData) {
        if (localData.readUserInfo().getUser() == null) return;
        new LogisticsClient().deleteLogistic(localData.readUserInfo().getUser().getId(),
                id,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        KLog.i(result);
                        ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                        if (resultInfo == null) return;
                        switch (resultInfo.getCode()) {

                            case ResultCode.SUCCEED:
                                LToast.show(context, "取消收藏");
                                isCollction = 1;
                                break;
                            default:
                                LToast.show(context, resultInfo.getMsg() + "");
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

                    }
                });
    }
}

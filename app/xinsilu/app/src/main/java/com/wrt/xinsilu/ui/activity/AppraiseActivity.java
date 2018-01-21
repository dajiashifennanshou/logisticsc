package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.socks.library.KLog;
import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.client.CommentClient;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
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
 * 评价界面
 */
public class AppraiseActivity extends SwipeBackActivity implements OnRatingListener{

    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.commit_edittext)
    EditText commitEdittext;
    @BindView(R.id.anonymous)
    CheckBox anonymous;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.business_icon)
    android.widget.ImageView businessIcon;
    @BindView(R.id.RatingBar1)
    RatingBar RatingBar1;
    @BindView(R.id.RatingBar2)
    RatingBar RatingBar2;
    @BindView(R.id.RatingBar3)
    RatingBar RatingBar3;
    @BindView(R.id.RatingBar4)
    RatingBar RatingBar4;
    private int goods_perfect_level = 5;
    private int get_goods_level = 5;
    private int send_goods_level = 5;
    private int service_level = 5;
    private CommentClient client;
    private LocalData data;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appraise);
        ButterKnife.bind(this);
        RatingBar1.setIsIndicator(false);
        RatingBar2.setIsIndicator(false);
        RatingBar3.setIsIndicator(false);
        RatingBar4.setIsIndicator(false);
        setOnRatingListener(RatingBar1,1);
        setOnRatingListener(RatingBar2,2);
        setOnRatingListener(RatingBar3,3);
        setOnRatingListener(RatingBar4,4);
        client = new CommentClient();
        data = new LocalData(context);
    }

    @OnClick({R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commit:
                if(TextUtils.isEmpty(commitEdittext.getText().toString())){
                    LToast.show(context,"请输入评价内容");
                    return;
                }
                LProgressDialog.show(context,"");
                // TODO: 2016/7/29 0029 请求网络
                KLog.i("-----------" + goods_perfect_level + get_goods_level + send_goods_level + service_level);
                client.comment(Common.GET_ORDER_COMMENT_DETAIL,
                        getIntent().getStringExtra("orderId"),//订单号
                        commitEdittext.getText().toString(), //评论内容
                        Math.round((goods_perfect_level + get_goods_level + send_goods_level + service_level) / 4),
                        data.readUserInfo().getUser().getId(), new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                KLog.i(result);
                                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                                if (resultInfo == null) return;

                                switch (resultInfo.getCode()) {
                                    case ResultCode.SUCCEED:
                                        LToast.show(context, "评价成功");
                                        finish();
                                        break;
                                    case ResultCode.FAILURE:
                                        LToast.show(context, resultInfo.getMsg());
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
                                LProgressDialog.dismiss();
                            }
                        });
                break;
        }
    }
    @Override
    public void setOnRatingListener(RatingBar bar, final int type) {

        bar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch (type){
                    case 1:
                        goods_perfect_level = (int)ratingBar.getRating();
                        KLog.i("1111111111111" + goods_perfect_level);
                        break;
                    case 2:
                        get_goods_level = (int)ratingBar.getRating();
                        KLog.i("2222222222222" + get_goods_level);
                        break;
                    case 3:
                        send_goods_level = (int)ratingBar.getRating();
                        KLog.i("3333333333333" + send_goods_level);
                        break;
                    case 4:
                        service_level = (int)ratingBar.getRating();
                        KLog.i("4444444444444" + service_level);
                        break;
                }

            }
        });
    }
}
interface OnRatingListener{
    /**
     * RatingBar点击监听
     * @param bar RatingBar控件
     * @param type 类型，判断是哪一个控件点击的，一个Ratingbar给一个类型，然后根据类型取值
     */
    void setOnRatingListener(RatingBar bar,int type);

}

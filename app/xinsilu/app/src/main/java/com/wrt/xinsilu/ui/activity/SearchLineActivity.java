package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.bean.ProvinceBean;
import com.wrt.xinsilu.client.GetLineInfoClient;
import com.wrt.xinsilu.dialog.SortPopupWindow;
import com.wrt.xinsilu.ui.fragment.LineSearchFragment;
import com.xsl.lerist.llibrarys.widget.LToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 路线搜索activity
 */
public class SearchLineActivity extends SwipeBackActivity {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.tranport_more)
    TextView tranportMore;
    @BindView(R.id.time_new)
    TextView timeNew;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.sort_all)
    CheckedTextView sortAll;
    @BindView(R.id.layout_line)
    LinearLayout layoutLine;
    private String[] str;
    private List<Fragment> list;
    private List<String> title;
    private GetLineInfoClient client;
    String[] mSort = new String[]{"综合排序", "离我最近", "重货价从低到高", "泡货价从低到高"};
    /**
     * 接受popupwindow传回来的点击的哪一个值
     */
    private int type;
    private SortPopupWindow window;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SortPopupWindow.FLAG_OPEN:
                    sortAll.setChecked(true);
                    break;
                case SortPopupWindow.FLAG_CLOSE:
                    sortAll.setChecked(false);
                    break;
                default:
                    type = msg.what;
                    sortAll.setText(mSort[type]);
                    break;
            }
        }
    };
    private ProvinceBean.CityBean cityNameStart;
    private ProvinceBean provinceNameStart;
    private ProvinceBean provinceNameEnd;
    private ProvinceBean.CityBean cityNameEnd;

    protected void initView() {
        if (cityNameStart == null || cityNameEnd == null) {
            LToast.show(context, "路线有误, 请重新选择");
            finish();
            return;
        }
        setTitle(cityNameStart + "——" + cityNameEnd);
        client = new GetLineInfoClient();
        list = new ArrayList<>();
        title = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_line);
        ButterKnife.bind(this);
        cityNameStart = (ProvinceBean.CityBean) getIntent().getSerializableExtra(ProvinceBean.CityBean.class.getSimpleName() + "start");
        provinceNameStart = (ProvinceBean) getIntent().getSerializableExtra(ProvinceBean.class.getSimpleName() + "start");
        provinceNameEnd = (ProvinceBean) getIntent().getSerializableExtra(ProvinceBean.class.getSimpleName() + "end");
        cityNameEnd = (ProvinceBean.CityBean) getIntent().getSerializableExtra(ProvinceBean.CityBean.class.getSimpleName() + "end");
        LineSearchFragment fragment = LineSearchFragment.newInstance(
                provinceNameStart.getId(),
                cityNameStart.getId(),
                provinceNameEnd.getId(),
                cityNameEnd.getId(),
                getIntent().getDoubleExtra("latitude", 0.00),
                getIntent().getDoubleExtra("lontitude", 0.00)
        );
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, fragment).commit();
        initView();
    }


    @OnClick({R.id.btn_back, R.id.sort_all, R.id.tranport_more, R.id.time_new})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.sort_all:
                window = new SortPopupWindow(this, handler, type);
                window.showPopupWindow(sortAll, false);
                break;
            case R.id.tranport_more:
                break;
            case R.id.time_new:
                break;
        }
    }
}

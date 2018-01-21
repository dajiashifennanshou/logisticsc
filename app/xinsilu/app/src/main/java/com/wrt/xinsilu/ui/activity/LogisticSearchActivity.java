package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.LogisticCollectionAdapter;
import com.wrt.xinsilu.bean.CollectionLineBean;
import com.wrt.xinsilu.client.GetLogisticClient;
import com.wrt.xinsilu.lerist.model.LogisticsCompanyInfo;
import com.wrt.xinsilu.ui.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class LogisticSearchActivity extends SwipeBackActivity{

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    /**
     * 历史纪录adapter
     */
    private LogisticCollectionAdapter adapter;
    /**
     * 测试
     */
    private List<String> list;
    private ArrayList<CollectionLineBean> line_way;
    private CollectionLineBean linebean;
    private ArrayList<LogisticsCompanyInfo> way;
    private String province;
    private String city;
    private double latitude;
    private double longitude;
    private String company_name;
    private GetLogisticClient client;
    private SearchFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logistics_search_layout);
        ButterKnife.bind(this);
        initView();
        getSupportFragmentManager().beginTransaction().replace(R.id.search_fragment,fragment).commit();

    }
    private void initView(){
        setTitle("物流商查询");
        province = getIntent().getStringExtra("nearProvince");
        company_name = getIntent().getStringExtra("logistic");
        city = getIntent().getStringExtra("nearCity");
        longitude = getIntent().getDoubleExtra("lontitude",0.0);
        latitude = getIntent().getDoubleExtra("latitude",0.0);
        fragment = SearchFragment.newInstance(province,city,company_name,latitude,longitude);
    }
}

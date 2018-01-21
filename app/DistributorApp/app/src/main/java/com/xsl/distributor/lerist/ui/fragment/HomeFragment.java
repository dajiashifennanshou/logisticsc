package com.xsl.distributor.lerist.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.socks.library.KLog;
import com.xsl.distributor.R;
import com.xsl.distributor.lerist.client.Authorization;
import com.xsl.distributor.lerist.model.NewsInfo;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.ui.activity.MyCloudRepositoryActivity;
import com.xsl.distributor.ws.params.HomeInfomationParams;
import com.xsl.distributor.ws.ui.activity.SearchActivity;
import com.xsl.distributor.ws.ui.activity.WebActivity;
import com.xsl.lerist.llibrarys.adapter.LRecyclerViewAdapter;
import com.xsl.lerist.llibrarys.adapter.LViewPagerAdapter;
import com.xsl.lerist.llibrarys.fragments.LFragment;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.widget.LPageIndicator;
import com.xsl.lerist.llibrarys.widget.LRecyclerView;
import com.xsl.lerist.llibrarys.widget.LViewPager;

import org.xutils.common.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends LFragment implements SwipeRefreshLayout.OnRefreshListener, LRecyclerViewAdapter.OnLoadListener {

    @BindView(R.id.f_home_lvp_banner)
    LViewPager lvp_banner;
    @BindView(R.id.f_home_lpi_banner)
    LPageIndicator lpi_banner;
    @BindView(R.id.f_home_lrv_news)
    LRecyclerView lrv_news;
    @BindView(R.id.refresh_layout)
    SpringView refreshLayout;
    @BindView(R.id.scrollview)
    NestedScrollView scrollview;
    private LRecyclerViewAdapter<NewsInfo> mNewsRecyclerViewAdapter;
    private HomeInfomationParams params;
    private int i = 1;
    private List<NewsInfo> list;
    private boolean isAllload;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {
        setTitle("首页");
        lrv_news.setFocusable(false);
        refreshLayout.setHeader(new DefaultHeader(context));
        refreshLayout.setFooter(new DefaultFooter(context));refreshLayout.setType(SpringView.Type.FOLLOW);
        refreshLayout.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                HomeFragment.this.onRefresh();
            }

            @Override
            public void onLoadmore() {
                HomeFragment.this.onLoad(0);
            }
        });
        scrollview.setNestedScrollingEnabled(true);
        lrv_news.setNestedScrollingEnabled(false);
        lrv_news.setFocusable(false);
        list = new ArrayList<>();
        params = new HomeInfomationParams();
        getTestNews();
        LViewPagerAdapter pagerAdapter = new LViewPagerAdapter(getBanners());
        lvp_banner.setAdapter(pagerAdapter);

        lpi_banner.setFocusResourceIds(R.drawable.drawable_dotline_foc)
                .setNormalResourceIds(R.drawable.drawable_dotline_nor)
                .setIconSize(Lerist.dip2px(context, 8), Lerist.dip2px(context, 2))
                .setInMargin(Lerist.dip2px(context, 2), 0, Lerist.dip2px(context, 2), 0)
                .setTextSize(0)
                .setViewPager(lvp_banner);
        lpi_banner.startAutoScroll(5000);

        mNewsRecyclerViewAdapter = new LRecyclerViewAdapter<NewsInfo>(context) {
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_home_news;
            }

            @Override
            public void onBindViewHolder(LRecyclerViewHolder viewHolder, int position, NewsInfo itemData) {
                viewHolder.setText(R.id.i_home_news_tv_title, itemData.getTitle());
            }
        };
        mNewsRecyclerViewAdapter.setOnItemClickListener(new LRecyclerViewAdapter.LOnItemClickListener() {
            @Override
            public void OnClickListener(View view, int position) {
                startActivity(new Intent(context, WebActivity.class)
                        .putExtra("title", mNewsRecyclerViewAdapter.getItemData(position).getTitle())
                        .putExtra("uri", mNewsRecyclerViewAdapter.getItemData(position).getUrl()));
            }

            @Override
            public void OnLongClickListener(View view, int position) {

            }
        });
        mNewsRecyclerViewAdapter.setOnLoadListener(this);
        lrv_news.setLayoutManager(new LinearLayoutManager(context));
        lrv_news.setAdapter(mNewsRecyclerViewAdapter);
        lrv_news.addItemDecoration(new LRecyclerView.RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL, 2, getResources().getColor(R.color.colorPrimaryContent)));
    }

    private void getTestNews() {
        post(i);
    }

    private ArrayList<LViewPagerAdapter.PagerItem> getBanners() {
        ArrayList<LViewPagerAdapter.PagerItem> pagerItems = new ArrayList<>();

        int[] testBanner = {
                R.mipmap.banner_1_3x,
                R.mipmap.banner_2_3x,
                R.mipmap.banner_3_3x
        };

        for (int i : testBanner) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(i);

            pagerItems.add(new LViewPagerAdapter.PagerItem("banner", imageView));
        }

        return pagerItems;
    }

    @OnClick({R.id.btn_search, R.id.f_home_ll_btn_my_depository})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                startActivity(SearchActivity.class);
                break;
            case R.id.f_home_ll_btn_my_depository:
                startActivity(MyCloudRepositoryActivity.class);
                break;
        }
    }

    @Override
    public void onRefresh() {
        i = 1;
        post(i);
    }

    private void post(int page) {
        params.post(UriConstants.CHECK_BULLETIN, page, Authorization.SIGN, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }
                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        if (i == 1) mNewsRecyclerViewAdapter.removeDataAll();
                        list = JSON.parseArray(resultInfo.getData(), NewsInfo.class);
                        if (list != null) {
                            mNewsRecyclerViewAdapter.addDatas(list);

                            isAllload = list.size() < 20;
                        }
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
                refreshLayout.onFinishFreshAndLoad();
            }
        });
    }

    @Override
    public void onLoad(int freeItemCount) {
        if (isAllload == false) {
            post(++i);
        } else {
            refreshLayout.onFinishFreshAndLoad();
        }
    }
}

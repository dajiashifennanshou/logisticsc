package com.xsl.distributor.ws.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.xsl.distributor.R;
import com.xsl.distributor.ws.ui.fragment.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 搜索界面
 */
public class SearchActivity extends SwipeBackActivity {

    @BindView(R.id.search_fragment)
    FrameLayout searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
//        setTitle("搜索");
        getSupportFragmentManager().beginTransaction().replace(R.id.search_fragment,new SearchFragment()).commit();

    }
}

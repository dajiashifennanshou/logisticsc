package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.wrt.xinsilu.R;
import com.xsl.lerist.llibrarys.utils.ApkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import carbon.widget.ImageView;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class AboutActivity extends SwipeBackActivity {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.version)
    TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusConfig();
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setTitle("关于中工储运");
        version.setText("中工储运货主" + ApkUtils.getVersionName(context));
    }

    @Override
    public int getStateBarColor() {
        return R.color.white;
    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }
}

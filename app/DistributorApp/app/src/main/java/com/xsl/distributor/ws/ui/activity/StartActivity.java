package com.xsl.distributor.ws.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.xsl.distributor.R;
import com.xsl.distributor.lerist.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class StartActivity extends SwipeBackActivity {

    @BindView(R.id.start_img)
    ImageView startImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        AlphaAnimation animation = new AlphaAnimation(0f,1f);
        animation.setDuration(1500);
        startImg.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.class);
                finish();
            }
        },4000);
    }
}

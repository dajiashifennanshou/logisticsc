package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;


import com.wrt.xinsilu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class StartActivity extends SwipeBackActivity {

    @BindView(R.id.start_img)
    ImageView startImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        AlphaAnimation animation = new AlphaAnimation(0f,1f);
        animation.setDuration(1000);
        startImg.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.class);
                finish();
            }
        },2500);
    }
}

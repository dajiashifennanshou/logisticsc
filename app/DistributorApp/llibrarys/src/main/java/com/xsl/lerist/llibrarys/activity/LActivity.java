package com.xsl.lerist.llibrarys.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.socks.library.KLog;
import com.xsl.lerist.llibrarys.R;
import com.xsl.lerist.llibrarys.utils.FontHelper;
import com.xsl.lerist.llibrarys.utils.Lerist;
import com.xsl.lerist.llibrarys.utils.SystemBarTintManager;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.x;


/**
 * Created by Lerist on 2015/8/27, 0027.
 */
public class LActivity extends AppCompatActivity {
    public LActivity context;
    private boolean isEnableFinishHint = false;
    private View rootView;
    private SystemBarTintManager tintManager;
    private boolean isCreated;
    private boolean isStarted;
    private boolean isResumed;
    private boolean isEnableNoKill;

    public LActivity getInstance() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.i(this.getClass().getSimpleName() + " onCreate...");
        context = this;
        LActivityManager.addActivity(this);
//        statusConfig();
//        fadeTransition();

        //设置屏幕方向
        if (getScreenOrientation() != -1) {
            setRequestedOrientation(getScreenOrientation());
        }

        //xUtils annotation
//        x.view().inject(this);
        isCreated = true;
    }

    public void fadeTransition() {
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 透明状态栏配置
     */
    public void statusConfig() {
        if (Lerist.isActivityFullscreen(this) == false && isTranslucentStateBar() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //透明导航栏
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。
            tintManager.setStatusBarTintResource(getStateBarColor());
            // 设置状态栏的文字颜色(MIUI)
            tintManager.setStatusBarDarkMode(true, this);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        rootView = getLayoutInflater().inflate(layoutResID, null);
        super.setContentView(rootView);
    }

    @Override
    public void setContentView(View view) {
        rootView = view;
        super.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        rootView = view;
        super.setContentView(view, params);
    }

    public View getRootView() {
        return rootView;
    }

    /**
     * 默认竖屏
     *
     * @return
     */
    public int getScreenOrientation() {
        return -1;
    }

    /**
     * 是否透明状态栏
     *
     * @return
     */
    public boolean isTranslucentStateBar() {
        return true;
    }

    public void setStatusBarTintResource(int resId) {
        if (tintManager != null) {
            tintManager.setStatusBarTintResource(resId);
        }
    }

    /**
     * 状态栏颜色
     *
     * @return
     */
    public int getStateBarColor() {
        return R.color.colorPrimaryStateBar;
    }

    @Override
    protected void onStart() {
        super.onStart();
        isStarted = true;
        //初始化默认视图
        initDefaultView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
    }

    /**
     * 初始化默认视图
     */
    private void initDefaultView() {
        View btn_back = findViewById(R.id.btn_back);
        if (btn_back != null)
            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
    }

    public void setText(String text, int resId) {
        TextView textView = find(resId, TextView.class);
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void setTitle(String title) {
        super.setTitle(title);
        setText(title, R.id.tv_title);
    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    public <E extends View> E find(int resid) {
        return (E) find(resid, View.class);
    }

    public <E extends View> E find(int resid, Class<E> e) {
        View view = findViewById(resid);
        return (E) view;
    }

    public void startActivity(Class c) {
        startActivity(new Intent(this, c));
    }

    public void startActivity(Class c, Bundle options) {
        startActivity(new Intent(this, c), options);
    }

    public void setEnableFinishHint(boolean enableFinishHint) {
        isEnableFinishHint = enableFinishHint;
    }

    public void setEnableNoKill(boolean enableNoKill) {
        isEnableNoKill = enableNoKill;
    }

    @Override
    public void finish() {
        super.finish();
        fadeTransition();
    }

//    public boolean isCreated() {
//        return isCreated;
//    }
//
//    public boolean isStarted() {
//        return isStarted;
//    }
//
//    public boolean isResumed() {
//        return isResumed;
//    }
//
//    public boolean isPaused() {
//        return !isResumed;
//    }
//
//    public boolean isStoped() {
//        return !isStarted;
//    }

    @Override
    protected void onPause() {
        super.onPause();
        isResumed = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isStarted = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LActivityManager.removeActivity(this);
        KLog.i(this.getClass().getSimpleName() + " onDestroy...");
    }

    private long lastBackPressedTimeMillis = 0;

    @Override
    public void onBackPressed() {
        if (isEnableNoKill) {
            onBackground();
            return;
        }

        if (isEnableFinishHint) {
            if (System.currentTimeMillis() - lastBackPressedTimeMillis > 2000) {
                lastBackPressedTimeMillis = System.currentTimeMillis();
                LToast.show(context, "再按一次退出", Gravity.BOTTOM);
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    public void onBackground() {
        PackageManager pm = getPackageManager();
        ResolveInfo homeInfo =
                pm.resolveActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME), 0);
        ActivityInfo ai = homeInfo.activityInfo;
        Intent startIntent = new Intent(Intent.ACTION_MAIN);
        startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        startIntent.setComponent(new ComponentName(ai.packageName, ai.name));
        try {
            startActivity(startIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

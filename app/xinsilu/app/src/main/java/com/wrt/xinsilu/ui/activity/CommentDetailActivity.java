package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.wrt.xinsilu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 评价详情
 */
public class CommentDetailActivity extends SwipeBackActivity {
    public static final String TAG = "CommentDetailActivity";

    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.tv_title)
    TextView title;

    protected void initValue() {
        title.setText("评价(" + getIntent().getIntExtra("CommentNumber", 0) + ")");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);
        ButterKnife.bind(this);
        initValue();
    }

}

package com.xsl.distributor.lerist.ui.activity;

import android.os.Bundle;

import com.umeng.fb.fragment.FeedbackFragment;
import com.xsl.distributor.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class FeedbackActivity extends SwipeBackActivity {

    private FeedbackFragment mFeedbackFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.umeng_fb_activity_conversation);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            String conversation_id = getIntent().getStringExtra(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID);
            mFeedbackFragment = FeedbackFragment.newInstance(conversation_id);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.umeng_fb_container, mFeedbackFragment)
                    .commit();
        }

        initView();
    }

    private void initView() {
        setTitle("反馈");
    }


    @Override
    protected void onNewIntent(android.content.Intent intent) {
        mFeedbackFragment.refresh();
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.umeng.fb;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;
import com.umeng.fb.res.e;
import com.umeng.fb.res.f;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ConversationActivity extends SwipeBackActivity {
    private final String a = ConversationActivity.class.getName();
    private FeedbackFragment b;

    public ConversationActivity() {
    }

    @TargetApi(11)
    @SuppressLint({"NewApi"})
    protected void onCreate(Bundle var1) {
        super.onCreate(var1);
        if(VERSION.SDK_INT >= 11 && this.getActionBar() != null) {
            this.getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.setContentView(f.a(this));
        if(var1 == null) {
            String var2 = this.getIntent().getStringExtra("conversation_id");
            if(var2 == null) {
                var2 = (new FeedbackAgent(this)).getDefaultConversation().getId();
            }

            this.b = FeedbackFragment.newInstance(var2);
            this.getSupportFragmentManager().beginTransaction().add(e.r(this), this.b).commit();
        }

        setTitle("反馈");
    }

    protected void onNewIntent(Intent var1) {
        this.b.refresh();
    }

    public boolean onCreateOptionsMenu(Menu var1) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem var1) {
        switch(var1.getItemId()) {
            case 16908332:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(var1);
        }
    }
}

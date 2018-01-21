package com.wrt.xinsilu.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.wrt.xinsilu.R;

public class TestActivity extends Activity {
    private Button btn;
    private RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        layout = (RelativeLayout) findViewById(R.id.button_hah);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CityPopupWindow popupWindow = new CityPopupWindow(TestActivity.this);
//                popupWindow.showPopupWindow(layout,true);
            }
        });



    }
}

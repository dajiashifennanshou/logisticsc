package com.wrt.xinsilu.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.wrt.xinsilu.R;
import com.wrt.xinsilu.adapter.DriverAdapter;
import com.wrt.xinsilu.adapter.SenderAdapter;
import com.wrt.xinsilu.bean.DriverBean;
import com.wrt.xinsilu.bean.SenderBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class UsuallySenderActivity extends SwipeBackActivity {

    @BindView(R.id.title_enter)
    carbon.widget.TextView titleEnter;
    @BindView(R.id.sender_list)
    ListView senderList;
    private DriverAdapter adapter_driver;
    private SenderAdapter adapter_sender;
    private DriverBean bean;
    private List<DriverBean> list_driver;
    private List<SenderBean> list_sender;
    /**
     * 判断是那个页面进来的 主要用于adapter判断
     * 1：发货人点进来
     * 2：收货人
     * 3：司机
     */
    private int type;
    public static final String INT_WHICH = "int_which";
    private SenderBean sender;

    protected void initValue() {
        initValue_driver();
        initValue_sender();
        if (type == 1) {
            adapter_sender = new SenderAdapter(context, list_sender, type, true);
            senderList.setAdapter(adapter_sender);
        } else if (type == 2) {
            adapter_sender = new SenderAdapter(context, list_sender, type, true);
            senderList.setAdapter(adapter_sender);
        } else if (type == 3) {
            adapter_driver = new DriverAdapter(context, list_driver, true);
            senderList.setAdapter(adapter_driver);
        }


    }

    /**
     * 初始化数据
     */
    private void initValue_driver() {
        list_driver = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bean = new DriverBean();
            bean.setDriver_name("张三");
            bean.setPhone_number("19111111111");
            bean.setLicense_number("川A12345");
            list_driver.add(bean);
        }
    }

    private void initValue_sender() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usually_sender);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra(INT_WHICH, 0);
    }

}

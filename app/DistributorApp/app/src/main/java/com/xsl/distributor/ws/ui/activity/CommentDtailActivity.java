package com.xsl.distributor.ws.ui.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.xsl.distributor.R;
import com.xsl.distributor.ws.adapter.CommentAdapter;
import com.xsl.distributor.ws.bean.CommentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/***
 * 评论
 */
public class CommentDtailActivity extends SwipeBackActivity {
    @BindView(R.id.basic_list_layout)
    ListView basicListLayout;
    private CommentBean bean;
    private List<CommentBean>list;
    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_list_llayout);
        ButterKnife.bind(this);
        setTitle("配送评价");
        initdata();
    }
    /**模拟数据*/

    private void initdata() {
        list = new ArrayList<>();
        for(int i = 0; i < 1; i++){
            bean = new CommentBean();
            bean.setUrl("");
            bean.setUser_name("");
            bean.setComment_detail(getIntent().getStringExtra("comment"));
            list.add(bean);
        }
        adapter = new CommentAdapter(context,list);
        basicListLayout.setAdapter(adapter);
    }
}

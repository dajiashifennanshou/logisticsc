package com.xsl.distributor.ws.dialog;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.xsl.distributor.ws.basic.BasicPopupWindow;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20 0020.
 * 添加银行卡省市的popupwindow
 */
public class AddBankPopopWindow<ProvinceBean> extends BasicPopupWindow implements AdapterView.OnItemClickListener{
    public AddBankPopopWindow(Activity context, Handler handler, List<ProvinceBean>list) {
        super(context, handler, list);
        setadapter();
    }
    private void setadapter() {
        if (list!=null) {
            ArrayAdapter adapter = new ArrayAdapter<ProvinceBean>(context,android.R.layout.simple_list_item_1,list);
            sort.setAdapter(adapter);
            sort.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        Message message = new Message();
        message.obj = list.get(position).toString();
        handler.sendMessage(message);
        dismiss();
    }
}

package com.xsl.distributor.ws.params;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.ws.basic.BasicParams;
import com.xsl.distributor.ws.bean.OrderConstListBean;
import com.xsl.distributor.ws.onResponse.OnResponse;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.BusProvider;
import com.xsl.lerist.llibrarys.utils.ListUtils;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.http.RequestParams;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13 0013.
 * 获取安装费用
 */
public class RunningMoneyParams extends BasicParams implements OnResponse{
    public RunningMoneyParams(Context context, String url, String obj) {
        super(context, url, obj);
    }
    public void join(){
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("dealerId",(String)obj);
        LToast.show(context,(String)obj);
        params.addBodyParameter("page","1");
        params.addBodyParameter("rows","10");
        params.addBodyParameter("sign","xsl");
        client.regist(this,params,null);
    }

    @Override
    public <T> void onReponse(ResultInfo str, Class<T> clazz) {
        Log.i("----->",str.getData());
        List<OrderConstListBean> list = JSON.parseArray(String.valueOf(str.getData()), OrderConstListBean.class);
        BusProvider.getInstance().post(ListUtils.toArrayList(list));
    }

    @Override
    public <T> void onFinish() {

    }
}

package com.xsl.distributor.ws.params;

import android.content.Context;
import android.util.Log;

import com.xsl.distributor.ws.basic.BasicParams;
import com.xsl.distributor.ws.onResponse.OnResponse;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.BusProvider;

import org.xutils.http.RequestParams;

/**
 * Created by Administrator on 2016/7/12 0012.
 * 使用时，调用
 */
public class JoinParams extends BasicParams implements OnResponse {

    /**
     * @param context
     * @param url     地址
     * @param obj     传递的参数 bean类
     */
    public JoinParams(Context context, String url, Object obj) {
        super(context, url, obj);
    }

    public void join(String joinName,long id, int days) {
        RequestParams params = new RequestParams(url);
        params.addParameter("joinName", joinName);
        params.addParameter("joinerId", id);
        params.addParameter("days", days);
        params.addParameter("sign", "xsl");
        client.regist(this, params, null);
    }

    @Override
    public <T> void onReponse(ResultInfo str, Class<T> clazz) {
        Log.i("result", str.getData());
        BusProvider.getInstance().post(str);
    }

    @Override
    public <T> void onFinish() {

    }
}

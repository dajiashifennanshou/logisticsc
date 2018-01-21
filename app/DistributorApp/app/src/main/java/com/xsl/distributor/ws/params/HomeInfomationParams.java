package com.xsl.distributor.ws.params;

import com.xsl.distributor.lerist.client.Authorization;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/23 0023.
 * 首页公告信息
 */
public class HomeInfomationParams extends LClient {
    /**
     * 获取首页公告信息
     * @param url 地址
     * @param page 页数
     * @param sign 签名
     * @param callback
     * @return
     */
    public Callback.Cancelable post(String url, int page,String sign, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("page", page);
        params.addParameter("rows", 20);
        params.addParameter("sign", Authorization.SIGN);
        return x.http().post(params, callback);
    }
}

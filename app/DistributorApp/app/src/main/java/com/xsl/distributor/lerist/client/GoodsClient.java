package com.xsl.distributor.lerist.client;

import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.utils.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class GoodsClient extends LClient {
    public Callback.Cancelable getInList(long dealerId, int page, int rows, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.GOODSIN);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("dealerId", DES.encrypt(dealerId + ""));
        params.addParameter("page",page);
        params.addParameter("rows", rows);
        return x.http().post(params, callback);
    }

    public Callback.Cancelable getOutList(long dealerId, int page, int rows, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.GOODSOUT);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("dealerId", DES.encrypt(dealerId + ""));
        params.addParameter("page",page);
        params.addParameter("rows", rows);
        return x.http().post(params, callback);
    }

    public Callback.Cancelable getGoodsList(long dealerId, int page, int rows, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.GOODSLIST);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("dealerId", DES.encrypt(dealerId + ""));
        params.addParameter("page", page);
        params.addParameter("rows", rows);
        return x.http().post(params, callback);
    }
}

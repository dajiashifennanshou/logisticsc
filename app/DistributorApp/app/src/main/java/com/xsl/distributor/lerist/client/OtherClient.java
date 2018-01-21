package com.xsl.distributor.lerist.client;

import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class OtherClient extends LClient {

    /**
     * 注册
     *
     * @param phoneOrEmail
     * @param pwd
     * @param callback
     * @return {
     * "code": 10000
     * }
     */
    public Callback.Cancelable search(String keyword, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.DEDICATED_MONEY_DETAIL);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("deliveryNo", keyword);
        return x.http().post(params, callback);
    }

}

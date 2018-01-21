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

public class CustomerClient extends LClient {
    public Callback.Cancelable getList(long id,int page, int rows, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.COMMONCLIENT);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("dealerId", DES.encrypt(id+""));
        params.addParameter("page", page);
        params.addParameter("rows", rows);
        return x.http().post(params, callback);
    }
}

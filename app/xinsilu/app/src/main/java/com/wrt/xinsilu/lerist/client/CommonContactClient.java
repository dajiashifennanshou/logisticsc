package com.wrt.xinsilu.lerist.client;

import com.wrt.xinsilu.client.Authorization;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.util.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Lerist on 2016/8/4, 0004.
 */

public class CommonContactClient extends LClient {
    public Callback.Cancelable getSenderList(long userId, int page, int rows, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.COMMON_CONTACTS);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("user_id", DES.encrypt(userId + ""));
        params.addParameter("page", page);
        params.addParameter("contacts_type", 0);
        params.addParameter("rows", rows);
        return x.http().post(params, callback);
    }

    public Callback.Cancelable getReceiverList(long userId, int page, int rows, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.COMMON_CONTACTS);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("user_id", DES.encrypt(userId + ""));
        params.addParameter("page", page);
        params.addParameter("contacts_type", 1);
        params.addParameter("rows", rows);
        return x.http().post(params, callback);
    }

    public Callback.Cancelable delete(long id, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.DELETE_COMMON_CONTACTS);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("id", DES.encrypt(id + ""));
        return x.http().post(params, callback);
    }

    /**
     * 删除常用司机
     * @param id
     * @param callback
     * @return
     */
    public Callback.Cancelable deleteDriver(long id, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.DELETE_COMMON_DRIVER);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("id", DES.encrypt(id + ""));
        return x.http().post(params, callback);
    }
}

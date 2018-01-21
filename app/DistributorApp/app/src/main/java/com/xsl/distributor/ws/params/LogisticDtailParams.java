package com.xsl.distributor.ws.params;

import com.xsl.distributor.lerist.client.Authorization;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.utils.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public class LogisticDtailParams extends LClient{
    /***
     * 获取运单详情
     * @param bumber
     * @param callback
     * @return
     */

    public Callback.Cancelable join(String bumber, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.DEDICATED_MONEY_DETAIL);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("order_number", DES.encrypt(bumber));
        params.addParameter("sign", Authorization.SIGN);
        return x.http().post(params, callback);
    }

}

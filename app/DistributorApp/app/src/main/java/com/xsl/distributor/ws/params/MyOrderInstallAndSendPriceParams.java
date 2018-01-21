package com.xsl.distributor.ws.params;

import android.util.Log;

import com.xsl.distributor.lerist.client.Authorization;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.utils.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/25 0025.
 * 获取安装配送费
 */
public class MyOrderInstallAndSendPriceParams extends LClient {
    /**
     * 获取下单之后的安装配送费
     * @param branchNo
     * @param type
     * @param callback
     * @return
     */
    public Callback.Cancelable getKindsOfMoneyDetail(String type,String branchNo,Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.GET_KIND_OF_PRICE);
        params.addParameter("type", type);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("branchNo", DES.encrypt(branchNo));
        Log.i("--------------",branchNo);
        params.addParameter("sign", Authorization.SIGN);
        return x.http().post(params, callback);
    }
}

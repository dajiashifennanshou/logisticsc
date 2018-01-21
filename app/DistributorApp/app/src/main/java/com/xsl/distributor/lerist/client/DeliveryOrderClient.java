package com.xsl.distributor.lerist.client;

import com.socks.library.KLog;
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

public class DeliveryOrderClient extends LClient {
    public Callback.Cancelable getList(long id, String branchNo, String orderStatus, int page, int rows, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.DELIVERYORDER_LIST);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("dealerId", DES.encrypt(id + ""));
        params.addParameter("branchNo", DES.encrypt(branchNo));
        params.addParameter("status", DES.encrypt(orderStatus));
        params.addParameter("page", page);
        params.addParameter("rows", rows);
        KLog.i(params.toString());
        return x.http().post(params, callback);
    }

    public Callback.Cancelable getDetail(String deliveryNo, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.DELIVERYORDER_DETAIL);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("deliveryNo", DES.encrypt(deliveryNo));
        return x.http().post(params, callback);
    }

    public Callback.Cancelable add(long dealerId,
                                   String goodsIds,
                                   String goodsNames,
                                   String branchNo,
                                   float installCost,
                                   float agentPaidCost,
                                   String consigneeName,
                                   String consigneePhone,
                                   String consigneeAddr,
                                   Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.DELIVERYORDER_ADD);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("dealerId", DES.encrypt(dealerId + ""));
        params.addParameter("goodsIds", DES.encrypt(goodsIds));
        params.addParameter("goodsNames", DES.encrypt(goodsNames));
        params.addParameter("branchNo", DES.encrypt(branchNo));
        params.addParameter("installCost", DES.encrypt(installCost + ""));
        params.addParameter("agentPaidCost", DES.encrypt("" + agentPaidCost));
        params.addParameter("consigneeName", DES.encrypt(consigneeName));
        params.addParameter("consigneePhone", DES.encrypt(consigneePhone));
        params.addParameter("consigneeAddr", DES.encrypt(consigneeAddr));
        return x.http().post(params, callback);
    }
}

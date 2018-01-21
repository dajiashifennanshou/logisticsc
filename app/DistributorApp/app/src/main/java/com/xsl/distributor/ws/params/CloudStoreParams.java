package com.xsl.distributor.ws.params;

import com.xsl.distributor.lerist.client.Authorization;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.utils.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/18 0018.
 * 加盟云仓
 */
public class CloudStoreParams extends LClient {
    /**
     * 申请加盟
     *
     * @param url      地址
     * @param user_id  经销商id
     * @param branchNo 网点编号
     * @param days     申请天数
     * @param sign     签名
     * @param callback
     */
    public Callback.Cancelable join(String url, String joinName, long user_id, String branchNo, int days, String sign, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("joinerId", DES.encrypt(user_id+""));
        params.addParameter("token", LocalData.getToken());
        params.addParameter("joinName",  DES.encrypt(joinName));
        params.addParameter("branchNo",  DES.encrypt(branchNo));
        params.addParameter("days",  DES.encrypt(days+""));
        params.addParameter("sign", Authorization.SIGN);
        return x.http().post(params, callback);
    }

    /**
     * 获取云仓网点
     *
     * @param url      地址
     * @param sign     签名
     * @param callback
     * @return
     */
    public Callback.Cancelable getStorageBranch(String url, String sign, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("sign", sign);
        params.addParameter("token", LocalData.getToken());
        return x.http().post(params, callback);
    }

}

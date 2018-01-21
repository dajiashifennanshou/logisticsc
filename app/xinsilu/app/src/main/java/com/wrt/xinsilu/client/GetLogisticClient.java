package com.wrt.xinsilu.client;

import com.socks.library.KLog;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.util.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/8 0008.
 * 获取物流商
 */
public class GetLogisticClient extends LClient {
    /**
     * 查找获取物流商
     * @param url
     * @param company_name 搜索的物流商名称
     * @param province 省
     * @param city 市
     * @param longitude 经度
     * @param latitude 纬度
     * @param callback
     * @return
     */
    public Callback.Cancelable getLogistic(String url,
                                           String company_name,
                                           String province,
                                           String city,
                                           double longitude,
                                           double latitude,
                                           int page,
                                           Callback.CommonCallback<String> callback){
        KLog.i("\n" + province + "\n" + city + "\n" + longitude + "\n" + latitude + "\n" + company_name);
        RequestParams params = new RequestParams(url);
        params.addParameter("company_name", DES.encrypt(company_name));
        params.addParameter("province", DES.encrypt(province));
        params.addParameter("city", DES.encrypt(city));
        params.addParameter("longitude", DES.encrypt("" + longitude));
        params.addParameter("latitude", DES.encrypt("" + latitude));
        params.addParameter("page", page);
        params.addParameter("rows", 20);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("sign",Authorization.SIGN);
        return x.http().post(params, callback);
    }
}

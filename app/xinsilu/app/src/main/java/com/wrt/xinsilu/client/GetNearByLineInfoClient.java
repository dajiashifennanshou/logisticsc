package com.wrt.xinsilu.client;

import com.wrt.xinsilu.util.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/2 0002.
 * 获取附近物流商
 */
public class GetNearByLineInfoClient extends LClient {
    /**
     *
     * @param url 地址
     * @param longitude 唯独
     * @param latitude 经度
     * @param province 省
     * @param city 市
     * @param county 县
     * @param page 当前页
     * @param rows 每页行数
     * @param callback
     * @return
     */
    public Callback.Cancelable getAddress(String url,double longitude, double latitude,String province,String city,String county,int page,int rows,  Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("longitude", DES.encrypt(longitude + ""));
        params.addParameter("latitude",DES.encrypt(latitude + ""));
        params.addParameter("province",DES.encrypt(province));
        params.addParameter("city",DES.encrypt(city));
        params.addParameter("county",DES.encrypt(county));
        params.addParameter("page",page);
        params.addParameter("rows",20);
        params.addParameter("sign",Authorization.SIGN);
        return x.http().post(params, callback);
    }
}

package com.xsl.distributor.ws.params;

import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.utils.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/20 0020.
 * 添加银行卡请求
 */
public class AddBankCardParams extends LClient {
    /**
     * 获取省信息
     *
     * @param url  地址
     * @param sign 签名
     */

    public Callback.Cancelable getProvince(String url, String sign, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("sign", sign);
        return x.http().post(params, callback);
    }

    /**
     * 获取市
     *
     * @param url          地址
     * @param provinceName 传递的省名字
     * @param sign         签名
     * @param callback
     * @return
     */
    public Callback.Cancelable getCity(String url, String provinceName, String sign, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("provinceName", DES.encrypt(provinceName));
        params.addParameter("sign", sign);
        return x.http().post(params, callback);
    }

    /**
     * 获取银行
     *
     * @param url          地址
     * @param provinceName 省名
     * @param cityName     城市名
     * @param sign         签名
     * @param callback
     * @return
     */
    public Callback.Cancelable getBank(String url, String provinceName, String cityName, String sign, Callback.CommonCallback<String> callback) {

        RequestParams params = new RequestParams(url);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("provinceName", DES.encrypt(provinceName));
        params.addParameter("cityName", DES.encrypt(cityName));
        params.addParameter("sign", sign);
        return x.http().post(params, callback);
    }

    /**
     * 获取支行
     *
     * @param url          地址
     * @param provinceName 省名
     * @param cityName     城市名
     * @param headName     开户银行
     * @param sign         签名
     * @param callback
     * @return
     */
    public Callback.Cancelable getBankOfBranch(String url, String provinceName, String cityName, String headName, String sign, Callback.CommonCallback<String> callback) {

        RequestParams params = new RequestParams(url);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("provinceName", DES.encrypt(provinceName));
        params.addParameter("cityName", DES.encrypt(cityName));
        params.addParameter("headName", DES.encrypt(headName));
        params.addParameter("sign", sign);
        return x.http().post(params, callback);
    }


}

package com.wrt.xinsilu.client;

import com.wrt.xinsilu.util.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/2 0002.
 * 发货路线查询接口
 */
public class GetLineInfoClient extends LClient{
    /**
     * 查询发货路线，有首页路线查询获取的数据
     * @param url
     * @param startProvince 出发省
     * @param startCity 出发市
     * @param endProvince 目的地省
     * @param endCity 目的地市
     * @param longitude 经度
     * @param latitude 纬度
     * @param callback
     * @return
     */
    public Callback.Cancelable getLineInfo(String url,
                                              String startProvince,
                                              String startCity,
                                              String endProvince,
                                              String endCity,
                                              double longitude,
                                              double latitude,
                                           int user_id,
                                              int page,
                                              Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("startProvince", DES.encrypt(startProvince));
        params.addParameter("startCity", DES.encrypt(startCity));
        params.addParameter("endProvince",DES.encrypt(endProvince));
        params.addParameter("endCity",DES.encrypt(endCity));
        params.addParameter("longitude",DES.encrypt(longitude + ""));
        params.addParameter("page",page);
        params.addParameter("rows",20);
        params.addParameter("user_id",DES.encrypt(user_id + ""));
        params.addParameter("latitude",DES.encrypt(latitude + ""));
        params.addParameter("sign",Authorization.SIGN);
        return x.http().post(params, callback);
    }
}

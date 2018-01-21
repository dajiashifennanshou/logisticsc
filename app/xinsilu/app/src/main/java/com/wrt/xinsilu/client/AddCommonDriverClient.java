package com.wrt.xinsilu.client;

import com.socks.library.KLog;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.util.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/4 0004.
 * 添加常用司机---删除司机---查找司机
 */
public class AddCommonDriverClient extends LClient {
    /**
     * 添加司机
     * @param url
     * @param user_id 用户id
     * @param driver_name 司机姓名
     * @param license_number 驾照
     * @param phone_number 电话号码
     * @return
     */
    public Callback.Cancelable addDriver(String url,
                                         int user_id,
                                         String driver_name,
                                         String phone_number,
                                         String license_number,
//                                         String province,
//                                         String city,
//                                         String county,
//                                         String address,
                                         Callback.CommonCallback<String> callback) {
        KLog.i("\n" + "user_id = " + user_id + "\n" +  "driver_name" + driver_name + "\n" + "license_number" + license_number + "\n" + "phone_number" + phone_number);
        RequestParams params = new RequestParams(url);
        params.addParameter("user_id",DES.encrypt("" + user_id));
        params.addParameter("driver_name",DES.encrypt(driver_name));
        params.addParameter("license_number",DES.encrypt(license_number));
        params.addParameter("phone_number",DES.encrypt(phone_number));
        params.addParameter("province","");
        params.addParameter("city","");
        params.addParameter("county","");
        params.addParameter("address","");
        params.addParameter("vehicle_type","");
        params.addParameter("token", LocalData.getToken());
        params.addParameter("sign",Authorization.SIGN);
        return x.http().post(params, callback);
    }

    /**
     * 删除司机
     * @param url
     * @param id 司机id
     * @param callback
     * @return
     */
    public Callback.Cancelable deleteDriver(String url,int id,Callback.CommonCallback<String> callback){
        RequestParams params = new RequestParams(url);
        params.addParameter("id",DES.encrypt("" + id));
        params.addParameter("token", LocalData.getToken());
        params.addParameter("sign",Authorization.SIGN);
        return x.http().post(params, callback);
    }

    /**
     * 获取常用司机
     * @param url
     * @param user_id 用户id
     * @param page 页数
     * @param callback
     * @return
     */
    public Callback.Cancelable getDriver(String url, int user_id, int page, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("user_id", DES.encrypt("" + user_id));
        params.addParameter("page",page);
        params.addParameter("rows",20);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("sign",Authorization.SIGN);
        return x.http().post(params, callback);
    }
}

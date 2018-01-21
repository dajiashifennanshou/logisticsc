package com.wrt.xinsilu.client;

import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.util.DES;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/8/4 0004.
 * 添加常用联系人---删除常用联系人---查找常用联系人
 */
public class AddCommonContactClient {
    /**
     * 添加常用联系人
     * @param url
     * @param user_id 用户id
     * @param contacts_name 姓名
     * @param phone_number 电话号码
     * @param telephone 座机
     * @param province 省
     * @param city 市
     * @param county 县
     * @param address 具体地址
     * @param company_name 公司名字
     * @param contacts_type 常用联系人类型 0：发货人 1：收货人
     * @param callback
     * @return
     */
    public Callback.Cancelable addCommonContact(String url,
                                                int user_id,
                                                String contacts_name,
                                                String phone_number,
//                                                String telephone,
//                                                String province,
//                                                String city,
//                                                String county,
                                                String address,
//                                                String company_name,
                                                String contacts_type,
                                                Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("user_id", DES.encrypt( ""+user_id));
        params.addParameter("contacts_name", DES.encrypt(contacts_name));
        params.addParameter("phone_number", DES.encrypt(phone_number));
        params.addParameter("telephone","");
        params.addParameter("province","");
        params.addParameter("city","");
        params.addParameter("county","");
        params.addParameter("address",DES.encrypt(address));
        params.addParameter("company_nam","");
        params.addParameter("contacts_type", DES.encrypt(contacts_type));
        params.addParameter("token", LocalData.getToken());
        params.addParameter("sign",Authorization.SIGN);
        return x.http().post(params, callback);
    }



}

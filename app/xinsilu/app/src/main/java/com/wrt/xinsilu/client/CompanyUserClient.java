package com.wrt.xinsilu.client;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.util.DES;
import com.xsl.lerist.llibrarys.client.LClient;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 申请企业货主
 * Created by Administrator on 2016/8/16 0016.
 */
public class CompanyUserClient extends LClient {
    /**
     * 申请企业货主
     * @param url
     * @param userId
     * @param company_name
     * @param company_addresst
     * @param legal_person
     * @param legal_mobile
     * @param contacts
     * @param contacts_mobile
     * @param company_code
     * @param fileName0
     * @param fileName1
     * @param fileName2
     * @param fileName3
     * @param fileName4
     * @param callback
     * @return
     */
    public Callback.Cancelable commit(String url,
                                      int userId,
                                      String company_name,
                                      String company_addresst,
                                      String legal_person,
                                      String legal_mobile,
                                      String contacts,
                                      String contacts_mobile,
                                      String company_code,
                                      String fileName0,
                                      String fileName1,
                                      String fileName2,
                                      String fileName3,
                                      String fileName4,
                                      Callback.CommonCallback<String> callback) {
        KLog.i("\n" + "company_name = " + company_name + "\n" + "company_addresst = " + company_addresst +
                "\n" + "legal_person = " + legal_person + "\n" + "legal_mobile = " + legal_mobile +
                "\n" +"contacts = " + contacts + "\n" + "contacts_mobile = " + contacts_mobile +
                "\n" +"company_code = " + company_code + "\n" + "fileName0 = " + fileName0 +
                "\n" +"fileName1 = " + fileName1 + "\n" + "fileName2 = " + fileName2 + "\n" + "fileName3 = " + fileName3 +
                "\n" + "fileName4 = " + fileName4);
        RequestParams params = new RequestParams(url);
        params.addParameter("userId",DES.encrypt(userId + ""));
        params.addParameter("company_name", DES.encrypt(company_name));
        params.addParameter("company_addresst", DES.encrypt(company_addresst));
        params.addParameter("legal_person", DES.encrypt(legal_person));
        params.addParameter("legal_mobile", DES.encrypt(legal_mobile));
        params.addParameter("contacts", DES.encrypt(contacts));
        params.addParameter("contacts_mobile", DES.encrypt(contacts_mobile));
        params.addParameter("company_code",DES.encrypt(company_code));
        try{
            params.addParameter("fileName0",new File(fileName0));
            params.addParameter("fileName1",new File(fileName1));
            params.addParameter("fileName2",new File(fileName2));
            params.addParameter("fileName3",new File(fileName3));
            params.addParameter("fileName4",new File(fileName4));
        }catch (Exception e){
            e.printStackTrace();
        }

        params.addParameter("sign",Authorization.SIGN);
        return x.http().post(params, callback);
    }

    /**
     * 修改基本信息
     * @param user_id
     * @param paramsHashMap
     * @param callback
     * @return
     */
    public Callback.Cancelable alterUserBasicInfo(int user_id,
                                   HashMap<String, Object> paramsHashMap,
                                   Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.UPDATE_USER);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("id", DES.encrypt(user_id + ""));
        for (String key : paramsHashMap.keySet()) {
            Object value = paramsHashMap.get(key);
            if (paramsHashMap.get(key) instanceof List) {
                value = JSON.toJSONString(paramsHashMap.get(key));
            }
            params.addParameter(key, DES.encrypt(value + ""));
        }
        KLog.i(params.toJSONString());
        return x.http().post(params, callback);
    }

    /**
     * 修改个人基本信息
     * @param id 用户id
     * @param true_name 真实姓名
     * @param address 详细地址
     * @param callback
     * @return
     */
    public Callback.Cancelable alterUserBasicInfo(int id,
                                                  String true_name,
                                                  String address,
                                                  Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.UPDATE_USER);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("id", DES.encrypt(id + ""));
        params.addParameter("true_name", DES.encrypt(true_name + ""));
        params.addParameter("address", DES.encrypt(address + ""));
        KLog.i(params.toJSONString());
        return x.http().post(params, callback);
    }

    /**
     * 修改公司信息
     * @param user_id
     * @param paramsHashMap
     * @param callback
     * @return
     */
    public Callback.Cancelable alterCompanyInfo(int user_id,
                                   HashMap<String, Object> paramsHashMap,
                                   Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.APPLY_ENTER_PRISE);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("user_id", DES.encrypt(user_id + ""));
        for (String key : paramsHashMap.keySet()) {
            Object value = paramsHashMap.get(key);
            if (paramsHashMap.get(key) instanceof List) {
                value = JSON.toJSONString(paramsHashMap.get(key));
            }
            params.addParameter(key, DES.encrypt(value + ""));
        }
        KLog.i(params.toJSONString());
        return x.http().post(params, callback);
    }

    /**
     * 查询公司信息
     * @param id 公司id
     * @param callback
     * @return
     */
    public Callback.Cancelable getCompanyInfo(int id,
                                                Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.APPLY_ENTER_PRISE);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("id", DES.encrypt(id + ""));
        KLog.i(params.toJSONString());
        return x.http().post(params, callback);
    }
}

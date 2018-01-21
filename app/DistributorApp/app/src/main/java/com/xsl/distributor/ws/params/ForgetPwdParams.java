package com.xsl.distributor.ws.params;

import com.xsl.distributor.lerist.client.Authorization;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.utils.DES;
import com.xsl.lerist.llibrarys.client.LClient;
import com.xsl.lerist.llibrarys.utils.EncryptUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by Administrator on 2016/7/19 0019.
 */
public class ForgetPwdParams extends LClient {
    /**
     * 修改密码
     *
     * @param callback
     * @return
     */
    public Callback.Cancelable update(String url, String mobile, String newPwd, String validCode, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("mobile", DES.encrypt(mobile));
        try {
            params.addParameter("password", EncryptUtil.MD5(newPwd));
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.addParameter("validCode", DES.encrypt(validCode));
        params.addParameter("sign", Authorization.SIGN);
        return x.http().post(params, callback);
    }
    /**
     * 发送验证码
     *
     * @param phoneOrEmail
     * @param callback
     * @return {
     * "code": 10000,
     * "data": "520134"
     * }
     */
    public Callback.Cancelable sendCode(String phoneOrEmail, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.SENDCODE_FORGETPWD);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("mobile", DES.encrypt(phoneOrEmail));
        return x.http().post(params, callback);
    }

}

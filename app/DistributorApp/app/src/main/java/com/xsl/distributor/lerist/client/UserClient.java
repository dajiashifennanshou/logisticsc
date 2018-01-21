package com.xsl.distributor.lerist.client;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.xsl.distributor.lerist.data.LocalData;
import com.xsl.distributor.lerist.model.ResultCode;
import com.xsl.distributor.lerist.model.UriConstants;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.distributor.lerist.utils.DES;
import com.xsl.lerist.llibrarys.client.LClient;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.EncryptUtil;
import com.xsl.lerist.llibrarys.widget.LToast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class UserClient extends LClient {

    /**
     * 注册
     *
     * @param phoneOrEmail
     * @param pwd
     * @param callback
     * @return {
     * "code": 10000
     * }
     */
    public Callback.Cancelable regist(String phoneOrEmail, String pwd, String validCode, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.REGISTER);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("validCode", DES.encrypt(validCode));
        try {
            params.addParameter("login_name", DES.encrypt(phoneOrEmail));
//            params.addParameter("login_name", EncryptUtil.encrypt(phoneOrEmail, EncryptUtil.getKEY()));
            params.addParameter("password", EncryptUtil.MD5(pwd));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x.http().post(params, callback);
    }

    public static void login(final Context context) {
        final LocalData localData = new LocalData(context);
        UserInfo userInfo = localData.readUserInfo();
        if (userInfo == null || userInfo.getUser() == null) {
            return;
        }

        new UserClient().login(userInfo.getUser().getLogin_name(), userInfo.getUser().getPassword(), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                KLog.i(result);
                ResultInfo resultInfo = ResultInfo.toResultInfo(result);
                if (resultInfo == null) {
                    return;
                }

                switch (resultInfo.getCode()) {
                    case ResultCode.SUCCEED:
                        //登录成功保存用户信息
                        UserInfo userInfo = JSON.parseObject(String.valueOf(resultInfo.getData()), UserInfo.class);
                        if (userInfo == null) {
                            return;
                        }
                        localData.saveUserInfo(userInfo);
                        new UserClient().registJPush(userInfo.getUser().getId(), JPushInterface.getRegistrationID(context), new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                KLog.i(result);
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {

                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                        break;
                    default:
                        LToast.show(context, "身份已过期, 请重新登录");
                        break;
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public Callback.Cancelable login(String phoneOrEmail, String pwd, Callback.CommonCallback<String> callback) {
        return login(DES.encrypt(phoneOrEmail), pwd, false, callback);
    }

    /**
     * 登录
     *
     * @param phoneOrEmail
     * @param pwd
     * @param callback
     * @return {
     * "code": 10000,
     * "data": {
     * "id": 8,
     * "phone": "13524954089",
     * "updateTime": "2016-07-05 11:29:26.0",
     * "updateUser": "feng",
     * "pwd": "123456"
     * }
     * }
     */
    public Callback.Cancelable login(String phoneOrEmail, String pwd, boolean isEncrypt, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.LOGIN);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        try {
            params.addParameter("login_name", isEncrypt ? DES.encrypt(phoneOrEmail) : phoneOrEmail);
//            params.addParameter("login_name", EncryptUtil.encrypt(phoneOrEmail, EncryptUtil.getKEY()));
            params.addParameter("password", isEncrypt ? EncryptUtil.MD5(pwd) : pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MobclickAgent.onProfileSignIn(phoneOrEmail);
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
        RequestParams params = new RequestParams(UriConstants.SENDCODE);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("login_name", DES.encrypt(phoneOrEmail));
        return x.http().post(params, callback);
    }

    public Callback.Cancelable registJPush(long userId, String regisId, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(UriConstants.REGISTER_JPUSH);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("userId", DES.encrypt(userId + ""));
        params.addParameter("regisId", DES.encrypt(regisId));
        return x.http().post(params, callback);
    }

}

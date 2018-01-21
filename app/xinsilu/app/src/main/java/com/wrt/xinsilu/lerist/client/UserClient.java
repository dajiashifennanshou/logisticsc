package com.wrt.xinsilu.lerist.client;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.umeng.analytics.MobclickAgent;
import com.wrt.xinsilu.bean.ResultCode;
import com.wrt.xinsilu.bean.UserInfo;
import com.wrt.xinsilu.client.Authorization;
import com.wrt.xinsilu.common.Common;
import com.wrt.xinsilu.data.LocalData;
import com.wrt.xinsilu.util.DES;
import com.xsl.lerist.llibrarys.client.LClient;
import com.xsl.lerist.llibrarys.model.ResultInfo;
import com.xsl.lerist.llibrarys.utils.EncryptUtil;
import com.xsl.lerist.llibrarys.utils.StringUtils;
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
        RequestParams params = new RequestParams(Common.REGISTER);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("validCode", validCode);
        try {
            params.addParameter("mobile", DES.encrypt(phoneOrEmail));
//            params.addParameter("login_name", EncryptUtil.encrypt(phoneOrEmail, EncryptUtil.getKEY()));
            params.addParameter("password", EncryptUtil.MD5(pwd));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x.http().post(params, callback);
    }

    public static void updateJPushId(final Context context) {
        LocalData localData = new LocalData(context);
        final UserInfo userInfo = localData.readUserInfo();
        if (userInfo == null) return;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String registrationID = JPushInterface.getRegistrationID(context);
                    KLog.i("registrationID: -----------" + registrationID + "");
                    if (!StringUtils.isEmpty(registrationID)) {
                        new UserClient().registJPush(userInfo.getUser().getId(), JPushInterface.getRegistrationID(context), new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                KLog.i(result);
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                ex.printStackTrace();
                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                        return;
                    }
                }
            }
        }).start();
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
                        updateJPushId(context);
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
        return login(phoneOrEmail, pwd, false, callback);
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
        RequestParams params = new RequestParams(Common.LOGIN);
        params.addParameter("sign", Authorization.SIGN);
        try {
            params.addParameter("login_name", DES.encrypt(phoneOrEmail));
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
        RequestParams params = new RequestParams(Common.GET_VALID_CODE);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("mobile", DES.encrypt(phoneOrEmail));
        return x.http().post(params, callback);
    }

    public Callback.Cancelable sendCode(String url, String phoneOrEmail, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(url);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("mobile", DES.encrypt(phoneOrEmail));
        return x.http().post(params, callback);
    }

    public Callback.Cancelable registJPush(long userId, String regisId, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.UPDATE_JPUSH);
        params.addParameter("sign", Authorization.SIGN);
        params.addParameter("token", LocalData.getToken());
        params.addParameter("userId", DES.encrypt(userId + ""));
        params.addParameter("regisId", DES.encrypt(regisId));
        return x.http().post(params, callback);
    }

    /**
     * 修改密码
     *
     * @param mobile
     * @param password
     * @param validCode
     * @return
     */
    public Callback.Cancelable updatePwd(String mobile, String password, String validCode, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.UPDATE_PWD);
        try {
            params.addParameter("password", EncryptUtil.MD5(password));
            params.addParameter("sign", Authorization.SIGN);
            params.addParameter("token", LocalData.getToken());
            params.addParameter("mobile", DES.encrypt(mobile));
            params.addParameter("validCode", DES.encrypt(validCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x.http().post(params, callback);
    }

    /**
     * 获取基本信息
     *
     * @param login_name 登录名，一般为手机号，登陆成功之后传回来的
     * @param callback
     * @return
     */
    public Callback.Cancelable getUserBasicInfo(String login_name, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.GET_USER);
        try {
            params.addParameter("sign", Authorization.SIGN);
            params.addParameter("token", LocalData.getToken());
            params.addParameter("login_name", DES.encrypt(login_name));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x.http().post(params, callback);
    }

    /**
     * 获取公司信息
     *
     * @param companyId
     * @param callback
     * @return
     */
    public Callback.Cancelable getUserCompany(long companyId, Callback.CommonCallback<String> callback) {
        RequestParams params = new RequestParams(Common.GET_USER_COMPANY);
        try {
            params.addParameter("sign", Authorization.SIGN);
            params.addParameter("token", LocalData.getToken());
            params.addParameter("id", DES.encrypt(companyId + ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x.http().post(params, callback);
    }
}

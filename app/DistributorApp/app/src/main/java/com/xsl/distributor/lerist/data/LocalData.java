package com.xsl.distributor.lerist.data;

import android.content.Context;

import com.socks.library.KLog;
import com.xsl.distributor.lerist.AppApplication;
import com.xsl.distributor.lerist.model.UserInfo;
import com.xsl.lerist.llibrarys.data.LLocalData;
import com.xsl.lerist.llibrarys.utils.Base64;
import com.xsl.lerist.llibrarys.utils.StringUtils;

/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class LocalData extends LLocalData {
    public LocalData(Context context) {
        super(context);
    }

    public static String getToken() {
        UserInfo userInfo = new LocalData(AppApplication.getInstance()).readUserInfo();
        if (userInfo != null) {
            return userInfo.getToken();
        }
        return null;
    }

    /**
     * 保存用户信息
     *
     * @param userInfo
     */
    public void saveUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            KLog.e("loginBean is null!");
            return;
        }
        if (userInfo.getUser() == null
                || StringUtils.isExistEmpty(userInfo.getUser().getLogin_name(), userInfo.getUser().getPassword())) {
            KLog.e("Save failure, loginBean exist empty!");
            return;
        }

        byte[] encodeSrc = Base64.encode(userInfo.toString().getBytes());
        if (encodeSrc == null) return;

        String encode = new String(encodeSrc);

        if (encode != null)
            put(UserInfo.class.getSimpleName(), encode);
    }

    /**
     * 读取用户信息
     *
     * @return
     */
    public UserInfo readUserInfo() {
        byte[] decodeSrc = Base64.decode(get(UserInfo.class.getSimpleName()));
        if (decodeSrc == null) return null;

        String decode = new String(decodeSrc);
        if (decode == null)
            return null;

        return UserInfo.toUserInfo(decode);
    }

    public void removeUserInfo() {
        remove(UserInfo.class.getSimpleName());
    }

    /**
     * 是否已登录
     *
     * @return
     */
    public boolean isLogined() {
        UserInfo userBean = readUserInfo();
        if (userBean != null && userBean.getUser() != null) {
            if (StringUtils.isExistEmpty(userBean.getUser().getLogin_name(), userBean.getUser().getPassword())) {
                KLog.e(" isLogined(): loginInfo exist empty!");
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}

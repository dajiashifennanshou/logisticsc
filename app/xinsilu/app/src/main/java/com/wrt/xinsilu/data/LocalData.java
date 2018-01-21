package com.wrt.xinsilu.data;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;
import com.wrt.xinsilu.MyApplication;
import com.wrt.xinsilu.bean.UserInfo;
import com.xsl.lerist.llibrarys.data.LLocalData;
import com.xsl.lerist.llibrarys.utils.Base64;
import com.xsl.lerist.llibrarys.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lerist on 2016/7/12, 0012.
 */

public class LocalData extends LLocalData {
    public LocalData(Context context) {
        super(context);
    }

    public static String getToken() {
        UserInfo userInfo = new LocalData(MyApplication.getInstance()).readUserInfo();
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
        if (StringUtils.isExistEmpty(userInfo.getUser().getLogin_name(), userInfo.getUser().getPassword())) {
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
        if (userBean != null) {
            if (StringUtils.isExistEmpty(userBean.getUser().getLogin_name(), userBean.getUser().getPassword())) {
                KLog.e(" isLogined(): loginInfo exist empty!");
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 保存历史
     *
     * @param logistic_name
     */
    public void putHistory(String logistic_name) {
        String str = get("history_logistics");
        List<String> list = str == null ? null : JSON.parseArray(str, String.class);
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals(logistic_name)) {
                list.remove(i);
                i--;
            }
        }
        list.add(logistic_name);
        put("history_logistics", JSON.toJSONString(list));
    }

    /**
     * 获取搜索的历史纪录
     *
     * @return
     */
    public List<String> getHistory() {
        String str = get("history_logistics");
        List<String> list = str == null ? null : JSON.parseArray(str, String.class);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    /**
     * 删除全部历史纪录
     */
    public void deleteAllHistory() {
        remove("history_logistics");
    }

    /**
     * 删除单个记录
     *
     * @param str
     */
    public void deleteHistory(String str) {
        List<String> historys = getHistory();
        for (int i = 0; i < historys.size(); i++) {
            if (historys.get(i).equals(str)) {
                historys.remove(i);
                remove("history_logistics");
                put("history_logistics", JSON.toJSONString(historys));
                return;
            }
        }
    }

}

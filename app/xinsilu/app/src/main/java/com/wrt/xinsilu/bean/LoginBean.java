package com.wrt.xinsilu.bean;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2016/6/28 0028.
 * 登陆上传字段
 */
public class LoginBean extends BasicBean {
    /**
     * 登陆名
     */
    private String login_name;
    /**
     * 密码
     */
    private String password;

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static LoginBean toLoginBean(String loginBeanStr) {
        try {
            LoginBean loginBean = JSON.parseObject(loginBeanStr, LoginBean.class);
            return loginBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.wrt.xinsilu.bean;

import com.alibaba.fastjson.JSON;

/**
 * Created by Lerist on 2016/7/7, 0007.
 */

public class UserInfo {


    /**
     * id : 215
     * login_name : 18408224311
     * password : 343b1c4a3ea721b2d640fc8700db0f36
     * mobile : 18408224311
     * user_type : 12
     * status : 1
     * create_time : 2016-08-03 16:20:10.0
     */

    private UserBean user;
    /**
     * user : {"id":215,"login_name":"18408224311","password":"343b1c4a3ea721b2d640fc8700db0f36","mobile":"18408224311","user_type":12,"status":1,"create_time":"2016-08-03 16:20:10.0"}
     * token : df8d6b7604094a09954a59ee31ae2cf2
     */

    private String token;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static UserInfo toUserInfo(String userInfoStr) {
        try {
            UserInfo userInfo = JSON.parseObject(userInfoStr, UserInfo.class);
            return userInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserBean {
        private int id;
        private String login_name;
        private String password;
        private String mobile;
        private int user_type;
        private int status;
        private String create_time;
        private long company_id;
        private long temporary_company_id;

        public long getTemporary_company_id() {
            return temporary_company_id;
        }

        public void setTemporary_company_id(long temporary_company_id) {
            this.temporary_company_id = temporary_company_id;
        }

        public long getCompany_id() {
            return company_id;
        }

        public void setCompany_id(long company_id) {
            this.company_id = company_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}

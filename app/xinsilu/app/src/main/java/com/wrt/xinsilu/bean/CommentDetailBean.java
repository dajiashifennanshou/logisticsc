package com.wrt.xinsilu.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/2 0002.
 * 评价详情类
 */
public class CommentDetailBean {

    public static class Wrapper{
        private ArrayList<CommentDetailBean> objectList;

        public ArrayList<CommentDetailBean> getObjectList() {
            return objectList;
        }

        public void setObjectList(ArrayList<CommentDetailBean> objectList) {
            this.objectList = objectList;
        }
    }
    /**
     * id : 10
     * order_number : 1471578394570
     * evaluate_content : 很好啊
     * evaluate_time : 1471592257000
     * evaluate_level : 4
     * user_id : 222
     * state : 1
     * user : {"id":222,"login_name":"13882669571","mobile":"13882669571","address":"天府二街","true_name":"牛魔王","user_type":2,"company_id":16,"create_time":"2016-08-18 10:15:18.0"}
     */

    private int id;
    private String order_number;
    private String evaluate_content;
    private String evaluate_time;
    private int evaluate_level;
    private int user_id;
    private int state;
    /**
     * id : 222
     * login_name : 13882669571
     * mobile : 13882669571
     * address : 天府二街
     * true_name : 牛魔王
     * user_type : 2
     * company_id : 16
     * create_time : 2016-08-18 10:15:18.0
     */

    private UserBean user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getEvaluate_content() {
        return evaluate_content;
    }

    public void setEvaluate_content(String evaluate_content) {
        this.evaluate_content = evaluate_content;
    }

    public String getEvaluate_time() {
        return evaluate_time;
    }

    public void setEvaluate_time(String evaluate_time) {
        this.evaluate_time = evaluate_time;
    }

    public int getEvaluate_level() {
        return evaluate_level;
    }

    public void setEvaluate_level(int evaluate_level) {
        this.evaluate_level = evaluate_level;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        private int id;
        private String login_name;
        private String mobile;
        private String address;
        private String true_name;
        private int user_type;
        private int company_id;
        private String create_time;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}

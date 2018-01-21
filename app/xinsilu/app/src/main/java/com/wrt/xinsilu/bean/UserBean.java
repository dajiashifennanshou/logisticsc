package com.wrt.xinsilu.bean;

/**
 * Created by Administrator on 2016/8/11 0011.
 * 获取个人信息
 */
public class UserBean {

    /**
     * id : 13
     * login_name : 13882669571
     * password : e10adc3949ba59abbe56e057f20f883e
     * mobile : 13882669571
     * email : 850226953@qq.com
     * address :
     * true_name : 罗静
     * user_type : 12
     * status : 1
     * company_id : 3
     */

    private int id;
    private String login_name;
    private String password;
    private String mobile;
    private String email;
    private String address;
    private String true_name;
    private int user_type;
    private int status;
    private int company_id;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}

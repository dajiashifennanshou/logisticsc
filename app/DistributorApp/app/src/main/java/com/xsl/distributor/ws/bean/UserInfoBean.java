package com.xsl.distributor.ws.bean;

/**
 * Created by Administrator on 2016/7/11 0011.
 * 账号信息
 */
public class UserInfoBean {
    /**公司名*/
    private String company_name;
    /**仓库地址*/
    private String address;
    /**有效时间*/
    private String time;
    /**绑定电话号码*/
    private String bind_phone_number;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getBind_phone_number() {
        return bind_phone_number;
    }

    public void setBind_phone_number(String bind_phone_number) {
        this.bind_phone_number = bind_phone_number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

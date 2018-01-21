package com.wrt.xinsilu.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2 0002.
 * 企业用户上传的照片信息
 */
public class CompanyImageBean {
    /**营业执照照片*/
    private String lis_code;
    /**公司照片*/
    private String company_img;
    /**法人身份证照片*/
    private String user_id_card;
    /**名片照片*/
    private String card_img;
    /**公司logo*/
    private String company_logo;

    public String getLis_code() {
        return lis_code;
    }

    public void setLis_code(String lis_code) {
        this.lis_code = lis_code;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getCard_img() {
        return card_img;
    }

    public void setCard_img(String card_img) {
        this.card_img = card_img;
    }

    public String getCompany_img() {
        return company_img;
    }

    public void setCompany_img(String company_img) {
        this.company_img = company_img;
    }

    public String getUser_id_card() {
        return user_id_card;
    }

    public void setUser_id_card(String user_id_card) {
        this.user_id_card = user_id_card;
    }
}

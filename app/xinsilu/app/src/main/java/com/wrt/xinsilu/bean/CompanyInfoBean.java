package com.wrt.xinsilu.bean;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class CompanyInfoBean {
    /**公司信息进去第一个数据，不做传递使用*/
    String text_first;
    /**公司信息进去第一个数据，做传递使用*/
    String company_info;
    /**hint*/
    String hint;

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getText_first() {
        return text_first;
    }

    public void setText_first(String text_first) {
        this.text_first = text_first;
    }

    public String getCompany_info() {
        return company_info;
    }

    public void setCompany_info(String company_info) {
        this.company_info = company_info;
    }
}

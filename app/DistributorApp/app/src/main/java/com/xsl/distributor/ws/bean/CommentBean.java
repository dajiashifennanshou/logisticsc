package com.xsl.distributor.ws.bean;

/**
 * Created by Administrator on 2016/7/11 0011.
 * 评论详情
 */
public class CommentBean  {
    /**头像*/
    private String url;
    /**评价人用户名*/
    private String user_name;
    /**评论时间*/
    private String date;
    /**打分*/
    private String star;
    /**评论详情*/
    private String comment_detail;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment_detail() {
        return comment_detail;
    }

    public void setComment_detail(String comment_detail) {
        this.comment_detail = comment_detail;
    }
}

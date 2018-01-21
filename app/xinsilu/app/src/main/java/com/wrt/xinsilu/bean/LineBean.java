package com.wrt.xinsilu.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28 0028.
 * 一个物流商的路线
 */
public class LineBean {
    /**线路起点*/
    private String from_way;
    /**线路终点*/
    private String to_way;
    /**时效*/
    private String time;
    /**重货*/
    private String weight_goods;
    /**服务类型*/
    private String service_type;
    /**泡货*/
    private String foam_goods;
    /**运输方式*/
    private String transprot_way;
    /**最低价*/
    private String lower_price;
    /**路线评价信息*/
    private List<LineAppraiseBean>list;
    /**是否下架*/
    private String is_sale;
    /**承运多少票*/
    private String transport_num;
    /**评论数量*/
    private String comment_num;
    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getTransport_num() {
        return transport_num;
    }

    public void setTransport_num(String transport_num) {
        this.transport_num = transport_num;
    }

    public String getIs_sale() {
        return is_sale;
    }

    public void setIs_sale(String is_sale) {
        this.is_sale = is_sale;
    }

    public String getFrom_way() {
        return from_way;
    }

    public void setFrom_way(String from_way) {
        this.from_way = from_way;
    }

    public List<LineAppraiseBean> getList() {
        return list;
    }

    public void setList(List<LineAppraiseBean> list) {
        this.list = list;
    }

    public String getLower_price() {
        return lower_price;
    }

    public void setLower_price(String lower_price) {
        this.lower_price = lower_price;
    }

    public String getTransprot_way() {
        return transprot_way;
    }

    public void setTransprot_way(String transprot_way) {
        this.transprot_way = transprot_way;
    }

    public String getFoam_goods() {
        return foam_goods;
    }

    public void setFoam_goods(String foam_goods) {
        this.foam_goods = foam_goods;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getWeight_goods() {
        return weight_goods;
    }

    public void setWeight_goods(String weight_goods) {
        this.weight_goods = weight_goods;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTo_way() {
        return to_way;
    }

    public void setTo_way(String to_way) {
        this.to_way = to_way;
    }
}

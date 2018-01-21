package com.xsl.distributor.ws.basic;

/**
 * Created by Administrator on 2016/7/14 0014.
 * 传递的基本的数据
 */
public class BasicBean {
    /**
     * 经销商id
     */
    private String dealerId;
    /**
     * 当前页
     */
    private String page;
    /**
     * 每页显示条数
     */
    private String rows;
    /**
     * 签名
     */
    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }
}

package com.xsl.distributor.ws.bean;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class BankBean {

    /**
     * headName : 中国人民银行
     */

    private String headName;

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    @Override
    public String toString() {
        return headName;
    }
}

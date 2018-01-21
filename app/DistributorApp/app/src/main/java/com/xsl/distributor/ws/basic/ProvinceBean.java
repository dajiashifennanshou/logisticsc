package com.xsl.distributor.ws.basic;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class ProvinceBean {

    /**
     * provinceName : 北京
     */

    private String provinceName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return provinceName;
    }
}

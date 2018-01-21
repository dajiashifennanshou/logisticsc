package com.xsl.distributor.ws.basic;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class CityBean {

    /**
     * cityName : 浙江
     */

    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return cityName;
    }
}

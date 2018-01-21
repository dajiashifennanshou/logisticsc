package com.wrt.xinsilu.bean;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
public class PlaceBean {
    /**省*/
    private String province;
    /**市*/
    private String city;
    /**县/区*/
    private String district;
    /**纬度*/
    private double latitude;
    /**经度*/
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}

package com.wrt.xinsilu.bean;

/**
 * Created by Administrator on 2016/6/29 0029.
 * 常用司机
 */
public class DriverBean {

    /**
     * id : 13
     * driver_name : 家具
     * license_number : 12345678912
     * phone_number : ghjgghjg
     * vehicle_type :
     * address :
     * user_id : 62
     * province :
     * city :
     * county :
     */

    private int id;
    private String driver_name;
    private String license_number;
    private String phone_number;
    private String vehicle_type;
    private String address;
    private int user_id;
    private String province;
    private String city;
    private String county;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}



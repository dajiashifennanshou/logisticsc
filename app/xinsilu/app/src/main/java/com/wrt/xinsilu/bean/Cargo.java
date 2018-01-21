package com.wrt.xinsilu.bean;

/**
 * Created by Administrator on 2016/6/28 0028.
 * 货物信息
 */
public class Cargo {
    /**
     * 货物名称
     */
    public String name;
    /**
     * 品牌名称
     */
    public String brand;
    /**
     * 货物型号
     */
    public String model;
    /**
     * 货物件数
     */
    public String number;
    /**
     * 货物套数
     */
    public String set_number;
    /**
     * 货物重量
     */
    public double single_weight;
    /**
     * 货物体积
     */
    public double single_volume;
    /**
     * 类型
     */
    public String package_type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSet_number() {
        return set_number;
    }

    public void setSet_number(String set_number) {
        this.set_number = set_number;
    }

    public double getSingle_weight() {
        return single_weight;
    }

    public void setSingle_weight(double single_weight) {
        this.single_weight = single_weight;
    }

    public double getSingle_volume() {
        return single_volume;
    }

    public void setSingle_volume(double single_volume) {
        this.single_volume = single_volume;
    }

    public String getPackage_type() {
        return package_type;
    }

    public void setPackage_type(String package_type) {
        this.package_type = package_type;
    }
}

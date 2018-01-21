package com.xsl.distributor.ws.bean;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class GoodsInfoBean {
    /**货物名称*/
    private String name;
    /**货物品牌*/
    private String brands;
    /**货物型号*/
    private String modle;
    /**货物件数*/
    private String num;
    /**套数*/
    private String set;
    /**货物重量*/
    private String weight;
    /**体积*/
    private String volume;
    /**包装信息*/
    private String info;
    /**价格*/
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getModle() {
        return modle;
    }

    public void setModle(String modle) {
        this.modle = modle;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

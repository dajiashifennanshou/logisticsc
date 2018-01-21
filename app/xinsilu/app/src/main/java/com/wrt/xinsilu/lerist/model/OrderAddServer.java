package com.wrt.xinsilu.lerist.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by Lerist on 2016/8/18, 0018.
 */

public class OrderAddServer {
    /**
     * is_im_ex_store			进出仓
     * is_load_cargo	Integer		是否装货
     * load_cargo_floor	Integer		装货楼层
     * load_cargo_is_elevator	Integer		是否电梯
     * is_unload_cargo	Integer		是否卸货
     * unload_cargo_floor	Integer		卸货楼层
     * is_collection_delivery	Integer		是否代收款
     * collection_delivery_money	double		代收金额
     * unload_cargo_is_elevator	Integer		卸货是否有电梯
     */

    private int is_im_ex_store;
    private int is_load_cargo;
    private int load_cargo_floor;
    private int load_cargo_is_elevator;
    private int is_unload_cargo;
    private int unload_cargo_floor;
    private int is_collection_delivery;
    private double collection_delivery_money;
    private int unload_cargo_is_elevator;

    public double getCollection_delivery_money() {
        return collection_delivery_money;
    }

    public int getIs_im_ex_store() {
        return is_im_ex_store;
    }

    public void setIs_im_ex_store(int is_im_ex_store) {
        this.is_im_ex_store = is_im_ex_store;
    }

    public int getIs_load_cargo() {
        return is_load_cargo;
    }

    public void setIs_load_cargo(int is_load_cargo) {
        this.is_load_cargo = is_load_cargo;
    }

    public void setCollection_delivery_money(double collection_delivery_money) {
        this.collection_delivery_money = collection_delivery_money;
    }

    public int getIs_unload_cargo() {
        return is_unload_cargo;
    }

    public void setIs_unload_cargo(int is_unload_cargo) {
        this.is_unload_cargo = is_unload_cargo;
    }

    public int getLoad_cargo_floor() {
        return load_cargo_floor;
    }

    public void setLoad_cargo_floor(int load_cargo_floor) {
        this.load_cargo_floor = load_cargo_floor;
    }

    public int getLoad_cargo_is_elevator() {
        return load_cargo_is_elevator;
    }

    public void setLoad_cargo_is_elevator(int load_cargo_is_elevator) {
        this.load_cargo_is_elevator = load_cargo_is_elevator;
    }

    public int getUnload_cargo_floor() {
        return unload_cargo_floor;
    }

    public void setUnload_cargo_floor(int unload_cargo_floor) {
        this.unload_cargo_floor = unload_cargo_floor;
    }

    public int getIs_collection_delivery() {
        return is_collection_delivery;
    }

    public void setIs_collection_delivery(int is_collection_delivery) {
        this.is_collection_delivery = is_collection_delivery;
    }

    public int getUnload_cargo_is_elevator() {
        return unload_cargo_is_elevator;
    }

    public void setUnload_cargo_is_elevator(int unload_cargo_is_elevator) {
        this.unload_cargo_is_elevator = unload_cargo_is_elevator;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

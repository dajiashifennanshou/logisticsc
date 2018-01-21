package com.xsl.distributor.lerist.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lerist on 2016/7/9, 0009.
 */

public class CustomerInfo implements Serializable{


    /**
     * id : 6
     * dealerId : 27
     * type : 0
     * clientName :
     * phone :
     * address :
     * list : [{"deliveryNo":"PS-2016071511224215"},{"deliveryNo":"PS-2016071511231486"}]
     */

    private int id;
    private int dealerId;
    private int type;
    private String clientName;
    private String phone;
    private String address;
    /**
     * deliveryNo : PS-2016071511224215
     */

    private List<ListBean> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable{
        private String deliveryNo;

        public String getDeliveryNo() {
            return deliveryNo;
        }

        public void setDeliveryNo(String deliveryNo) {
            this.deliveryNo = deliveryNo;
        }
    }
}

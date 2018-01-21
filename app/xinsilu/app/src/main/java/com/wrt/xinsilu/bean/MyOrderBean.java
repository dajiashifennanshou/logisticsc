package com.wrt.xinsilu.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 * 我的订单
 */
public class MyOrderBean implements Serializable{

    public static class MyOrderBeanWraper{
        private List<MyOrderBean> objectList = new ArrayList<>();

        public List<MyOrderBean> getObjectList() {
            return objectList;
        }

        public void setObjectList(List<MyOrderBean> objectList) {
            this.objectList = objectList;
        }
    }
    /**
     * id : 34
     * order_number : 5652838160805007
     * consignor_name : 风
     * consignor_province : 四川省
     * consignor_city : 成都市
     * consignor_county : 武侯区
     * consignor_address : 诺伊佩拉
     * consignor_phone_number : 13524954089
     * consignor_telephone :
     * driver_name :
     * driver_phone :
     * vehicle_number :
     * vehicle_type : 7
     * consignee_name : 小累
     * consignee_province : 重庆市
     * consignee_city : 市辖区
     * consignee_county : 涪陵区
     * consignee_address : 诺斯玛尔
     * consignee_phone_number : 13524954089
     * consignee_telephone :
     * send_cargo_type : 0
     * delivery_start_time :
     * delivery_end_time :
     * receive_cargo_type : 0
     * receipt_type : 0
     * receipt_title : 0
     * receipt_company_name :
     * pay_type : 1
     * paid_price : 0
     * final_price : 0
     * state : 0
     * order_time : 2016-08-05 09:45:38.0
     * user_id : 12
     * tms_line_id : 1
     * is_draft : 1
     * is_wait_pay : 0
     * estimate_weight : 15
     * estimate_volume : 11
     * estimate_freight : 0.15
     * is_confirm : 0
     * take_cargo_cost : 0
     * estimate_total : 0.15
     * is_payment : 1
     * com : {"id":1,"company_name":"中工储运物流","logo":"upload/company/login/3/14/ec693d61-4422-429f-ad4a-41f1407d6fbc.jpg"}
     */

    private int id;
    private String order_number;
    private String consignor_name;
    private String consignor_province;
    private String consignor_city;
    private String consignor_county;
    private String consignor_address;
    private String consignor_phone_number;
    private String consignor_telephone;
    private String driver_name;
    private String driver_phone;
    private String vehicle_number;
    private int vehicle_type;
    private String consignee_name;
    private String consignee_province;
    private String consignee_city;
    private String consignee_county;
    private String consignee_address;
    private String consignee_phone_number;
    private String consignee_telephone;
    private int send_cargo_type;
    private String delivery_start_time;
    private String delivery_end_time;
    private int receive_cargo_type;
    private int receipt_type;
    private int receipt_title;
    private String receipt_company_name;
    private int pay_type;
    private int paid_price;
    private int final_price;
    private int state;
    private String order_time;
    private int user_id;
    private int tms_line_id;
    private int is_draft;
    private int is_wait_pay;
    private int estimate_weight;
    private int estimate_volume;
    private double estimate_freight;
    private int is_confirm;
    private double take_cargo_cost;
    private double estimate_total;
    private int is_payment;
    /**
     * id : 1
     * company_name : 中工储运物流
     * logo : upload/company/login/3/14/ec693d61-4422-429f-ad4a-41f1407d6fbc.jpg
     */

    private ComBean com;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getConsignor_name() {
        return consignor_name;
    }

    public void setConsignor_name(String consignor_name) {
        this.consignor_name = consignor_name;
    }

    public String getConsignor_province() {
        return consignor_province;
    }

    public void setConsignor_province(String consignor_province) {
        this.consignor_province = consignor_province;
    }

    public String getConsignor_city() {
        return consignor_city;
    }

    public void setConsignor_city(String consignor_city) {
        this.consignor_city = consignor_city;
    }

    public String getConsignor_county() {
        return consignor_county;
    }

    public void setConsignor_county(String consignor_county) {
        this.consignor_county = consignor_county;
    }

    public String getConsignor_address() {
        return consignor_address;
    }

    public void setConsignor_address(String consignor_address) {
        this.consignor_address = consignor_address;
    }

    public String getConsignor_phone_number() {
        return consignor_phone_number;
    }

    public void setConsignor_phone_number(String consignor_phone_number) {
        this.consignor_phone_number = consignor_phone_number;
    }

    public String getConsignor_telephone() {
        return consignor_telephone;
    }

    public void setConsignor_telephone(String consignor_telephone) {
        this.consignor_telephone = consignor_telephone;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDriver_phone() {
        return driver_phone;
    }

    public void setDriver_phone(String driver_phone) {
        this.driver_phone = driver_phone;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public int getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(int vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getConsignee_name() {
        return consignee_name;
    }

    public void setConsignee_name(String consignee_name) {
        this.consignee_name = consignee_name;
    }

    public String getConsignee_province() {
        return consignee_province;
    }

    public void setConsignee_province(String consignee_province) {
        this.consignee_province = consignee_province;
    }

    public String getConsignee_city() {
        return consignee_city;
    }

    public void setConsignee_city(String consignee_city) {
        this.consignee_city = consignee_city;
    }

    public String getConsignee_county() {
        return consignee_county;
    }

    public void setConsignee_county(String consignee_county) {
        this.consignee_county = consignee_county;
    }

    public String getConsignee_address() {
        return consignee_address;
    }

    public void setConsignee_address(String consignee_address) {
        this.consignee_address = consignee_address;
    }

    public String getConsignee_phone_number() {
        return consignee_phone_number;
    }

    public void setConsignee_phone_number(String consignee_phone_number) {
        this.consignee_phone_number = consignee_phone_number;
    }

    public String getConsignee_telephone() {
        return consignee_telephone;
    }

    public void setConsignee_telephone(String consignee_telephone) {
        this.consignee_telephone = consignee_telephone;
    }

    public int getSend_cargo_type() {
        return send_cargo_type;
    }

    public void setSend_cargo_type(int send_cargo_type) {
        this.send_cargo_type = send_cargo_type;
    }

    public String getDelivery_start_time() {
        return delivery_start_time;
    }

    public void setDelivery_start_time(String delivery_start_time) {
        this.delivery_start_time = delivery_start_time;
    }

    public String getDelivery_end_time() {
        return delivery_end_time;
    }

    public void setDelivery_end_time(String delivery_end_time) {
        this.delivery_end_time = delivery_end_time;
    }

    public int getReceive_cargo_type() {
        return receive_cargo_type;
    }

    public void setReceive_cargo_type(int receive_cargo_type) {
        this.receive_cargo_type = receive_cargo_type;
    }

    public int getReceipt_type() {
        return receipt_type;
    }

    public void setReceipt_type(int receipt_type) {
        this.receipt_type = receipt_type;
    }

    public int getReceipt_title() {
        return receipt_title;
    }

    public void setReceipt_title(int receipt_title) {
        this.receipt_title = receipt_title;
    }

    public String getReceipt_company_name() {
        return receipt_company_name;
    }

    public void setReceipt_company_name(String receipt_company_name) {
        this.receipt_company_name = receipt_company_name;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public int getPaid_price() {
        return paid_price;
    }

    public void setPaid_price(int paid_price) {
        this.paid_price = paid_price;
    }

    public int getFinal_price() {
        return final_price;
    }

    public void setFinal_price(int final_price) {
        this.final_price = final_price;
    }

    public int getState() {
        return state;
    }

    public String getStateStr() {
        String stateStr =
                new String[]{"预约", "已受理", "提货中", "议价", "货物入库", "运输中", "已到达", "送货中", "已签收", "已拒绝", "已作废", "已取消"}[state];
        return stateStr;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTms_line_id() {
        return tms_line_id;
    }

    public void setTms_line_id(int tms_line_id) {
        this.tms_line_id = tms_line_id;
    }

    public int getIs_draft() {
        return is_draft;
    }

    public void setIs_draft(int is_draft) {
        this.is_draft = is_draft;
    }

    public int getIs_wait_pay() {
        return is_wait_pay;
    }

    public void setIs_wait_pay(int is_wait_pay) {
        this.is_wait_pay = is_wait_pay;
    }

    public int getEstimate_weight() {
        return estimate_weight;
    }

    public void setEstimate_weight(int estimate_weight) {
        this.estimate_weight = estimate_weight;
    }

    public int getEstimate_volume() {
        return estimate_volume;
    }

    public void setEstimate_volume(int estimate_volume) {
        this.estimate_volume = estimate_volume;
    }

    public double getEstimate_freight() {
        return estimate_freight;
    }

    public void setEstimate_freight(double estimate_freight) {
        this.estimate_freight = estimate_freight;
    }

    public int getIs_confirm() {
        return is_confirm;
    }

    public void setIs_confirm(int is_confirm) {
        this.is_confirm = is_confirm;
    }

    public double getTake_cargo_cost() {
        return take_cargo_cost;
    }

    public void setTake_cargo_cost(double take_cargo_cost) {
        this.take_cargo_cost = take_cargo_cost;
    }

    public double getEstimate_total() {
        return estimate_total;
    }

    public void setEstimate_total(double estimate_total) {
        this.estimate_total = estimate_total;
    }

    public int getIs_payment() {
        return is_payment;
    }

    public void setIs_payment(int is_payment) {
        this.is_payment = is_payment;
    }

    public ComBean getCom() {
        return com;
    }

    public void setCom(ComBean com) {
        this.com = com;
    }

    public static class ComBean implements Serializable{
        private int id;
        private String company_name;
        private String logo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}

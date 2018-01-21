package com.wrt.xinsilu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9 0009.
 * 物流商订单详情
 */
public class LogisticOrderDetailBean implements Serializable{


    /**
     * id : 63
     * order_number : 1472038625714
     * way_bill_number : 1417701160824015
     * consignor_name : 土豪必备
     * consignor_province : 四川省
     * consignor_city : 成都市
     * consignor_county : 锦江区
     * consignor_address : 给你你你
     * consignor_phone_number : 15886253639
     * driver_name : 黄文辉
     * driver_phone : 18764089388
     * vehicle_number : 川AF530K
     * vehicle_type : 7
     * consignee_name : 更健康
     * consignee_province : 江苏省
     * consignee_city : 南京市
     * consignee_county : 玄武区
     * consignee_address : 何必呢
     * consignee_phone_number : 13697994946
     * send_cargo_type : 1
     * receive_cargo_type : 1
     * is_insurance : 0
     * insurance_money : 16.0
     * pay_type : 0
     * total_weight : 1.0
     * total_volume : 1.0
     * total_worth : 700.0
     * state : 3
     * order_time : 2016-08-24 19:37:05.0
     * user_id : 131
     * tms_line_id : 25
     * is_draft : 1
     * estimate_weight : 1.0
     * estimate_volume : 1.0
     * estimate_freight : 550.0
     * take_cargo_cost : 0.01
     * estimate_total : 578.21
     * is_payment : 0
     * is_evaluation : 1
     * cargoLst : [{"id":106,"name":"沙发","brand":"纸鹤","model":"688","package_type":42,"number":5,"set_number":1,"single_weight":1,"single_volume":1,"count_cost_mode":0,"price":0.01,"order_id":63,"package_name":"木箱"}]
     * payment : {"state":1}
     * startLineAddr : 四川省双流县成都市成新浦快速通道金桥段锦和草莓园院内
     * endLineAddr : 江苏省浦口区南京市泰冯路20号
     * total_cost : 0.02
     */

    private int id;
    private String order_number;
    private String way_bill_number;
    private String consignor_name;
    private String consignor_province;
    private String consignor_city;
    private String consignor_county;
    private String consignor_address;
    private String consignor_phone_number;
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
    private int send_cargo_type;
    private int receive_cargo_type;
    private int is_insurance;
    private double insurance_money;
    private int pay_type;
    private double total_weight;
    private double total_volume;
    private double total_worth;
    private int state;
    private String order_time;
    private int user_id;
    private int tms_line_id;
    private int is_draft;
    private double estimate_weight;
    private double estimate_volume;
    private double estimate_freight;
    private double take_cargo_cost;
    private double estimate_total;
    private int is_payment;
    private int is_evaluation;
    /**
     * state : 1
     */

    private PaymentBean payment;
    private String startLineAddr;
    private String endLineAddr;
    private double total_cost;
    /**
     * id : 106
     * name : 沙发
     * brand : 纸鹤
     * model : 688
     * package_type : 42
     * number : 5
     * set_number : 1
     * single_weight : 1.0
     * single_volume : 1.0
     * count_cost_mode : 0
     * price : 0.01
     * order_id : 63
     * package_name : 木箱
     */

    private List<CargoLstBean> cargoLst;

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

    public String getWay_bill_number() {
        return way_bill_number;
    }

    public void setWay_bill_number(String way_bill_number) {
        this.way_bill_number = way_bill_number;
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

    public int getSend_cargo_type() {
        return send_cargo_type;
    }

    public void setSend_cargo_type(int send_cargo_type) {
        this.send_cargo_type = send_cargo_type;
    }

    public int getReceive_cargo_type() {
        return receive_cargo_type;
    }

    public void setReceive_cargo_type(int receive_cargo_type) {
        this.receive_cargo_type = receive_cargo_type;
    }

    public int getIs_insurance() {
        return is_insurance;
    }

    public void setIs_insurance(int is_insurance) {
        this.is_insurance = is_insurance;
    }

    public double getInsurance_money() {
        return insurance_money;
    }

    public void setInsurance_money(double insurance_money) {
        this.insurance_money = insurance_money;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public double getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(double total_weight) {
        this.total_weight = total_weight;
    }

    public double getTotal_volume() {
        return total_volume;
    }

    public void setTotal_volume(double total_volume) {
        this.total_volume = total_volume;
    }

    public double getTotal_worth() {
        return total_worth;
    }

    public void setTotal_worth(double total_worth) {
        this.total_worth = total_worth;
    }

    public int getState() {
        return state;
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

    public double getEstimate_weight() {
        return estimate_weight;
    }

    public void setEstimate_weight(double estimate_weight) {
        this.estimate_weight = estimate_weight;
    }

    public double getEstimate_volume() {
        return estimate_volume;
    }

    public void setEstimate_volume(double estimate_volume) {
        this.estimate_volume = estimate_volume;
    }

    public double getEstimate_freight() {
        return estimate_freight;
    }

    public void setEstimate_freight(double estimate_freight) {
        this.estimate_freight = estimate_freight;
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

    public int getIs_evaluation() {
        return is_evaluation;
    }

    public void setIs_evaluation(int is_evaluation) {
        this.is_evaluation = is_evaluation;
    }

    public PaymentBean getPayment() {
        return payment;
    }

    public void setPayment(PaymentBean payment) {
        this.payment = payment;
    }

    public String getStartLineAddr() {
        return startLineAddr;
    }

    public void setStartLineAddr(String startLineAddr) {
        this.startLineAddr = startLineAddr;
    }

    public String getEndLineAddr() {
        return endLineAddr;
    }

    public void setEndLineAddr(String endLineAddr) {
        this.endLineAddr = endLineAddr;
    }

    public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }

    public List<CargoLstBean> getCargoLst() {
        return cargoLst;
    }

    public void setCargoLst(List<CargoLstBean> cargoLst) {
        this.cargoLst = cargoLst;
    }

    public static class PaymentBean {
        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

    public static class CargoLstBean {
        private int id;
        private String name;
        private String brand;
        private String model;
        private int package_type;
        private int number;
        private int set_number;
        private double single_weight;
        private double single_volume;
        private int count_cost_mode;
        private double price;
        private int order_id;
        private String package_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public int getPackage_type() {
            return package_type;
        }

        public void setPackage_type(int package_type) {
            this.package_type = package_type;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getSet_number() {
            return set_number;
        }

        public void setSet_number(int set_number) {
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

        public int getCount_cost_mode() {
            return count_cost_mode;
        }

        public void setCount_cost_mode(int count_cost_mode) {
            this.count_cost_mode = count_cost_mode;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }
    }
}

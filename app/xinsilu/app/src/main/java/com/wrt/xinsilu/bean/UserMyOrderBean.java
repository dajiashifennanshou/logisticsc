package com.wrt.xinsilu.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18 0018.
 * 首页我的订单里面显示的类容
 */
public class UserMyOrderBean {

    /**
     * pageIndex : 1
     * pageSize : 10
     * objectList : [{"id":144,"order_number":"1471509010581","consignor_name":"家里","consignor_province":"四川省","consignor_city":"成都市","consignor_county":"市辖区","consignor_address":"古古怪怪给","consignor_phone_number":"1311111111","consignor_telephone":"","driver_name":"fgw","driver_phone":"55555555","vehicle_number":"穿Z77777","vehicle_type":7,"consignee_name":"啊","consignee_province":"贵州省","consignee_city":"毕节市","consignee_county":"市辖区","consignee_address":"很好很好","consignee_phone_number":"17555555555","consignee_telephone":"","send_cargo_type":0,"receive_cargo_type":0,"is_insurance":0,"insurance_money":55,"pay_type":0,"total_weight":1,"total_volume":1,"total_worth":0.03,"state":0,"order_time":"2016-08-18 16:30:10.0","user_id":62,"tms_line_id":3,"is_draft":1,"estimate_weight":0,"estimate_volume":0,"estimate_freight":0,"take_cargo_cost":0,"estimate_total":1,"is_payment":1,"com":{"id":3,"company_name":"顺风物流服务有限公司","logo":"upload/company/login/6/3/ae319476-0579-44ed-805a-930ef4d3f458.jpg"}}]
     * limitMin : 0
     * limitMax : 10
     */

    private int pageIndex;
    private int pageSize;
    private int limitMin;
    private int limitMax;
    /**
     * id : 144
     * order_number : 1471509010581
     * consignor_name : 家里
     * consignor_province : 四川省
     * consignor_city : 成都市
     * consignor_county : 市辖区
     * consignor_address : 古古怪怪给
     * consignor_phone_number : 1311111111
     * consignor_telephone :
     * driver_name : fgw
     * driver_phone : 55555555
     * vehicle_number : 穿Z77777
     * vehicle_type : 7
     * consignee_name : 啊
     * consignee_province : 贵州省
     * consignee_city : 毕节市
     * consignee_county : 市辖区
     * consignee_address : 很好很好
     * consignee_phone_number : 17555555555
     * consignee_telephone :
     * send_cargo_type : 0
     * receive_cargo_type : 0
     * is_insurance : 0
     * insurance_money : 55.0
     * pay_type : 0
     * total_weight : 1.0
     * total_volume : 1.0
     * total_worth : 0.03
     * state : 0
     * order_time : 2016-08-18 16:30:10.0
     * user_id : 62
     * tms_line_id : 3
     * is_draft : 1
     * estimate_weight : 0.0
     * estimate_volume : 0.0
     * estimate_freight : 0.0
     * take_cargo_cost : 0.0
     * estimate_total : 1.0
     * is_payment : 1
     * com : {"id":3,"company_name":"顺风物流服务有限公司","logo":"upload/company/login/6/3/ae319476-0579-44ed-805a-930ef4d3f458.jpg"}
     */

    private List<ObjectListBean> objectList;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getLimitMin() {
        return limitMin;
    }

    public void setLimitMin(int limitMin) {
        this.limitMin = limitMin;
    }

    public int getLimitMax() {
        return limitMax;
    }

    public void setLimitMax(int limitMax) {
        this.limitMax = limitMax;
    }

    public List<ObjectListBean> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<ObjectListBean> objectList) {
        this.objectList = objectList;
    }

    public static class ObjectListBean {
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
        private double total_cost;
        /**
         * id : 3
         * company_name : 顺风物流服务有限公司
         * logo : upload/company/login/6/3/ae319476-0579-44ed-805a-930ef4d3f458.jpg
         */

        private ComBean com;

        public double getTotal_cost() {
            return total_cost;
        }

        public void setTotal_cost(double total_cost) {
            this.total_cost = total_cost;
        }

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

        public ComBean getCom() {
            return com;
        }

        public void setCom(ComBean com) {
            this.com = com;
        }

        public static class ComBean {
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
}

package com.xsl.distributor.ws.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/7/12 0012.
 * s
 */
public class LogisticDetailBean implements Parcelable{


    /**
     * id : 3
     * server_type : 90
     * start_outlets : 7
     * start_outlets_name : 远征物流
     * end_outlets : 6
     * end_outlets_name : 金鑫物流
     * transport_mode : 5
     * time_long : 72
     * heavy_cargo_price_low : 0.01
     * bulky_cargo_price_low : 0.02
     * heavy_cargo_price_mid : 0.02
     * bulky_cargo_price_mid : 0.03
     * heavy_cargo_price_high : 0.03
     * bulky_cargo_price_high : 0.04
     * lowest_price : 0.01
     * status : 1
     * release_state : 1
     * outlets_id : 7
     * evaluate_level : 4
     * outlets_number : 02
     * name : 远征物流
     * type : 2
     * nature : 4
     * province : 510000
     * city : 510100
     * county : 510107
     * address : 成都市武侯区
     * contact_person : 罗静
     * mobile :
     * phone : 13882669571
     * email :
     * remark :
     * create_time : 2016-06-06 16:50:28.0
     * create_person_id : 9
     * company_id : 3
     * company_name : 顺风物流服务有限公司
     * company_address : 成都市双流县大井村九江仓储
     * legal_person : 出错
     * legal_mobile : 15982453579
     * contacts : 陈亮
     * contacts_mobile : 15982453579
     * qq : 850226953
     * post_code : 638000
     * company_phone : 028-00022211
     * company_fax : 028-12312233
     * company_tax_no : 3534523454
     * finance_email : 850226953@qq.com
     * company_info :
     * logo : upload/company/login/6/3/ae319476-0579-44ed-805a-930ef4d3f458.jpg
     * business_license : upload/company/businessLicense/8/1/8f886165-e7cd-495f-9ac6-1c0f174d2a12.jpg
     * company_photo : upload/company/companyPhoto/11/13/8be8a4cc-2dd9-472f-9697-e89f1d215542.jpg
     * legal_photo : upload/company/legalPhoto/2/11/590ac3ed-41c3-48c5-9d83-b682f1cb274e.jpg
     * card_photo : upload/company/cardPhoto/12/3/87d99508-cf49-43a9-a342-8d09f5344a32.jpg
     * company_code : 95949
     */

    private ColBean col;
    /**
     * id : 1
     * is_receipt : 1
     * order_id : 1
     */

    private ServerBean server;
    /**
     * id : 1
     * order_number : 5695949160708001
     * consignor_name : 测试1
     * consignor_province : 510000
     * consignor_city : 510100
     * consignor_county : 510107
     * consignor_address : 测试地址
     * consignor_phone_number : 17012341234
     * consignor_telephone :
     * driver_name :
     * driver_phone :
     * vehicle_number :
     * vehicle_type : 7
     * consignee_name : 测试2
     * consignee_province : 440000
     * consignee_city : 440100
     * consignee_county : 440111
     * consignee_address : 测试地址
     * consignee_phone_number : 13511112222
     * consignee_telephone :
     * send_cargo_type : 1
     * delivery_date : 2016-07-08 00:00:00.0
     * delivery_start_time : 10:00
     * delivery_end_time : 11:00
     * receive_cargo_type : 0
     * is_insurance : 1
     * receipt_type : 0
     * receipt_title : 0
     * receipt_company_name :
     * pay_type : 0
     * paid_price : 100
     * final_price : 200
     * state : 0
     * order_time : 2016-07-08 10:30:49.0
     * user_id : 13
     * tms_line_id : 3
     * is_draft : 0
     * is_wait_pay : 0
     * estimate_weight : 1
     * estimate_volume : 1
     * estimate_freight : 0.04
     * is_confirm : 0
     * take_cargo_cost : 0.1
     * estimate_total : 0.12
     * is_payment : 1
     */

    private OrderBean order;
    /**
     * id : 1
     * name : 测试
     * brand : 测试
     * model : 测试
     * package_type : 41
     * number : 1
     * set_number : 1
     * single_weight : 1
     * single_volume : 1
     * count_cost_mode : 1
     * order_id : 1
     */

    private List<CargoBean> cargo;

    public LogisticDetailBean() {
    }

    protected LogisticDetailBean(Parcel in) {
    }

    public static final Creator<LogisticDetailBean> CREATOR = new Creator<LogisticDetailBean>() {
        @Override
        public LogisticDetailBean createFromParcel(Parcel in) {
            return new LogisticDetailBean(in);
        }

        @Override
        public LogisticDetailBean[] newArray(int size) {
            return new LogisticDetailBean[size];
        }
    };

    public ColBean getCol() {
        return col;
    }

    public void setCol(ColBean col) {
        this.col = col;
    }

    public ServerBean getServer() {
        return server;
    }

    public void setServer(ServerBean server) {
        this.server = server;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<CargoBean> getCargo() {
        return cargo;
    }

    public void setCargo(List<CargoBean> cargo) {
        this.cargo = cargo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static class ColBean {
        private int id;
        private String server_type;
        private int start_outlets;
        private String start_outlets_name;
        private int end_outlets;
        private String end_outlets_name;
        private int transport_mode;
        private int time_long;
        private double heavy_cargo_price_low;
        private double bulky_cargo_price_low;
        private double heavy_cargo_price_mid;
        private double bulky_cargo_price_mid;
        private double heavy_cargo_price_high;
        private double bulky_cargo_price_high;
        private double lowest_price;
        private int status;
        private int release_state;
        private int outlets_id;
        private int evaluate_level;
        private String outlets_number;
        private String name;
        private int type;
        private int nature;
        private String province;
        private String city;
        private String county;
        private String address;
        private String contact_person;
        private String mobile;
        private String phone;
        private String email;
        private String remark;
        private String create_time;
        private int create_person_id;
        private int company_id;
        private String company_name;
        private String company_address;
        private String legal_person;
        private String legal_mobile;
        private String contacts;
        private String contacts_mobile;
        private String qq;
        private String post_code;
        private String company_phone;
        private String company_fax;
        private String company_tax_no;
        private String finance_email;
        private String company_info;
        private String logo;
        private String business_license;
        private String company_photo;
        private String legal_photo;
        private String card_photo;
        private String company_code;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getServer_type() {
            return server_type;
        }

        public void setServer_type(String server_type) {
            this.server_type = server_type;
        }

        public int getStart_outlets() {
            return start_outlets;
        }

        public void setStart_outlets(int start_outlets) {
            this.start_outlets = start_outlets;
        }

        public String getStart_outlets_name() {
            return start_outlets_name;
        }

        public void setStart_outlets_name(String start_outlets_name) {
            this.start_outlets_name = start_outlets_name;
        }

        public int getEnd_outlets() {
            return end_outlets;
        }

        public void setEnd_outlets(int end_outlets) {
            this.end_outlets = end_outlets;
        }

        public String getEnd_outlets_name() {
            return end_outlets_name;
        }

        public void setEnd_outlets_name(String end_outlets_name) {
            this.end_outlets_name = end_outlets_name;
        }

        public int getTransport_mode() {
            return transport_mode;
        }

        public void setTransport_mode(int transport_mode) {
            this.transport_mode = transport_mode;
        }

        public int getTime_long() {
            return time_long;
        }

        public void setTime_long(int time_long) {
            this.time_long = time_long;
        }

        public double getHeavy_cargo_price_low() {
            return heavy_cargo_price_low;
        }

        public void setHeavy_cargo_price_low(double heavy_cargo_price_low) {
            this.heavy_cargo_price_low = heavy_cargo_price_low;
        }

        public double getBulky_cargo_price_low() {
            return bulky_cargo_price_low;
        }

        public void setBulky_cargo_price_low(double bulky_cargo_price_low) {
            this.bulky_cargo_price_low = bulky_cargo_price_low;
        }

        public double getHeavy_cargo_price_mid() {
            return heavy_cargo_price_mid;
        }

        public void setHeavy_cargo_price_mid(double heavy_cargo_price_mid) {
            this.heavy_cargo_price_mid = heavy_cargo_price_mid;
        }

        public double getBulky_cargo_price_mid() {
            return bulky_cargo_price_mid;
        }

        public void setBulky_cargo_price_mid(double bulky_cargo_price_mid) {
            this.bulky_cargo_price_mid = bulky_cargo_price_mid;
        }

        public double getHeavy_cargo_price_high() {
            return heavy_cargo_price_high;
        }

        public void setHeavy_cargo_price_high(double heavy_cargo_price_high) {
            this.heavy_cargo_price_high = heavy_cargo_price_high;
        }

        public double getBulky_cargo_price_high() {
            return bulky_cargo_price_high;
        }

        public void setBulky_cargo_price_high(double bulky_cargo_price_high) {
            this.bulky_cargo_price_high = bulky_cargo_price_high;
        }

        public double getLowest_price() {
            return lowest_price;
        }

        public void setLowest_price(double lowest_price) {
            this.lowest_price = lowest_price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getRelease_state() {
            return release_state;
        }

        public void setRelease_state(int release_state) {
            this.release_state = release_state;
        }

        public int getOutlets_id() {
            return outlets_id;
        }

        public void setOutlets_id(int outlets_id) {
            this.outlets_id = outlets_id;
        }

        public int getEvaluate_level() {
            return evaluate_level;
        }

        public void setEvaluate_level(int evaluate_level) {
            this.evaluate_level = evaluate_level;
        }

        public String getOutlets_number() {
            return outlets_number;
        }

        public void setOutlets_number(String outlets_number) {
            this.outlets_number = outlets_number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getNature() {
            return nature;
        }

        public void setNature(int nature) {
            this.nature = nature;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContact_person() {
            return contact_person;
        }

        public void setContact_person(String contact_person) {
            this.contact_person = contact_person;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getCreate_person_id() {
            return create_person_id;
        }

        public void setCreate_person_id(int create_person_id) {
            this.create_person_id = create_person_id;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getCompany_address() {
            return company_address;
        }

        public void setCompany_address(String company_address) {
            this.company_address = company_address;
        }

        public String getLegal_person() {
            return legal_person;
        }

        public void setLegal_person(String legal_person) {
            this.legal_person = legal_person;
        }

        public String getLegal_mobile() {
            return legal_mobile;
        }

        public void setLegal_mobile(String legal_mobile) {
            this.legal_mobile = legal_mobile;
        }

        public String getContacts() {
            return contacts;
        }

        public void setContacts(String contacts) {
            this.contacts = contacts;
        }

        public String getContacts_mobile() {
            return contacts_mobile;
        }

        public void setContacts_mobile(String contacts_mobile) {
            this.contacts_mobile = contacts_mobile;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getPost_code() {
            return post_code;
        }

        public void setPost_code(String post_code) {
            this.post_code = post_code;
        }

        public String getCompany_phone() {
            return company_phone;
        }

        public void setCompany_phone(String company_phone) {
            this.company_phone = company_phone;
        }

        public String getCompany_fax() {
            return company_fax;
        }

        public void setCompany_fax(String company_fax) {
            this.company_fax = company_fax;
        }

        public String getCompany_tax_no() {
            return company_tax_no;
        }

        public void setCompany_tax_no(String company_tax_no) {
            this.company_tax_no = company_tax_no;
        }

        public String getFinance_email() {
            return finance_email;
        }

        public void setFinance_email(String finance_email) {
            this.finance_email = finance_email;
        }

        public String getCompany_info() {
            return company_info;
        }

        public void setCompany_info(String company_info) {
            this.company_info = company_info;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getBusiness_license() {
            return business_license;
        }

        public void setBusiness_license(String business_license) {
            this.business_license = business_license;
        }

        public String getCompany_photo() {
            return company_photo;
        }

        public void setCompany_photo(String company_photo) {
            this.company_photo = company_photo;
        }

        public String getLegal_photo() {
            return legal_photo;
        }

        public void setLegal_photo(String legal_photo) {
            this.legal_photo = legal_photo;
        }

        public String getCard_photo() {
            return card_photo;
        }

        public void setCard_photo(String card_photo) {
            this.card_photo = card_photo;
        }

        public String getCompany_code() {
            return company_code;
        }

        public void setCompany_code(String company_code) {
            this.company_code = company_code;
        }
    }

    public static class ServerBean {
        private int id;
        private int is_receipt;
        private int order_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_receipt() {
            return is_receipt;
        }

        public void setIs_receipt(int is_receipt) {
            this.is_receipt = is_receipt;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

    }

    public static class OrderBean {
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
        private String delivery_date;
        private String delivery_start_time;
        private String delivery_end_time;
        private int receive_cargo_type;
        private int is_insurance;
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

        public String getDelivery_date() {
            return delivery_date;
        }

        public void setDelivery_date(String delivery_date) {
            this.delivery_date = delivery_date;
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

        public int getIs_insurance() {
            return is_insurance;
        }

        public void setIs_insurance(int is_insurance) {
            this.is_insurance = is_insurance;
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
    }

    public static class CargoBean {
        private int id;
        private String name;
        private String brand;
        private String model;
        private int package_type;
        private int number;
        private int set_number;
        private int single_weight;
        private int single_volume;
        private int count_cost_mode;
        private int order_id;

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

        public int getSingle_weight() {
            return single_weight;
        }

        public void setSingle_weight(int single_weight) {
            this.single_weight = single_weight;
        }

        public int getSingle_volume() {
            return single_volume;
        }

        public void setSingle_volume(int single_volume) {
            this.single_volume = single_volume;
        }

        public int getCount_cost_mode() {
            return count_cost_mode;
        }

        public void setCount_cost_mode(int count_cost_mode) {
            this.count_cost_mode = count_cost_mode;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }
    }
}

package com.brightsoft.entity;

import java.math.BigInteger;

public class LcPlatformOrder {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String order_number;
	private String way_bill_number;
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
	private BigInteger vehicle_type;
	private String consignee_name;
	private String consignee_province;
	private String consignee_city;
	private String consignee_county;
	private String consignee_address;
	private String consignee_phone_number;
	private String consignee_telephone;
	private BigInteger send_cargo_type;
	private String delivery_date;
	private String delivery_start_time;
	private String delivery_end_time;
	private BigInteger receive_cargo_type;
	private Integer is_insurance;
	private Double insurance_money;
	private Integer receipt_type;
	private Integer receipt_title;
	private String receipt_company_name;
	private Integer pay_type;
	private Double total_weight;
	private Double total_volume;
	private Double total_worth;
	private Double advance_price;
	private Double paid_price;
	private Double final_price;
	private Integer state;
	private String order_time;
	private BigInteger user_id;
	private BigInteger tms_line_id;
	private Integer is_delete;
	private Integer is_draft;
	private Integer is_wait_pay;
	private Double estimate_weight;
	private Double estimate_volume;
	private Double estimate_freight;
	private Integer is_confirm;
	private Double take_cargo_cost;
	private Double estimate_total;
	private Integer is_payment;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
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
	public BigInteger getVehicle_type() {
		return vehicle_type;
	}
	public void setVehicle_type(BigInteger vehicle_type) {
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
	public BigInteger getSend_cargo_type() {
		return send_cargo_type;
	}
	public void setSend_cargo_type(BigInteger send_cargo_type) {
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
	public BigInteger getReceive_cargo_type() {
		return receive_cargo_type;
	}
	public void setReceive_cargo_type(BigInteger receive_cargo_type) {
		this.receive_cargo_type = receive_cargo_type;
	}
	public Integer getIs_insurance() {
		return is_insurance;
	}
	public void setIs_insurance(Integer is_insurance) {
		this.is_insurance = is_insurance;
	}
	public Double getInsurance_money() {
		return insurance_money;
	}
	public void setInsurance_money(Double insurance_money) {
		this.insurance_money = insurance_money;
	}
	public Integer getReceipt_type() {
		return receipt_type;
	}
	public void setReceipt_type(Integer receipt_type) {
		this.receipt_type = receipt_type;
	}
	public Integer getReceipt_title() {
		return receipt_title;
	}
	public void setReceipt_title(Integer receipt_title) {
		this.receipt_title = receipt_title;
	}
	public String getReceipt_company_name() {
		return receipt_company_name;
	}
	public void setReceipt_company_name(String receipt_company_name) {
		this.receipt_company_name = receipt_company_name;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	public Double getTotal_weight() {
		return total_weight;
	}
	public void setTotal_weight(Double total_weight) {
		this.total_weight = total_weight;
	}
	public Double getTotal_volume() {
		return total_volume;
	}
	public void setTotal_volume(Double total_volume) {
		this.total_volume = total_volume;
	}
	public Double getTotal_worth() {
		return total_worth;
	}
	public void setTotal_worth(Double total_worth) {
		this.total_worth = total_worth;
	}
	public Double getAdvance_price() {
		return advance_price;
	}
	public void setAdvance_price(Double advance_price) {
		this.advance_price = advance_price;
	}
	public Double getPaid_price() {
		return paid_price;
	}
	public void setPaid_price(Double paid_price) {
		this.paid_price = paid_price;
	}
	public Double getFinal_price() {
		return final_price;
	}
	public void setFinal_price(Double final_price) {
		this.final_price = final_price;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	public BigInteger getUser_id() {
		return user_id;
	}
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	public BigInteger getTms_line_id() {
		return tms_line_id;
	}
	public void setTms_line_id(BigInteger tms_line_id) {
		this.tms_line_id = tms_line_id;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	public Integer getIs_draft() {
		return is_draft;
	}
	public void setIs_draft(Integer is_draft) {
		this.is_draft = is_draft;
	}
	public Integer getIs_wait_pay() {
		return is_wait_pay;
	}
	public void setIs_wait_pay(Integer is_wait_pay) {
		this.is_wait_pay = is_wait_pay;
	}
	public Double getEstimate_weight() {
		return estimate_weight;
	}
	public void setEstimate_weight(Double estimate_weight) {
		this.estimate_weight = estimate_weight;
	}
	public Double getEstimate_volume() {
		return estimate_volume;
	}
	public void setEstimate_volume(Double estimate_volume) {
		this.estimate_volume = estimate_volume;
	}
	public Double getEstimate_freight() {
		return estimate_freight;
	}
	public void setEstimate_freight(Double estimate_freight) {
		this.estimate_freight = estimate_freight;
	}
	public Integer getIs_confirm() {
		return is_confirm;
	}
	public void setIs_confirm(Integer is_confirm) {
		this.is_confirm = is_confirm;
	}
	public Double getTake_cargo_cost() {
		return take_cargo_cost;
	}
	public void setTake_cargo_cost(Double take_cargo_cost) {
		this.take_cargo_cost = take_cargo_cost;
	}
	public Double getEstimate_total() {
		return estimate_total;
	}
	public void setEstimate_total(Double estimate_total) {
		this.estimate_total = estimate_total;
	}
	public Integer getIs_payment() {
		return is_payment;
	}
	public void setIs_payment(Integer is_payment) {
		this.is_payment = is_payment;
	}
	
}

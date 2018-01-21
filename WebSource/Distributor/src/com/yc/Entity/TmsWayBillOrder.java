package com.yc.Entity; 

import java.io.Serializable;
import java.math.*;

public class TmsWayBillOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String order_number;
	private String way_bill_number;
	private BigInteger start_outlets;
	private String start_outlets_name;
	private BigInteger target_outlets;
	private String target_outlets_name;
	private String target_province;
	private String target_city;
	private String target_county;
	private String consignor;
	private String consignor_company;
	private String consignor_mobile;
	private String consignor_address;
	private String consignee;
	private String consignee_company;
	private String consignee_mobile;
	private String consignee_address;
	private Integer send_type;
	private Integer receive_type;
	private Integer receipt_slip;
	private Integer receipt_slip_num;
	private Integer receipt_status;
	private String city_driver_name;
	private String city_driver_phone;
	private String city_vehicle_number;
	private String pay_method;
	private Double total;
	private Integer status;
	private Integer sign_status;
	private String way_bill_order_time;
	private BigInteger way_bill_order_user;
	private String remark;
	private Double agency_fund;
	private Double agency_fund_poundage;
	private Double insurance_money;
	private Double insurance;
	private Double take_cargo_cost;
	private Double load_unload_cost;
	private Double transfer_cost;
	private Double other_cost;
	private Double freight;
	private Double advance_cost;
	private Double immediate_pay;
	private Double arrive_pay;
	private Double back_pay;
	private Integer is_wait_pay;
	private Integer exception_status;

	private Integer is_payment;//支付状态
	public Integer getIs_payment() {
		return is_payment;
	}
	public void setIs_payment(Integer is_payment) {
		this.is_payment = is_payment;
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setOrder_number(String order_number){
		this.order_number=order_number;
	}
	public String getOrder_number(){
		return order_number;
	}
	public void setWay_bill_number(String way_bill_number){
		this.way_bill_number=way_bill_number;
	}
	public String getWay_bill_number(){
		return way_bill_number;
	}
	public void setStart_outlets(BigInteger start_outlets){
		this.start_outlets=start_outlets;
	}
	public BigInteger getStart_outlets(){
		return start_outlets;
	}
	public void setStart_outlets_name(String start_outlets_name){
		this.start_outlets_name=start_outlets_name;
	}
	public String getStart_outlets_name(){
		return start_outlets_name;
	}
	public void setTarget_outlets(BigInteger target_outlets){
		this.target_outlets=target_outlets;
	}
	public BigInteger getTarget_outlets(){
		return target_outlets;
	}
	public void setTarget_outlets_name(String target_outlets_name){
		this.target_outlets_name=target_outlets_name;
	}
	public String getTarget_outlets_name(){
		return target_outlets_name;
	}
	public void setTarget_province(String target_province){
		this.target_province=target_province;
	}
	public String getTarget_province(){
		return target_province;
	}
	public void setTarget_city(String target_city){
		this.target_city=target_city;
	}
	public String getTarget_city(){
		return target_city;
	}
	public void setTarget_county(String target_county){
		this.target_county=target_county;
	}
	public String getTarget_county(){
		return target_county;
	}
	public void setConsignor(String consignor){
		this.consignor=consignor;
	}
	public String getConsignor(){
		return consignor;
	}
	public void setConsignor_company(String consignor_company){
		this.consignor_company=consignor_company;
	}
	public String getConsignor_company(){
		return consignor_company;
	}
	public void setConsignor_mobile(String consignor_mobile){
		this.consignor_mobile=consignor_mobile;
	}
	public String getConsignor_mobile(){
		return consignor_mobile;
	}
	public void setConsignor_address(String consignor_address){
		this.consignor_address=consignor_address;
	}
	public String getConsignor_address(){
		return consignor_address;
	}
	public void setConsignee(String consignee){
		this.consignee=consignee;
	}
	public String getConsignee(){
		return consignee;
	}
	public void setConsignee_company(String consignee_company){
		this.consignee_company=consignee_company;
	}
	public String getConsignee_company(){
		return consignee_company;
	}
	public void setConsignee_mobile(String consignee_mobile){
		this.consignee_mobile=consignee_mobile;
	}
	public String getConsignee_mobile(){
		return consignee_mobile;
	}
	public void setConsignee_address(String consignee_address){
		this.consignee_address=consignee_address;
	}
	public String getConsignee_address(){
		return consignee_address;
	}
	public void setSend_type(Integer send_type){
		this.send_type=send_type;
	}
	public Integer getSend_type(){
		return send_type;
	}
	public void setReceive_type(Integer receive_type){
		this.receive_type=receive_type;
	}
	public Integer getReceive_type(){
		return receive_type;
	}
	public void setReceipt_slip(Integer receipt_slip){
		this.receipt_slip=receipt_slip;
	}
	public Integer getReceipt_slip(){
		return receipt_slip;
	}
	public void setReceipt_slip_num(Integer receipt_slip_num){
		this.receipt_slip_num=receipt_slip_num;
	}
	public Integer getReceipt_slip_num(){
		return receipt_slip_num;
	}
	public void setReceipt_status(Integer receipt_status){
		this.receipt_status=receipt_status;
	}
	public Integer getReceipt_status(){
		return receipt_status;
	}
	public void setCity_driver_name(String city_driver_name){
		this.city_driver_name=city_driver_name;
	}
	public String getCity_driver_name(){
		return city_driver_name;
	}
	public void setCity_driver_phone(String city_driver_phone){
		this.city_driver_phone=city_driver_phone;
	}
	public String getCity_driver_phone(){
		return city_driver_phone;
	}
	public void setCity_vehicle_number(String city_vehicle_number){
		this.city_vehicle_number=city_vehicle_number;
	}
	public String getCity_vehicle_number(){
		return city_vehicle_number;
	}
	public void setPay_method(String pay_method){
		this.pay_method=pay_method;
	}
	public String getPay_method(){
		return pay_method;
	}
	public void setTotal(Double total){
		this.total=total;
	}
	public Double getTotal(){
		return total;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setSign_status(Integer sign_status){
		this.sign_status=sign_status;
	}
	public Integer getSign_status(){
		return sign_status;
	}
	public void setWay_bill_order_time(String way_bill_order_time){
		this.way_bill_order_time=way_bill_order_time;
	}
	public String getWay_bill_order_time(){
		return way_bill_order_time;
	}
	public void setWay_bill_order_user(BigInteger way_bill_order_user){
		this.way_bill_order_user=way_bill_order_user;
	}
	public BigInteger getWay_bill_order_user(){
		return way_bill_order_user;
	}
	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}
	public void setAgency_fund(Double agency_fund){
		this.agency_fund=agency_fund;
	}
	public Double getAgency_fund(){
		return agency_fund;
	}
	public void setAgency_fund_poundage(Double agency_fund_poundage){
		this.agency_fund_poundage=agency_fund_poundage;
	}
	public Double getAgency_fund_poundage(){
		return agency_fund_poundage;
	}
	public void setInsurance_money(Double insurance_money){
		this.insurance_money=insurance_money;
	}
	public Double getInsurance_money(){
		return insurance_money;
	}
	public void setInsurance(Double insurance){
		this.insurance=insurance;
	}
	public Double getInsurance(){
		return insurance;
	}
	public void setTake_cargo_cost(Double take_cargo_cost){
		this.take_cargo_cost=take_cargo_cost;
	}
	public Double getTake_cargo_cost(){
		return take_cargo_cost;
	}
	public void setLoad_unload_cost(Double load_unload_cost){
		this.load_unload_cost=load_unload_cost;
	}
	public Double getLoad_unload_cost(){
		return load_unload_cost;
	}
	public void setTransfer_cost(Double transfer_cost){
		this.transfer_cost=transfer_cost;
	}
	public Double getTransfer_cost(){
		return transfer_cost;
	}
	public void setOther_cost(Double other_cost){
		this.other_cost=other_cost;
	}
	public Double getOther_cost(){
		return other_cost;
	}
	public void setFreight(Double freight){
		this.freight=freight;
	}
	public Double getFreight(){
		return freight;
	}
	public void setAdvance_cost(Double advance_cost){
		this.advance_cost=advance_cost;
	}
	public Double getAdvance_cost(){
		return advance_cost;
	}
	public void setImmediate_pay(Double immediate_pay){
		this.immediate_pay=immediate_pay;
	}
	public Double getImmediate_pay(){
		return immediate_pay;
	}
	public void setArrive_pay(Double arrive_pay){
		this.arrive_pay=arrive_pay;
	}
	public Double getArrive_pay(){
		return arrive_pay;
	}
	public void setBack_pay(Double back_pay){
		this.back_pay=back_pay;
	}
	public Double getBack_pay(){
		return back_pay;
	}
	public void setIs_wait_pay(Integer is_wait_pay){
		this.is_wait_pay=is_wait_pay;
	}
	public Integer getIs_wait_pay(){
		return is_wait_pay;
	}
	public void setException_status(Integer exception_status){
		this.exception_status=exception_status;
	}
	public Integer getException_status(){
		return exception_status;
	}
}


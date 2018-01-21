package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import java.math.BigInteger;


/** LcPlatformOrderBill
	ID    BIGINT(20)
	ORDER_NUMBER    VARCHAR(20)
	FREIGHT    DOUBLE(22,31)
	AGENCY_FUND_POUNDAGE    DOUBLE(22,31)
	LOAD_UNLOAD_CHARGE    DOUBLE(22,31)
	ADDED_SERVICE_CHARGE    DOUBLE(22,31)
	INSURANCE    DOUBLE(22,31)
	TOTAL_COST    DOUBLE(22,31)
	PREPAID_COST    DOUBLE(11)
	OTHER_COST    DOUBLE(11)
	PAY_DATE    DATETIME(19)
	STATE    INT(11)
	START_OUTLETS    VARCHAR(50)
	END_OUTLETS    VARCHAR(50)
	COMPANY_NAME    VARCHAR(50)
	CREATE_TIME    DATETIME(19)
	TAKE_CARGO_COST    DOUBLE(22,31)
	SEND_CARGO_COST    DOUBLE(22,31)
	LOAD_CARGO_COST    DOUBLE(22,31)
	UNLOAD_CARGO_COST    DOUBLE(22,31)
	ESTIMATE_TAKE_CARGO_COST    DOUBLE(22,31)
	ESTIMATE_SEND_CARGO_COST    DOUBLE(22,31)
	ESTIMATE_LOAD_CARGO_COST    DOUBLE(22,31)
	ESTIMATE_UNLOAD_CARGO_COST    DOUBLE(22,31)
	ESTIMATE_FREIGHT    DOUBLE(22,31)
	ESTIMATE_TOTAL_COST    DOUBLE(22,31)
	IS_CONFIRM    INT(11)
	IS_PAYMENT    INT(11)
*/
public class LcPlatformOrderBill implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String order_number;
	private Double freight;
	private Double agency_fund_poundage;
	private Double load_unload_charge;
	private Double added_service_charge;
	private Double insurance;
	private Double total_cost;
	private Double prepaid_cost;
	private Double other_cost;
	private String pay_date;
	private Integer state;
	private String start_outlets;
	private String end_outlets;
	private String company_name;
	private String create_time;
	private Double take_cargo_cost;
	private Double send_cargo_cost;
	private Double load_cargo_cost;
	private Double unload_cargo_cost;
	private Double estimate_take_cargo_cost;
	private Double estimate_send_cargo_cost;
	private Double estimate_load_cargo_cost;
	private Double estimate_unload_cargo_cost;
	private Double estimate_freight;
	private Double estimate_total_cost;
	private Integer is_confirm;
	private Integer is_payment;

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
	public void setFreight(Double freight){
		this.freight=freight;
	}
	public Double getFreight(){
		return freight;
	}
	public void setAgency_fund_poundage(Double agency_fund_poundage){
		this.agency_fund_poundage=agency_fund_poundage;
	}
	public Double getAgency_fund_poundage(){
		return agency_fund_poundage;
	}
	public void setLoad_unload_charge(Double load_unload_charge){
		this.load_unload_charge=load_unload_charge;
	}
	public Double getLoad_unload_charge(){
		return load_unload_charge;
	}
	public void setAdded_service_charge(Double added_service_charge){
		this.added_service_charge=added_service_charge;
	}
	public Double getAdded_service_charge(){
		return added_service_charge;
	}
	public void setInsurance(Double insurance){
		this.insurance=insurance;
	}
	public Double getInsurance(){
		return insurance;
	}
	public void setTotal_cost(Double total_cost){
		this.total_cost=total_cost;
	}
	public Double getTotal_cost(){
		return total_cost;
	}
	public void setPrepaid_cost(Double prepaid_cost){
		this.prepaid_cost=prepaid_cost;
	}
	public Double getPrepaid_cost(){
		return prepaid_cost;
	}
	public void setOther_cost(Double other_cost){
		this.other_cost=other_cost;
	}
	public Double getOther_cost(){
		return other_cost;
	}
	public void setPay_date(String pay_date){
		this.pay_date=pay_date;
	}
	public String getPay_date(){
		return pay_date;
	}
	public void setState(Integer state){
		this.state=state;
	}
	public Integer getState(){
		return state;
	}
	public void setStart_outlets(String start_outlets){
		this.start_outlets=start_outlets;
	}
	public String getStart_outlets(){
		return start_outlets;
	}
	public void setEnd_outlets(String end_outlets){
		this.end_outlets=end_outlets;
	}
	public String getEnd_outlets(){
		return end_outlets;
	}
	public void setCompany_name(String company_name){
		this.company_name=company_name;
	}
	public String getCompany_name(){
		return company_name;
	}
	public void setCreate_time(String create_time){
		this.create_time=create_time;
	}
	public String getCreate_time(){
		return create_time;
	}
	public void setTake_cargo_cost(Double take_cargo_cost){
		this.take_cargo_cost=take_cargo_cost;
	}
	public Double getTake_cargo_cost(){
		return take_cargo_cost;
	}
	public void setSend_cargo_cost(Double send_cargo_cost){
		this.send_cargo_cost=send_cargo_cost;
	}
	public Double getSend_cargo_cost(){
		return send_cargo_cost;
	}
	public void setLoad_cargo_cost(Double load_cargo_cost){
		this.load_cargo_cost=load_cargo_cost;
	}
	public Double getLoad_cargo_cost(){
		return load_cargo_cost;
	}
	public void setUnload_cargo_cost(Double unload_cargo_cost){
		this.unload_cargo_cost=unload_cargo_cost;
	}
	public Double getUnload_cargo_cost(){
		return unload_cargo_cost;
	}
	public void setEstimate_take_cargo_cost(Double estimate_take_cargo_cost){
		this.estimate_take_cargo_cost=estimate_take_cargo_cost;
	}
	public Double getEstimate_take_cargo_cost(){
		return estimate_take_cargo_cost;
	}
	public void setEstimate_send_cargo_cost(Double estimate_send_cargo_cost){
		this.estimate_send_cargo_cost=estimate_send_cargo_cost;
	}
	public Double getEstimate_send_cargo_cost(){
		return estimate_send_cargo_cost;
	}
	public void setEstimate_load_cargo_cost(Double estimate_load_cargo_cost){
		this.estimate_load_cargo_cost=estimate_load_cargo_cost;
	}
	public Double getEstimate_load_cargo_cost(){
		return estimate_load_cargo_cost;
	}
	public void setEstimate_unload_cargo_cost(Double estimate_unload_cargo_cost){
		this.estimate_unload_cargo_cost=estimate_unload_cargo_cost;
	}
	public Double getEstimate_unload_cargo_cost(){
		return estimate_unload_cargo_cost;
	}
	public void setEstimate_freight(Double estimate_freight){
		this.estimate_freight=estimate_freight;
	}
	public Double getEstimate_freight(){
		return estimate_freight;
	}
	public void setEstimate_total_cost(Double estimate_total_cost){
		this.estimate_total_cost=estimate_total_cost;
	}
	public Double getEstimate_total_cost(){
		return estimate_total_cost;
	}
	public void setIs_confirm(Integer is_confirm){
		this.is_confirm=is_confirm;
	}
	public Integer getIs_confirm(){
		return is_confirm;
	}
	public void setIs_payment(Integer is_payment){
		this.is_payment=is_payment;
	}
	public Integer getIs_payment(){
		return is_payment;
	}
}


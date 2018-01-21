package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.*;

import java.math.BigInteger;


/** LcPlatformOrderAgencyFund
	ID    BIGINT(20)
	ORDER_NUMBER    VARCHAR(20)
	AGENCY_FUND    DOUBLE(22,31)
	RECEIVED_FUND    DOUBLE(22,31)
	UNCOLLECTED_FUND    DOUBLE(21)
	OPERATE_TIME    DATETIME(19)
	STATE    INT(11)
*/
public class LcPlatformOrderAgencyFund implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String order_number;
	private Double agency_fund;
	private Double received_fund;
	private Double uncollected_fund;
	private String operate_time;
	private Integer state;

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
	public void setAgency_fund(Double agency_fund){
		this.agency_fund=agency_fund;
	}
	public Double getAgency_fund(){
		return agency_fund;
	}
	public void setReceived_fund(Double received_fund){
		this.received_fund=received_fund;
	}
	public Double getReceived_fund(){
		return received_fund;
	}
	public void setUncollected_fund(Double uncollected_fund){
		this.uncollected_fund=uncollected_fund;
	}
	public Double getUncollected_fund(){
		return uncollected_fund;
	}
	public void setOperate_time(String operate_time){
		this.operate_time=operate_time;
	}
	public String getOperate_time(){
		return operate_time;
	}
	public void setState(Integer state){
		this.state=state;
	}
	public Integer getState(){
		return state;
	}
}


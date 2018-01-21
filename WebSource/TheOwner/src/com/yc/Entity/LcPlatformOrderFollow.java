package com.yc.Entity; 

import java.io.Serializable;
import java.util.Date;
import java.math.BigInteger;


/** LcPlatformOrderFollow
	ID    BIGINT(20)
	ORDER_NUMBER    VARCHAR(20)
	WAY_BILL_NUMBER    VARCHAR(20)
	STATUS    INT(11)
	HANDLE_TIME    DATETIME(19)
	HANDLE_INFO    VARCHAR(255)
	OPERATE_PERSON    VARCHAR(20)
*/
public class LcPlatformOrderFollow implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String order_number;
	private String way_bill_number;
	private Integer status;
	private String handle_time;
	private String handle_info;
	private String operate_person;

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
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setHandle_time(String handle_time){
		this.handle_time=handle_time;
	}
	public String getHandle_time(){
		return handle_time;
	}
	public void setHandle_info(String handle_info){
		this.handle_info=handle_info;
	}
	public String getHandle_info(){
		return handle_info;
	}
	public void setOperate_person(String operate_person){
		this.operate_person=operate_person;
	}
	public String getOperate_person(){
		return operate_person;
	}
}


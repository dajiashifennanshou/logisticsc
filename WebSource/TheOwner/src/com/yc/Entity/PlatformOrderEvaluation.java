package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class PlatformOrderEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String order_number;
	private String evaluate_content;
	private String evaluate_time;
	private Integer evaluate_level;
	private BigInteger user_id;
	private Integer state;
	private Integer status;
	private  PlatformUser user;

	
	
	public PlatformUser getUser() {
		return user;
	}
	public void setUser(PlatformUser user) {
		this.user = user;
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
	public void setEvaluate_content(String evaluate_content){
		this.evaluate_content=evaluate_content;
	}
	public String getEvaluate_content(){
		return evaluate_content;
	}
	public void setEvaluate_time(String evaluate_time){
		this.evaluate_time=evaluate_time;
	}
	public String getEvaluate_time(){
		return evaluate_time;
	}
	public void setEvaluate_level(Integer evaluate_level){
		this.evaluate_level=evaluate_level;
	}
	public Integer getEvaluate_level(){
		return evaluate_level;
	}
	public void setUser_id(BigInteger user_id){
		this.user_id=user_id;
	}
	public BigInteger getUser_id(){
		return user_id;
	}
	public void setState(Integer state){
		this.state=state;
	}
	public Integer getState(){
		return state;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	public Integer getStatus(){
		return status;
	}
}


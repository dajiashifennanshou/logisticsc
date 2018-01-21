package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class PlatformUserApply implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String apply_name;
	private Integer apply_type;
	private Integer apply_state;
	private String apply_feedback;
	private BigInteger user_id;
	private BigInteger version;
	private String time;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setApply_name(String apply_name){
		this.apply_name=apply_name;
	}
	public String getApply_name(){
		return apply_name;
	}
	public void setApply_type(Integer apply_type){
		this.apply_type=apply_type;
	}
	public Integer getApply_type(){
		return apply_type;
	}
	public void setApply_state(Integer apply_state){
		this.apply_state=apply_state;
	}
	public Integer getApply_state(){
		return apply_state;
	}
	public void setApply_feedback(String apply_feedback){
		this.apply_feedback=apply_feedback;
	}
	public String getApply_feedback(){
		return apply_feedback;
	}
	public void setUser_id(BigInteger user_id){
		this.user_id=user_id;
	}
	public BigInteger getUser_id(){
		return user_id;
	}
	public void setVersion(BigInteger version){
		this.version=version;
	}
	public BigInteger getVersion(){
		return version;
	}
	public void setTime(String time){
		this.time=time;
	}
	public String getTime(){
		return time;
	}
}


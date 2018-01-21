package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class LcPlatformUserCommonDriver implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String driver_name;
	private String license_number;
	private String phone_number;
	private String vehicle_type;
	private String address;
	private BigInteger user_id;
	private String create_time;
	private String province;
	private String city;
	private String county;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setDriver_name(String driver_name){
		this.driver_name=driver_name;
	}
	public String getDriver_name(){
		return driver_name;
	}
	public void setLicense_number(String license_number){
		this.license_number=license_number;
	}
	public String getLicense_number(){
		return license_number;
	}
	public void setPhone_number(String phone_number){
		this.phone_number=phone_number;
	}
	public String getPhone_number(){
		return phone_number;
	}
	public void setVehicle_type(String vehicle_type){
		this.vehicle_type=vehicle_type;
	}
	public String getVehicle_type(){
		return vehicle_type;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setUser_id(BigInteger user_id){
		this.user_id=user_id;
	}
	public BigInteger getUser_id(){
		return user_id;
	}
	public void setCreate_time(String create_time){
		this.create_time=create_time;
	}
	public String getCreate_time(){
		return create_time;
	}
	public void setProvince(String province){
		this.province=province;
	}
	public String getProvince(){
		return province;
	}
	public void setCity(String city){
		this.city=city;
	}
	public String getCity(){
		return city;
	}
	public void setCounty(String county){
		this.county=county;
	}
	public String getCounty(){
		return county;
	}
}


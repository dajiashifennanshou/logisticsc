package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;

public class LcPlatformOrderCargo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String name;
	private String brand;
	private String model;
	private Integer package_type;
	private Integer number;
	private Integer set_number;
	private Double single_weight;
	private Double single_volume;
	private Integer count_cost_mode;
	private Double price;
	private BigInteger order_id;

	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setBrand(String brand){
		this.brand=brand;
	}
	public String getBrand(){
		return brand;
	}
	public void setModel(String model){
		this.model=model;
	}
	public String getModel(){
		return model;
	}
	public void setPackage_type(Integer package_type){
		this.package_type=package_type;
	}
	public Integer getPackage_type(){
		return package_type;
	}
	public void setNumber(Integer number){
		this.number=number;
	}
	public Integer getNumber(){
		return number;
	}
	public void setSet_number(Integer set_number){
		this.set_number=set_number;
	}
	public Integer getSet_number(){
		return set_number;
	}
	public void setSingle_weight(Double single_weight){
		this.single_weight=single_weight;
	}
	public Double getSingle_weight(){
		return single_weight;
	}
	public void setSingle_volume(Double single_volume){
		this.single_volume=single_volume;
	}
	public Double getSingle_volume(){
		return single_volume;
	}
	public void setCount_cost_mode(Integer count_cost_mode){
		this.count_cost_mode=count_cost_mode;
	}
	public Integer getCount_cost_mode(){
		return count_cost_mode;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	public Double getPrice(){
		return price;
	}
	public void setOrder_id(BigInteger order_id){
		this.order_id=order_id;
	}
	public BigInteger getOrder_id(){
		return order_id;
	}
}


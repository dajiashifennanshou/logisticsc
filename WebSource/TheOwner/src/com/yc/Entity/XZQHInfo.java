package com.yc.Entity; 

import java.io.Serializable;
import java.util.List;
public class XZQHInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String pid;
	private String province;
	private String city;
	private String county;
	private List<XZQHInfo> list;
	public XZQHInfo(String id) {
		super();
		this.id = id;
	}
	
	public XZQHInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setPid(String pid){
		this.pid=pid;
	}
	public String getPid(){
		return pid;
	}
	public List<XZQHInfo> getList() {
		return list;
	}
	public void setList(List<XZQHInfo> list) {
		this.list = list;
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
}


package com.yc.Entity;

import java.math.BigInteger;
import java.util.List;

public class LogisticsInfo {

	private BigInteger cId;//公司信息ID
	private String companyName;//公司名称
	private BigInteger outletsId;//网点信息ID
	private String province;//省
	private String city;//市
	private String county;//区/县
	private String address;//详细地址
	private Integer distance;//距离
	private Double longitude;//经度
	private Double latitude;//纬度

	private List<TmsLineInfo> list;//路线信息
	
	
	public BigInteger getcId() {
		return cId;
	}
	public void setcId(BigInteger cId) {
		this.cId = cId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public BigInteger getOutletsId() {
		return outletsId;
	}
	public void setOutletsId(BigInteger outletsId) {
		this.outletsId = outletsId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<TmsLineInfo> getList() {
		return list;
	}
	public void setList(List<TmsLineInfo> list) {
		this.list = list;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
}

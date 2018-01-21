package com.brightsoft.model;

import java.io.Serializable;

public class PlatformHomeOrderCity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String startProvince;
	private String startProvinceValue;
	private String startCity;
	private String startCityValue;
	private String startCounty;
	private String startCountyValue;
	
	
	public String getStartProvinceValue() {
		return startProvinceValue;
	}

	public void setStartProvinceValue(String startProvinceValue) {
		this.startProvinceValue = startProvinceValue;
	}

	public String getStartCityValue() {
		return startCityValue;
	}

	public void setStartCityValue(String startCityValue) {
		this.startCityValue = startCityValue;
	}

	public String getStartCountyValue() {
		return startCountyValue;
	}

	public void setStartCountyValue(String startCountyValue) {
		this.startCountyValue = startCountyValue;
	}

	public String getStartProvince() {
		return startProvince;
	}

	public void setStartProvince(String startProvince) {
		this.startProvince = startProvince;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getStartCounty() {
		return startCounty;
	}

	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
	}
	
	
}

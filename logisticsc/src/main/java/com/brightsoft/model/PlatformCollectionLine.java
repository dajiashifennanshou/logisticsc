package com.brightsoft.model;

import java.io.Serializable;

/**
 * 我的收藏 线路
 * @author ThinkPad
 *
 */
public class PlatformCollectionLine implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	 private String companyName;
	 
	 private Long recordId;
	 
	 private Long comId;
	 
	 private Long userId;
	 
	 private String startProvince;

	 private String startCity;
	 
	 private String startCounty;
	    
	 private String endProvince;

	 private String endCity;
	 
	 private String endCounty;
	 
	 
	 private String startProvinceValue;

	 private String startCityValue;
	 
	 private String startCountyValue;
	    
	 private String endProvinceValue;

	 private String endCityValue;
	 
	 private String endCountyValue;
	 
	 
	 private String serverType;

    private Double heavyCargoPriceLow;
    
    private Double heavyCargoPriceMid;
    
    private Double heavyCargoPriceHigh;
    
    private Double bulkyCargoPriceLow;

    private Double bulkyCargoPriceMid;
    
    private Double bulkyCargoPriceHigh;
    
	private Double lowestPrice;
	 
	 
	
	public String getStartCounty() {
		return startCounty;
	}

	public void setStartCounty(String startCounty) {
		this.startCounty = startCounty;
	}

	public String getEndCounty() {
		return endCounty;
	}

	public void setEndCounty(String endCounty) {
		this.endCounty = endCounty;
	}

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

	public String getEndProvinceValue() {
		return endProvinceValue;
	}

	public void setEndProvinceValue(String endProvinceValue) {
		this.endProvinceValue = endProvinceValue;
	}

	public String getEndCityValue() {
		return endCityValue;
	}

	public void setEndCityValue(String endCityValue) {
		this.endCityValue = endCityValue;
	}

	public String getEndCountyValue() {
		return endCountyValue;
	}

	public void setEndCountyValue(String endCountyValue) {
		this.endCountyValue = endCountyValue;
	}

	public Long getComId() {
		return comId;
	}

	public void setComId(Long comId) {
		this.comId = comId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
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

	public String getEndProvince() {
		return endProvince;
	}

	public void setEndProvince(String endProvince) {
		this.endProvince = endProvince;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	

	public Double getHeavyCargoPriceLow() {
		return heavyCargoPriceLow;
	}

	public void setHeavyCargoPriceLow(Double heavyCargoPriceLow) {
		this.heavyCargoPriceLow = heavyCargoPriceLow;
	}

	public Double getHeavyCargoPriceMid() {
		return heavyCargoPriceMid;
	}

	public void setHeavyCargoPriceMid(Double heavyCargoPriceMid) {
		this.heavyCargoPriceMid = heavyCargoPriceMid;
	}

	public Double getHeavyCargoPriceHigh() {
		return heavyCargoPriceHigh;
	}

	public void setHeavyCargoPriceHigh(Double heavyCargoPriceHigh) {
		this.heavyCargoPriceHigh = heavyCargoPriceHigh;
	}

	public Double getBulkyCargoPriceLow() {
		return bulkyCargoPriceLow;
	}

	public void setBulkyCargoPriceLow(Double bulkyCargoPriceLow) {
		this.bulkyCargoPriceLow = bulkyCargoPriceLow;
	}

	public Double getBulkyCargoPriceMid() {
		return bulkyCargoPriceMid;
	}

	public void setBulkyCargoPriceMid(Double bulkyCargoPriceMid) {
		this.bulkyCargoPriceMid = bulkyCargoPriceMid;
	}

	public Double getBulkyCargoPriceHigh() {
		return bulkyCargoPriceHigh;
	}

	public void setBulkyCargoPriceHigh(Double bulkyCargoPriceHigh) {
		this.bulkyCargoPriceHigh = bulkyCargoPriceHigh;
	}

	public Double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	 
}

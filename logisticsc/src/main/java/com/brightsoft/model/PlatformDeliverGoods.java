package com.brightsoft.model;

import java.io.Serializable;


/**
 * 我要发货  
 * @author ThinkPad
 *
 */
public class PlatformDeliverGoods implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/*******线路*************/
	private Long id;
	
	private String serverType;

    private String transportMode;

    private Integer timeLong;

    private Double heavyCargoPriceLow;
    
    private Double heavyCargoPriceMid;
    
    private Double heavyCargoPriceHigh;
    
    private Double bulkyCargoPriceLow;

    private Double bulkyCargoPriceMid;
    
    private Double bulkyCargoPriceHigh;

    
    private Double lowestPrice;
    
    private Integer evaluateLevel;

    /*****************网点************************/

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

    /***************公司**********************/
    private String companyName;
    private Long companyId;
    private String QQ;
    
    private String contacts1Mobile;
    /***************订单***************/
    private Integer countOrder;
    /***************订单评价********************/
    private Integer countOrderEvaluation;
    
    private String conditionType;
    
    
    
	
	public Integer getEvaluateLevel() {
		return evaluateLevel;
	}
	public void setEvaluateLevel(Integer evaluateLevel) {
		this.evaluateLevel = evaluateLevel;
	}
	public String getContacts1Mobile() {
		return contacts1Mobile;
	}
	public void setContacts1Mobile(String contacts1Mobile) {
		this.contacts1Mobile = contacts1Mobile;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public Integer getTimeLong() {
		return timeLong;
	}
	public void setTimeLong(Integer timeLong) {
		this.timeLong = timeLong;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
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
	public Integer getCountOrder() {
		return countOrder;
	}
	public void setCountOrder(Integer countOrder) {
		this.countOrder = countOrder;
	}
	public Integer getCountOrderEvaluation() {
		return countOrderEvaluation;
	}
	public void setCountOrderEvaluation(Integer countOrderEvaluation) {
		this.countOrderEvaluation = countOrderEvaluation;
	}
	public Double getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
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
	
}

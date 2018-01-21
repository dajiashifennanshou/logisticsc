package com.yc.Entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 我要发货  
 * @author ThinkPad
 *
 */
public class PlatformDeliverGoods implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/*******线路*************/
	private BigInteger id;
	private String server_type;
	private String start_outlets;
	private String start_outlets_name;
	private String end_outlets;
	private String end_outlets_name;
	private String transport_mode;
	private Double time_long;
	private Double heavy_cargo_price_low;
	private Double bulky_cargo_price_low;
	private Double heavy_cargo_price_mid;
	private Double bulky_cargo_price_mid;
	private Double heavy_cargo_price_high;
	private Double bulky_cargo_price_high;
	private Double lowest_price;
	private Integer status;
	private Integer release_state;
	private String create_time;
	private BigInteger create_person_id;
	private Integer is_take_cargo;
	private Integer is_give_cargo;
	private BigInteger outlets_id;
	private Integer evaluate_level;
	private Double remain_money;
	private Integer recommended;
	private Integer isAddServer;//是否存在增值服务0存在、1不存在；不存在时禁止下单
	
	private AdditionalServerConf conf;//增值服务配置

    /*****************网点************************/

    private String startProvince;
    private String startCity;
    private String startCounty;
    private String startAddress;
    
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
    private BigInteger companyId;
    private String qq;
    
    private String contactsMobile;
    /***************订单***************/
    private Integer countOrder;
    /***************订单评价********************/
    private Integer countOrderEvaluation;
    
    private String conditionType;
    //距离
    private Integer distance;//距离
	private Double longitude;//经度
	private Double latitude;//纬度
	private BigInteger user_id;
	private Integer isCollect;//是否收藏
	public BigInteger getUser_id() {
		return user_id;
	}
	public void setUser_id(BigInteger user_id) {
		this.user_id = user_id;
	}
	public Integer getIsCollect() {
		return isCollect;
	}
	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
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
	public String getContactsMobile() {
		return contactsMobile;
	}
	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigInteger getCompanyId() {
		return companyId;
	}
	public void setCompanyId(BigInteger companyId) {
		this.companyId = companyId;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
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
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
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
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getServer_type() {
		return server_type;
	}
	public void setServer_type(String server_type) {
		this.server_type = server_type;
	}
	public String getStart_outlets() {
		return start_outlets;
	}
	public void setStart_outlets(String start_outlets) {
		this.start_outlets = start_outlets;
	}
	public String getStart_outlets_name() {
		return start_outlets_name;
	}
	public void setStart_outlets_name(String start_outlets_name) {
		this.start_outlets_name = start_outlets_name;
	}
	public String getEnd_outlets() {
		return end_outlets;
	}
	public void setEnd_outlets(String end_outlets) {
		this.end_outlets = end_outlets;
	}
	public String getEnd_outlets_name() {
		return end_outlets_name;
	}
	public void setEnd_outlets_name(String end_outlets_name) {
		this.end_outlets_name = end_outlets_name;
	}
	public String getTransport_mode() {
		return transport_mode;
	}
	public void setTransport_mode(String transport_mode) {
		this.transport_mode = transport_mode;
	}
	public Double getTime_long() {
		return time_long;
	}
	public void setTime_long(Double time_long) {
		this.time_long = time_long;
	}
	public Double getHeavy_cargo_price_low() {
		return heavy_cargo_price_low;
	}
	public void setHeavy_cargo_price_low(Double heavy_cargo_price_low) {
		this.heavy_cargo_price_low = heavy_cargo_price_low;
	}
	public Double getBulky_cargo_price_low() {
		return bulky_cargo_price_low;
	}
	public void setBulky_cargo_price_low(Double bulky_cargo_price_low) {
		this.bulky_cargo_price_low = bulky_cargo_price_low;
	}
	public Double getHeavy_cargo_price_mid() {
		return heavy_cargo_price_mid;
	}
	public void setHeavy_cargo_price_mid(Double heavy_cargo_price_mid) {
		this.heavy_cargo_price_mid = heavy_cargo_price_mid;
	}
	public Double getBulky_cargo_price_mid() {
		return bulky_cargo_price_mid;
	}
	public void setBulky_cargo_price_mid(Double bulky_cargo_price_mid) {
		this.bulky_cargo_price_mid = bulky_cargo_price_mid;
	}
	public Double getHeavy_cargo_price_high() {
		return heavy_cargo_price_high;
	}
	public void setHeavy_cargo_price_high(Double heavy_cargo_price_high) {
		this.heavy_cargo_price_high = heavy_cargo_price_high;
	}
	public Double getBulky_cargo_price_high() {
		return bulky_cargo_price_high;
	}
	public void setBulky_cargo_price_high(Double bulky_cargo_price_high) {
		this.bulky_cargo_price_high = bulky_cargo_price_high;
	}
	public Double getLowest_price() {
		return lowest_price;
	}
	public void setLowest_price(Double lowest_price) {
		this.lowest_price = lowest_price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRelease_state() {
		return release_state;
	}
	public void setRelease_state(Integer release_state) {
		this.release_state = release_state;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public BigInteger getCreate_person_id() {
		return create_person_id;
	}
	public void setCreate_person_id(BigInteger create_person_id) {
		this.create_person_id = create_person_id;
	}
	public Integer getIs_take_cargo() {
		return is_take_cargo;
	}
	public void setIs_take_cargo(Integer is_take_cargo) {
		this.is_take_cargo = is_take_cargo;
	}
	public Integer getIs_give_cargo() {
		return is_give_cargo;
	}
	public void setIs_give_cargo(Integer is_give_cargo) {
		this.is_give_cargo = is_give_cargo;
	}
	public BigInteger getOutlets_id() {
		return outlets_id;
	}
	public void setOutlets_id(BigInteger outlets_id) {
		this.outlets_id = outlets_id;
	}
	public Integer getEvaluate_level() {
		return evaluate_level;
	}
	public void setEvaluate_level(Integer evaluate_level) {
		this.evaluate_level = evaluate_level;
	}
	public Double getRemain_money() {
		return remain_money;
	}
	public void setRemain_money(Double remain_money) {
		this.remain_money = remain_money;
	}
	public Integer getRecommended() {
		return recommended;
	}
	public void setRecommended(Integer recommended) {
		this.recommended = recommended;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public Integer getIsAddServer() {
		return isAddServer;
	}
	public void setIsAddServer(Integer isAddServer) {
		this.isAddServer = isAddServer;
	}
	public AdditionalServerConf getConf() {
		return conf;
	}
	public void setConf(AdditionalServerConf conf) {
		this.conf = conf;
	}
	
}

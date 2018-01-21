package com.brightsoft.entity; 

import java.io.Serializable;
import java.math.BigInteger;
/**
 * 线路管理
 * Author:luojing
 * 2016年6月27日 下午5:37:32
 */
public class TmsLineInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String server_type;
	private BigInteger start_outlets;
	private String start_outlets_name;
	private BigInteger end_outlets;
	private String end_outlets_name;
	private BigInteger transport_mode;
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
	public BigInteger getStart_outlets() {
		return start_outlets;
	}
	public void setStart_outlets(BigInteger start_outlets) {
		this.start_outlets = start_outlets;
	}
	public String getStart_outlets_name() {
		return start_outlets_name;
	}
	public void setStart_outlets_name(String start_outlets_name) {
		this.start_outlets_name = start_outlets_name;
	}
	public BigInteger getEnd_outlets() {
		return end_outlets;
	}
	public void setEnd_outlets(BigInteger end_outlets) {
		this.end_outlets = end_outlets;
	}
	public String getEnd_outlets_name() {
		return end_outlets_name;
	}
	public void setEnd_outlets_name(String end_outlets_name) {
		this.end_outlets_name = end_outlets_name;
	}
	public BigInteger getTransport_mode() {
		return transport_mode;
	}
	public void setTransport_mode(BigInteger transport_mode) {
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
	
}


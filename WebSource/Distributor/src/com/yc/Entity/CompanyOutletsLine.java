package com.yc.Entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 公司，网点，线路
 * @Author:luojing
 * @2016年7月5日 上午11:15:59
 */
public class CompanyOutletsLine implements Serializable  {
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
	private Integer is_take_cargo;
	private Integer is_give_cargo;
	private BigInteger outlets_id;
	private Integer evaluate_level;
	private Double remain_money;
	
	private String outlets_number;
	private String name;
	private BigInteger type;
	private BigInteger nature;
	private String province;
	private String city;
	private String county;
	private String address;
	private String contact_person;
	private String mobile;
	private String phone;
	private String email;
	private String remark;
	private String create_time;
	private BigInteger create_person_id;
	private BigInteger company_id;
	
	private String company_name;
	private String company_address;
	private String legal_person;
	private String legal_mobile;
	private String contacts;
	private String contacts_mobile;
	private String qq;
	private String post_code;
	private String company_phone;
	private String company_fax;
	private String company_tax_no;
	private String finance_email;
	private String company_info;
	private String logo;
	private String business_license;
	private String company_photo;
	private String legal_photo;
	private String card_photo;
	private String company_code;
	
	public CompanyOutletsLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyOutletsLine(BigInteger id) {
		super();
		this.id = id;
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
	public String getOutlets_number() {
		return outlets_number;
	}
	public void setOutlets_number(String outlets_number) {
		this.outlets_number = outlets_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getType() {
		return type;
	}
	public void setType(BigInteger type) {
		this.type = type;
	}
	public BigInteger getNature() {
		return nature;
	}
	public void setNature(BigInteger nature) {
		this.nature = nature;
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
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public BigInteger getCompany_id() {
		return company_id;
	}
	public void setCompany_id(BigInteger company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getLegal_person() {
		return legal_person;
	}
	public void setLegal_person(String legal_person) {
		this.legal_person = legal_person;
	}
	public String getLegal_mobile() {
		return legal_mobile;
	}
	public void setLegal_mobile(String legal_mobile) {
		this.legal_mobile = legal_mobile;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getContacts_mobile() {
		return contacts_mobile;
	}
	public void setContacts_mobile(String contacts_mobile) {
		this.contacts_mobile = contacts_mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}
	public String getCompany_phone() {
		return company_phone;
	}
	public void setCompany_phone(String company_phone) {
		this.company_phone = company_phone;
	}
	public String getCompany_fax() {
		return company_fax;
	}
	public void setCompany_fax(String company_fax) {
		this.company_fax = company_fax;
	}
	public String getCompany_tax_no() {
		return company_tax_no;
	}
	public void setCompany_tax_no(String company_tax_no) {
		this.company_tax_no = company_tax_no;
	}
	public String getFinance_email() {
		return finance_email;
	}
	public void setFinance_email(String finance_email) {
		this.finance_email = finance_email;
	}
	public String getCompany_info() {
		return company_info;
	}
	public void setCompany_info(String company_info) {
		this.company_info = company_info;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getBusiness_license() {
		return business_license;
	}
	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}
	public String getCompany_photo() {
		return company_photo;
	}
	public void setCompany_photo(String company_photo) {
		this.company_photo = company_photo;
	}
	public String getLegal_photo() {
		return legal_photo;
	}
	public void setLegal_photo(String legal_photo) {
		this.legal_photo = legal_photo;
	}
	public String getCard_photo() {
		return card_photo;
	}
	public void setCard_photo(String card_photo) {
		this.card_photo = card_photo;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	
}

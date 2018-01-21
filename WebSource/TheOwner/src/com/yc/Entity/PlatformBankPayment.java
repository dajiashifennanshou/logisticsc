package com.yc.Entity; 

import java.io.Serializable;
import java.math.BigInteger;
public class PlatformBankPayment implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger id;
	private String customernumber;
	private String requestid;
	private String amount;
	private String assure;
	private String productname;
	private String productcat;
	private String productdesc;
	private String divideinfo;
	private String callbackurl;
	private String webcallbackurl;
	private String bankid;
	private String period;
	private String memo;
	private String payproducttype;
	private String order_number;
	private Integer order_type;
	private String time;
	private Integer state;
	private BigInteger user_id;
	
	public PlatformBankPayment(String requestid) {
		super();
		this.requestid = requestid;
	}
	public PlatformBankPayment(BigInteger id, Integer state) {
		super();
		this.id = id;
		this.state = state;
	}
	public PlatformBankPayment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setId(BigInteger id){
		this.id=id;
	}
	public BigInteger getId(){
		return id;
	}
	public void setCustomernumber(String customernumber){
		this.customernumber=customernumber;
	}
	public String getCustomernumber(){
		return customernumber;
	}
	public void setRequestid(String requestid){
		this.requestid=requestid;
	}
	public String getRequestid(){
		return requestid;
	}
	public void setAmount(String amount){
		this.amount=amount;
	}
	public String getAmount(){
		return amount;
	}
	public void setAssure(String assure){
		this.assure=assure;
	}
	public String getAssure(){
		return assure;
	}
	public void setProductname(String productname){
		this.productname=productname;
	}
	public String getProductname(){
		return productname;
	}
	public void setProductcat(String productcat){
		this.productcat=productcat;
	}
	public String getProductcat(){
		return productcat;
	}
	public void setProductdesc(String productdesc){
		this.productdesc=productdesc;
	}
	public String getProductdesc(){
		return productdesc;
	}
	public void setDivideinfo(String divideinfo){
		this.divideinfo=divideinfo;
	}
	public String getDivideinfo(){
		return divideinfo;
	}
	public void setCallbackurl(String callbackurl){
		this.callbackurl=callbackurl;
	}
	public String getCallbackurl(){
		return callbackurl;
	}
	public void setWebcallbackurl(String webcallbackurl){
		this.webcallbackurl=webcallbackurl;
	}
	public String getWebcallbackurl(){
		return webcallbackurl;
	}
	public void setBankid(String bankid){
		this.bankid=bankid;
	}
	public String getBankid(){
		return bankid;
	}
	public void setPeriod(String period){
		this.period=period;
	}
	public String getPeriod(){
		return period;
	}
	public void setMemo(String memo){
		this.memo=memo;
	}
	public String getMemo(){
		return memo;
	}
	public void setPayproducttype(String payproducttype){
		this.payproducttype=payproducttype;
	}
	public String getPayproducttype(){
		return payproducttype;
	}
	public void setOrder_number(String order_number){
		this.order_number=order_number;
	}
	public String getOrder_number(){
		return order_number;
	}
	public void setOrder_type(Integer order_type){
		this.order_type=order_type;
	}
	public Integer getOrder_type(){
		return order_type;
	}
	public void setTime(String time){
		this.time=time;
	}
	public String getTime(){
		return time;
	}
	public void setState(Integer state){
		this.state=state;
	}
	public Integer getState(){
		return state;
	}
	public void setUser_id(BigInteger user_id){
		this.user_id=user_id;
	}
	public BigInteger getUser_id(){
		return user_id;
	}
}


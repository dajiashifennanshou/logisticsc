package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.brightsoft.utils.dateConvertor.XdateConvert;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * 保险分账
 * @author ThinkPad
 *
 */
public class platformVoSplitInsurance implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long userId;

    private String insOrderNum;

    private String insComTag;

    private String insType;

    private String insTsType;

    private String insTrueName;

    private String insTel;

    private String insCardNum;

    private String insAddress;

    private String insCheNum;

    private String insHeTongNum;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @XStreamConverter(value=XdateConvert.class)
    private Date insStartTime;

    private String insHuoWu;

    private String insHuowuNum;

    private Double insJine;

    private String insStartAdd;

    private String insEndAdd;

    private String insMidAdd;

    private Double insFee;

    private Date insCreateTime;

    private Integer insArea;

    private Integer insStatus;

    private String insJsy;

    private String insJsySfz;

    private String insJsySj;

    private String insXsNum;

    private String insYyNum;

    private String insFdjNum;

    private String insCjNum;

    private String insDes;

    private String insSysBaodan;

    private String insLastBaodan;

    private String insFileUrl;

    private String insGoodsType;

    private String insTransType;

    private String insLoadType;

    private String insBaoZhuang;

    
    private String customernumber;

    private String requestid;

    private String orderrequestid;

    private String divideinfo;

    private String callbackurl;
    
    private Integer orderType;
    
    private String orderNumber;

    private Date time;

    private Integer state;

    private String suername;
    
    private String loginName;
    
    
    private String startTime;//起始时间
    
  	private String endTime;//结束时间
  	
  	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getInsOrderNum() {
		return insOrderNum;
	}

	public void setInsOrderNum(String insOrderNum) {
		this.insOrderNum = insOrderNum;
	}

	public String getInsComTag() {
		return insComTag;
	}

	public void setInsComTag(String insComTag) {
		this.insComTag = insComTag;
	}

	public String getInsType() {
		return insType;
	}

	public void setInsType(String insType) {
		this.insType = insType;
	}

	public String getInsTsType() {
		return insTsType;
	}

	public void setInsTsType(String insTsType) {
		this.insTsType = insTsType;
	}

	public String getInsTrueName() {
		return insTrueName;
	}

	public void setInsTrueName(String insTrueName) {
		this.insTrueName = insTrueName;
	}

	public String getInsTel() {
		return insTel;
	}

	public void setInsTel(String insTel) {
		this.insTel = insTel;
	}

	public String getInsCardNum() {
		return insCardNum;
	}

	public void setInsCardNum(String insCardNum) {
		this.insCardNum = insCardNum;
	}

	public String getInsAddress() {
		return insAddress;
	}

	public void setInsAddress(String insAddress) {
		this.insAddress = insAddress;
	}

	public String getInsCheNum() {
		return insCheNum;
	}

	public void setInsCheNum(String insCheNum) {
		this.insCheNum = insCheNum;
	}

	public String getInsHeTongNum() {
		return insHeTongNum;
	}

	public void setInsHeTongNum(String insHeTongNum) {
		this.insHeTongNum = insHeTongNum;
	}

	public Date getInsStartTime() {
		return insStartTime;
	}

	public void setInsStartTime(Date insStartTime) {
		this.insStartTime = insStartTime;
	}

	public String getInsHuoWu() {
		return insHuoWu;
	}

	public void setInsHuoWu(String insHuoWu) {
		this.insHuoWu = insHuoWu;
	}

	public String getInsHuowuNum() {
		return insHuowuNum;
	}

	public void setInsHuowuNum(String insHuowuNum) {
		this.insHuowuNum = insHuowuNum;
	}

	public Double getInsJine() {
		return insJine;
	}

	public void setInsJine(Double insJine) {
		this.insJine = insJine;
	}

	public String getInsStartAdd() {
		return insStartAdd;
	}

	public void setInsStartAdd(String insStartAdd) {
		this.insStartAdd = insStartAdd;
	}

	public String getInsEndAdd() {
		return insEndAdd;
	}

	public void setInsEndAdd(String insEndAdd) {
		this.insEndAdd = insEndAdd;
	}

	public String getInsMidAdd() {
		return insMidAdd;
	}

	public void setInsMidAdd(String insMidAdd) {
		this.insMidAdd = insMidAdd;
	}

	public Double getInsFee() {
		return insFee;
	}

	public void setInsFee(Double insFee) {
		this.insFee = insFee;
	}

	public Date getInsCreateTime() {
		return insCreateTime;
	}

	public void setInsCreateTime(Date insCreateTime) {
		this.insCreateTime = insCreateTime;
	}

	public Integer getInsArea() {
		return insArea;
	}

	public void setInsArea(Integer insArea) {
		this.insArea = insArea;
	}

	public Integer getInsStatus() {
		return insStatus;
	}

	public void setInsStatus(Integer insStatus) {
		this.insStatus = insStatus;
	}

	public String getInsJsy() {
		return insJsy;
	}

	public void setInsJsy(String insJsy) {
		this.insJsy = insJsy;
	}

	public String getInsJsySfz() {
		return insJsySfz;
	}

	public void setInsJsySfz(String insJsySfz) {
		this.insJsySfz = insJsySfz;
	}

	public String getInsJsySj() {
		return insJsySj;
	}

	public void setInsJsySj(String insJsySj) {
		this.insJsySj = insJsySj;
	}

	public String getInsXsNum() {
		return insXsNum;
	}

	public void setInsXsNum(String insXsNum) {
		this.insXsNum = insXsNum;
	}

	public String getInsYyNum() {
		return insYyNum;
	}

	public void setInsYyNum(String insYyNum) {
		this.insYyNum = insYyNum;
	}

	public String getInsFdjNum() {
		return insFdjNum;
	}

	public void setInsFdjNum(String insFdjNum) {
		this.insFdjNum = insFdjNum;
	}

	public String getInsCjNum() {
		return insCjNum;
	}

	public void setInsCjNum(String insCjNum) {
		this.insCjNum = insCjNum;
	}

	public String getInsDes() {
		return insDes;
	}

	public void setInsDes(String insDes) {
		this.insDes = insDes;
	}

	public String getInsSysBaodan() {
		return insSysBaodan;
	}

	public void setInsSysBaodan(String insSysBaodan) {
		this.insSysBaodan = insSysBaodan;
	}

	public String getInsLastBaodan() {
		return insLastBaodan;
	}

	public void setInsLastBaodan(String insLastBaodan) {
		this.insLastBaodan = insLastBaodan;
	}

	public String getInsFileUrl() {
		return insFileUrl;
	}

	public void setInsFileUrl(String insFileUrl) {
		this.insFileUrl = insFileUrl;
	}

	public String getInsGoodsType() {
		return insGoodsType;
	}

	public void setInsGoodsType(String insGoodsType) {
		this.insGoodsType = insGoodsType;
	}

	public String getInsTransType() {
		return insTransType;
	}

	public void setInsTransType(String insTransType) {
		this.insTransType = insTransType;
	}

	public String getInsLoadType() {
		return insLoadType;
	}

	public void setInsLoadType(String insLoadType) {
		this.insLoadType = insLoadType;
	}

	public String getInsBaoZhuang() {
		return insBaoZhuang;
	}

	public void setInsBaoZhuang(String insBaoZhuang) {
		this.insBaoZhuang = insBaoZhuang;
	}


	public String getCustomernumber() {
		return customernumber;
	}

	public void setCustomernumber(String customernumber) {
		this.customernumber = customernumber;
	}

	public String getRequestid() {
		return requestid;
	}

	public void setRequestid(String requestid) {
		this.requestid = requestid;
	}

	public String getOrderrequestid() {
		return orderrequestid;
	}

	public void setOrderrequestid(String orderrequestid) {
		this.orderrequestid = orderrequestid;
	}

	public String getDivideinfo() {
		return divideinfo;
	}

	public void setDivideinfo(String divideinfo) {
		this.divideinfo = divideinfo;
	}

	public String getCallbackurl() {
		return callbackurl;
	}

	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSuername() {
		return suername;
	}

	public void setSuername(String suername) {
		this.suername = suername;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
    
}

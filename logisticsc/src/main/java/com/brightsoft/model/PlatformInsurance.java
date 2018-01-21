package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.brightsoft.utils.dateConvertor.XdateConvert;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public class PlatformInsurance implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

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
    /***************/
    private String userName;
    
    private String userPwd;
    
    private String userKey;
    
    private String backDataUrl;
    
    private PlatformUser platformUser;
    
    private InsuranceCompany insuranceCompany;

    public InsuranceCompany getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public PlatformUser getPlatformUser() {
		return platformUser;
	}

	public void setPlatformUser(PlatformUser platformUser) {
		this.platformUser = platformUser;
	}

	public String getBackDataUrl() {
		return backDataUrl;
	}

	public void setBackDataUrl(String backDataUrl) {
		this.backDataUrl = backDataUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
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

    public String getInsOrderNum() {
        return insOrderNum;
    }

    public void setInsOrderNum(String insOrderNum) {
        this.insOrderNum = insOrderNum == null ? null : insOrderNum.trim();
    }

    public String getInsComTag() {
        return insComTag;
    }

    public void setInsComTag(String insComTag) {
        this.insComTag = insComTag == null ? null : insComTag.trim();
    }

    public String getInsType() {
        return insType;
    }

    public void setInsType(String insType) {
        this.insType = insType == null ? null : insType.trim();
    }

    public String getInsTsType() {
        return insTsType;
    }

    public void setInsTsType(String insTsType) {
        this.insTsType = insTsType == null ? null : insTsType.trim();
    }

    public String getInsTrueName() {
        return insTrueName;
    }

    public void setInsTrueName(String insTrueName) {
        this.insTrueName = insTrueName == null ? null : insTrueName.trim();
    }

    public String getInsTel() {
        return insTel;
    }

    public void setInsTel(String insTel) {
        this.insTel = insTel == null ? null : insTel.trim();
    }

    public String getInsCardNum() {
        return insCardNum;
    }

    public void setInsCardNum(String insCardNum) {
        this.insCardNum = insCardNum == null ? null : insCardNum.trim();
    }

    public String getInsAddress() {
        return insAddress;
    }

    public void setInsAddress(String insAddress) {
        this.insAddress = insAddress == null ? null : insAddress.trim();
    }

    public String getInsCheNum() {
        return insCheNum;
    }

    public void setInsCheNum(String insCheNum) {
        this.insCheNum = insCheNum == null ? null : insCheNum.trim();
    }

    public String getInsHeTongNum() {
        return insHeTongNum;
    }

    public void setInsHeTongNum(String insHeTongNum) {
        this.insHeTongNum = insHeTongNum == null ? null : insHeTongNum.trim();
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
        this.insHuoWu = insHuoWu == null ? null : insHuoWu.trim();
    }

    public String getInsHuowuNum() {
        return insHuowuNum;
    }

    public void setInsHuowuNum(String insHuowuNum) {
        this.insHuowuNum = insHuowuNum == null ? null : insHuowuNum.trim();
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
        this.insStartAdd = insStartAdd == null ? null : insStartAdd.trim();
    }

    public String getInsEndAdd() {
        return insEndAdd;
    }

    public void setInsEndAdd(String insEndAdd) {
        this.insEndAdd = insEndAdd == null ? null : insEndAdd.trim();
    }

    public String getInsMidAdd() {
        return insMidAdd;
    }

    public void setInsMidAdd(String insMidAdd) {
        this.insMidAdd = insMidAdd == null ? null : insMidAdd.trim();
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
        this.insJsy = insJsy == null ? null : insJsy.trim();
    }

    public String getInsJsySfz() {
        return insJsySfz;
    }

    public void setInsJsySfz(String insJsySfz) {
        this.insJsySfz = insJsySfz == null ? null : insJsySfz.trim();
    }

    public String getInsJsySj() {
        return insJsySj;
    }

    public void setInsJsySj(String insJsySj) {
        this.insJsySj = insJsySj == null ? null : insJsySj.trim();
    }

    public String getInsXsNum() {
        return insXsNum;
    }

    public void setInsXsNum(String insXsNum) {
        this.insXsNum = insXsNum == null ? null : insXsNum.trim();
    }

    public String getInsYyNum() {
        return insYyNum;
    }

    public void setInsYyNum(String insYyNum) {
        this.insYyNum = insYyNum == null ? null : insYyNum.trim();
    }

    public String getInsFdjNum() {
        return insFdjNum;
    }

    public void setInsFdjNum(String insFdjNum) {
        this.insFdjNum = insFdjNum == null ? null : insFdjNum.trim();
    }

    public String getInsCjNum() {
        return insCjNum;
    }

    public void setInsCjNum(String insCjNum) {
        this.insCjNum = insCjNum == null ? null : insCjNum.trim();
    }

    public String getInsDes() {
        return insDes;
    }

    public void setInsDes(String insDes) {
        this.insDes = insDes == null ? null : insDes.trim();
    }

    public String getInsSysBaodan() {
        return insSysBaodan;
    }

    public void setInsSysBaodan(String insSysBaodan) {
        this.insSysBaodan = insSysBaodan == null ? null : insSysBaodan.trim();
    }

    public String getInsLastBaodan() {
        return insLastBaodan;
    }

    public void setInsLastBaodan(String insLastBaodan) {
        this.insLastBaodan = insLastBaodan == null ? null : insLastBaodan.trim();
    }

    public String getInsFileUrl() {
        return insFileUrl;
    }

    public void setInsFileUrl(String insFileUrl) {
        this.insFileUrl = insFileUrl == null ? null : insFileUrl.trim();
    }

    public String getInsGoodsType() {
        return insGoodsType;
    }

    public void setInsGoodsType(String insGoodsType) {
        this.insGoodsType = insGoodsType == null ? null : insGoodsType.trim();
    }

    public String getInsTransType() {
        return insTransType;
    }

    public void setInsTransType(String insTransType) {
        this.insTransType = insTransType == null ? null : insTransType.trim();
    }

    public String getInsLoadType() {
        return insLoadType;
    }

    public void setInsLoadType(String insLoadType) {
        this.insLoadType = insLoadType == null ? null : insLoadType.trim();
    }

    public String getInsBaoZhuang() {
        return insBaoZhuang;
    }

    public void setInsBaoZhuang(String insBaoZhuang) {
        this.insBaoZhuang = insBaoZhuang == null ? null : insBaoZhuang.trim();
    }
}
package com.brightsoft.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsuranceCompany implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String insComName;

    private String insComTag;

    private String insComLogoUrl;

    private Integer insStatus;
    
    private Long createPersonId;
    
    private Date createTime;
    /********************/
    private List<InsuranceType> insuranceTypeList = new ArrayList<InsuranceType>();;
    
    private List<InsuranceTsType> insuranceTsTypeList = new ArrayList<InsuranceTsType>();

    public List<InsuranceType> getInsuranceTypeList() {
		return insuranceTypeList;
	}

	public void setInsuranceTypeList(List<InsuranceType> insuranceTypeList) {
		this.insuranceTypeList = insuranceTypeList;
	}

	public List<InsuranceTsType> getInsuranceTsTypeList() {
		return insuranceTsTypeList;
	}

	public void setInsuranceTsTypeList(List<InsuranceTsType> insuranceTsTypeList) {
		this.insuranceTsTypeList = insuranceTsTypeList;
	}

	public Long getCreatePersonId() {
		return createPersonId;
	}

	public void setCreatePersonId(Long createPersonId) {
		this.createPersonId = createPersonId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsComName() {
        return insComName;
    }

    public void setInsComName(String insComName) {
        this.insComName = insComName == null ? null : insComName.trim();
    }

    public String getInsComTag() {
        return insComTag;
    }

    public void setInsComTag(String insComTag) {
        this.insComTag = insComTag == null ? null : insComTag.trim();
    }

    public String getInsComLogoUrl() {
        return insComLogoUrl;
    }

    public void setInsComLogoUrl(String insComLogoUrl) {
        this.insComLogoUrl = insComLogoUrl == null ? null : insComLogoUrl.trim();
    }

    public Integer getInsStatus() {
        return insStatus;
    }

    public void setInsStatus(Integer insStatus) {
        this.insStatus = insStatus;
    }
}
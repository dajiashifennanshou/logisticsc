package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class InsuranceRate implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long insCompanyId;

    private Long insTypeId;

    private Long insTsTypeId;

    private Double insRate;

    private String insRemark;

    private Integer insStatus;
    
    private Long createPersonId;
    
    private Date createTime;
    
    private float insLowestPrice;
    /**********************/
    private InsuranceType insType;
    
    private InsuranceTsType insTsType;
    
    private InsuranceCompany insuranceCompany;

    public InsuranceCompany getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public InsuranceTsType getInsTsType() {
		return insTsType;
	}

	public void setInsTsType(InsuranceTsType insTsType) {
		this.insTsType = insTsType;
	}

	public InsuranceType getInsType() {
		return insType;
	}

	public void setInsType(InsuranceType insType) {
		this.insType = insType;
	}

	public float getInsLowestPrice() {
		return insLowestPrice;
	}

	public void setInsLowestPrice(float insLowestPrice) {
		this.insLowestPrice = insLowestPrice;
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

    public Long getInsCompanyId() {
        return insCompanyId;
    }

    public void setInsCompanyId(Long insCompanyId) {
        this.insCompanyId = insCompanyId;
    }

    public Long getInsTypeId() {
        return insTypeId;
    }

    public void setInsTypeId(Long insTypeId) {
        this.insTypeId = insTypeId;
    }

    public Long getInsTsTypeId() {
        return insTsTypeId;
    }

    public void setInsTsTypeId(Long insTsTypeId) {
        this.insTsTypeId = insTsTypeId;
    }

    public Double getInsRate() {
        return insRate;
    }

    public void setInsRate(Double insRate) {
        this.insRate = insRate;
    }

    public String getInsRemark() {
        return insRemark;
    }

    public void setInsRemark(String insRemark) {
        this.insRemark = insRemark == null ? null : insRemark.trim();
    }

    public Integer getInsStatus() {
        return insStatus;
    }

    public void setInsStatus(Integer insStatus) {
        this.insStatus = insStatus;
    }
}
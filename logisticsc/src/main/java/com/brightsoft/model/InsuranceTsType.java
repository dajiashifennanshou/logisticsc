package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class InsuranceTsType implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String insTsTypeName;

    private String insTsTypeTag;

    private Integer insStatus;
    
    private Long createPersonId;
    
    private Date createTime;

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

    public String getInsTsTypeName() {
        return insTsTypeName;
    }

    public void setInsTsTypeName(String insTsTypeName) {
        this.insTsTypeName = insTsTypeName == null ? null : insTsTypeName.trim();
    }

    public String getInsTsTypeTag() {
        return insTsTypeTag;
    }

    public void setInsTsTypeTag(String insTsTypeTag) {
        this.insTsTypeTag = insTsTypeTag == null ? null : insTsTypeTag.trim();
    }

    public Integer getInsStatus() {
        return insStatus;
    }

    public void setInsStatus(Integer insStatus) {
        this.insStatus = insStatus;
    }
}
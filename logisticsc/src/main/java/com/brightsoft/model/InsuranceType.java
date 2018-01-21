package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

public class InsuranceType implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String insTypeName;

    private String insTypeTag;

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

    public String getInsTypeName() {
        return insTypeName;
    }

    public void setInsTypeName(String insTypeName) {
        this.insTypeName = insTypeName == null ? null : insTypeName.trim();
    }

    public String getInsTypeTag() {
        return insTypeTag;
    }

    public void setInsTypeTag(String insTypeTag) {
        this.insTypeTag = insTypeTag == null ? null : insTypeTag.trim();
    }

    public Integer getInsStatus() {
        return insStatus;
    }

    public void setInsStatus(Integer insStatus) {
        this.insStatus = insStatus;
    }
}
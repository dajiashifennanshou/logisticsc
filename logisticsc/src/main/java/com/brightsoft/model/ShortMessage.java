package com.brightsoft.model;

import java.io.Serializable;

public class ShortMessage implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String name;

    private String triggerEvent;

    private String content;

    private String remark;

    private Long outletsId;
    
    // --------------
    
    private String outletsName;

    public String getOutletsName() {
		return outletsName;
	}

	public void setOutletsName(String outletsName) {
		this.outletsName = outletsName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTriggerEvent() {
        return triggerEvent;
    }

    public void setTriggerEvent(String triggerEvent) {
        this.triggerEvent = triggerEvent == null ? null : triggerEvent.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getOutletsId() {
        return outletsId;
    }

    public void setOutletsId(Long outletsId) {
        this.outletsId = outletsId;
    }
}
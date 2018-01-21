package com.brightsoft.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysHelp implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long parentId;

    private String helpName;

    private String helpRemark;

    private Date heleTime;

    private Integer helpState;

    private Integer helpIsPage;
    
    List<SysHelp> helps = new ArrayList<SysHelp>();
    
    

    public List<SysHelp> getHelps() {
		return helps;
	}

	public void setHelps(List<SysHelp> helps) {
		this.helps = helps;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getHelpName() {
        return helpName;
    }

    public void setHelpName(String helpName) {
        this.helpName = helpName == null ? null : helpName.trim();
    }

    public String getHelpRemark() {
        return helpRemark;
    }

    public void setHelpRemark(String helpRemark) {
        this.helpRemark = helpRemark == null ? null : helpRemark.trim();
    }

    public Date getHeleTime() {
        return heleTime;
    }

    public void setHeleTime(Date heleTime) {
        this.heleTime = heleTime;
    }

    public Integer getHelpState() {
        return helpState;
    }

    public void setHelpState(Integer helpState) {
        this.helpState = helpState;
    }

    public Integer getHelpIsPage() {
        return helpIsPage;
    }

    public void setHelpIsPage(Integer helpIsPage) {
        this.helpIsPage = helpIsPage;
    }
}
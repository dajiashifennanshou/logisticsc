package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AdvertisementInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String adName;

    private String adFilePath;

    private Long adPositionId;

    private String adOwner;

    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date endTime;

    private Long createPersonId;

    private Integer state;
    
    public Integer publishType;
    
    private String adUrl;
    /*******************/
    private String adPositionVal;

    public Integer getPublishType() {
		return publishType;
	}

	public void setPublishType(Integer publishType) {
		this.publishType = publishType;
	}

	public String getAdFilePath() {
		return adFilePath;
	}

	public void setAdFilePath(String adFilePath) {
		this.adFilePath = adFilePath;
	}

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

    public Long getAdPositionId() {
        return adPositionId;
    }

    public void setAdPositionId(Long adPositionId) {
        this.adPositionId = adPositionId;
    }

    public String getAdOwner() {
        return adOwner;
    }

    public void setAdOwner(String adOwner) {
        this.adOwner = adOwner == null ? null : adOwner.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getCreatePersonId() {
        return createPersonId;
    }

    public void setCreatePersonId(Long createPersonId) {
        this.createPersonId = createPersonId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	public String getAdPositionVal() {
		return adPositionVal;
	}

	public void setAdPositionVal(String adPositionVal) {
		this.adPositionVal = adPositionVal;
	}
}
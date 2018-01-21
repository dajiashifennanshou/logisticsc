package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import com.brightsoft.utils.DateTools;
/**
 * 投诉处理表
 * @author ThinkPad
 *
 */
public class PlatformOrderComlainHandle implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long complaintId;

    private String handleContent;

    private String handlePeople;

    private Date handleTime;
    
    private String handleTimeStr;

    public String getHandleTimeStr() {
		return handleTimeStr;
	}

	public void setHandleTimeStr(String handleTimeStr) {
		this.handleTimeStr = handleTimeStr;
	}

	private Integer handleSatisfiedDegree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public String getHandleContent() {
        return handleContent;
    }

    public void setHandleContent(String handleContent) {
        this.handleContent = handleContent == null ? null : handleContent.trim();
    }

    public String getHandlePeople() {
        return handlePeople;
    }

    public void setHandlePeople(String handlePeople) {
        this.handlePeople = handlePeople == null ? null : handlePeople.trim();
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
    	this.handleTimeStr = DateTools.dateToStr2(handleTime);
        this.handleTime = handleTime;
    }

    public Integer getHandleSatisfiedDegree() {
        return handleSatisfiedDegree;
    }

    public void setHandleSatisfiedDegree(Integer handleSatisfiedDegree) {
        this.handleSatisfiedDegree = handleSatisfiedDegree;
    }
}
package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import com.brightsoft.utils.DateTools;
/**
 * 评价回复表
 * @author ThinkPad
 *
 */
public class PlatformOrderEvaluationReply implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long evaluateId;

    private String replyContent;

    private Date replyTime;
    
    public String getReplyTimeStr() {
		return replyTimeStr;
	}

	public void setReplyTimeStr(String replyTimeStr) {
		this.replyTimeStr = replyTimeStr;
	}

	private String replyTimeStr;

    private String replyPeople;

    private Integer state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Long evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
    	this.replyTimeStr = DateTools.dateToStr2(replyTime);
        this.replyTime = replyTime;
    }

    public String getReplyPeople() {
        return replyPeople;
    }

    public void setReplyPeople(String replyPeople) {
        this.replyPeople = replyPeople == null ? null : replyPeople.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
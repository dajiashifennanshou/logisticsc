package com.brightsoft.model;

import java.io.Serializable;

public class MessageSubscription implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String messageType;

    private String handleSegment;
    
    private int checked; //是否选中
    
    private Integer noticeType; 
    
    
    public Integer getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public String getHandleSegment() {
        return handleSegment;
    }

    public void setHandleSegment(String handleSegment) {
        this.handleSegment = handleSegment == null ? null : handleSegment.trim();
    }

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}
    
}